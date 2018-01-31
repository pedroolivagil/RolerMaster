package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.persistence.entities.interfaces.Advantage;
import com.olivadevelop.rolermaster.persistence.entities.interfaces.Attribute;
import com.olivadevelop.rolermaster.persistence.entities.interfaces.Ability;
import com.olivadevelop.rolermaster.persistence.entities.subentities.Virtue;
import com.olivadevelop.rolermaster.persistence.entities.subentities.Weapon;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.tools.utils.BasicEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 26/01/2018.
 * RolerMaster
 */
public class Character extends BasicEntity {

    private Integer idCharacter;
    private Integer idUser;

    // Character data
    private String name;
    private String surname;
    private String lastname;
    private Date birthdate;
    private String planet;
    private String home;
    private Gender gender;
    private String chronicle;
    private Integer behaviour;
    private Integer character;
    private Integer concept;
    private Integer humanity;
    private Integer will;
    private Integer faith;
    private Integer health;
    private String experience;

    // Skills and points
    private List<Attribute> attributes;
    private List<Ability> abilities;
    private List<Advantage> advantages;
    private List<Virtue> virtues;
    private List<Weapon> weapons;

    public Character() {
    }

    public Character(JSONObject json) throws JSONException {
        super(json);
    }

    public Integer getIdCharacter() {
        return idCharacter;
    }

    public void setIdCharacter(Integer idCharacter) {
        this.idCharacter = idCharacter;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getChronicle() {
        return chronicle;
    }

    public void setChronicle(String chronicle) {
        this.chronicle = chronicle;
    }

    public Integer getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(Integer behaviour) {
        this.behaviour = behaviour;
    }

    public Integer getCharacter() {
        return character;
    }

    public void setCharacter(Integer character) {
        this.character = character;
    }

    public Integer getConcept() {
        return concept;
    }

    public void setConcept(Integer concept) {
        this.concept = concept;
    }

    public Integer getHumanity() {
        return humanity;
    }

    public void setHumanity(Integer humanity) {
        this.humanity = humanity;
    }

    public Integer getWill() {
        return will;
    }

    public void setWill(Integer will) {
        this.will = will;
    }

    public Integer getFaith() {
        return faith;
    }

    public void setFaith(Integer faith) {
        this.faith = faith;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public List<Attribute> getAttributes() {
        if (Tools.isNull(attributes)) {
            attributes = new ArrayList<>();
        }
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public void addAttribute(Attribute attribute) {
        this.getAttributes().add(attribute);
    }

    public void removeAttribute(Attribute attribute) {
        this.getAttributes().remove(attribute);
    }

    public List<Ability> getAbilities() {
        if (Tools.isNull(abilities)) {
            abilities = new ArrayList<>();
        }
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public void addHability(Ability ability) {
        this.getAbilities().add(ability);
    }

    public void removeHability(Ability ability) {
        this.getAbilities().remove(ability);
    }

    public List<Advantage> getAdvantages() {
        if (Tools.isNull(advantages)) {
            advantages = new ArrayList<>();
        }
        return advantages;
    }

    public void setAdvantages(List<Advantage> advantages) {
        this.advantages = advantages;
    }

    public void addAdvantage(Advantage advantage) {
        this.getAdvantages().add(advantage);
    }

    public void removeAdvantage(Advantage advantage) {
        this.getAdvantages().add(advantage);
    }

    public List<Virtue> getVirtues() {
        if (Tools.isNull(virtues)) {
            virtues = new ArrayList<>();
        }
        return virtues;
    }

    public void setVirtues(List<Virtue> virtues) {
        this.virtues = virtues;
    }

    public void addVirtue(Virtue virtue) {
        this.getVirtues().add(virtue);
    }

    public void removeVirtue(Virtue virtue) {
        this.getVirtues().remove(virtue);
    }

    public List<Weapon> getWeapons() {
        if (Tools.isNull(weapons)) {
            weapons = new ArrayList<>();
        }
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    public void addWeapon(Weapon weapon) {
        this.getWeapons().add(weapon);
    }

    public void removeWeapon(Weapon weapon) {
        this.getWeapons().remove(weapon);
    }
}
