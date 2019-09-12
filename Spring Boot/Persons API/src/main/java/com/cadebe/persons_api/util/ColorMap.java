package com.cadebe.persons_api.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ColorMap {

    private static Map<Integer, String> mappedValues;

    static {
        mappedValues = new HashMap<>();
        mappedValues.put(0, "Blue");
        mappedValues.put(1, "Green");
        mappedValues.put(2, "Purple");
        mappedValues.put(3, "Red");
        mappedValues.put(4, "Lemon yellow");
        mappedValues.put(5, "Turquoise");
        mappedValues.put(6, "White");
    }

    public ColorMap() {
    }

    public static int getOrdinalFromEnum(String inputColor) {
        String col = inputColor.substring(0, 1).toUpperCase() + inputColor.substring(1).toLowerCase();
        Iterator hmIterator = ColorMap.mappedValues.entrySet().iterator();
        while (hmIterator.hasNext()) {
            Map.Entry pair = (Map.Entry) hmIterator.next();
            String color = ((String) pair.getValue());
            if (color.equals((col))) {
                return (int) pair.getKey();
            }
        }
        return -1;
    }
}
