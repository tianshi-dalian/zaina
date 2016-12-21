package com.zaina.bean;

import com.zaina.entity.Positiontable;
import com.zaina.entity.Rotationmap;

import java.util.List;

public class ExtRotationmap extends Positiontable {
    private List<Rotationmap> list;

    public List<Rotationmap> getList() {
        return list;
    }

    public void setList(List<Rotationmap> list) {
        this.list = list;
    }
}