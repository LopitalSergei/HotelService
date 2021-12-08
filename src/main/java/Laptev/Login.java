package Laptev;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static Laptev.Options.AdminOption.selectionOfAdminOptions;
import static Laptev.Options.UserOption.*;

public class Login {
    static Scanner scanner = new Scanner(System.in);

    static void choosingRole(Statement statement) throws SQLException {
        System.out.println("Выбериет роль под которой хотите войти:");
        System.out.println("1: Admin");
        System.out.println("2: User");
        System.out.print("Роль: ");

        String choice = scanner.nextLine();
        switch (choice) {
            case "Admin":
            case "1":
                loginAdmin(statement);
                selectionOfAdminOptions(statement);
                break;
            case "User":
            case "2":
                authorization(statement);
                selectionOfUserOptions(statement);
                break;
            default:
                System.out.println("\nТакой роли не существует\n");
                choosingRole(statement);
        }
    }

    private static void loginAdmin(Statement statement) throws SQLException {
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        if (password.equals("root")) {
            Main.admin = true;
            System.out.println("Вход с правами администратора выполнен\n");
        } else if (password.equals("exit")) {
            choosingRole(statement);
        } else {
            System.out.println("Пароль неверный\n");
            System.out.println("Если не знаете пароль, напишите 'exit'");
            loginAdmin(statement);
        }
    }

    private static void authorization(Statement statement) throws SQLException {
        System.out.println("\nUser, введите своё имя для входа:");
        System.out.print("Имя: ");

        String name = scanner.nextLine();
        ResultSet resultSet = statement.executeQuery("select \"Person_id\" from \"Persons\" where \"Name\" = '" + name + "'");
        if (resultSet.next()) {
            Main.userID = resultSet.getInt(1);
            Main.userName = name;
        } else {
            System.out.println("\nТакого пользователя не существует\n");
            creatingUser(statement, name);
        }
    }

    private static void creatingUser(Statement statement, String name) throws SQLException {
        System.out.println("Хотите создать учетную запись?");
        System.out.println("1: Да");
        System.out.println("2: Нет");
        System.out.print("Ответ: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "Да":
            case "1":
                statement.executeUpdate("insert into \"Persons\" (\"Name\") values ('" + name + "')");
                Main.userName = name;
                ResultSet resultSet = statement.executeQuery("select \"Person_id\" from \"Persons\" where \"Name\" = '" + name + "'");
                if (resultSet.next()) {
                    Main.userID = resultSet.getInt(1);
                }
                System.out.println("Учетная запись создана\n");
                break;
            case "Нет":
            case "2":
                System.out.println("\nПользователь не хочет создавать учетую запись\n");
                System.exit(0);
            default:
                System.out.println("\nТакого варианта нет\n");
                creatingUser(statement, name);
        }
    }
}
