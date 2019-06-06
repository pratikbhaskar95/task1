package com.example.ptblr_1178.project_first.model;

/**
 * Created by ptbr-1167 on 23/5/19.
 */


// for getting data from the database we need to create the model class

public class TaskModel {

    private String name;
    private String desc;

    public TaskModel(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public TaskModel(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
