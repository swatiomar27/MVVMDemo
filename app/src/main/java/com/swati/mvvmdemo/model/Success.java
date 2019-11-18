package com.swati.mvvmdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Success implements Serializable {

    @SerializedName("total")
    @Expose
    private Integer total;
    private final static long serialVersionUID = 2060220077275196647L;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
