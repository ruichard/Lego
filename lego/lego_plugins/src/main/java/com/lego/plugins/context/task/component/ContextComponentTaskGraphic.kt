package com.lego.plugins.context.task.component

import com.ktnail.x.Logger
import com.lego.plugins.basic.LogTags
import com.lego.plugins.context.model.Component
import com.lego.plugins.basic.publish.maven.PublishingTaskProvider
import com.lego.plugins.basic.task.TaskGraphic
import com.lego.plugins.basic.task.graphicDependsOn
import com.lego.plugins.basic.task.graphicFinalizedWith
import com.lego.plugins.basic.utility.Ext
import com.lego.plugins.basic.utility.p
import com.lego.plugins.basic.utility.propertyOr
import org.gradle.api.Project
import org.gradle.api.plugins.BasePlugin
import java.io.File

/**
 * Task graphic of business code of contexts,
 * graph publish tasks.
 *
 * @since 1.4
 */
class ContextComponentTaskGraphic(
    project: Project,
    componentTasks: ContextComponentTasks,
    publishingTaskProvider: PublishingTaskProvider,
    file: File
) : TaskGraphic() {
    private val component = Component(project, componentTasks.context, componentTasks.variant, file)

    private val publishComponentTask = componentTasks.publishTask
    private val publishDevComponentTask = componentTasks.publishDevTask

    init {
        val publishingTask = if (null != publishComponentTask) publishingTaskProvider.getTask(component).apply {
            doLast {
                Logger.p(LogTags.PUBLISH_COMPONENTS,null) { " <${component.groupId}:${component.artifactId}:${component.version}>  SUCCEEDED !!!" }
            }
        } else null
        val publishingDevTask = if (null != publishDevComponentTask) publishingTaskProvider.getTask(component,true).apply {
            doLast {
                Logger.p(LogTags.PUBLISH_COMPONENTS,null) { " <${component.groupId}:${component.artifactId}:${component.version}>  DEV SUCCEEDED !!!" }
            }
        } else null

        whenGraph {
            // clean > assembleProvider > publishLibTask > publishingTask
            if (project.propertyOr(Ext.LEGO_CLEAN_BEFORE_TASK, true))
                componentTasks.variant.preBuildProvider.get().graphicDependsOn(project.tasks.getByName(BasePlugin.CLEAN_TASK_NAME))

            if (null != publishComponentTask && null != publishingTask) {
                publishComponentTask.graphicDependsOn(componentTasks.variant.assembleProvider)
                publishComponentTask.graphicFinalizedWith(publishingTask)
            }

            if (null != publishDevComponentTask && null != publishingDevTask) {
                publishDevComponentTask.graphicDependsOn(componentTasks.variant.assembleProvider)
                publishDevComponentTask.graphicFinalizedWith(publishingDevTask)
            }
        }
    }
}