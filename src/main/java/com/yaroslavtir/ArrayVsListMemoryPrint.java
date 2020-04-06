package com.yaroslavtir;

import java.util.ArrayList;
import java.util.List;

public class ArrayVsListMemoryPrint {

    public static final String VALUE = "some short value for test memory size";
    public static final boolean WITH_VALUE = true;
    public static final int COLUMN_SIZE = 5;
    public static final int ROW_SIZE = 1_000_000;

    private static String[][] array = new String[ROW_SIZE][COLUMN_SIZE];
    private static List<List<String>> data = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        printObjectSize(getArraySize());
        printObjectSize(getListSize());
    }

    private static long getArraySize() {

        long size = 0;
        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < COLUMN_SIZE; j++) {
                if (WITH_VALUE) {
                    size = size + InstrumentationAgent.getObjectSize(VALUE + i + j);
                    array[i][j] = VALUE + i + j;
                }
            }
        }
        return size + InstrumentationAgent.getObjectSize(array);
    }

    private static long getListSize() {

        long size = 0;
        for (int i = 0; i < ROW_SIZE; i++) {
            List<String> list = new ArrayList<>();
            size = size + InstrumentationAgent.getObjectSize(list);
            data.add(list);
            for (int j = 0; j < COLUMN_SIZE; j++) {
                if (WITH_VALUE) {
                    size = size + InstrumentationAgent.getObjectSize(VALUE + i + j);
                    list.add(VALUE + i + j);
                }
            }
        }

        return size + InstrumentationAgent.getObjectSize(data);
    }

    public static void printObjectSize(long value) {

        System.out.println("Object type: size: " + value + " bytes, or " + bytesToMeg(value) + " MB");
    }

    public static long bytesToMeg(long bytes) {

        return bytes / 1024L * 1024L;
    }

}
