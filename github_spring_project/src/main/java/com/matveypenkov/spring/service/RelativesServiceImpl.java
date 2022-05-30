package com.matveypenkov.spring.service;

import com.matveypenkov.spring.entity.Person;
import com.matveypenkov.spring.dao.RelativesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RelativesServiceImpl implements RelativesService {

    @Autowired
    private RelativesDAO relativesDAO;


    @Override
    @Transactional
    public List<Person> getAllRelatives() {
        return relativesDAO.getAllRelatives();
    }

    @Override
    @Transactional
    public void saveRelative(Person person) {
        relativesDAO.saveRelative(person);
    }

    @Override
    @Transactional
    public Person getRelative(int id) {
        return relativesDAO.getRelative(id);
    }

    @Override
    @Transactional
    public void deleteRelative(int id) {
        relativesDAO.deleteRelative(id);
    }

    @Override
    @Transactional
    public Person bioRelative(int id) {
        return relativesDAO.bioRelative(id);
    }
}
