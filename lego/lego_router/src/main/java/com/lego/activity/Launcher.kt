package com.lego.activity

import android.app.Activity
import android.content.Intent
import com.lego.route.*
import com.lego.router.annotations.RInvariant

/**
 * Launcher a Android Activity.
 *
 * @since: 1.0
 */
@RInvariant
class Launcher {
    fun launch(
        clazz: Class<out Activity>,
        queries: Queries,
        pathQueries: List<Query>? = null,
        results: ResultGroups? = null
    ) {
        (queries as? LaunchQueries)?.let { launchQueries ->
            launchQueries.context?.let { context ->
                Intent(context, clazz).let { intent ->
                    launchQueries.flags?.let { flag ->
                        intent.addFlags(flag)
                    }
                    intent.putQueriesExtras(launchQueries)
                    pathQueries?.let { pathQueries ->
                        intent.putPathQueriesExtras(pathQueries)
                    }
                    launchQueries.requestCode.let { requestCode ->
                        when {
                            null != launchQueries.fragment -> if (null != requestCode) {
                                launchQueries.fragment?.startActivityForResult(intent, requestCode)
                            } else {
                                launchQueries.fragment?.startActivity(intent)
                            }
                            null != launchQueries.activity -> if (null != requestCode) {
                                launchQueries.activity?.startActivityForResult(intent, requestCode)
                            } else {
                                launchQueries.activity?.startActivity(intent)
                            }
                            else -> {
                                context.startActivity(intent)
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun Intent.putQueriesExtras(queries: Queries) {
    putExtras(queries.toBundle())
}

private fun Intent.putPathQueriesExtras(pathQueries: List<Query>) {
    pathQueries.forEachIndexed { _, query ->
        putExtra(query.name, query.value?.toString())
    }
}
