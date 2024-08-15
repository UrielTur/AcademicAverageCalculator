package db;
import org.example.OpeningScreen;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MyJDBC {

    private static List<String> strings;
    private static List<Double> grades;
    private static List<String> times;
    private static List<String> dates;
    private static ResultSet resultSpecialSet;



    public static boolean register(String username){

        if (!exist(username)) {
            try {
                Connection connection = DriverManager.getConnection(
                        OpeningScreen.DB_URL,
                        OpeningScreen.DB_USERNAME,
                        OpeningScreen.PASSWORD
                );

                PreparedStatement insertUser = connection.prepareStatement(
                        "INSERT INTO " + OpeningScreen.DB_USERS_TABLE_NAME + "(username, finalGrade) VALUES(?, ?)");

                insertUser.setString(1 , username);
                insertUser.setDouble(2, 0);

                insertUser.executeUpdate();
                return true;
            }catch (
                    SQLException e){
                e.printStackTrace();
            }
        }
        return false;

    }

    public static void match(String username, double grade) {
        try {
            Connection connection = DriverManager.getConnection(
                    OpeningScreen.DB_URL,
                    OpeningScreen.DB_USERNAME,
                    OpeningScreen.PASSWORD
            );

            if (exist(username)) {
                PreparedStatement updateUsersTable = connection.prepareStatement(
                        "UPDATE " + OpeningScreen.DB_USERS_TABLE_NAME + " SET finalGrade = ? WHERE username = ?");

                updateUsersTable.setString(2, username);
                updateUsersTable.setDouble(1, grade);

                updateUsersTable.executeUpdate();
            }

            LocalDateTime localDateTime = LocalDateTime.now();
            PreparedStatement insertGradesTable = connection.prepareStatement(
                    "INSERT INTO " + OpeningScreen.DB_GRADES_TABLE_NAME + " (username,grade,time, date) VALUES (?, ?, ?, ?)");

            insertGradesTable.setString(1, username);
            insertGradesTable.setDouble(2, grade);
            insertGradesTable.setString(3, localDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            insertGradesTable.setString(4, localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            insertGradesTable.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static boolean exist(String username){
        try {
            Connection connection = DriverManager.getConnection(
                    OpeningScreen.DB_URL,
                    OpeningScreen.DB_USERNAME,
                    OpeningScreen.PASSWORD
            );

            PreparedStatement checkUserExist = connection.prepareStatement(
                    "SELECT * FROM " + OpeningScreen.DB_USERS_TABLE_NAME + " WHERE USERNAME = ?");
            checkUserExist.setString(1 , username);
            ResultSet resultSet = checkUserExist.executeQuery();
            if (!resultSet.isBeforeFirst()){
                return false;
            }

        }catch (
                SQLException e){
            e.printStackTrace();
        }

        return true;
    }


    public static void show(String name) {
        try {
            Connection connection = DriverManager.getConnection(
                    OpeningScreen.DB_URL,
                    OpeningScreen.DB_USERNAME,
                    OpeningScreen.PASSWORD
            );

            PreparedStatement showTogether = connection.prepareStatement(
                    "SELECT users.username, grades.grade, grades.time, grades.date " +
                         "FROM " + OpeningScreen.DB_USERS_TABLE_NAME + " users " +
                         "JOIN " + OpeningScreen.DB_GRADES_TABLE_NAME + " grades " +
                         "ON users.username = grades.username " +
                         "WHERE users.username = ?"
            );
            showTogether.setString(1, name);

            resultSpecialSet = showTogether.executeQuery();

            strings = new ArrayList<>();
            grades = new ArrayList<>();
            times = new ArrayList<>();
            dates = new ArrayList<>();

            while (resultSpecialSet.next()) {
                String username = resultSpecialSet.getString("username");
                double grade = resultSpecialSet.getDouble("grade");
                String time = resultSpecialSet.getString("time");
                String date = resultSpecialSet.getString("date");



                strings.add(username);
                grades.add(grade);
                times.add(time);
                dates.add(date);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void showUpdate(String name) {
        try {
            Connection connection = DriverManager.getConnection(
                    OpeningScreen.DB_URL,
                    OpeningScreen.DB_USERNAME,
                    OpeningScreen.PASSWORD
            );

            PreparedStatement showTogether = connection.prepareStatement(
                    "SELECT users.username, grades.grade, grades.time, grades.date " +
                            "FROM " + OpeningScreen.DB_USERS_TABLE_NAME + " users " +
                            "JOIN " + OpeningScreen.DB_GRADES_TABLE_NAME + " grades " +
                            "ON users.username = grades.username " +
                            "WHERE users.username = ? ORDER BY grades.date DESC, grades.time DESC LIMIT 1"
            );
            showTogether.setString(1, name);

            resultSpecialSet = showTogether.executeQuery();

            while (resultSpecialSet.next()) {
                String username = resultSpecialSet.getString("username");
                double grade = resultSpecialSet.getDouble("grade");
                String time = resultSpecialSet.getString("time");
                String date = resultSpecialSet.getString("date");


//                if (strings == null) {
//                    strings = new ArrayList<>();
//                    grades = new ArrayList<>();
//                    times = new ArrayList<>();
//                    dates = new ArrayList<>();
//                }

                strings.add(username);
                grades.add(grade);
                times.add(time);
                dates.add(date);

                System.out.println(grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static List<String> getStrings() {
        return strings;
    }

    public static List<Double> getGrades() {
        return grades;
    }

    public static List<String> getTimes() {
        return times;
    }

    public static List<String> getDates() {
        return dates;
    }

}
