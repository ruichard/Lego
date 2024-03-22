package com.lego.annotations.route.function;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Repeatable(RFunctionRepeatable.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR})
public @interface RFunction {
    String uri() default "";
    String path();
    String version() default "";
    boolean navigationOnly() default false;
    boolean forResult() default true;
    Class resultType() default Object.class;
}