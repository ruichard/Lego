package com.lego.test

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lego.route.Result
import com.lego.router.navigate
import com.lego.router.result
import com.lego.router.uri.Path
import com.lego.test.router.TestAggregate
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class PathTest {
    @Before
    fun init(){
        TestAggregate.register()
    }

    @Test
    fun usePathQueryRouter() {
        navigate {
            uri = "test://agg.test/path-queries/one/two/xxx"
            result<String, String, String> { r1, r2, r3 ->
                Assert.assertEquals(r1, "one")
                Assert.assertEquals(r2, "two")
                Assert.assertEquals(r3, "xxx")
            }
        }
    }

    @Test
    fun usePathQueries() {
        val queries = Path("path-queries/?{name}/#{value}/action={type}.jsp").getParameters("path-queries/?zhangsan/#student123/action=get@String.jsp")
        Assert.assertEquals(queries[0].value.toString(), "zhangsan")
        Assert.assertEquals(queries[1].value.toString(), "student123")
        Assert.assertEquals(queries[2].value.toString(), "get@String")
    }

}