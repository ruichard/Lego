package com.mars.component.home.test.java;

import android.content.Context;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import lego.generate.context.demo_com_mars_lego_test_detail_java.DetailJavaContext;
import lego.generate.context.demo_com_mars_lego_test_detail_java.TestJavaBean;

public class TestJavaInvokeJavaFunctionTask {
    public void invoke(Context context, Function1<String, Unit> onFinish) {

        String tag = " B JAVA FUNCTION TASK ";

        DetailJavaContext.touch(() -> {
            onFinish.invoke(" XX DBG B touch DJavaContext.URI：touch!!!");
            return null;
        }).miss(() -> {
            onFinish.invoke(" XX DBG B touch DJavaContext.URI：miss!!!");
            return null;
        });

        DetailJavaContext.doSth();

        DetailJavaContext.propertyProperty(value -> {
            onFinish.invoke(tag+" NA DBG JAVA propertyProperty value:"+value);
            return null;
        });

        DetailJavaContext.doSthAsyncOpen((v1, v2) -> {
            onFinish.invoke(tag + " NA DBG JAVA doSthAsyncOpen value:" + v1 + "," + v2);
            return null;
        });

        DetailJavaContext.doSthAsyncInterface ((v1, v2) -> {
            onFinish.invoke(tag + " NA DBG JAVA doSthAsyncInterface value:" + v1 + "," + v2);
            return null;
        });

        DetailJavaContext.doSthStatic(8, "asd", 22L, value -> {
            onFinish.invoke(tag + " NA DBG JAVA doSthCompanion value:" + value);
            return null;
        });

        DetailJavaContext.propertyStatic(value -> {
            onFinish.invoke(tag + " NA DBG JAVA propertyCompanion value:" + value);
            return null;
        });

        DetailJavaContext.doSthBeanProvideInstanceByFunc("msg from HOME", new TestJavaBean(8, null), value -> {
            onFinish.invoke(tag + " NA DBG JAVA doSthBean:" + value.getD1());
            return null;
        });
    }
}