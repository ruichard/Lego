package com.lego.plugins.basic.utility

import com.android.builder.model.BuildType
import com.ktnail.x.pascalToCamel
import com.ktnail.x.uriToPascal
import com.lego.plugins.basic.exception.LegoProjectPathNotSetException
import com.lego.plugins.context.ContextPlugin
import com.lego.plugins.extension.context.ContextExtension
import com.lego.plugins.extension.context.source.SourceExtension
import com.lego.plugins.extension.root.model.MavenMode
import com.lego.plugins.extension.root.model.Picked

fun ContextExtension.firstPriorityMavenVersion(picked: Picked?, tagSource: SourceExtension?) =
    (picked?.mode as? MavenMode)?.maven?.version ?: tagSource?.maven?.version ?: mavenVersion

fun ContextExtension.firstPriorityMavenVariant(picked: Picked?, tagSource: SourceExtension?) =
    (picked?.mode as? MavenMode)?.maven?.variant ?: tagSource?.maven?.variant ?: mavenVariant

fun ContextExtension.firstPriorityProjectPath(tagSource: SourceExtension?) =
    tagSource?.project?.path ?: projectPath ?: throw LegoProjectPathNotSetException(
        uri
    )

fun ContextExtension.buildTypeName() =
    publishArtifactName.uriToPascal().pascalToCamel() + ContextPlugin.CONTEXT_LIB_BUILD_TYPE_NAME

fun BuildType.isContextLibBuildType() = name.endsWith(ContextPlugin.CONTEXT_LIB_BUILD_TYPE_NAME)

val ContextExtension.useResetCompiler: Boolean
    get() = lego.project.propertyOr(Ext.LEGO_USE_RESET_COMPILER, false) && !publishOriginalValue