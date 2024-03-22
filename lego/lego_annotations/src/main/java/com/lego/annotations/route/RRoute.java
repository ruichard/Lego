package com.lego.annotations.route;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Repeatable(RRouteRepeatable.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.TYPE})
public @interface RRoute {
    String uri() default "";
    @Deprecated
    String context() default "";
    String path();
    String version() default "";
    boolean navigationOnly() default false;
    boolean forResult() default false;
    Class resultType() default Object.class;
}