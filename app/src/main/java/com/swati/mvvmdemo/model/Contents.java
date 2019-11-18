package com.swati.mvvmdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Contents implements Serializable {

    @SerializedName("quotes")
    @Expose
    private List<Quote> quotes = null;
    private final static long serialVersionUID = 7656994405615142630L;

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }

}
