package com.example.framgiababatundefatoyesunday.mymvpfundemo.register;

import com.example.framgiababatundefatoyesunday.mymvpfundemo.Model.Contact;

public interface RegisterContract {
    interface ViewModel {
        void login(Contact contact);
        void showToast(String message);
        void showLoginFailed();
        void pickImage();
    }
    interface Presenter {
        void doLogin();
        void filePicker();
    }
}