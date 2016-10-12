package com.dress.collocation.pojo;

import java.io.Serializable;

/**
 * Created by admin on 2016/10/10.
 */
public class User implements Serializable{
    private Long id;
    private String name;
    private Integer age;
    private String address;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString(){
        if(name!=null){
            return name;
        }
        return null;
    }
}
