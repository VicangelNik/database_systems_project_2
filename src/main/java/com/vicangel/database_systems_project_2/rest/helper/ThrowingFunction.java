package com.vicangel.database_systems_project_2.rest.helper;

import java.util.function.Function;

@FunctionalInterface
public interface ThrowingFunction<T, R, E extends Exception> {

  R apply(T t) throws E;

  static <T, R> Function<T, R> throwingFunctionWrapper(
    ThrowingFunction<T, R, Exception> throwingFunction) {

    return t -> {
      try {
        return throwingFunction.apply(t);
      } catch (Exception ex) {
        throw new RuntimeException(ex);
      }
    };
  }
}
