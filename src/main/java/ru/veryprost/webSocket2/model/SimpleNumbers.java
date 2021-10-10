package ru.veryprost.webSocket2.model;

import java.util.List;

public class SimpleNumbers {
    private int[] lengthArray;

    private List<List<Integer>> baseList;
    private List<List<Integer>> resultList;

    public SimpleNumbers() {
    }

    public SimpleNumbers(int[] lengthArray) {
        this.lengthArray = lengthArray;
    }

    public int[] getLengthArray() {
        return lengthArray;
    }

    public void setLengthArray(int[] lengthArray) {
        this.lengthArray = lengthArray;
    }

    public List<List<Integer>> getBaseList() {
        return baseList;
    }

    public void setBaseList(List<List<Integer>> baseList) {
        this.baseList = baseList;
    }

    public List<List<Integer>> getResulList() {
        return resultList;
    }

    public void setResulList(List<List<Integer>> resulList) {
        this.resultList = resulList;
    }
}