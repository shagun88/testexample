package net.codejava.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class User{
    @Id
    private String empId;

    @NotNull
    private String accessKey;

    @NotNull
    private String country;

    @OneToOne(cascade = CascadeType.ALL)  // parent or primary key
    @JoinColumn(name = "empId", referencedColumnName = "empId")
    private Hierarchy hierarchy;

    public User(){}

    public User(String empId, String accessKey, String country, Hierarchy hierarchy) {
        this.empId = empId;
        this.accessKey = accessKey;
        this.country = country;
        this.hierarchy = hierarchy;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Hierarchy getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Hierarchy hierarchy) {
        this.hierarchy = hierarchy;
    }

}
