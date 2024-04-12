import java.util.function.Function;

public class Infer {
    
    public static <T> T defLeft(T value) {
        return value;
    }

    public static void defLeft(Runnable runnable) {runnable.run();}

    public static <T> T useLeft(T value) {
        return value;
    }

    public static void useLeft(Runnable runnable) {runnable.run();}

    public static <T> T defRight(T value) {
        return value;
    }

    public static void defRight(Runnable runnable) {runnable.run();}

    public static <T> T useRight(T value) {
        return value;
    }

    public static void useRight(Runnable runnable) {runnable.run();}
}
