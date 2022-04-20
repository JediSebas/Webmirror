package com.jedisebas.webmirror;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user", schema = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String lastname;
    private String password;
    private String email;
    private String emailpassword;
    private String nick;
    private String instagram_login;
    private String instagram_password;
    private byte time_event;
    private byte weather_event;
    private byte gmail_event;
    private byte quote_event;
    private byte calendar_event;
    private byte photos_event;
    private byte instagram_event;
    private int time_x = 100;
    private int weather_x = 200;
    private int gmail_x = 300;
    private int quote_x = 400;
    private int calendar_x = 500;
    private int photos_x = 600;
    private int time_y = 100;
    private int weather_y = 200;
    private int gmail_y = 300;
    private int quote_y = 400;
    private int calendar_y = 500;
    private int photos_y = 600;

    public User() {
    }

    public User(Long id, String name, String lastname, String password, String email, String emailpassword, String nick, String instagram_login, String instagram_password, byte time_event, byte weather_event, byte gmail_event, byte quote_event, byte calendar_event, byte photos_event, byte instagram_event, int time_x, int weather_x, int gmail_x, int quote_x, int calendar_x, int photos_x, int time_y, int weather_y, int gmail_y, int quote_y, int calendar_y, int photos_y) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.emailpassword = emailpassword;
        this.nick = nick;
        this.instagram_login = instagram_login;
        this.instagram_password = instagram_password;
        this.time_event = time_event;
        this.weather_event = weather_event;
        this.gmail_event = gmail_event;
        this.quote_event = quote_event;
        this.calendar_event = calendar_event;
        this.photos_event = photos_event;
        this.instagram_event = instagram_event;
        this.time_x = time_x;
        this.weather_x = weather_x;
        this.gmail_x = gmail_x;
        this.quote_x = quote_x;
        this.calendar_x = calendar_x;
        this.photos_x = photos_x;
        this.time_y = time_y;
        this.weather_y = weather_y;
        this.gmail_y = gmail_y;
        this.quote_y = quote_y;
        this.calendar_y = calendar_y;
        this.photos_y = photos_y;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailpassword() {
        return emailpassword;
    }

    public void setEmailpassword(String emailpassword) {
        this.emailpassword = emailpassword;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @Override
    public String toString() {
        return "User{\n" +
                "id=" + id + "'\n" +
                ", name='" + name + "'\n" +
                ", lastname='" + lastname + "'\n" +
                ", password='" + password + "'\n" +
                ", email='" + email + "'\n" +
                ", emailpassword='" + emailpassword + "'\n" +
                ", nick='" + nick + "'\n" +
                "}";
    }

    public String getInstagram_login() {
        return instagram_login;
    }

    public void setInstagram_login(String instagram_login) {
        this.instagram_login = instagram_login;
    }

    public String getInstagram_password() {
        return instagram_password;
    }

    public void setInstagram_password(String instagram_password) {
        this.instagram_password = instagram_password;
    }

    public byte getTime_event() {
        return time_event;
    }

    public void setTime_event(byte time_event) {
        this.time_event = time_event;
    }

    public byte getWeather_event() {
        return weather_event;
    }

    public void setWeather_event(byte weather_event) {
        this.weather_event = weather_event;
    }

    public byte getGmail_event() {
        return gmail_event;
    }

    public void setGmail_event(byte gmail_event) {
        this.gmail_event = gmail_event;
    }

    public byte getQuote_event() {
        return quote_event;
    }

    public void setQuote_event(byte quote_event) {
        this.quote_event = quote_event;
    }

    public byte getCalendar_event() {
        return calendar_event;
    }

    public void setCalendar_event(byte calendar_event) {
        this.calendar_event = calendar_event;
    }

    public byte getPhotos_event() {
        return photos_event;
    }

    public void setPhotos_event(byte photos_event) {
        this.photos_event = photos_event;
    }

    public byte getInstagram_event() {
        return instagram_event;
    }

    public void setInstagram_event(byte instagram_event) {
        this.instagram_event = instagram_event;
    }

    public int getTime_x() {
        return time_x;
    }

    public void setTime_x(int time_x) {
        this.time_x = time_x;
    }

    public int getWeather_x() {
        return weather_x;
    }

    public void setWeather_x(int weather_x) {
        this.weather_x = weather_x;
    }

    public int getGmail_x() {
        return gmail_x;
    }

    public void setGmail_x(int gmail_x) {
        this.gmail_x = gmail_x;
    }

    public int getQuote_x() {
        return quote_x;
    }

    public void setQuote_x(int quote_x) {
        this.quote_x = quote_x;
    }

    public int getCalendar_x() {
        return calendar_x;
    }

    public void setCalendar_x(int calendar_x) {
        this.calendar_x = calendar_x;
    }

    public int getPhotos_x() {
        return photos_x;
    }

    public void setPhotos_x(int photos_x) {
        this.photos_x = photos_x;
    }

    public int getTime_y() {
        return time_y;
    }

    public void setTime_y(int time_y) {
        this.time_y = time_y;
    }

    public int getWeather_y() {
        return weather_y;
    }

    public void setWeather_y(int weather_y) {
        this.weather_y = weather_y;
    }

    public int getGmail_y() {
        return gmail_y;
    }

    public void setGmail_y(int gmail_y) {
        this.gmail_y = gmail_y;
    }

    public int getQuote_y() {
        return quote_y;
    }

    public void setQuote_y(int quote_y) {
        this.quote_y = quote_y;
    }

    public int getCalendar_y() {
        return calendar_y;
    }

    public void setCalendar_y(int calendar_y) {
        this.calendar_y = calendar_y;
    }

    public int getPhotos_y() {
        return photos_y;
    }

    public void setPhotos_y(int photos_y) {
        this.photos_y = photos_y;
    }
}
