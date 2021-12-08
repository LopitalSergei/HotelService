package Laptev.Options;

import Laptev.Main;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UserOption {
    static Scanner scanner = new Scanner(System.in);

    public static void selectionOfUserOptions(Statement statement) throws SQLException {
        System.out.println("\nВыберите опцию, которая вас интересует:");
        System.out.println("1: Создать заявку на номер в отеле");
        System.out.println("2: Посмотреть мои заказы");
        System.out.println("3: Выход");
        System.out.print("Опция: ");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("\nСоздание заявки...\n");
                System.out.print("Введите желаемое количество мест в номере: ");
                int places = scanner.nextInt();

                scanner.nextLine();
                System.out.print("Введите желаемый класс апартаментов (1: первый, 2: второй, 3: третий): ");
                int selectRoomClass = scanner.nextInt();
                String roomClass = null;
                switch (selectRoomClass) {
                    case 1:
                        roomClass = "FIRST_CLASS";
                        break;
                    case 2:
                        roomClass = "SECOND_CLASS";
                        break;
                    case 3:
                        roomClass = "THIRD_CLASS";
                }

                System.out.print("Введите время пребывания в номере (в часах): ");
                int duration_hours = scanner.nextInt();

                preparationOfApplication(statement, places, roomClass, duration_hours);

                System.out.println("Ваша заявка отправлена на обработку.\n");
                break;
            case 2:
                System.out.println("Ваши заказы:");
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("\nТакой опции нет\n");
                selectionOfUserOptions(statement);
        }
    }

    private static void preparationOfApplication(Statement statement, int places, String room_class, int duration_hours) throws SQLException {
        statement.executeUpdate("insert into \"Aplications\" (\"Client_name\", \"Places\", \"Room_class\", \"Duration_hours\", \"Person_id\") " +
                "values ('" + Main.userName + "', '" + places + "', '" + room_class + "', '" + duration_hours + "', '" + Main.userID + "')");
    }
}
