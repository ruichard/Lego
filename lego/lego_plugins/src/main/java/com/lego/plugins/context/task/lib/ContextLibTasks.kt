package com.lego.plugins.context.task.lib

import com.ktnail.x.Logger
import com.ktnail.x.toCamel
import com.lego.plugins.MainPlugin
import com.lego.plugins.context.ContextPlugin
import com.lego.plugins.extension.context.ContextExtension
import com.lego.plugins.basic.publish.maven.PublicationType
import com.lego.plugins.basic.utility.isTaskTarget
import com.lego.plugins.basic.utility.legoLibDevTask
import com.lego.plugins.basic.utility.legoLibTask
import com.lego.plugins.context.AllContextController
import org.gradle.api.Project
import org.gradle.api.Task

class ContextLibTasks(
    project: Project,
    val context: ContextExtension,
    buildType: String
) {
    companion object {
        fun create(
            project: Project,
            context: ContextExtension,
            buildType: String
        ): ContextLibTasks? =
            if (context.enableProvideRoute)
                ContextLibTasks(project, context, buildType)
            else
                null
        }

    val assembleTask: Task = project.legoLibTask(toCamel(ContextPlugin.ASSEMBLE_TASK_NAME_PREFIX, buildType))
    val publishTask: Task? = if(context.enablePublish) project.legoLibTask(toCamel(ContextPlugin.PUBLISH_TASK_NAME_PREFIX, buildType)) else null
    val publishDevTask: Task? = if(context.enablePublishDev) project.legoLibDevTask(toCamel(ContextPlugin.PUBLISH_DEV_TASK_NAME_PREFIX, buildType)) else null

    fun isConfiguring(project: Project): PublicationType? {
        return when {
            project.isTaskTarget(
                *arrayOf(
                    assembleTask.name,
                    publishTask?.name,
                    AllContextController.ASSEMBLE_ALL_LIBS_NAME,
                    AllContextController.PUBLISH_ALL_LIBS_NAME
                ).filterNotNull().toTypedArray()
            ) -> PublicationType.FORMAL
            project.isTaskTarget(
                *arrayOf(
                    publishDevTask?.name,
                    AllContextController.PUBLISH_ALL_DEV_LIBS_NAME
                ).filterNotNull().toTypedArray()
            ) -> PublicationType.DEV
            else -> null
        }
    }
}