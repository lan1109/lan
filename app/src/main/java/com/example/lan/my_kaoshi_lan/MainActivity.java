package com.example.lan.my_kaoshi_lan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lan.my_kaoshi_lan.adapter.MyAdapter;
import com.example.lan.my_kaoshi_lan.beans.Bean;
import com.example.lan.my_kaoshi_lan.module.MyModule_class;
import com.example.lan.my_kaoshi_lan.porerter.MyPorerter;
import com.example.lan.my_kaoshi_lan.porerter.Myporerter_class;
import com.example.lan.my_kaoshi_lan.view.MyView;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyView {

    private Toolbar mToo;
    private XRecyclerView mXre;
    private ImageView mTooImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        MyPorerter myPorerter = new Myporerter_class(new MyModule_class(), this);
        myPorerter.up();
    }

    private void initView() {
        mToo = (Toolbar) findViewById(R.id.too);
        mXre = (XRecyclerView) findViewById(R.id.xre);
        setSupportActionBar(mToo);

        mTooImg = (ImageView) findViewById(R.id.too_img);
        mTooImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImgActivity.class);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        Serializable img = intent.getSerializableExtra("img");
        if (img != null) {
            RequestOptions requestOptions = RequestOptions.circleCropTransform();
            Glide.with(this).load(img).apply(requestOptions).into(mTooImg);
        }
    }

    @Override
    public void showSucess(String s) {
        Gson gson = new Gson();
        Bean bean = gson.fromJson(s, Bean.class);
        List<Bean.RecentBean> recent = bean.getRecent();
        Log.e("1111", "showSucess: " + recent);
        LinearLayoutManager manager1 = new LinearLayoutManager(this);
        mXre.setLayoutManager(manager1);

        MyAdapter myAdapter = new MyAdapter(this, recent);
        mXre.setAdapter(myAdapter);
    }

    @Override
    public void showError(String error) {

    }

}
