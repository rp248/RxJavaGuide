package com.rxjava.guide.observables;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class DeferExample extends CoreRxExample {
    @Override
    public void displayNotes() {

    }

    @Override
    public void runExample() {
        super.runExample();

        Observable<Integer> deferObservable = Observable.<Integer>defer(new Callable<ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> call() throws Exception {
                return Observable.fromArray(1,2,3,4);
            }
        });

        deferObservable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                printLog("Consumer::::received-"+integer);
            }
        });

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        deferObservable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                printLog("Observer::::received-"+integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }


        });
    }
}
