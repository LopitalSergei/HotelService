package Laptev.DBTables;

import java.util.List;


public class Person {
    private String personName;
    private List<Request> requests;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return "Person :\n[personName = " + personName + "]\n" + requests + '\n';
    }
}
