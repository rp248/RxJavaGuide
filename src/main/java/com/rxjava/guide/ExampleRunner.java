package com.rxjava.guide;

import com.rxjava.guide.obserables.CreateExample;
import com.rxjava.guide.obserables.RxExample;

public class ExampleRunner {
    public static void main(String a[]) {
        RxExample rxExample = new CreateExample();
        rxExample.runExample();
    }
}
