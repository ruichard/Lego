package com.lego.annotations.route.page;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Repeatable(RPageRepeatable.class)
@Target({ ElementType.TYPE})
public @interface RPage {
    String uri() default "";
    String path();
    String version() default "";
    boolean navigationOnly() default false;
}