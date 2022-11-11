package com.yet.spring.bean;

import java.text.DateFormat;
import java.util.Date;

public class Event {

    private int id;
    private String msg;
    private Date date;
    private DateFormat formatter;

    public Event() {
    }

    public Event(Date date, DateFormat formatter) {
        this.date = date;
        this.formatter = formatter;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return String.format("id: %d message: %s date: %s", id, msg, formatter.format(date));
    }
}