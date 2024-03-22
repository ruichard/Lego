package com.lego.apt.files.source.value

import com.lego.annotations.source.RContextLib
import com.lego.annotations.source.RGeneratedValue
import com.lego.apt.Constants
import com.lego.apt.codebase.value.ValueCodeBase
import com.lego.apt.codebase.value.ValueFieldCodeBase
import com.lego.apt.utility.addInterfaces
import com.lego.apt.utility.addAnnotations
import com.lego.apt.utility.addRGeneratedAnnotation
import com.squareup.kotlinpoet.*
import java.io.File

internal fun generateValueFiles(
    uri: String,
    values: List<ValueCodeBase>,
    version: String,
    provideDirectory: File
) {
    values.forEach { value ->
        with(FileSpec) {
            builder(Constants.Contexts.makeContextPackageName(uri), value.className).addType(
                TypeSpec.classBuilder(value.className).primaryConstructor(
                    FunSpec.constructorBuilder().addConstructorParameter(
                        value.fields,
                        uri
                    ).build()
                ).addAnnotations(
                    value.annotations
                ).addFieldsProperty(
                    value.fields,
                    uri
                ).addConstantsProperty(
                    value.fields,
                    uri
                ).addInterfaces(
                    value.interfaces
                ).addRGeneratedAnnotation(
                    "value",version
                ).addAnnotation(
                    AnnotationSpec.builder(RContextLib::class.java).addMember("uri = %S", uri).build()
                ).addAnnotation(
                    AnnotationSpec.builder(RGeneratedValue::class.java).addMember("uri = %S", uri).build()
                ).addAnnotation(
                    AnnotationSpec.builder(ClassName.bestGuess(Constants.Contexts.KEEP_ANNOTATION_CLASS_NAME)).build()
                ).addKdoc(
                    Constants.KDoc.value(uri, version)
                ).build()
            ).build().writeTo(provideDirectory)
        }
    }
}

fun FunSpec.Builder.addConstructorParameter(
    fields: List<ValueFieldCodeBase>,
    contextUri: String
): FunSpec.Builder {
    fields.filter { codebase -> !codebase.isConstant }.forEach { field ->
        addParameter(field.name, field.toContextTypeName(contextUri))
    }
    return this
}

fun TypeSpec.Builder.addFieldsProperty(
    fields: List<ValueFieldCodeBase>,
    contextUri: String
): TypeSpec.Builder {
    fields.filter { codebase -> !codebase.isConstant }.forEach { field ->
        addProperty(
            PropertySpec.builder(
                field.name, field.toContextTypeName(contextUri)
            ).addAnnotations(field.annotations.map { codebase ->
                AnnotationSpec.builder(
                    Class.forName(
                        codebase.className
                    ).asClassName()
                ).addAnnotationMembers(codebase.members).build()
            }).initializer(field.name).build()
        )
    }
    return this
}
fun TypeSpec.Builder.addConstantsProperty(
    fields: List<ValueFieldCodeBase>,
    contextUri: String
): TypeSpec.Builder {
    fields.filter { codebase -> codebase.isConstant }.let { constants ->
        if (constants.isNotEmpty()) {
            addType(TypeSpec.companionObjectBuilder().apply {
                constants.forEach { constant ->
                    addProperty(
                        PropertySpec.builder(
                            constant.name, constant.toContextTypeName(contextUri)
                        ).addModifiers(
                            KModifier.CONST
                        ).initializer(
                            constant.defaultValueCode ?: "null"
                        ).build()
                    ).build()
                }
            }.build())
        }
    }
    return this
}


fun AnnotationSpec.Builder.addAnnotationMembers(members: List<String>): AnnotationSpec.Builder {
    members.forEach {
        addMember(it)
    }
    return this
}

