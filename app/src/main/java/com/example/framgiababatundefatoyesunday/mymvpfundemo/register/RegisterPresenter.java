package com.example.framgiababatundefatoyesunday.mymvpfundemo.register;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.TextUtils;
import com.example.framgiababatundefatoyesunday.mymvpfundemo.App;
import com.example.framgiababatundefatoyesunday.mymvpfundemo.Model.Contact;
import com.example.framgiababatundefatoyesunday.mymvpfundemo.R;
import com.example.framgiababatundefatoyesunday.mymvpfundemo.database.UserManagerUtil;
import java.util.List;

public class RegisterPresenter implements RegisterContract.Presenter {
    public ObservableField<String> username;
    public ObservableField<String> mobile;
    public ObservableField<String> email;
    public ObservableField<String> password;
    public ObservableField<String> imageUrl;
    public ObservableBoolean isImageSelected;
    public ObservableBoolean isNewOrEdit;
    private RegisterContract.ViewModel mViewModel;
    private List<Contact> mContactList;
    private Boolean isUpdate = false;
    private Contact mContact;

    public RegisterPresenter(RegisterContract.ViewModel viewModel, Contact contact) {
        mViewModel = viewModel;
        initFields();
        setValues(contact);
    }

    private void initFields() {
        username = new ObservableField<>();
        mobile = new ObservableField<>();
        email = new ObservableField<>();
        password = new ObservableField<>();
        imageUrl = new ObservableField<>();
        isImageSelected = new ObservableBoolean();
        isNewOrEdit = new ObservableBoolean();
        mContactList = UserManagerUtil.getListContacts(App.self());
    }

    private void setValues(Contact contact) {
        if (contact == null) return;
        mContact = contact;
        username.set(contact.getName());
        mobile.set(contact.getMobile());
        email.set(contact.getEmail());
        if (TextUtils.isEmpty(contact.getImageUrl())) {
            isImageSelected.set(false);
        } else {
            imageUrl.set(contact.getImageUrl());
            isImageSelected.set(true);
        }
        isNewOrEdit.set(true);
        isUpdate = true;
    }

    private boolean isValidate() {
        if (TextUtils.isEmpty(username.get())) {
            mViewModel.showToast(App.self().getString(R.string.err_username));
            return false;
        }
        if (TextUtils.isEmpty(mobile.get())) {
            mViewModel.showToast(App.self().getString(R.string.err_phone));
            return false;
        }
        if (TextUtils.isEmpty(email.get())) {
            mViewModel.showToast(App.self().getString(R.string.err_email));
            return false;
        }
        if (TextUtils.isEmpty(password.get())) {
            mViewModel.showToast(App.self().getString(R.string.err_password));
            return false;
        }

        if (!isUpdate) {
            for (Contact contact : mContactList) {
                if (contact.getEmail().equals(email.get())) {
                    mViewModel.showToast(App.self().getString(R.string.err_user_exist));
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void doLogin() {
        if (isValidate()) {
            if (isUpdate) {
                Contact contact =
                        new Contact(mContact.getId(), username.get(), mobile.get(), email.get(),
                                imageUrl.get(), password.get());
                UserManagerUtil.updateContact(App.self(), contact);
                mViewModel.login(contact);
                return;
            }
            Contact contact = new Contact(username.get(), mobile.get(), email.get(), imageUrl.get(),
                    password.get());
            UserManagerUtil.saveContact(App.self(), contact);
            mViewModel.login(contact);
        }
    }

    @Override
    public void filePicker() {
        mViewModel.pickImage();
    }
}