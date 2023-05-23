package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        write();
        read();

    }

    public static void write() {
        Connection connection = null;
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/day5", "root", "1");

            PreparedStatement preparedStatement = connection.prepareStatement("insert into product values(?,?,?,?)");


            for (int i = 0; i < 1000000; i++) {
                preparedStatement.setInt(1, i + 100);
                preparedStatement.setString(2, "name" + i);
                preparedStatement.setInt(3,i);
                preparedStatement.setInt(4, 1);
                preparedStatement.executeUpdate();
            }

            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("select * from product");

            String name;
            while (resultSet.next()) {
                name = resultSet.getString("name");
                System.out.println(name);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public static void read() {

        Connection connection = null;
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/day5", "root", "1");


            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("select * from product");

            String name;
            while (resultSet.next()) {
                name = resultSet.getString("name");
                System.out.println(name);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}