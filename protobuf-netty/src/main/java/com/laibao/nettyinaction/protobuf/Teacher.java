package com.laibao.nettyinaction.protobuf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author laibao wang
 */
public class Teacher implements Serializable{
    private static final long serialVersionUID = -7335616930998984039L;
    private long teacherId;
    private int age;
    private String name;
    private List<String> courses = new ArrayList();

    public Teacher() {
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }
}
