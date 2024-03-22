package com.lego.plugins.basic.exception

/**
 * Thrown when router and context versions not match exception.
 *
 * @since 1.9
 */
internal class LegoRouterAndContextVersionsNotMatchException(
    private val generatedBy: String,
    private val uri: String?,
    private val routerVersion: String,
    private val msg: String
) : LegoException() {
    override fun toString() = "LegoPluginNotApplyException router and context versions not " +
            "match! context[$uri] generatedBy[${generatedBy}] and routerVersion[${routerVersion}]" +
            " , $msg "
}