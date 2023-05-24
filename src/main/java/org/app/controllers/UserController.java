package org.app.controllers;

import org.app.config.DBConnector;
import org.app.data.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class UserController {

    private final DBConnector connector;

    public UserController() {
        connector = new DBConnector();
    }

    public User loginUser(String email, String password) {

        String query = "Select u.id, u.email, u.name, ut.type, uc.phone, uc.address \n" +
                        "from user u \n" +
                        "join user_type ut on ut.id = u.user_type_id \n" +
                        "join user_contact uc on u.id = uc.user_id \n"+
                        "where email='%s' and password='%s';".formatted(email, password);
        User user = null;
        try {
            ResultSet resultSet = connector.runSQLResultStatement(query);
            while (resultSet.next()) {
                user = User.builder()
                        .id(resultSet.getInt("id"))
                        .email(resultSet.getString("email"))
                        .name(resultSet.getString("name"))
                        .type(resultSet.getString("type"))
                        .address(resultSet.getString("address"))
                        .phone(resultSet.getString("phone"))
                        .build();

            }

            return user;
        } catch (Exception exception){
            System.out.println(exception);

        }
        return null;

    }


    public List<User> getCustomers(){
        String query = "select u.id, u.email, u.name, ut.type, uc.phone, uc.address \n" +
                        "from user u \n" +
                        "join user_contact uc on u.id = uc.user_id \n"+
                        "join user_type ut on ut.id = u.user_type_id \n" +
                        "where ut.type = 'Customer';";

        List<User> customers = new ArrayList<>();
        try {
            ResultSet resultSet = connector.runSQLResultStatement(query);
            while (resultSet.next()) {
                customers.add(User.builder()
                        .id(resultSet.getInt("id"))
                        .email(resultSet.getString("email"))
                        .name(resultSet.getString("name"))
                        .type(resultSet.getString("type"))
                        .address(resultSet.getString("address"))
                        .phone(resultSet.getString("phone"))
                        .build());

            }
        }catch (Exception exception){
            System.out.println(exception);
        }

        return customers;
    }

    public User getCustomer(Integer id){
        String query = "select u.id, u.email, u.name, ut.type, uc.phone, uc.address\n" +
                        "from user u\n" +
                        "join user_type ut on ut.id = u.user_type_id\n" +
                        "join user_contact uc on u.id = uc.user_id \n"+
                        "where u.id = "+id+";";

        User user = null;
        try {
            ResultSet resultSet = connector.runSQLResultStatement(query);
            while (resultSet.next()) {
                user = User.builder()
                        .id(resultSet.getInt("id"))
                        .email(resultSet.getString("email"))
                        .name(resultSet.getString("name"))
                        .type(resultSet.getString("type"))
                        .address(resultSet.getString("address"))
                        .phone(resultSet.getString("phone"))
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
            connector.runSQLExecuteStatement(query);
            query = String.format("select id from user where email='%s'", email);
            ResultSet resultSet = connector.runSQLResultStatement(query);
            while (resultSet.next()) {
                Integer user_id = resultSet.getInt("id");

                query = String.format("INSERT INTO user_contact (phone, address, user_id) VALUES" +
                        "('%s', '%s', %s);", phone, address, user_id);
                connector.runSQLExecuteStatement(query);
            }

        }catch (Exception exception){
            System.out.println(exception);
        }

    }
}
