package com.animal.main.Entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+")
    private String name;

    @Column(unique = true)
    private String identification_code;

    @Pattern(regexp = "^(?:[a-zA-Z]*|[ ]*)$")
    private String race;

    private String sex;

    private String dob;

    private int medical_expense;

    private Boolean can_be_used_with_young_children;
    private Boolean can_be_used_with_older_children;
    private Boolean can_with_cats;
    private Boolean can_with_dogs;
    private Boolean suitable_as_an_indoor_cat;
    private String breed;
    @ColumnDefault("false")
    private Boolean reserved = false;
    private int user_reserved_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentification_code() {
        return identification_code;
    }

    public void setIdentification_code(String identification_code) {
        this.identification_code = identification_code;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getMedical_expense() {
        return medical_expense;
    }

    public void setMedical_expense(int medical_expense) {
        this.medical_expense = medical_expense;
    }

    public Boolean getCan_be_used_with_young_children() {
        return can_be_used_with_young_children;
    }

    public void setCan_be_used_with_young_children(Boolean can_be_used_with_young_children) {
        this.can_be_used_with_young_children = can_be_used_with_young_children;
    }

    public Boolean getCan_be_used_with_older_children() {
        return can_be_used_with_older_children;
    }

    public void setCan_be_used_with_older_children(Boolean can_be_used_with_older_children) {
        this.can_be_used_with_older_children = can_be_used_with_older_children;
    }

    public Boolean getCan_with_cats() {
        return can_with_cats;
    }

    public void setCan_with_cats(Boolean can_with_cats) {
        this.can_with_cats = can_with_cats;
    }

    public Boolean getCan_with_dogs() {
        return can_with_dogs;
    }

    public void setCan_with_dogs(Boolean can_with_dogs) {
        this.can_with_dogs = can_with_dogs;
    }

    public Boolean getSuitable_as_an_indoor_cat() {
        return suitable_as_an_indoor_cat;
    }

    public void setSuitable_as_an_indoor_cat(Boolean suitable_as_an_indoor_cat) {
        this.suitable_as_an_indoor_cat = suitable_as_an_indoor_cat;
    }

    public Boolean getReserved() {
        return reserved;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }

    public int getUser_reserved_id() {
        return user_reserved_id;
    }

    public void setUser_reserved_id(int user_reserved_id) {
        this.user_reserved_id = user_reserved_id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
