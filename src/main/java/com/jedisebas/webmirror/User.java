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

    public User() {
    }

    public User(Long id, String name, String lastname, String password, String email, String emailpassword, String nick) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.emailpassword = emailpassword;
        this.nick = nick;
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
}
