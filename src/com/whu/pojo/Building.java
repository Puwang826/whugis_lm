package com.whu.pojo;

public class Building {
    private Integer gid;
    private String name;
    private String json;
    private String type;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getMeaning() {
        return type;
    }

    public void setMeaning(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Building{" +
                "gid=" + gid +
                ", name='" + name + '\'' +
                ", json='" + json + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public Building() {
    }

    public Building(Integer gid, String name, String json, String type) {
        this.gid = gid;
        this.name = name;
        this.json = json;
        this.type = type;
    }

}
