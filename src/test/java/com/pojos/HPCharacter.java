package com.pojos;

import com.google.gson.annotations.SerializedName;
import groovyjarjarantlr4.v4.codegen.model.SrcOp;

import java.lang.ref.SoftReference;

public class HPCharacter {
    /*
    {
    "_id": "5a0fa4daae5bc100213c232e",
    "name": "Hannah Abbott",
    "role": "student",
    "house": "Hufflepuff",
    "school": "Hogwarts School of Witchcraft and Wizardry",
    "__v": 0,
    "ministryOfMagic": false,
    "orderOfThePhoenix": false,
    "dumbledoresArmy": true,
    "deathEater": false,
    "bloodStatus": "half-blood",
    "species": "human"
}
     */
    @SerializedName("_id")
    private String id;
    private String name;
    private String role;
    private String house;
    private String school;
    @SerializedName("__v")
    private String v;
    private String ministryOfMagic;
    private String orderOfThePhoenix;
    private String dumbledoresArmy;
    private String deathEater;
    private String bloodStatus;
    private String species;

    public HPCharacter() {
    }

    public HPCharacter(String id, String name, String role, String house, String school, String v, String ministryOfMagic, String orderOfThePhoenix, String dumbledoresArmy, String deathEater, String bloodStatus, String species) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.house = house;
        this.school = school;
        this.v = v;
        this.ministryOfMagic = ministryOfMagic;
        this.orderOfThePhoenix = orderOfThePhoenix;
        this.dumbledoresArmy = dumbledoresArmy;
        this.deathEater = deathEater;
        this.bloodStatus = bloodStatus;
        this.species = species;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getMinistryOfMagic() {
        return ministryOfMagic;
    }

    public void setMinistryOfMagic(String ministryOfMagic) {
        this.ministryOfMagic = ministryOfMagic;
    }

    public String getOrderOfThePhoenix() {
        return orderOfThePhoenix;
    }

    public void setOrderOfThePhoenix(String orderOfThePhoenix) {
        this.orderOfThePhoenix = orderOfThePhoenix;
    }

    public String getDumbledoresArmy() {
        return dumbledoresArmy;
    }

    public void setDumbledoresArmy(String dumbledoresArmy) {
        this.dumbledoresArmy = dumbledoresArmy;
    }

    public String getDeathEater() {
        return deathEater;
    }

    public void setDeathEater(String deathEater) {
        this.deathEater = deathEater;
    }

    public String getBloodStatus() {
        return bloodStatus;
    }

    public void setBloodStatus(String bloodStatus) {
        this.bloodStatus = bloodStatus;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @Override
    public String toString() {
        return "HPCharacter{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", house='" + house + '\'' +
                ", school='" + school + '\'' +
                ", v='" + v + '\'' +
                ", ministryOfMagic='" + ministryOfMagic + '\'' +
                ", orderOfThePhoenix='" + orderOfThePhoenix + '\'' +
                ", dumbledoresArmy='" + dumbledoresArmy + '\'' +
                ", deathEater='" + deathEater + '\'' +
                ", bloodStatus='" + bloodStatus + '\'' +
                ", species='" + species + '\'' +
                '}';
    }
}
