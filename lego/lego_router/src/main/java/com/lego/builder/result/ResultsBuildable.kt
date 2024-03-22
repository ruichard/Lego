package com.lego.builder.result

interface ResultsBuildable<T> {
    fun result(
        onReceive: (Any?) -> Unit
    ): T

    fun result(
        onReceive: (Any?, Any?) -> Unit
    ): T

    fun result(
        onReceive: (Any?, Any?, Any?) -> Unit
    ): T

    fun result(
        onReceive: (Any?, Any?, Any?, Any?) -> Unit
    ): T

    fun result(
        onReceive: (Any?, Any?, Any?, Any?, Any?) -> Unit
    ): T

    fun result(
        onReceive: (Any?, Any?, Any?, Any?, Any?, Any?) -> Unit
    ): T

    fun result(
        onReceive: (Any?, Any?, Any?, Any?, Any?, Any?, Any?) -> Unit
    ): T
}