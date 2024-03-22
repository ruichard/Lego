package com.mars.component.home.test.java;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import lego.generate.context.demo_com_mars_lego_test_detail.DetailContext;
import lego.generate.context.demo_com_mars_lego_test_detail.TestDataBean;

public class TestJavaInvokeFunctionTask {
    public void invoke(Context context, Function1<String, Unit> onFinish) {

        String tag = " B JAVA FUNCTION TASK ";

        DetailContext.touch(() -> {
            onFinish.invoke(" XX DBG B touch AContext.URI：touch!!!");
            return null;
        }).miss(() -> {
            onFinish.invoke(" XX DBG B touch AContext.URI：miss!!!");
            return null;
        });

        DetailContext.doSth();

        @Nullable
        Integer doSthHOFResult = DetailContext.doSthHof(3, "123", new TestDataBean(1, "456"));

        onFinish.invoke(tag+" NA DBG doSthHOF doSthHOFResult:"+doSthHOFResult);

        DetailContext.propertyProperty(value -> {
            onFinish.invoke(tag+" NA DBG propertyProperty value:"+value);
            return null;
        });

        DetailContext.doSthAsync2HOF((v1, v2) -> {
            onFinish.invoke(tag + " NA DBG doSthAsyncHOF 1 value:" + v1 + "," + v2);
            return null;
        },(v1, v2) -> {
            onFinish.invoke(tag + " NA DBG doSthAsyncHOF 2 value:" + v1 + "," + v2);
            return null;
        });

        DetailContext.doSthAsyncOpen("textUri", (v1, v2) -> {
            onFinish.invoke(tag + " NA DBG doSthAsyncOpen value:" + v1 + "," + v2);
            return null;
        });

        DetailContext.doSthAsyncInterface ((v1, v2) -> {
            onFinish.invoke(tag + " NA DBG doSthAsyncInterface value:" + v1 + "," + v2);
            return null;
        });

        DetailContext.viewGet(context, view -> {
            onFinish.invoke(tag + " NA DBG getView value:" + view);
            return null;
        });

        DetailContext.doSthCompanion(8, "asd", 22L, value -> {
            onFinish.invoke(tag + " NA DBG doSthCompanion value:" + value);
            return null;
        });

        DetailContext.propertyCompanion(value -> {
            onFinish.invoke(tag + " NA DBG propertyCompanion value:" + value);
            return null;
        });

        DetailContext.doSthHOFCompanion(6, value -> {
            onFinish.invoke(tag + " NA DBG doSthHOFCompanion value:" + value);
            return null;
        });

        DetailContext.doSthBean(new TestDataBean(8, null), value -> {
            onFinish.invoke(tag + " NA DBG doSthBean:" + value.getD1());
            return null;
        });

        Fragment result = DetailContext.Fragment.page1();

        onFinish.invoke(tag + " NA DBG makeApiFragment value:" + result);
    }
}