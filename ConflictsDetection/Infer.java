package ConflictsDetection;

import java.util.function.Function;

public class Infer {
    
    public static <T> T defLeft(T value) {
        return value;
    }

    public static <T, R> R defLeft(T agrs, Function<T,R> func) {
        return func.apply(agrs);
    }

    public static <T> T useLeft(T value) {
        return value;
    }

    public static <T, R> R useLeft(T agrs, Function<T,R> func) {
        return func.apply(agrs);
    }

    public static <T> T defRight(T value) {
        return value;
    }

    public static <T, R> R defRight(T agrs, Function<T,R> func) {
        return func.apply(agrs);
    }

    public static <T> T useRight(T value) {
        return value;
    }

    public static <T, R> R useRight(T input, Function<T,R> func) {
        return func.apply(input);
    }
}
