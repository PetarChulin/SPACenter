package com.example.spacenter.model.entity;

public class Counter {

    private int count;

    public Counter() {
        count = 1;
    }

    public int incrementAndGet() {
        return count++;
    }
}
