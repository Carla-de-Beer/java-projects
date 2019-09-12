package com.cadebe.persons_api.dao;

import com.cadebe.persons_api.model.Person;
import com.cadebe.persons_api.util.CSVReader;
import com.cadebe.persons_api.util.CSVWriter;
import com.cadebe.persons_api.util.FileData;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Repository("mockDao")
public class PersonDaoMockImpl implements PersonDao {

    private Map<UUID, Person> database;

    public PersonDaoMockImpl() {
        readCSVFileAndFillMockDataStructure();
    }

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
        this.database.put(person.getId(), person);
        writeNewEntryToCSVFile(person);
        return person;
    }

    @Override
    public List<Person> findAll() {
        return new ArrayList<>(this.database.values());
    }

    @Override
    public Optional<Person> findById(UUID personId) {
        Person person = this.database.get(personId);
        return Optional.ofNullable(person);
    }

    @Override
    public List<Person> findAllByColorPreference(int color) {
        List<Person> resultList = new ArrayList<>();
        List<Person> personsList = findAll();
        for (Person person : personsList) {
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
            this.database.put(person.get().getId(),updatedPerson);
            CSVWriter.writeCSVFile(findAll());
            return person.get();
        }
        return null;
    }

    @Override
    public void deleteById(UUID personId) {
        this.database.remove(personId);
        // Update CSV data to reflect deletion
        CSVWriter.writeCSVFile(findAll());
    }

    private void readCSVFileAndFillMockDataStructure() {
        this.database = CSVReader.readCSVFile();
    }

    private void writeNewEntryToCSVFile(Person person) {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(FileData.FILE_NAME.toString(), true));
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            String line = person.getLastName() + ", " + person.getFirstName() + ", " +
                    person.getZipcode() + " " + person.getCity() + ", " + person.getColor() + ", ";
            printWriter.println(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
