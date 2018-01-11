package com.rxjava.guide.obserables;

public abstract class CoreRxExample implements RxExample {
    public void printLog(String log) {
        System.out.println(log);
    }

    @Override
    public void runExample() {
        displayNotes();
    }
}
