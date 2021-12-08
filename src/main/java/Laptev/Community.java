package Laptev;

import Laptev.DBTables.Person;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Community {
    private List<Person> person;

    public List<Person> getPerson() {
        return person;
    }

    public void setPersons(List<Person> person) {
        this.person = person;
    }
}
