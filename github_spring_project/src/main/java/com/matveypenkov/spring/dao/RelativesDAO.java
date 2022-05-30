package com.matveypenkov.spring.dao;

import com.matveypenkov.spring.entity.Person;
import org.hibernate.sql.Alias;

import java.util.List;

public interface RelativesDAO {
    public List<Person> getAllRelatives();

    public void saveRelative(Person person);

    public Person getRelative(int id);

    public void deleteRelative(int id);

    public Person bioRelative(int id);
}
