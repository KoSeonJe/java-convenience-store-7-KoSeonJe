package store.common.util;

public class NumberUtils {

    public static boolean isPositive(int number) {
        if (number < 0) {
            return false;
        }
        return true;
    }
}
