package com.example.rxjavaoperatorassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button just;
    private Button range;
    private Button list;
    private Button array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        just=findViewById(R.id.btnJust);
        range=findViewById(R.id.btnRange);
        list=findViewById(R.id.btnList);
        array=findViewById(R.id.btnArray);
        just.setOnClickListener(this);
        range.setOnClickListener(this);
        list.setOnClickListener(this);
        array.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        final String TAG="deeksha";
        switch (id){
            case R.id.btnJust:
                Observable<String> stringObservable=Observable.just("Reeta");
                Observer<String> ree=new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        //Log.v(TAG,"OnSubcribe");
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Log.v(TAG,s);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                     //   Log.v(TAG,"OnError");
                    }

                    @Override
                    public void onComplete() {
                      //  Log.v(TAG,"OnComplete");
                    }
                };
                stringObservable.subscribe(ree);
                break;
            case R.id.btnArray:
                String[] name={"reeta","deeksha","ruchi","sachin","chotu"};
                Observable<String> stringObservable1=Observable.fromArray(name);
                Observer<String> observerName=new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        String data=s;
                        Log.v(TAG,data);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                };
                stringObservable1.subscribe(observerName);
                break;
            case R.id.btnRange:
                Observable<Integer> integerObservable= Observable.range(20,21);
                Observer<Integer> rangeObserver=new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                       Log.v(TAG,"Values = "+integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                };
                integerObservable.subscribe(rangeObserver);
                break;
           case R.id.btnList:
               Observable<Student> studentObservable=Observable.fromIterable(getStudent()).
                       filter(new Predicate<Student>() {
                           @Override
                           public boolean test(Student student) throws Throwable {
                               String name=student.getStudent_name();
                               if (name.length()>6) return true;
                               else return false;

                           }
                       }).subscribeOn(Schedulers.io());
               Observer<Student> ree3=new Observer<Student>() {
                   @Override
                   public void onSubscribe(@NonNull Disposable d) {

                   }

                   @Override
                   public void onNext(@NonNull Student student) {
                      Log.v(TAG,student.getStudent_name());
                   }

                   @Override
                   public void onError(@NonNull Throwable e) {

                   }

                   @Override
                   public void onComplete() {

                   }
               };
               studentObservable.subscribe(ree3);
               break;
        }

    }

    public List<Student> getStudent(){
        Random random=new Random();
        String[] name={"rahul","rohit","rajkumar","ruchi","reeta","deeksha","sachin","shubham","shivkumar","ragvendra"};
        List<Student> list=new ArrayList<>();
        for (int i=0;i<10;i++){
            Student s1=new Student(i,name[i]);
            list.add(s1);
        }
        return list;
    }
}