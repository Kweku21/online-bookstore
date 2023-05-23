package org.app.controllers;

import org.app.config.DBConnector;
import org.app.data.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserController {

    private final DBConnector connector;

    public UserController() {
        connector = new DBConnector();
    }

    public User loginUser(String email, String password) {

        String query = String.format("Select u.id, u.email, u.name, ut.type from user u " +
                "join user_type ut on ut.id = u.user_type_id where email='%s' and password='%s';", email, password);
        try {
            ResultSet resultSet = connector.runSQLStatement(query);
            User user = null;
            while (resultSet.next()) {
                user = User.builder()
                        .id(resultSet.getInt("id"))
                        .email(resultSet.getString("email"))
                        .name(resultSet.getString("name"))
                        .type(resultSet.getString("type"))
                        .build();

            }

            return user;
        } catch (Exception exception){
            System.out.println(exception);
            return null;
        }

    }


    public List<User> getCustomers(){
        String query = "select u.id, u.email, u.name, ut.type" +
                        "from user u" +
                        "join user_type ut on ut.id = u.user_type_id" +
                        "where ut.type = 'Customer';";

        List<User> customers = new ArrayList<>();
        try {
            ResultSet resultSet = connector.runSQLStatement(query);
            while (resultSet.next()) {
                customers.add(User.builder()
                        .id(resultSet.getInt("id"))
                        .email(resultSet.getString("email"))
                        .name(resultSet.getString("name"))
                        .type(resultSet.getString("type"))
                        .build());

            }
        }catch (Exception exception){
            System.out.println(exception);
        }

        return customers;
    }

    public User getCustomer(Integer id){
        String query = "select u.id, u.email, u.name, ut.type\n" +
                        "from user u\n" +
                        "join user_type ut on ut.id = u.user_type_id\n" +
                        "where u.id = "+id+";";

        User user = null;
        try {
            ResultSet resultSet = connector.runSQLStatement(query);
            while (resultSet.next()) {
                user = User.builder()
                        .id(resultSet.getInt("id"))
                        .email(resultSet.getString("email"))
                        .name(resultSet.getString("name"))
                        .type(resultSet.getString("type"))
                        .build();

            }
        }catch (Exception exception){
            System.out.println(exception);
        }

        return user;
    }


    public void createCustomer(String name, String phone, String address, String email, String password){
        // Create User
        String query = String.format("INSERT INTO user (email, password, name, user_type_id) VALUES" +
                        "('%s', '%s', '%s', 3);",email, password, name);

        try {
            ResultSet resultSet = connector.runSQLStatement(query);
            query = String.format("select id from user where email='%s'", email);
            resultSet = connector.runSQLStatement(query);
            while (resultSet.next()) {
                Integer user_id = resultSet.getInt("id");

                query = String.format("INSERT INTO user_contact (phone, address, user_id) VALUES" +
                        "('%s', '%s', %s);", phone, address, user_id);
                resultSet = connector.runSQLStatement(query);
            }

        }catch (Exception exception){
            System.out.println(exception);
        }

    }
}
