package com.yaroslavtir;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ArrayVsListMemoryPrintForProfiler {

    public static final String VALUE = "some short value for test memory size";
    public static final boolean WITH_VALUE = true;
    public static final int COLUMN_SIZE = 5;
    public static final int ROW_SIZE = 1_000_000;

    private static String[][] array = new String[ROW_SIZE][COLUMN_SIZE];
    private static List<List<String>> data = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        initArray();
        initList();

        while (true) {
            TimeUnit.SECONDS.sleep(2);
            System.out.println(array.length);
            System.out.println(data.size());
        }

    }

    private static void initArray() {

        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < COLUMN_SIZE; j++) {
                if (WITH_VALUE) {
                    array[i][j] = VALUE + i + j;
                }
            }
        }
    }

    private static void initList() {

        for (int i = 0; i < ROW_SIZE; i++) {
            List<String> list = new ArrayList<>();
            data.add(list);
            for (int j = 0; j < COLUMN_SIZE; j++) {
                if (WITH_VALUE) {
                    list.add(array[i][j]);
                }
            }
        }
    }

}
