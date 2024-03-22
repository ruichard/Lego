package com.lego.apt.codebase.value

import com.blueprint.kotlin.lang.element.KbpAnnotationElement
import com.blueprint.kotlin.lang.element.KbpRooterElement
import com.blueprint.kotlin.lang.utility.findInterface
import com.lego.apt.Constants
import com.lego.apt.codebase.AnnotationCodeBase
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.asClassName
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.io.Serializable

/**
 * The code structure of Router Value.
 *
 * @since 1.1
 */
class ValueCodeBase(
    val className: String,
    val interfaces: List<ClassName>,
    val annotations: List<AnnotationCodeBase>,
    val fields: List<ValueFieldCodeBase>
) {
    companion object {
        operator fun invoke(classElement: KbpRooterElement): ValueCodeBase {
            return ValueCodeBase(
                classElement.simpleNames,
                createInterfaceNames(classElement),
                createAnnotationCodeBase(classElement.annotations),
                classElement.properties.filter { entry ->
                    entry.value.type.name.toString() != Constants.Contexts.PARCELABLE_CREATOR_CLASS_NAME && entry.value.type.name.toString() != classElement.qualifiedName + Constants.Contexts.COMPANION_CLASS_NAME
                }.map { entry ->
                    ValueFieldCodeBase(
                        entry.value.name,
                        entry.value.type,
                        createFiledAnnotationCodeBase(entry.value.annotations),
                        entry.value.defaultValue,
                        entry.value.isConstant
                    )
                }
            )
        }

        private fun createInterfaceNames(classElement: KbpRooterElement): List<ClassName> {
            return mutableListOf<ClassName>().apply {
//                classElement.jmElement?.findInterface(Serializable::class.qualifiedName.toString())?.let { element ->
//                    add(element.asClassName())
//                }
                classElement.jmElement?.findInterface("android.os.Parcelable")?.let { element ->
                    add(element.asClassName())
                }
            }
        }

        private fun createAnnotationCodeBase(elements: List<KbpAnnotationElement>): List<AnnotationCodeBase> {
            return elements.filter { element ->
                element.className == "kotlinx.android.parcel.Parcelize"
            }.map { element ->
                AnnotationCodeBase(
                    element.className,
                    element.members.toList().map { pair ->
                        "${pair.first} = ${pair.second}"
                    }
                )
            }
        }

        private fun createFiledAnnotationCodeBase(elements: List<KbpAnnotationElement>): List<AnnotationCodeBase> {
            return elements.filter { element ->
                element.className != Nullable::class.qualifiedName.toString() && element.className != NotNull::class.qualifiedName.toString()
            }.map { element ->
                AnnotationCodeBase(
                    element.className,
                    element.members.toList().map { pair ->
                        "${pair.first} = ${pair.second}"
                    }
                )
            }
        }
    }

}
