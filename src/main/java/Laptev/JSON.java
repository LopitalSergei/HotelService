package Laptev;

import Laptev.DBTables.Person;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JSON {
    private static final String FILENAME = "8.hotel.json";
    private static ArrayList<Person> arrPersonsJSON;

    static void readerJSON(Statement statement) throws IOException, SQLException {
        ObjectMapper objectMapper = new ObjectMapper();

        Community newPerson = objectMapper.readValue(new File(FILENAME), Community.class);

        arrPersonsJSON = new ArrayList<>();

        for (Person p : newPerson.getPerson()) {
            System.out.println(p);
            arrPersonsJSON.add(p);
        }
        JSONInDB(statement);
    }

    private static void JSONInDB(Statement statement) throws SQLException {
        ResultSet resultSetPerson = statement.executeQuery("SELECT * FROM \"Persons\"");
        ResultSet resultSetRequest = statement.executeQuery("SELECT * FROM \"Requests\"");
        ArrayList<String> arrNamePersons = new ArrayList<>();
        ArrayList<Integer> arrPersonIDInRequests = new ArrayList<>();
        while (resultSetPerson.next()) {
            arrNamePersons.add(resultSetPerson.getString(2));
            arrPersonIDInRequests.add(resultSetRequest.getInt(7));
        }
        personDB(statement, arrNamePersons);
        requestDB(statement, arrPersonIDInRequests);
    }

    private static void personDB(Statement statement, ArrayList<String> arrNamePersons) throws SQLException {
        boolean flag = false;
        for (Person person : arrPersonsJSON) {
            for (String arrPerson : arrNamePersons) {
                if (person.getPersonName().equals(arrPerson)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                statement.executeUpdate("insert into \"Persons\" (\"Name\") values ('" + person.getPersonName() + "')");
            }
            flag = false;
        }
    }

    private static void requestDB(Statement statement, ArrayList<Integer> arrPersonIDInRequests) throws SQLException {


    }
}
