package com.animal.main.Entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 50, message = "Value must be at least 50")
    @Max(value = 300, message = "Value must be at most 300")
    private int penCode1;

    @Min(value = 100, message = "Value must be at least 100")
    @Max(value = 300, message = "Value must be at most 300")
    private int penCode2;

    private String penName;
    @Column(unique = true, name = "animal_id")
    private int animal_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPenCode1() {
        return penCode1;
    }

    public void setPenCode1(int penCode1) {
        this.penCode1 = penCode1;
    }

    public int getPenCode2() {
        return penCode2;
    }

    public void setPenCode2(int penCode2) {
        this.penCode2 = penCode2;
    }

    public String getPenName() {
        return penName;
    }

    public void setPenName(String penName) {
        this.penName = penName;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }

    @Override
    public String toString() {
        return "Accommodation{" +
                "id=" + id +
                ", penCode1='" + penCode1 + '\'' +
                ", penCode2='" + penCode2 + '\'' +
                ", penName='" + penName + '\'' +
                ", animal_id=" + animal_id +
                '}';
    }
}