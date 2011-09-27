package com.teracode.prototipogwt.backend.extras.annotations.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation used to restrict access to a class or method based on user roles and permissions.
 * 
 * @author Maxi
 */
@Target({TYPE,METHOD})
@Documented
@Retention(RUNTIME)
@Inherited
public @interface Restrict 
{
}
