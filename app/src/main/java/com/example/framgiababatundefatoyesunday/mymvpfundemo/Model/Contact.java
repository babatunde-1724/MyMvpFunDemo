package com.example.framgiababatundefatoyesunday.mymvpfundemo.Model;

import com.j256.ormlite.field.DatabaseField;
import java.io.Serializable;

public class Contact implements Serializable {

    @DatabaseField(generatedId = true)
    int mId;
    @DatabaseField
    String mName;
    @DatabaseField
    String mMobile;
    @DatabaseField
    String mEmail;
    @DatabaseField
    String mImageUrl;
    @DatabaseField
    String mPassword;

    public Contact() {
    }

    public Contact(String email, String password) {
        this.mEmail = email;
        this.mPassword = password;
    }

    public Contact(String mName, String mMobile, String mEmail, String mImageUrl, String password) {
        this.mName = mName;
        this.mMobile = mMobile;
        this.mEmail = mEmail;
        this.mImageUrl = mImageUrl;
        this.mPassword = password;
    }

    public Contact(int mId, String mName, String mMobile, String mEmail, String mImageUrl, String password) {
        this.mId = mId;
        this.mName = mName;
        this.mMobile = mMobile;
        this.mEmail = mEmail;
        this.mImageUrl = mImageUrl;
        this.mPassword = password;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getMobile() {
        return mMobile;
    }

    public void setMobile(String mMobile) {
        this.mMobile = mMobile;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
}