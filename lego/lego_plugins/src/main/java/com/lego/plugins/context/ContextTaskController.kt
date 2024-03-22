package com.lego.plugins.context

import com.ktnail.x.Logger
import com.lego.plugins.basic.LogTags
import com.lego.plugins.basic.publish.maven.PublicationType
import com.lego.plugins.basic.publish.maven.PublishingTaskProvider
import com.lego.plugins.basic.utility.*
import com.lego.plugins.context.task.component.ContextComponentTaskGraphic
import com.lego.plugins.context.task.component.ContextComponentTasks
import com.lego.plugins.context.task.lib.ContextLibTaskGraphic
import com.lego.plugins.context.task.lib.ContextLibTasks
import com.lego.plugins.context.transform.MakeLibsTransform
import org.gradle.api.Action
import org.gradle.api.Project
import java.io.File

class ContextTaskController(
    private val project: Project,
    private val libTmpDirRoot: File = project.libTmpDirRoot
) {
    private val publishingTaskProvider = PublishingTaskProvider(project)

    private val publishContextLibTasks = mutableListOf<ContextLibTasks>()
    private val publishContextComponentTasks = mutableListOf<ContextComponentTasks>()

    private var operation: PublishOperation = PublishOperation.NOT_CONTEXT_TASK
    private var publicationType: PublicationType? = null

    init {
        initConfig()
    }

    fun addTasks(tasks: ContextLibTasks) {
        tasks.isConfiguring(project)?.let { type ->
            operation = PublishOperation.PUBLISH_CONTEXT_LIB
            project.applyMaven()
            publicationType = type
            publishContextLibTasks.add(tasks)
            tasks.config()
            tasks.configKapt()
        }
        project.putKaptContext(tasks.context)
    }

    fun addTasks(tasks: ContextComponentTasks) {
        tasks.configuringPublicationType(project)?.let { type ->
            operation = PublishOperation.PUBLISH_COMPONENT
            project.applyMaven()
            publicationType = type
            publishContextComponentTasks.add(tasks)
        }
    }

    fun compute(action: (PublishOperation, PublicationType?) -> Unit) {
        project.afterEvaluate {
            if (operation == PublishOperation.PUBLISH_CONTEXT_LIB) {
                publishContextLibTasks.forEach { tasks ->
                    tasks.graphic()
                }
            } else if (operation == PublishOperation.PUBLISH_COMPONENT) {
                publishContextComponentTasks.forEach { tasks ->
                    tasks.graphic()
                }
            }
            action(operation, publicationType)
        }
    }

    private fun initConfig() {
        if (project.autoGenerateAggregate) {
            enableLegoKapt()
            project.putKaptBooleanArgument(Arguments.Declare.AGGREGATE_ENABLE, true)
            if (!project.generateAggregateInBuildDir) {
                project.legoExtension.listenAggregateSetChanged { path ->
                    project.addToJavaSourceSet("main", path)
                    project.putKaptArgument(Arguments.Declare.AGGREGATE_GENERATED, path)
                }
            }
        } else {
            project.legoExtension.listenAggregateSetChanged { path ->
                project.addToJavaSourceSet("main", path)
            }
        }
    }

    private fun ContextLibTasks.config() {
        project.androidExtension?.buildTypes(Action { container ->
            container.maybeCreate(context.buildTypeName()).apply {
                isDebuggable = true
                setMatchingFallbacks("debug")
            }
            Logger.p(
                LogTags.COMPUTE_CONTEXT_LIBS,
                project
            ) { " ADD BUILD_TYPES (${context.buildTypeName()})" }
        })
        if (!context.useResetCompiler) {
            project.androidExtension?.registerTransform(MakeLibsTransform(project, context))
        }
    }

    private fun ContextLibTasks.configKapt() {
        enableLegoKapt()
        project.putKaptContextEnable(context)
        if (!project.autoGenerateAggregate) {
            project.legoExtension.listenAggregateSetChanged { path ->
                project.putKaptArgument(Arguments.Declare.AGGREGATE_GENERATED, path)
            }
            project.putKaptBooleanArgument(Arguments.Declare.AGGREGATE_ENABLE, true)
        } else {
            project.putKaptBooleanArgument(Arguments.Declare.AGGREGATE_ENABLE, false)
        }
        project.putKaptBooleanArgument(Arguments.Declare.CONTEXT_ROUTER_ENABLE, project.generateRouterContext)
    }

    private fun ContextLibTasks.graphic() {
        project.firstVariant(context.buildTypeName()) { variant ->
            ContextLibTaskGraphic(project, this, publishingTaskProvider, context).graph(variant, libTmpDirRoot)
        }
    }

    private fun ContextComponentTasks.graphic() {
        variant.outputAar?.let { output ->
            ContextComponentTaskGraphic(project, this, publishingTaskProvider, output).graph()
        }
    }

    private fun enableLegoKapt() {
        project.applyKapt()
        project.addLegoKaptDependency()
        project.legoConfigExtension.listenDefaultSchemeChanged { scheme ->
            scheme?.let {
                Logger.p(LogTags.COMPUTE_CONTEXT_LIBS, project) { " DefaultScheme REGISTERED  " + "(${scheme})" }
                project.putKaptArgument(Arguments.Declare.DEFAULT_SCHEME, scheme)
            }
        }
    }

    enum class PublishOperation {
        PUBLISH_CONTEXT_LIB,
        PUBLISH_COMPONENT,
        NOT_CONTEXT_TASK
    }
}

