package org.app.controllers;

import org.app.config.DBConnector;
import org.app.data.Book;
import org.app.data.Order;
import org.app.data.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderController {

    private final DBConnector connector;

    public OrderController() {
        this.connector = new DBConnector();
    }

    public List<Order> getCustomerOrders(Integer customer_id){
        String query = "select o.cost, o.quantity, o.id, u.name, u.email, b.title, b.description\n" +
                "from orders o\n" +
                "join book b on b.id = o.book_id\n" +
                "join user u on o.user_id = u.id\n" +
                "where user_id = %s;".formatted(customer_id);

        List<Order> orders = new ArrayList<>();

        try {
            ResultSet resultSet = connector.runSQLResultStatement(query);

            while (resultSet.next()){
                orders.add(
                        Order.builder()
                                .cost(resultSet.getInt("cost"))
                                .quantity(resultSet.getInt("quantity"))
                                .id(resultSet.getInt("id"))
                                .book(Book.builder().title(resultSet.getString("title"))
                                        .description(resultSet.getString("description")).build())
                                .user(User.builder().name(resultSet.getString("name"))
                                        .email(resultSet.getString("email")).build())
                                .build()
                );

            }

        }catch (Exception exception){
            System.out.println(exception);
        }

        return orders;
    }

    public List<Order> getAllOrders(){
        String query = "select o.cost, o.quantity, o.id, u.name, u.email, b.title, b.description\n" +
                "from orders o\n" +
                "join book b on b.id = o.book_id\n" +
                "join user u on o.user_id = u.id;\n";

        List<Order> orders = new ArrayList<>();

        try {
            ResultSet resultSet = connector.runSQLResultStatement(query);

            while (resultSet.next()){
                orders.add(
                        Order.builder()
                                .cost(resultSet.getInt("cost"))
                                .quantity(resultSet.getInt("quantity"))
                                .id(resultSet.getInt("id"))
                                .book(Book.builder().title(resultSet.getString("title"))
                                        .description(resultSet.getString("description")).build())
                                .user(User.builder().name(resultSet.getString("name"))
                                        .email(resultSet.getString("email")).build())
                                .build()
                );

            }

        }catch (Exception exception){
            System.out.println(exception);
        }

        return orders;
    }

    public Order getOrder(Integer order_id){
      String query = "select o.cost, o.quantity, o.id, u.name, u.email, b.title, b.description\n" +
              "from orders o\n" +
              "join book b on b.id = o.book_id\n" +
              "join user u on o.user_id = u.id\n" +
              "where o.id = %s;".formatted(order_id);

      Order order = null;

        try {
            ResultSet resultSet = connector.runSQLResultStatement(query);

            while (resultSet.next()){
                order = Order.builder()
                                .cost(resultSet.getInt("cost"))
                                .quantity(resultSet.getInt("quantity"))
                                .id(resultSet.getInt("id"))
                                .book(Book.builder().title(resultSet.getString("title"))
                                        .description(resultSet.getString("description")).build())
                                .user(User.builder().name(resultSet.getString("name"))
                                        .email(resultSet.getString("email")).build())
                                .build();

            }

        }catch (Exception exception){
            System.out.println(exception);
        }

      return order;
    }

}
