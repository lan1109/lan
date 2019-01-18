package com.example.lan.my_kaoshi_lan.utils;

import com.example.lan.my_kaoshi_lan.http.MyServier;
import com.example.lan.my_kaoshi_lan.module.MyModule;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class UtilsManager {
    private static UtilsManager sUtilsManager;

    public  static UtilsManager getUtilsManager(){
        if (sUtilsManager == null){
            synchronized (UtilsManager.class){
                if (sUtilsManager == null){
                    sUtilsManager =new UtilsManager();
                }
            }
        }
        return  sUtilsManager;
    }

    public void  getData(final MyModule.Finsh finsh){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(MyServier.url)
                .build();
        MyServier myServier = retrofit.create(MyServier.class);
        Observable<String> stringObservable = myServier.get();
        stringObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {
                        finsh.setSucess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        finsh.setError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
