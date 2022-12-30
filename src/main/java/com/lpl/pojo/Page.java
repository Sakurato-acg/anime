package com.lpl.pojo;

import java.util.List;

public class Page <T>{
    private int total;
    private List<T> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Page{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }

    public Page() {
    }

    public Page(int total, List<T> list) {
        this.total = total;
        this.list = list;
    }
}
