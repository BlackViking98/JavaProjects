package com.matveypenkov.spring.service;

import com.matveypenkov.spring.entity.Person;

import java.util.List;

public interface RelativesService {
    public List<Person> getAllRelatives();

    public void saveRelative(Person person);

    public Person getRelative(int id);

    public void deleteRelative(int id);

    public Person bioRelative(int id);
}
