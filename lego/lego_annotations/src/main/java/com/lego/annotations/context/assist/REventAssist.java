package com.lego.annotations.context.assist;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Deprecated
@Retention(RetentionPolicy.SOURCE)
@Repeatable(REventAssistRepeatable.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.TYPE})
public @interface REventAssist {
    String uri() default "";
    @Deprecated
    String context() default "";
    String assistForTag() default "";
}