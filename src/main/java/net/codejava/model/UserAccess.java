package net.codejava.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="useraccess")
public class UserAccess {

    public UserAccess(){}

    public void setId(long id) {
        this.id = id;
    }

    private long id;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    @NotNull
    private String empid;
    @NotNull
    private String accessKey;
    @NotNull
    private String country;
    @NotNull
    private String subuser;
    @NotNull
    private String subuser_accesskey;
    @NotNull
    private String subuser_country;

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
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

    public String getSubuser() {
        return subuser;
    }

    public void setSubuser(String subuser) {
        this.subuser = subuser;
    }

    public String getSubuser_accesskey() {
        return subuser_accesskey;
    }

    public void setSubuser_accesskey(String subuser_accesskey) {
        this.subuser_accesskey = subuser_accesskey;
    }

    public String getSubuser_country() {
        return subuser_country;
    }

    public void setSubuser_country(String subuser_country) {
        this.subuser_country = subuser_country;
    }

}
