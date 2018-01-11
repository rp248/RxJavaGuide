package com.rxjava.guide;

import com.rxjava.guide.observables.CreateExample;
import com.rxjava.guide.observables.DeferExample;
import com.rxjava.guide.observables.RxExample;

public class ExampleRunner {
    public static void main(String a[]) {
        //RxExample rxExample = new CreateExample();
        //rxExample.runExample();

        RxExample rxExample = new DeferExample();
        rxExample.runExample();
    }
}
