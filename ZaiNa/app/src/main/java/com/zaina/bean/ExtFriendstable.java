package com.zaina.bean;




import com.zaina.entity.Friendstable;
import com.zaina.entity.VFriendsAndtel;

import java.util.List;

/**
 * Created by Administrator on 2016/12/9.
 */
public class ExtFriendstable extends Friendstable {
    private List<VFriendsAndtel> friendList;
    private String telPhone;

    public List<VFriendsAndtel> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<VFriendsAndtel> friendList) {
        this.friendList = friendList;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }
}
