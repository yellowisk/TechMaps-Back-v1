package br.ifsp.techmaps.usecases.util;

import java.util.Collection;

public abstract class Validator {
    public abstract class Notification<T> {
        public abstract Notification validate(T type);

        public static boolean nullOrEmpty(String string) {
            return string == null || string.isEmpty();
        }

        public static boolean nullOrEmpty(Collection collection) {
            return collection == null || collection.isEmpty();
        }

    }
}
