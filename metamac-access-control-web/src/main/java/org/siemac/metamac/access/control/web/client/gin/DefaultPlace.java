package org.siemac.metamac.access.control.web.client.gin;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.siemac.metamac.access.control.web.client.AccessControlPlaceManager;

import com.google.inject.BindingAnnotation;

/**
 * This annotation is used in {@link AccessControlPlaceManager} and is bind
 * in {@link ClientModule}. It's purpose is to bind the default place to a
 * default presenter.
 */

// see ClientModule
// bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.signInPage);

@BindingAnnotation
@Target({FIELD, PARAMETER, METHOD})
@Retention(RUNTIME)
public @interface DefaultPlace {
}