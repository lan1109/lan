package com.example.lan.my_kaoshi_lan;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lan.my_kaoshi_lan.beans.ImgBean;
import com.example.lan.my_kaoshi_lan.sdk.ImgUtils;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ImgActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 保存
     */
    private Button mBt;
    private ImageView mTooImgg;
    private ImgBean mImgBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);
        initView();
    }

    private void initView() {
        mBt = (Button) findViewById(R.id.bt);
        mBt.setOnClickListener(this);
        mTooImgg = (ImageView) findViewById(R.id.too_imgg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt:
                OkHttpClient client = new OkHttpClient.Builder()
                        .build();
                //数据流
                MultipartBody.Builder builder = new MultipartBody.Builder();
                //文件上传的文件夹名字
                builder.addFormDataPart("key", "lanlan");
                File mFile = new File(ImgUtils.getSDPath() + "/www.png");
                if (mFile.exists()) {
                    String type = "multipart/form-data";

                    RequestBody fileBody = RequestBody.create(MediaType.parse(type), mFile);

                    MultipartBody file1 = builder.setType(MultipartBody.FORM)
                            .addFormDataPart("file", mFile.getName(), fileBody)
                            .addFormDataPart("uid", "442")
                            .build();

                    Request request = new Request.Builder()
                            .url("http://yun918.cn/study/public/file_upload.php")
                            .post(file1)
                            .build();
                    Call call = client.newCall(request);

                    call.enqueue(new Callback() {




                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.i("sss", "onFailure: 上传失败" + e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String string = response.body().string();
                            Log.i("sss", "onResponse: 上传成功" + string);
                            mImgBean = new Gson().fromJson(string, ImgBean.class);
                            if (mImgBean.getCode() == 200) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        RequestOptions options = new RequestOptions().circleCrop();
                                        Glide.with(ImgActivity.this).load(mImgBean.getData().getUrl()).apply(options).into(mTooImgg);
                                        Toast.makeText(ImgActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(ImgActivity.this, MainActivity.class);
                                        intent.putExtra("img", mImgBean.getData().getUrl());
                                        startActivity(intent);

                                    }
                                });
                            }
                        }
                    });
                }
                break;
        }

    }
}
