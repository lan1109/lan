package com.example.lan.my_kaoshi_lan.porerter;

import com.example.lan.my_kaoshi_lan.module.MyModule;
import com.example.lan.my_kaoshi_lan.view.MyView;

public class Myporerter_class implements MyPorerter, MyModule.Finsh {

    private MyModule mMyModule;
    private MyView mMyView;

    public Myporerter_class(MyModule myModule, MyView myView) {
        mMyModule = myModule;
        mMyView = myView;
    }

    @Override
    public void up() {
        if (mMyView != null) {
            mMyModule.FinshManager(this);
        }

    }

    @Override
    public void setSucess(String sucess) {
        if (mMyView != null) {
            mMyView.showSucess(sucess);
        }
    }

    @Override
    public void setError(String error) {
        if (mMyView != null) {

            mMyView.showError(error);
        }
    }

}
