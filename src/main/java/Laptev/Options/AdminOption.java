package Laptev.Options;

import Laptev.DBTables.Aplication;
import Laptev.DBTables.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminOption {
    static Scanner scanner = new Scanner(System.in);
    static List<Aplication> arrAplications = new ArrayList<>();
    static List<Room> arrRooms = new ArrayList<>();

    public static void selectionOfAdminOptions(Statement statement) throws SQLException {
        System.out.println("Выберите опцию, которая вас интересует:");
        System.out.println("1: Посмотреть список клиентов");
        System.out.println("2: Посмотреть необработанные заявки");
        System.out.println("3: Выход");
        System.out.print("Опция: ");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Список всех клиентов:");
                break;
            case 2:
                System.out.println("\nНеобработанные заявки:");
                ResultSet resultSet = statement.executeQuery("SELECT count(*) FROM \"Aplications\"");
                resultSet.next();
                int countAplications = resultSet.getInt(1);
                System.out.println("Колличество необработанных заявок: " + countAplications + '\n');
                if (countAplications != 0) {
                    aplicationHandler(statement);
                } else {
                    selectionOfAdminOptions(statement);
                }
                break;
            case 3:
                System.exit(0);
                break;

            default:
                System.out.println("\nТакой опции нет\n");
                selectionOfAdminOptions(statement);
        }
    }

    private static void aplicationHandler(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM \"Aplications\"");

        long aplicationId;
        String clientName;
        int places;
        String roomClass;
        int durationHours;
        long personId;

        while (resultSet.next()) {
            aplicationId = resultSet.getLong(1);
            clientName = resultSet.getString(2);
            places = resultSet.getInt(3);
            roomClass = resultSet.getString(4);
            durationHours = resultSet.getInt(5);
            personId = resultSet.getLong(6);

            readAplication(aplicationId, clientName, places, roomClass, durationHours);

            Aplication newAplication = new Aplication(aplicationId, clientName, places, roomClass, durationHours, personId);
            arrAplications.add(newAplication);
        }
        choseOneApl(statement);
    }

    private static void readAplication(long aplicationId, String clientName, int places, String roomClass, int durationHours) {
        System.out.println("\nЗаявка номер: " + aplicationId);
        System.out.println("Имя клиента: " + clientName);
        System.out.println("Колличество желаемых мест: " + places);
        System.out.println("Желаемый класс номера: " + roomClass);
        System.out.println("Время пребывания (в часах): " + durationHours);
    }

    private static void choseOneApl(Statement statement) throws SQLException {
        System.out.print("\nВыберите номер заявки для обработки: ");
        boolean exists = false;
        long aplicationId;
        int numOfAplication = scanner.nextInt();
        String name;
        int places;
        String roomClass;
        int durationHours;
        long personId;
        for (Aplication apl : arrAplications) {
            if (apl.getAplicationId() == numOfAplication) {
                aplicationId = apl.getAplicationId();
                name = apl.getClientName();
                places = apl.getPlaces();
                roomClass = apl.getRoomClass();
                durationHours = apl.getDurationHours();
                personId = apl.getPersonId();
                readAplication(numOfAplication, name, places, roomClass, durationHours);
                exists = true;
                choseSuitableRoom(statement, name, places, roomClass, durationHours, personId, aplicationId);
                break;
            }
        }
        if (!exists) {
            System.out.println("\nТакой заявки не существует\n");
        }
    }

    private static void choseSuitableRoom(Statement statement, String clientName, int places, String roomClass, int durationHours, long personId, long aplicationId) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT count(*) FROM \"Rooms\" where \"Places\" >= " + places + "and \"Room_class\" = '" + roomClass + "' and \"Status\" is null");
        resultSet.next();
        int countRoom = resultSet.getInt(1);
        System.out.println("\nКолличество подходящих комнат: " + countRoom + '\n');
        if (countRoom == 0) {
            choseOneApl(statement);
        }
        scanner.nextLine();
        System.out.print("Показать подходящие комнаты (1: Да, 2: Нет): ");
        String str = scanner.nextLine();
        switch (str) {
            case "1":
            case "Да":
                writeSuitableRoom(statement, places, roomClass);
                bookingARoom(statement, durationHours, personId, aplicationId);
                break;
            default:
                choseOneApl(statement);
                break;
        }
    }

    private static void writeSuitableRoom(Statement statement, int places, String roomClass) throws SQLException {
        int roomNumber;
        int countPlace;
        String classOfRoom;

        System.out.println("\nПодходящие комнаты:");
        System.out.println("-------------------------------");
        ResultSet resultSet = statement.executeQuery("SELECT * FROM \"Rooms\" where \"Places\" >= " + places + "and \"Room_class\" = '" + roomClass + "' and \"Status\" is null");
        while (resultSet.next()) {
            roomNumber = resultSet.getInt(1);
            countPlace = resultSet.getInt(2);
            classOfRoom = resultSet.getString(3);

            Room newRoom = new Room(roomNumber, countPlace, classOfRoom);
            arrRooms.add(newRoom);
        }
        for (Room arrRoom : arrRooms) {
            System.out.println("Номер комнаты: " + arrRoom.getRoomNumber());
            System.out.println("Колличество мест: " + arrRoom.getPlaces());
            System.out.println("Класс комнаты: " + arrRoom.getRoomClass());
            System.out.println("-------------------------------");
        }
    }

    private static void bookingARoom(Statement statement, int durationHours, long personId, long aplicationId) throws SQLException {
        System.out.print("\nВыберите номер комнаты для брони: ");
        int roomNumber = scanner.nextInt();
        for (Room arrRoom : arrRooms) {
            if (arrRoom.getRoomNumber() == roomNumber) {
                statement.executeUpdate("update \"Rooms\" set \"Status\" = 'BOOKED' where \"Room_number\" = '" + roomNumber + "'");
                createRequest(statement, roomNumber, arrRoom.getPlaces(), arrRoom.getRoomClass(), durationHours, personId);
                deleteAplication(statement, aplicationId);
                choseOneApl(statement);
            }
        }
        System.out.println("Такая комната не подходит.");
    }

    private static void createRequest(Statement statement, int roomNumber, int places, String roomClass, int durationHours, long personId) throws SQLException {
        statement.executeUpdate("insert into \"Requests\" (\"Room_number\", \"Places\", \"Room_class\", \"Duration_hours\", \"Request_status\", \"Person_id\") values ('" + roomNumber + "', '" + places + "', '" + roomClass + "', '" + durationHours + "', 'IN_PROGRESS', '" + personId + "')");
    }

    private static void deleteAplication(Statement statement, long aplicationId) throws SQLException {
        statement.executeUpdate("delete from \"Aplications\" where \"Aplication_id\" = '" + aplicationId + "'");
    }
}