package com.yet.spring.bean;

import java.text.DateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Event {

    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);

    public static void initAutoId(int id) {
        AUTO_ID.set(id);
    }

    public static boolean isDay(int start, int end) {
        LocalTime time = LocalTime.now();

        return time.getHour() > start && time.getHour() < end;
    }
    private int id;
    private String msg;
    private Date date;
    private DateFormat formatter;

    public Event() {
    }

    public Event(int id, Date date, String msg) {
        this.id = id;
        this.date = date;
        this.msg = msg;
    }

    public Event(Date date, DateFormat df) {
        this.id = AUTO_ID.getAndIncrement();
        this.date = date;
        this.formatter = df;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public Date getDate() {
        return date;
    }

    public DateFormat getFormatter() {
        return formatter;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", msg=" + msg + ", date=" + formatter.format(date) + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Event other = (Event) obj;
        return id == other.id;
    }
}
