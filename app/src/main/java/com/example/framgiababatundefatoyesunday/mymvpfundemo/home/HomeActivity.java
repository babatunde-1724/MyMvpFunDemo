package com.example.framgiababatundefatoyesunday.mymvpfundemo.home;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.framgiababatundefatoyesunday.mymvpfundemo.Constants.Constant;
import com.example.framgiababatundefatoyesunday.mymvpfundemo.Model.Contact;
import com.example.framgiababatundefatoyesunday.mymvpfundemo.R;
import com.example.framgiababatundefatoyesunday.mymvpfundemo.databinding.ActivityHomeBinding;
import com.example.framgiababatundefatoyesunday.mymvpfundemo.login.LoginActivity;
import com.example.framgiababatundefatoyesunday.mymvpfundemo.register.RegisterActivity;

public class HomeActivity extends AppCompatActivity implements HomeContract.ViewModel {
    private HomePresenter mPresenter;
    private ActivityHomeBinding mBinding;
    private Contact mContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        getIntentExtras();
        mPresenter = new HomePresenter(this, mContact);
        mBinding.setPresenter(mPresenter);
    }

    private void getIntentExtras() {
        Intent intent = this.getIntent();
        mContact = (Contact) intent.getSerializableExtra(Constant.EXTRA_CONTACT);
    }

    @Override
    public void logout() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void editUser(Contact contact) {
        startActivity(new Intent(this, RegisterActivity.class).putExtra(Constant.EXTRA_CONTACT, contact));
        finish();
    }
}