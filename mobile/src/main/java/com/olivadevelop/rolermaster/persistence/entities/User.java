package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Entity;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Id;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.OneToOne;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Persistence;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.RelatedEntity;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Unique;
import com.olivadevelop.rolermaster.olivaobjectpersistence.entities._BasicEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 15/01/2018.
 * RolerMaster
 */
@Entity
@Persistence(collectionName = "user")
public class User extends _BasicEntity {

    private boolean flagActive;
    private boolean flagStatus;
    private boolean master;

    @Id
    private Integer idUser;

    @Unique
    private String username;
    private String password;

    @Unique
    private String email;

    @Unique
    private String name;

    @Persistence(columnName = "lastname")
    private String lastName;
    private String phone;
    private Date birthdate;

    @OneToOne
    @RelatedEntity(joinColumn = "idGender")
    private Gender gender;

    @OneToOne
    @RelatedEntity(joinColumn = "idCountry")
    private Country country;

    private List<User> rolers;

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

    public boolean isMaster() {
        return master;
    }

    public void setMaster(boolean master) {
        this.master = master;
    }

    public List<User> getRolers() {
        return rolers;
    }

    public void setRolers(List<User> rolers) {
        this.rolers = rolers;
    }

    public boolean isFlagActive() {
        return flagActive;
    }

    public void setFlagActive(boolean flagActive) {
        this.flagActive = flagActive;
    }

    public boolean isFlagStatus() {
        return flagStatus;
    }

    public void setFlagStatus(boolean flagStatus) {
        this.flagStatus = flagStatus;
    }
}
