package com.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HPHouse {

    /*
    {
        "_id": "5a05e2b252f721a3cf2ea33f",
        "name": "Gryffindor",
        "mascot": "lion",
        "headOfHouse": "Minerva McGonagall",
        "houseGhost": "Nearly Headless Nick",
        "founder": "Goderic Gryffindor",
        "__v": 0,
        "school": "Hogwarts School of Witchcraft and Wizardry",
        "members": [
            "5a0fa648ae5bc100213c2332",

        ],
        "values": [
            "courage",
            "bravery",
            "nerve",
            "chivalry"
        ],
        "colors": [
            "scarlet",
            "gold"
        ]
    },
     */
    @SerializedName("_id")
    private String id;
    private String name;
    private String mascot;
    private String headOfHouse;
    private String houseGhost;
    private String founder;
    @SerializedName("__v")
    private String v;
    private String school;
    private List<HPMember> members;
    private List<String> values;
    private List<String> colors;

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

    public String getMascot() {
        return mascot;
    }

    public void setMascot(String mascot) {
        this.mascot = mascot;
    }

    public String getHeadOfHouse() {
        return headOfHouse;
    }

    public void setHeadOfHouse(String headOfHouse) {
        this.headOfHouse = headOfHouse;
    }

    public String getHouseGhost() {
        return houseGhost;
    }

    public void setHouseGhost(String houseGhost) {
        this.houseGhost = houseGhost;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public List<HPMember> getMembers() {
        return members;
    }

    public void setMembers(List<HPMember> members) {
        this.members = members;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    @Override
    public String toString() {
        return "HPHouse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mascot='" + mascot + '\'' +
                ", headOfHouse='" + headOfHouse + '\'' +
                ", houseGhost='" + houseGhost + '\'' +
                ", founder='" + founder + '\'' +
                ", v='" + v + '\'' +
                ", school='" + school + '\'' +
                ", members=" + members +
                ", values=" + values +
                ", colors=" + colors +
                '}';
    }
}
