package com.rxjava.guide.obserables;

import com.rxjava.guide.MockApi;
import com.rxjava.guide.MockApiListener;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class CreateExample extends CoreRxExample {

    private Disposable disposable;

    @Override
    public void displayNotes() {

    }

    @Override
    public void runExample() {
        super.runExample();
        Observable<Integer> integerObservable = Observable.<Integer>create(new ObservableOnSubscribe<Integer>(){

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                printLog("ObservableEmitter will acts as new Observable, which is created by create operator");
                printLog("We have to use this ObservableEmitter to emit the events or Items");

                MockApi mockApi = new MockApi();
                mockApi.getMockData((MockApiListener<Integer>) integer -> {
                    if (integer<0) {
                        emitter.onComplete();
                    }
                    else {
                        emitter.onNext(integer);
                    }

                }, 10, 1000);


            }
        });

        demoSubscriberTypes(integerObservable);
    }

    /**
     * The Observable, which is created by Create operator, can start emitting items when a Observer subscribes to it.
     */
    private void demoSubscriberTypes(Observable<Integer> observable) {
        consumerAcceptOnlySubscription(observable);
        observerSubscription(observable);
    }

    private void consumerAcceptOnlySubscription(Observable<Integer> observable) {
        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                printLog("consumerAcceptOnlySubscription:Item received from ObservableEmitter-"+String.valueOf(integer));
            }
        });
    }

    private void observerSubscription(Observable<Integer> observable) {
        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                printLog("onSubscribe(Disposable d) will be called upon subscription with Observable ");
                setDisposable(d);
            }

            @Override
            public void onNext(Integer integer) {
                printLog("observerSubscription::::tem received from ObservableEmitter-"+String.valueOf(integer));
                if (getDisposable() != null) {
                    getDisposable().dispose();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                printLog("observerSubscription::::onComplete");
            }
        });
    }

    public Disposable getDisposable() {
        return disposable;
    }

    public void setDisposable(Disposable disposable) {
        this.disposable = disposable;
    }
}
