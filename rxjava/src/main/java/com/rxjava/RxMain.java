package com.rxjava;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxMain {
    public static void main(String[] args){
        for (String p: args) {
            System.out.println(p);
        }
        Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello,RxJava11");
                subscriber.onNext("Hello,RxJava12");
                subscriber.onNext("Hello,RxJava13");
                subscriber.onCompleted();
            }
        });
        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };
        myObservable.subscribe(mySubscriber);

        Observable<String> myObservable2 = Observable.just("Hello,RxJava2");
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        };
        myObservable2.subscribe(onNextAction);
        Subscriber<Integer> mySubscriber2 = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer.toString());
            }
        };
        myObservable2.map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                return s.hashCode();
            }
        }).subscribe(mySubscriber2);
        String[] names = {"1","2"};
        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

        testFlatMap();
    }

    private static void testFlatMap(){
        Observable<List<String>> observable = Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                ArrayList<String> list = new ArrayList<String>();
                list.add("list1");
                list.add("list2");
                list.add("list3");
                subscriber.onNext(list);
            }
        });
        observable.flatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> strings) {
                return Observable.from(strings);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

        observable.map(new Func1<List<String>, String>() {
            @Override
            public String call(List<String> strings) {
                return strings.get(0);
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s + " 123");
            }
        });
    }

    private static void testRxjava2(){
        Flowable.just("hello,rxjava2").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }

    private static void testRxJava(){
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //todo 通知观察者有变化
                subscriber.onNext("123");
                subscriber.onCompleted();
            }
        });

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                //todo 如何处理接收到的事件
                System.out.print("s:" + s);
            }
        };
        Subscription subscription = observable.subscribe(observer);

        Observable<String> observable1 = Observable.just("hello", "cai", "xiaoxiao");

        String[] names = {"hello","cai","xiaoxiao"};
        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.print(s);
            }
        });

        observable.subscribeOn(Schedulers.io()).observeOn(Schedulers.computation());

    }
}
