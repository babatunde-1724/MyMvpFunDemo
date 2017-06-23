package com.example.framgiababatundefatoyesunday.mymvpfundemo.login;

import android.databinding.ObservableField;
import android.text.TextUtils;
import com.example.framgiababatundefatoyesunday.mymvpfundemo.App;
import com.example.framgiababatundefatoyesunday.mymvpfundemo.Model.Contact;
import com.example.framgiababatundefatoyesunday.mymvpfundemo.R;
import com.example.framgiababatundefatoyesunday.mymvpfundemo.database.UserManagerUtil;
import java.util.ArrayList;
import java.util.List;

public class LoginPresenter implements LoginContract.Presenter {
    public ObservableField<String> email;
    public ObservableField<String> password;
    private LoginContract.ViewModel mViewModel;
    private List<Contact> allContacts;

    public LoginPresenter(LoginContract.ViewModel viewModel) {
        mViewModel = viewModel;
        initFields();
        allContacts = UserManagerUtil.getListContacts(App.self());
    }

    private void initFields() {
        email = new ObservableField<>();
        password = new ObservableField<>();
    }

    private boolean isValidate() {
        if (TextUtils.isEmpty(email.get())) {
            mViewModel.showLoginFailed(App.self().getString(R.string.err_email));
            return false;
        }
        if (TextUtils.isEmpty(password.get())) {
            mViewModel.showLoginFailed(App.self().getString(R.string.err_password));
            return false;
        }
        return true;
    }

    @Override
    public void doLogin() {
        if (isValidate()) {
            if (allContacts == null) allContacts = new ArrayList<>();
            Contact validContact = new Contact(email.get(), password.get());
            for (Contact contact : allContacts) {
                if (contact.getEmail().equals(validContact.getEmail()) && contact.getPassword()
                        .equals(validContact.getPassword())) {
                    mViewModel.login(contact);
                    return;
                }
            }
            mViewModel.showLoginFailed(App.self().getString(R.string.err_login));
        }
    }

    @Override
    public void register() {
        mViewModel.register();
    }
}