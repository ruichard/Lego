package com.lego.plugins.root

import com.android.build.gradle.api.BaseVariant
import com.ktnail.x.Logger
import com.ktnail.x.toCamel
import com.lego.plugins.LegoPlugin
import com.lego.plugins.basic.LogTags
import com.lego.plugins.basic.exception.LegoMavenVariantNotSetException
import com.lego.plugins.basic.exception.LegoMavenVersionNotSetException
import com.lego.plugins.basic.exception.LegoPluginNotApplyException
import com.lego.plugins.basic.exception.LegoProjectNotFoundException
import com.lego.plugins.basic.utility.*
import com.lego.plugins.extension.root.model.MavenMode
import com.lego.plugins.extension.root.model.NoSourceMode
import com.lego.plugins.extension.root.model.ProjectMode
import com.lego.plugins.root.checker.CheckRouterRule
import com.lego.plugins.root.checker.transform.CheckRouterTransform
import com.lego.plugins.root.files.LegoExtSourceFiles
import com.lego.plugins.root.pick.ContextPicker
import org.gradle.api.Project
import org.gradle.api.Task
import java.io.File

/**
 * The the plugin of lego root project (application).
 * Provide the ability to pick  contexts.
 *
 * @ Since:1.5
 */
class RootPlugin : LegoPlugin() {
    private var _picker: ContextPicker? = null
    private val picker: ContextPicker
        get() = _picker ?: throw LegoPluginNotApplyException()

    override fun apply(project: Project) {
        super.apply(project)

        project.fuzzyApplyLegoConfigFiles(project.projectDir)

        _picker = ContextPicker(project)

        makeGeneratedDirs { generateDir, variant ->
            variant.registerJavaGeneratingTask(
                makeGenerateFileTask(
                    variant,
                    generateDir
                ), generateDir
            )
        }

        project.afterEvaluate {
            doPickContexts()
            project.addLegoRouterDependency()
        }

        if (project.propertyOr(Ext.LEGO_ENABLE_CHECK_ROUTER_VERSION, false) && CheckRouterRule.RULES.isNotEmpty()) {
            Logger.p(LogTags.CHECK_ROUTER_VERSION, project) { " enable ! " }
            project.androidExtension?.registerTransform(CheckRouterTransform(project))
        }
    }


    private fun makeGenerateFileTask(variant: BaseVariant, generateDir: File): Task {
        return project.legoTask(toCamel("generate", variant.name, "LegoRootSources")) { task ->
            task.doLast {
                doGenerate(generateDir, variant)
            }
        }
    }

    private fun doGenerate(generateDir: File, variant: BaseVariant) {
        LegoExtSourceFiles(
            picker.pickedContexts(variant),
            generateDir
        ).generate()
    }

    private fun doPickContexts(){
        picker.pick { picked, context, tagSource ->
            when (picked.mode) {
                is NoSourceMode -> { }
                is ProjectMode -> {
                    val path = context.firstPriorityProjectPath(tagSource)
                    if (path != project.path){
                        val projectToPick = try { project.project(path) } catch (e: Exception) { throw LegoProjectNotFoundException(context.uri, path) }
                        if (null != picked.forFlavor)
                            addImplementationDependency(picked.forFlavor, projectToPick)
                        else
                            addImplementationDependency(projectToPick)
                    }
                }
                is MavenMode -> {
                    val version = context.firstPriorityMavenVersion(picked, tagSource) ?: throw LegoMavenVersionNotSetException(context.uri)
                    val variant = context.firstPriorityMavenVariant(picked, tagSource) ?: throw LegoMavenVariantNotSetException(context.uri)
                    if (null != picked.forFlavor)
                        addImplementationDependency(picked.forFlavor, context.publishGroupId, context.publishComponentArtifactId(variant), version)
                    else
                        addImplementationDependency(context.publishGroupId, context.publishComponentArtifactId(variant), version)
                }
            }
        }
    }


}

