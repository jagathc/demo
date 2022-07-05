package com.sf.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    private Integer id;
    private Integer managerId;
    private String name;

    @Override
    public String toString() {
        return id + " : " + name + " : " + managerId;
    }
}
