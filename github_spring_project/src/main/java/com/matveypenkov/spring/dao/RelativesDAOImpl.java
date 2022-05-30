package com.matveypenkov.spring.dao;

import com.matveypenkov.spring.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.swing.border.EmptyBorder;
import java.util.List;

@Repository
public class RelativesDAOImpl implements RelativesDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Person> getAllRelatives() {
        Session session = sessionFactory.getCurrentSession();
        List<Person> allRelatives = session.createQuery("from Person", Person.class).getResultList();
        return allRelatives;
    }

    @Override
    public void saveRelative(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(person);
    }

    @Override
    public Person getRelative(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        return person;
    }

    @Override
    public void deleteRelative(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Person> query = session.createQuery("delete from Person where id =:personId");
        //этой строкой мы подставляем вместо employeeId параметра значение id
        query.setParameter("personId", id);
        // метод update и delete операций
        query.executeUpdate();
    }

    @Override
    public Person bioRelative(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        return person;
    }
}
