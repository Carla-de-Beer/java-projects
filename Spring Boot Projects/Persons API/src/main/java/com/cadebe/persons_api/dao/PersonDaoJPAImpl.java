package com.cadebe.persons_api.dao;

import com.cadebe.persons_api.model.Person;
import com.cadebe.persons_api.util.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("JPA_Dao")
public class PersonDaoJPAImpl implements PersonDao {

    @Autowired
    private PersonJPA personDao;

    @Override
    public Person save(Person person) {
        List<Person> list = findAll();
        // Exclude duplicate new entries
        for (Person p : list) {
            if (p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName())
                    && p.getCity().equals(person.getCity()) && p.getZipcode().equals(person.getZipcode())) {
                return null;
            }
        }
        return personDao.save(person);
    }

    @Override
    public List<Person> findAll() {
        return (List<Person>) this.personDao.findAll();
    }

    @Override
    public Optional findById(UUID id) {
        return personDao.findById(id);
    }

    @Override
    public List<Person> findAllByColorPreference(int color) {
        List<Person> list = (List<Person>) this.personDao.findAll();
        List<Person> resultList = new ArrayList<>();
        for (Person person : list) {
            if (person.getColor() == color) {
                resultList.add(person);
            }
        }
        return resultList;
    }

    @Override
    public Person updateById(UUID id, Person updatedPerson) {
        Optional<Person> person = findById(id);
        if (person.isPresent()) {
            updatedPerson.setId(id);
            personDao.save(updatedPerson);
            return person.get();
        }
        return null;
    }

    @Override
    public void deleteById(UUID id) {
        personDao.deleteById(id);
    }
}