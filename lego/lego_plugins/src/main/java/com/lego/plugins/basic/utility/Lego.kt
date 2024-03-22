package com.lego.plugins.basic.utility

import com.android.build.gradle.api.BaseVariant
import com.ktnail.x.Logger
import com.ktnail.x.uri.buildUri
import com.lego.plugins.basic.LogTags
import com.lego.plugins.basic.exception.*
import com.lego.plugins.basic.utility.Lego.CONTEXT_PLUGIN_NAME
import com.lego.plugins.basic.utility.Lego.ROOT_PLUGIN_NAME
import com.lego.plugins.basic.utility.Lego.TEST_PLUGIN_NAME
import com.lego.plugins.extension.LegoConfigExtension
import com.lego.plugins.extension.LegoExtension
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.plugins.AppliedPlugin
import java.io.File
import java.net.URI

object Lego {
    const val MAIN_PLUGIN_NAME = "lego"
    const val ROOT_PLUGIN_NAME = "lego-root"
    const val CONTEXT_PLUGIN_NAME = "lego-context"
    const val TEST_PLUGIN_NAME = "lego-test"
    const val CONFIG_EXTENSION_NAME = "lego-config"
    const val LEGO_TASK_GROUP_NAME = "lego"
    const val LEGO_LIB_TASK_GROUP_NAME = "lego-lib"
    const val LEGO_COMPONENT_TASK_GROUP_NAME = "lego-component"
    const val LEGO_MODULE_TASK_GROUP_NAME = "lego-module"
    const val LEGO_LIB_DEV_TASK_GROUP_NAME = "lego-lib-dev"
    const val LEGO_COMPONENT_DEV_TASK_GROUP_NAME = "lego-component-dev"
    const val LEGO_MODULE_DEV_TASK_GROUP_NAME = "lego-module-dev"
    const val LEGO_LIBS_TASK_GROUP_NAME = "lego-lib-all"
}

val Project.legoExtensionName
    get() = project.propertyOr(Ext.LEGO_EXTENSION_NAME) { "lego" }

val Project.legoExtension: LegoExtension
    get() = (extensions.findByName(legoExtensionName) as? LegoExtension) ?: extensions.create(
        legoExtensionName,
        LegoExtension::class.java,
        this
    )

fun Project.legoTask(name: String, action: (Task) -> Unit): Task =
    task(name, action).apply {
        group = Lego.LEGO_TASK_GROUP_NAME
    }

fun <T : Task> Project.legoTask(
    name: String,
    type: Class<T>,
    action: (T) -> Unit
): T = tasks.create(name, type, action).apply {
    group = Lego.LEGO_TASK_GROUP_NAME
}

fun Project.legoLibTask(name: String): Task =
    task(name) {}.apply {
        group = Lego.LEGO_LIB_TASK_GROUP_NAME
    }

fun Project.legoLibDevTask(name: String): Task =
    task(name) {}.apply {
        group = Lego.LEGO_LIB_DEV_TASK_GROUP_NAME
    }

fun Project.legoComponentTask(name: String): Task =
    task(name) {}.apply {
        group = Lego.LEGO_COMPONENT_TASK_GROUP_NAME
    }

fun Project.legoComponentDevTask(name: String): Task =
    task(name) {}.apply {
        group = Lego.LEGO_COMPONENT_DEV_TASK_GROUP_NAME
    }

fun Project.legoModuleTask(name: String): Task =
    task(name) {}.apply {
        group = Lego.LEGO_MODULE_TASK_GROUP_NAME
    }

fun Project.legoModuleDevTask(name: String, config: (Task) -> Unit = {}): Task =
    task(name, config).apply {
        group = Lego.LEGO_MODULE_DEV_TASK_GROUP_NAME
    }

fun Project.legoLibsTask(name: String): Task =
    task(name) {}.apply {
        group = Lego.LEGO_LIBS_TASK_GROUP_NAME
    }

val Project.legoConfig
    get() =
        when {
            pluginManager.hasPlugin(Lego.MAIN_PLUGIN_NAME) -> this
            rootProject.pluginManager.hasPlugin(Lego.MAIN_PLUGIN_NAME) -> rootProject
            else -> throw LegoConfigPluginNotApplyException()
        }

val Project.legoConfigExtension
    get() =
        legoConfig.run {
            (extensions.findByName(Lego.CONFIG_EXTENSION_NAME) as? LegoConfigExtension)
                ?: extensions.create(
                    Lego.CONFIG_EXTENSION_NAME,
                    LegoConfigExtension::class.java,
                    this
                )
                ?: throw LegoConfigPluginNotApplyException()
        }

val Project.startTaskFromThisProject
    get() = projectDir.absolutePath == gradle.startParameter.projectDir?.absolutePath

val Project.startTaskFromRootProject
    get() = rootProject.startTaskFromThisProject

val Project.startTaskFromTerminal
    get() = null == gradle.startParameter.projectDir?.absolutePath

val Project.isTaskTarget
    get() = startTaskFromThisProject || startTaskFromRootProject || startTaskFromTerminal

fun Project.isTaskTarget(vararg taskNames: String) =
    isTaskTarget && gradle.startParameter.taskNames.any { name -> taskNames.contains(name) }

val Project.isLegoRoot
    get() = pluginManager.hasPlugin(ROOT_PLUGIN_NAME)

val Project.autoGenerateAggregate
    get() = propertyOr(Ext.LEGO_AUTO_GENERATE_AGGREGATE, false)

val Project.generateAggregateInBuildDir
    get() = propertyOr(Ext.LEGO_GENERATE_AGGREGATE_IN_BUILD_DIR, true)

val Project.generateRouterContext
    get() = propertyOr(Ext.LEGO_GENERATE_ROUTER_CONTEXT, false)

val Project.legoMavenRepository: URI
    get() = uri(propertyOr(Ext.LEGO_MAVEN_REPOSITORY) { throw LegoMavenRepositoryNotSetException() })

val Project.legoMavenLocalRepository: URI
    get() = uri(propertyOr(Ext.LEGO_MAVEN_LOCAL_REPOSITORY) { "./lego_maven_local" })

fun Project.addLegoRepository() {
    repositories.maven { maven ->
        maven.url = legoMavenRepository
    }
    repositories.maven { maven ->
        maven.url = legoMavenLocalRepository
    }
}

fun String.toUriIfAuthority(project: Project) = if (contains("://")) {
    this
} else {
    buildUri(
        project.legoConfigExtension.globalConfig.scheme
            ?: throw LegoDefaultSchemeNotSetException(), this
    )
}

fun Project.isAndroidApplication() = pluginManager.hasPlugin(PluginName.ANDROID_APPLICATION)

fun Project.ifAndroidApplication(action: (AppliedPlugin) -> Unit) = pluginManager.withPlugin(PluginName.ANDROID_APPLICATION, action)

fun Project.applyContext() {
    if (!pluginManager.hasPlugin(CONTEXT_PLUGIN_NAME)) pluginManager.apply(CONTEXT_PLUGIN_NAME)
}

fun Project.applyRoot() {
    if (!pluginManager.hasPlugin(ROOT_PLUGIN_NAME)) pluginManager.apply(ROOT_PLUGIN_NAME)
}

fun Project.applyTest() {
    if (!pluginManager.hasPlugin(TEST_PLUGIN_NAME)) pluginManager.apply(TEST_PLUGIN_NAME)
}

fun Project.addLegoKaptDependency() {
    addDependency(Kapt.CONFIGURATION_NAME, propertyOr(Ext.LEGO_KAPT_VERSION) { throw LegoKaptDependencyNotSetException() })
}

fun Project.addLegoRouterDependency() {
    addDependency(DependencyType.IMPLEMENTATION, propertyOr(Ext.LEGO_ROUTER_VERSION) { throw LegoRouterDependencyNotSetException() })
}

fun Project.fuzzyApplyLegoConfigFiles(dir: File) {
    dir.listFiles()?.filter { file ->
        file.name.startsWith("lego") && file.name.endsWith(".gradle")
    }?.forEach { file ->
        Logger.p(LogTags.PLUGIN, this) { " APPLIED FILE file:${file.absolutePath}" }
        apply(mapOf("from" to file))
    }
}

fun Project.fuzzyApplyGivenLegoConfigFileDirs() {
    (properties[Ext.LEGO_CONFIG_FILE_DIRS] as? List<*>)?.forEach { path ->
        fuzzyApplyLegoConfigFiles(File(path.toString()))
    }
}

fun Logger.p(tag: String, project: Project?, action: () -> String) {
    d(tag + project.let { _ -> if (null != project) " [${project.path}] " else "" } + action())
}

fun BaseVariant.match(other: BaseVariant) = name == other.name || buildType.name == other.name

val Project.libTmpDirRoot: File
    get() = File(project.propertyOr(Ext.LEGO_TMP_LIB_DIR) {
        project.rootProject.rootDir.absolutePath + File.separator + "lego_libs"
    })
