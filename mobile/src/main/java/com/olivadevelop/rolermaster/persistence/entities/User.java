package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.BasicEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 15/01/2018.
 * RolerMaster
 */
public class User extends BasicEntity {
    private Integer idUser;
    private String username;
    private String password;
    private String email;
    private String name;
    private String lastname;
    private String phone;
    private Country country;
    private Date birthdate;
    private Gender gender;

    public User() {
    }

    public User(JSONObject json) throws JSONException {
        super(json);
    }

    public User(Integer idUser, String username, String password, String email, String name, String lastname, String phone, Country country, Date birthdate, Gender gender) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.country = country;
        this.birthdate = birthdate;
        this.gender = gender;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public void fillEntity(JSONObject json) throws JSONException {
        if (Tools.isNotNull(json)) {
            this.idUser = json.getInt("idUser");
            this.username = json.getString("username");
            this.password = json.getString("password");
            this.email = json.getString("email");
            this.name = json.getString("name");
            this.lastname = json.getString("lastname");
            this.phone = json.getString("phone");
            this.country = new Country(json.getJSONObject("country"));
            this.birthdate = Tools.getDateFromString(json.getLong("birthdate"));
            this.gender = new Gender(json.getJSONObject("gender"));
        }
    }
}
