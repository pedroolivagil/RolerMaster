package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.BasicEntity;
import com.olivadevelop.rolermaster.tools.utils.ConverterJSONArrayToList;
import com.olivadevelop.rolermaster.tools.utils.intefraces.Persistence;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 15/01/2018.
 * RolerMaster
 */
public class User extends BasicEntity {
    private boolean master;
    private List<Integer> rolers;
    private Integer idUser;
    private String username;
    private String password;
    private String email;
    private String name;
    @Persistence(columnName = "lastname")
    private String lastName;
    private String phone;
    private Date birthdate;
    private Gender gender;
    private Country country;

    public User() {
        super();
    }

    public User(JSONObject json) throws JSONException {
        super(json);
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
            this.lastName = json.getString("lastname");
            this.phone = json.getString("phone");
            this.country = new Country(json.getJSONObject("country"));
            this.birthdate = Tools.getDateFromStringLong(json.getString("birthdate"));
            this.gender = new Gender(json.getJSONObject("gender"));
            this.master = json.getBoolean("master");
            ConverterJSONArrayToList<Integer> converter = new ConverterJSONArrayToList<>(Integer.class);
            this.rolers = converter.convert(json.getJSONArray("rolers"));
        }
    }
}
