package me.javawold.dsa;

import java.util.Collection;

public class CollectionUtils {

    public static final int[] INT_ARRAY = new int[0];

    private CollectionUtils() {

    }

    public static int[] toIntArray(Collection<Integer> collection) {
        if (collection == null || collection.isEmpty())
            return INT_ARRAY;

        int[] arr = new int[collection.size()];
        int i = 0;
        for (Integer num : collection) {
            arr[i++] = num;
        }
        return arr;
    }

}
