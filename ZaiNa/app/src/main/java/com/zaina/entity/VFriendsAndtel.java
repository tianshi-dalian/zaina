package com.zaina.entity;

import com.zaina.common.CommonBean;

public class VFriendsAndtel extends CommonBean {
    private String frienduuid;

    private String username;

    private String gender;

    private String age;

    private String headimg;

    private String account;

    private String uuid;
    //姓名首字母
    private String userNameLetter;
    //姓名全拼
    private String quanpin;

    public String getFrienduuid() {
        return frienduuid;
    }

    public void setFrienduuid(String frienduuid) {
        this.frienduuid = frienduuid == null ? null : frienduuid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg == null ? null : headimg.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getUserNameLetter() {
        return userNameLetter;
    }

    public void setUserNameLetter(String userNameLetter) {
        this.userNameLetter = userNameLetter;
    }

    public String getQuanpin() {
        return quanpin;
    }

    public void setQuanpin(String quanpin) {
        this.quanpin = quanpin;
    }
}