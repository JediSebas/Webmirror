package com.jedisebas.webmirror;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "event", schema = "event")
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private Long userid;
    private String name;
    private Long howlong;
    private String date;

    public Event() {
    }

    public Event(Long id, Long userid, String name, Long howlong, String date) {
        this.id = id;
        this.userid = userid;
        this.name = name;
        this.howlong = howlong;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getHowlong() {
        return howlong;
    }

    public void setHowlong(Long howlong) {
        this.howlong = howlong;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Event {\n" +
                "id = " + id +
                "userid = " + userid +
                "name = " + name +
                "howlong = " + howlong +
                "date = " + date +
                "\n}";
    }
}
