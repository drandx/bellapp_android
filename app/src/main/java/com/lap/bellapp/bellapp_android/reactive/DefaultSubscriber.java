package com.lap.bellapp.bellapp_android.reactive;

import com.lap.bellapp.bellapp_android.reactive.executor.PostExecutionThread;
import com.lap.bellapp.bellapp_android.reactive.executor.ThreadExecutor;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by juangarcia on 11/7/15.
 */
public class DefaultSubscriber<T> implements rx.Observer<T> {


    //This threads will be used to subscribe to an Observer directly. ObserveOn -> PostExecution, SubscribeOn ThreadExecutor.
    public final ThreadExecutor threadExecutor;
    public final PostExecutionThread postExecutionThread;
    private Subscription subscription = Subscriptions.empty();

    public DefaultSubscriber(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    public void subscribeToObservable(Observable<T> observable, Observer<T> subscriber){
        this.subscription = observable
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(subscriber);
    }

    public void unsibscribeOperations(){
        this.subscription.unsubscribe();
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {

    }
}
