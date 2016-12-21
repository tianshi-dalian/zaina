package com.zaina.entity;



import com.zaina.common.CommonBean;

import java.util.Date;

public class Friendstable extends CommonBean {
    private Integer id;

    private String uuid;

    private String frienduuid;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getFrienduuid() {
        return frienduuid;
    }

    public void setFrienduuid(String frienduuid) {
        this.frienduuid = frienduuid == null ? null : frienduuid.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}