package com.dins.hw.addressbook.addressbookrestservice.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * To avoid ambiguity the annotation is developed for different implementation of UserRepository
 */
@Qualifier
@Target({ElementType.FIELD , ElementType.TYPE , ElementType.CONSTRUCTOR , ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserRepositoryAnnotation {
}
