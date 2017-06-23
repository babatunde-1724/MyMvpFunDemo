package com.example.framgiababatundefatoyesunday.mymvpfundemo.home;

import com.example.framgiababatundefatoyesunday.mymvpfundemo.Model.Contact;

public interface HomeContract {
    interface ViewModel {
        void logout();
        void editUser(Contact contact);
    }
    interface Presenter {
        void logout();
        void editUser();
    }
}