package com.example.ptblr_1178.project_first.model;

/**
 * Created by ptbr-1167 on 23/5/19.
 */

public class RegisterModel {

    private String name;
    public String phoneno;
    private String email;
    public String password;
    private String cpassword;

    public RegisterModel(String name,String phoneno,String email,String password,String cpassword){
        this.name=name;
        this.phoneno=phoneno;
        this.email=email;
        this.password=password;
        this.cpassword=cpassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }
}
