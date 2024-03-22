package com.lego.plugins.context.model

import com.android.build.gradle.api.BaseVariant
import com.ktnail.x.camelToPascal
import com.ktnail.x.pascalToSnake
import com.ktnail.x.toPascal
import com.ktnail.x.toSnake
import com.lego.plugins.basic.publish.maven.Publication
import com.lego.plugins.basic.utility.DependencyType
import com.lego.plugins.basic.utility.Ext
import com.lego.plugins.basic.utility.flavorDependencyType
import com.lego.plugins.basic.utility.propertyOr
import com.lego.plugins.extension.context.ContextExtension
import org.gradle.api.Project
import java.io.File

/**
 * Model of business code of contexts.
 *
 * @since 1.4
 */
class Component(
    val project: Project,
    val context: ContextExtension,
    variant: BaseVariant,
    override val publicationFile: File
) : Publication {
    override val publicationName = toPascal(context.publishArtifactName, "component", variant.name.camelToPascal())
    override val devPublicationName = toPascal(context.publishArtifactName, "dev", "component", variant.name.camelToPascal())
    override val artifactId = context.publishComponentArtifactId(variant.name)
    override val groupId = context.publishGroupId
    override val version = project.propertyOr(Ext.R_PUB_VERSION) {
        context.versionToPublish.toString()
    }
    override val devVersion = project.propertyOr(Ext.R_PUB_VERSION) {
        context.versionToPublishDev.toString()
    }
    override val source: Publication? = null
    override val addDependencyTypes = arrayOf(
        DependencyType.API,
        DependencyType.IMPLEMENTATION,
        variant.name.flavorDependencyType(DependencyType.API),
        variant.name.flavorDependencyType(DependencyType.IMPLEMENTATION)
    )
    override val addDependencies: Array<Publication> = emptyArray()

    companion object {
        fun String.nameToComponentArtifactId(variantName: String) = toSnake(false, this, "component", variantName.pascalToSnake(false)).replace("_", "-")
    }
}
