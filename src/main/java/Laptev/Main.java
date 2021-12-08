package Laptev;

import java.io.IOException;
import java.sql.*;

import static Laptev.Login.*;
import static Laptev.JSON.*;

public class Main {
    private final static String PASSWORD = "root";
    private final static String URL = "jdbc:postgresql://localhost:5432/HotelService";
    private final static String USERNAME = "root";

    public static int userID;
    public static String userName;
    public static boolean admin;


    public static void main(String[] args) throws IOException {
        try {
            Driver driver = new org.postgresql.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            System.out.println("Ошибка регистрации драйвера");
            return;
        }

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()
        ) {
//                statement.addBatch("insert into \"Persons\" (\"Person_id\", \"Name\") values(1, '" + arrPersons.get(0).getPersonName() + "')");
//                statement.addBatch("insert into \"Request\" (\"Request_id\", \"Places\", \"Class\", \"Duration_hours\", \"Room_number\", \"Request_status\")" +
//                        "values(1, '"+arrPersons.get(0).getRequests().get(0).getPlaces() +"')");
//                //statement.executeUpdate("insert into \"Persons\" (\"Person_id\", \"Name\") values(1, '" + arrPersons.get(0).getPersonName() + "')");

           choosingRole(statement);
         //   readerJSON(statement);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось установить соединение");
        }
    }
}