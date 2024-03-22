package com.lego.plugins.root

import com.lego.plugins.LegoPlugin
import com.lego.plugins.basic.exception.LegoPluginNotApplyException
import com.lego.plugins.basic.exception.LegoTestMavenVersionNotSetException
import com.lego.plugins.basic.exception.LegoTestNotLegoRootException
import com.lego.plugins.basic.utility.addLegoRouterDependency
import com.lego.plugins.basic.utility.firstPriorityMavenVersion
import com.lego.plugins.basic.utility.isLegoRoot
import com.lego.plugins.context.model.Lib
import com.lego.plugins.root.pick.ContextPicker
import org.gradle.api.Project

/**
 *  The the test plugin of lego.
 *  Provide dependencies on all context which RootPlugin picked,
 *  in order to test all contexts.
 *
 *  @see com.lego.plugins.root.RootPlugin
 *
 *  @since 1.6
 */
class TestPlugin : LegoPlugin() {
    private var _picker: ContextPicker? = null
    private val picker: ContextPicker
        get() = _picker ?: throw LegoPluginNotApplyException()

    override fun apply(project: Project) {
        super.apply(project)
        if(!project.isLegoRoot) throw LegoTestNotLegoRootException(
            project.name
        )
        _picker = ContextPicker(project)

        project.afterEvaluate {
            picker.pick { picked, context, tagSource ->
                if(context.enableProvideRoute){
                    val version =
                        context.firstPriorityMavenVersion(picked, tagSource) ?: throw LegoTestMavenVersionNotSetException(context.uri)
                    if (null != picked.forFlavor){
                        addAndroidTestImplementationDependency(picked.forFlavor, context.publishGroupId, context.publishLibArtifactId(Lib.Type.CONTEXT), version)
                        if(context.publishOriginalValue){
                            addAndroidTestCompileOnlyDependency(picked.forFlavor, context.publishGroupId, context.publishLibArtifactId(Lib.Type.ORIGINAL_VALUE), version)
                        }
                    }else{
                        addAndroidTestImplementationDependency(context.publishGroupId, context.publishLibArtifactId(Lib.Type.CONTEXT), version)
                        if(context.publishOriginalValue){
                            addAndroidTestCompileOnlyDependency(context.publishGroupId, context.publishLibArtifactId(Lib.Type.ORIGINAL_VALUE), version)
                        }
                    }
                }
            }
            project.addLegoRouterDependency()
        }
    }

}

