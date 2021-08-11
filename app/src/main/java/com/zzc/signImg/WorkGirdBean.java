package com.zzc.signImg;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class WorkGirdBean implements MultiItemEntity {

    String orderType;
    String id;
    boolean check;
    int status;
    //item类型
    private int fieldType;
    //俩个选项
    public static final int second = 0;
    //三个选项
    public static final int third = 1;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WorkGirdBean{" +
                "orderType='" + orderType + '\'' +
                "id='" + id + '\'' +
                ", check=" + check +
                '}';
    }

    public WorkGirdBean(int fieldType){
        //将传入的type赋值
        this.fieldType = fieldType;
    }

    @Override
    public int getItemType() {
        //返回传入的itemType
        return fieldType;
    }
}
