package com.common.baselib.net.bean;

/**
 * Created by Sudroid on 2017/10/17.
 */

public class TitleBean {

    private int type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public TitleBean(int type) {
        this.type = type;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
