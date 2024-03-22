package com.lego.plugins.context

import com.ktnail.x.camelToPascal
import com.lego.plugins.basic.utility.*
import com.lego.plugins.context.task.lib.AllContextLibsTaskGraphic
import com.lego.plugins.extension.context.provider.ContextsProvider
import org.gradle.api.Project

class AllContextController(
    private val project: Project
) {
    companion object {
        val ASSEMBLE_ALL_LIBS_NAME = "${ContextPlugin.ASSEMBLE_TASK_NAME_PREFIX}All${ContextPlugin.CONTEXT_LIB_BUILD_TYPE_NAME.camelToPascal()}"
        val PUBLISH_ALL_LIBS_NAME = "${ContextPlugin.PUBLISH_TASK_NAME_PREFIX}All${ContextPlugin.CONTEXT_LIB_BUILD_TYPE_NAME.camelToPascal()}"
        val PUBLISH_ALL_DEV_LIBS_NAME = "${ContextPlugin.PUBLISH_DEV_TASK_NAME_PREFIX}All${ContextPlugin.CONTEXT_LIB_BUILD_TYPE_NAME.camelToPascal()}"
    }

    fun init() {
        project.fuzzyApplyLegoConfigFiles(project.projectDir)
        project.fuzzyApplyGivenLegoConfigFileDirs()

        graphicAllContextTasks()
        processContextProjects()
        processRootProjects()
    }

    private fun graphicAllContextTasks() {
        AllContextLibsTaskGraphic(project).let { taskGraphic ->
            project.legoConfigExtension.apply {
                listenAssembleLibTaskRegistered { task ->
                    taskGraphic.addAssembleLibTasks(task)
                }
                listenPublishLibTaskRegistered { task ->
                    taskGraphic.addPublishLibTasks(task)
                }
                listenPublishDevLibTaskRegistered { task ->
                    taskGraphic.addPublishDevLibTasks(task)
                }
            }
        }
    }

    private fun processContextProjects() {
        ContextsProvider(project).observeAnyRegister { context ->
            context.projectPathToPublish?.let { path ->
                project.findProject(path)?.let { subProject ->
                    subProject.pluginManager.withPlugin(PluginName.KOTLIN_ANDROID) {
                        subProject.applyContext()
                    }
                }
            }
        }
    }

    private fun processRootProjects() {
        project.allprojects { subProject ->
            val autoApplyRoot = project.propertyOr(Ext.LEGO_AUTO_APPLY_ROOT_PLUGIN, true)
            val autoApplyTest = project.propertyOr(Ext.LEGO_AUTO_APPLY_TEST_PLUGIN, false)
            if(autoApplyRoot||autoApplyTest){
                subProject.ifAndroidApplication {
                    if (autoApplyRoot) {
                        subProject.applyRoot()
                    }
                    if (autoApplyTest) {
                        subProject.applyTest()
                    }
                }
            }
        }
    }

}