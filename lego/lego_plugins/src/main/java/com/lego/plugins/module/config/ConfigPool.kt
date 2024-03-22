package com.lego.plugins.module.config

import com.ktnail.x.Logger
import com.ktnail.x.writeLines
import com.lego.plugins.basic.LogTags
import com.lego.plugins.basic.exception.module.LegoModuleProjectRecordNotFoundException
import com.lego.plugins.basic.utility.p
import com.lego.plugins.module.config.extension.ModulesExtension
import com.lego.plugins.module.config.record.ConfigRecords
import com.lego.plugins.module.config.record.ProjectConfigRecord
import com.lego.plugins.module.config.record.VariantConfigRecord
import com.lego.plugins.module.config.record.getByModule
import com.lego.plugins.module.relation.Module
import com.lego.plugins.module.relation.localNoMavenMode
import org.gradle.api.Project
import java.io.File

class ConfigPool(val project: Project) {

    private val configDir
        get() = File(project.rootDir.absolutePath + File.separator +
                (if (project.localNoMavenMode) "lego_module_list_aar" else "lego_module_list_maven")
        ).apply {
            mkdirs()
        }

    private val configFile
        get() = File(configDir.absolutePath + File.separator + "modules.gradle")

    private var recordsNow: ConfigRecords? = null
    private val recordsToWrite: ConfigRecords = mutableMapOf()

    fun addRecord(module: Module) {
        val project = module.project.path
        val variant = module.variant.name
        recordsToWrite.getOrPut(project) {
            ProjectConfigRecord(recordsNow?.get(project)?.byteCodeModel ?: true)
        }.variants[module.variant.name] = recordsNow?.get(project)?.variants?.get(variant) ?: VariantConfigRecord()
    }

    fun writeModuleConfigs() {
        configDir.deleteRecursively()
        if (recordsToWrite.isNotEmpty()) {
            val lines = mutableListOf<String>()
            lines.add("$LEGO_MODULES_EXTENSION_NAME {")
            recordsToWrite.forEach { (path, project) ->
                lines.add("  path(\"${path}\") {")
                lines.add("    byteCodeMode ${project.byteCodeModel} ")
                project.variants.forEach { (name,variant) ->
                    lines.add("    variant(\"${name}\") {")
                    variant.token?.let {
                        lines.add("      token \"${variant.token}\"")
                    }
                    lines.add("    }")
                }
                lines.add("  }")
            }
            lines.add("}")
            configFile.writeLines(lines)
        }
    }

    fun updateToken(module: Module, token: String) {
        recordsToWrite.getByModule(module)?.token = token
        writeModuleConfigs()
    }

    fun loadFile(rootProject: Project) {
        if(configFile.exists()){
            rootProject.legoModuleExtension.apply {
                rootProject.apply(mapOf("from" to configFile))
                recordsNow = extensionRecords
            }
        }
    }

    private val Project.legoModuleExtension: ModulesExtension
        get() = (extensions.findByName(LEGO_MODULES_EXTENSION_NAME) as? ModulesExtension) ?: extensions.create(
            LEGO_MODULES_EXTENSION_NAME,
            ModulesExtension::class.java,
            this
        )

    companion object{
        private const val LEGO_MODULES_EXTENSION_NAME = "modules_snapshot"
    }

    private fun getRecord(project: Project) = recordsToWrite[project.path]?: recordsNow?.get(project.path)

    fun byteCodeModeEnable(project: Project): Boolean {
        return (getRecord(project)?.byteCodeModel == true).apply {
            Logger.p(LogTags.MODULE_LINK, project) { " CHECK DEPENDENCIES byteCodeModeEnable:($this)" }
        }
    }

    fun byteCodeModeVersion(module: Module): String? {
        val projectRecord = getRecord(module.project) ?: throw LegoModuleProjectRecordNotFoundException(module.project.path)
        return (projectRecord.variants[module.variant.name] ?: throw LegoModuleProjectRecordNotFoundException(module.project.path)).getVersion()
    }

}