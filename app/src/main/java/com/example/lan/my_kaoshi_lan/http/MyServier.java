package com.example.lan.my_kaoshi_lan.http;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MyServier {
    String url = "http://news-at.zhihu.com/api/4/news/";

    @GET("hot")
    Observable<String>get();

}
