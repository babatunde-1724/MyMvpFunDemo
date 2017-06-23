package com.example.framgiababatundefatoyesunday.mymvpfundemo.login;

import com.example.framgiababatundefatoyesunday.mymvpfundemo.Model.Contact;

public interface LoginContract {
    interface ViewModel {
        void login(Contact contact);
        void showLoginFailed(String error);
        void register();
    }
    interface Presenter {
        void doLogin();
        void register();
    }
}