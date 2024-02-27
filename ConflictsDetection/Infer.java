package ConflictsDetection;

public class Infer {
    
    public static <T> T defLeft(T value) {
        return value;
    }

    public static <T> T defRight(T value) {
        return value;
    }

    public static <T> T useLeft(T value) {
        return value;
    }

    public static <T> T useRight(T value) {
        return value;
    }

    public static <T> T defBase(T value) {
        return value;
    }
}
