package com.example.lan.my_kaoshi_lan.module;

import com.example.lan.my_kaoshi_lan.utils.UtilsManager;

public class MyModule_class implements  MyModule{

    @Override
    public void FinshManager(Finsh finsh) {
        UtilsManager.getUtilsManager().getData(finsh);
    }
}
