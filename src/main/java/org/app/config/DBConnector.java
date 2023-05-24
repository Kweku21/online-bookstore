package org.app.config;

import java.sql.*;

public class DBConnector {

    private Connection connection;

    public DBConnector() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://dbms-project-kweku-b018.aivencloud.com:22359/bookstore", "avnadmin", "AVNS_Hw6hL6PXQE_RomNJ2Ti");
        // connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "rootpass");

        }catch (Exception exception){
            System.out.println(exception);
            connection = null;
        }

    }

    public ResultSet runSQLResultStatement(String query){

        try{
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        }catch (Exception exception){
            System.out.println(exception);

            return null;
        }

    }

    public void runSQLExecuteStatement(String query){
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        }catch (Exception exception){
            System.out.println(exception);

        }
    }
}
