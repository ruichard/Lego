package com.lego.router

import com.lego.builder.router.RouterBuildable
import com.lego.route.mapping.caseToTypeOfT
import com.lego.router.annotations.RInvariant

/**
 * The result callback of RouterBuildable, with 1 parameter.
 *
 * @see com.lego.builder.result.ResultsBuildable
 *
 * @since 1.6
 */
@RInvariant
inline fun <reified R1> RouterBuildable.result(
    noinline onReceive: (R1) -> Unit
) =
    receiveResults { results ->
        onReceive(
            results.value(0).caseToTypeOfT()
        )
    }

/**
 * The result callback of RouterBuildable, with 2 parameter.
 *
 * @see com.lego.builder.result.ResultsBuildable
 *
 * @since 1.6
 */
@RInvariant
inline fun <reified R1, reified R2> RouterBuildable.result(
    noinline onReceive: (R1, R2) -> Unit
) =
    receiveResults { results ->
        onReceive(
            results.value(0).caseToTypeOfT(),
            results.value(1).caseToTypeOfT()
        )
    }

/**
 * The result callback of RouterBuildable, with 3 parameter.
 *
 * @see com.lego.builder.result.ResultsBuildable
 *
 * @since 1.6
 */
@RInvariant
inline fun <reified R1, reified R2, reified R3> RouterBuildable.result(
    noinline onReceive: (R1, R2, R3) -> Unit
) =
    receiveResults { results ->
        onReceive(
            results.value(0).caseToTypeOfT(),
            results.value(1).caseToTypeOfT(),
            results.value(2).caseToTypeOfT()
        )
    }

/**
 * The result callback of RouterBuildable, with 4 parameter.
 *
 * @see com.lego.builder.result.ResultsBuildable
 *
 * @since 1.6
 */
@RInvariant
inline fun <reified R1, reified R2, reified R3, reified R4> RouterBuildable.result(
    noinline onReceive: (R1, R2, R3, R4) -> Unit
) =
    receiveResults { results ->
        onReceive(
            results.value(0).caseToTypeOfT(),
            results.value(1).caseToTypeOfT(),
            results.value(2).caseToTypeOfT(),
            results.value(3).caseToTypeOfT()
        )
    }

/**
 * The result callback of RouterBuildable, with 5 parameter.
 *
 * @see com.lego.builder.result.ResultsBuildable
 *
 * @since 1.6
 */
@RInvariant
inline fun <reified R1, reified R2, reified R3, reified R4, reified R5> RouterBuildable.result(
    noinline onReceive: (R1, R2, R3, R4, R5) -> Unit
) =
    receiveResults { results ->
        onReceive(
            results.value(0).caseToTypeOfT(),
            results.value(1).caseToTypeOfT(),
            results.value(2).caseToTypeOfT(),
            results.value(3).caseToTypeOfT(),
            results.value(4).caseToTypeOfT()
        )
    }

/**
 * The result callback of RouterBuildable, with 6 parameter.
 *
 * @see com.lego.builder.result.ResultsBuildable
 *
 * @since 1.6
 */
@RInvariant
inline fun <reified R1, reified R2, reified R3, reified R4, reified R5, reified R6> RouterBuildable.result(
    noinline onReceive: (R1, R2, R3, R4, R5, R6) -> Unit
) =
    receiveResults { results ->
        onReceive(
            results.value(0).caseToTypeOfT(),
            results.value(1).caseToTypeOfT(),
            results.value(2).caseToTypeOfT(),
            results.value(3).caseToTypeOfT(),
            results.value(4).caseToTypeOfT(),
            results.value(5).caseToTypeOfT()
        )
    }