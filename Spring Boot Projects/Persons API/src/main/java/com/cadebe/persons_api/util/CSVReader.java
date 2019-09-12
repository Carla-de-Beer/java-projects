package com.cadebe.persons_api.util;

import com.cadebe.persons_api.model.Person;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@NoArgsConstructor
@Slf4j
public class CSVReader {

    public static Map<UUID, Person> readCSVFile() {
        String csvFile = FileData.FILE_NAME.toString();
        BufferedReader br = null;
        String line = "";
        Map<UUID, Person> resultMap = new HashMap<>();
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                UUID personId = UUID.randomUUID();
                Person person = CSVReader.createPerson(line);
                person.setId(personId);
                resultMap.put(personId, person);
            }
        } catch (IOException | NumberFormatException e) {
            log.error("Exception '{}' occurred trying to read the input file", e.getMessage());
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    log.error("Exception '{}' occurred trying to read the input file", e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return resultMap;
    }

    private static Person createPerson(String line) {
        String[] entry = line.split(FileData.DELIMETER.toString());
        String address = entry[2].trim();
        int index = address.indexOf(" ");
        String zipcode = address.substring(0, index);
        String city = address.substring(index);
        return new Person(null, entry[1].trim(), entry[0].trim(), zipcode, city, Integer.parseInt(entry[3].trim()));
    }
}