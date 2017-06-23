package com.example.framgiababatundefatoyesunday.mymvpfundemo.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.example.framgiababatundefatoyesunday.mymvpfundemo.Constants.Constant;
import com.example.framgiababatundefatoyesunday.mymvpfundemo.Model.Contact;
import com.example.framgiababatundefatoyesunday.mymvpfundemo.R;
import com.example.framgiababatundefatoyesunday.mymvpfundemo.databinding.ActivityLoginBinding;
import com.example.framgiababatundefatoyesunday.mymvpfundemo.home.HomeActivity;
import com.example.framgiababatundefatoyesunday.mymvpfundemo.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.ViewModel {
    private LoginPresenter mPresenter;
    private ActivityLoginBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mPresenter = new LoginPresenter(this);
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void login(Contact contact) {
        if (contact == null) return;
        startActivity(
                new Intent(this, HomeActivity.class).putExtra(Constant.EXTRA_CONTACT, contact));
        finish();
    }

    @Override
    public void showLoginFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void register() {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}