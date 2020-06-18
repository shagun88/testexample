package net.codejava.model;

import javax.persistence.*;

@Entity
@Table(name = "teamhierarchy")
public class Hierarchy {

   /* public void setId(long id) {
        this.id = id;
    }

    private long id;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }*/
   @Id
    private String empid;
    private String managerId;
    @OneToOne(mappedBy = "hierarchy") // Child or Foreign Key
    private User user;

    public Hierarchy(){}

    public Hierarchy(String empid, String managerId) {
        this.empid = empid;
        this.managerId = managerId;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }
}
