package com.matveypenkov.spring.entity;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="relatives")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Size(min=2,max=20, message = "имя должно быть больше 2 или меньше 20 символов")
    @Column(name="name")
    private String name;
    @NotBlank(message = "необходимо ввести фамилию")
    @Column(name="surname")
    private String surname;
    @Column(name="middleName")
    private String middleName;
    @Column(name="city")
    private String city;
    @Column(name="dateOfBirth")
    @Pattern(regexp ="\\d{2}\\.\\d{2}\\.\\d{4}", message = "пожалуйста вводите в формате ДД.MM.ГГГГ")
    private String dateOfBirth = "01.01.1900";
    @Column(name="age")
    @Min(value = 0, message = "не может быть меньше 0")
    @Max(value = 150, message = "не может превышать 151")
    private int age;
    @Column(name="bio")
    private String bio;
    @Column(name="motherID")
    private int motherID=0;
    @Column(name="fatherID")
    private int fatherID=0;
    @Column(name="generation")
    private int generation=0;
    @Column(name="imageLink")
    private String imageLink="/files/avatar.jpg";

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", city='" + city + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", age=" + age +
                ", bio='" + bio + '\'' +
                ", motherID=" + motherID +
                ", fatherID=" + fatherID +
                ", generation=" + generation +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }

    public Person(String name, String surname, String middleName, String city, String dateOfBirth, int age, String bio, int motherID, int fatherID, int generation, String imageLink) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.city = city;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.bio = bio;
        this.motherID = motherID;
        this.fatherID = fatherID;
        this.generation = generation;
        this.imageLink = imageLink;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getMotherID() {
        return motherID;
    }

    public void setMotherID(int motherID) {
        this.motherID = motherID;
    }

    public int getFatherID() {
        return fatherID;
    }

    public void setFatherID(int fatherID) {
        this.fatherID = fatherID;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
            this.city = city;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
