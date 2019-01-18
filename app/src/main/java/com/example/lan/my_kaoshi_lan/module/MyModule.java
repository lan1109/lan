package com.example.lan.my_kaoshi_lan.module;

public interface MyModule {
    interface  Finsh{
        void setSucess(String sucess);
        void setError(String error);
    }
    void FinshManager(Finsh finsh);
}
