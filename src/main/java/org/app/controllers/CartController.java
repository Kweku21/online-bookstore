package org.app.controllers;

import lombok.AllArgsConstructor;
import org.app.config.DBConnector;
import org.app.data.Book;
import org.app.data.Cart;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartController {

    private final DBConnector connector;
    private final BookController bookController;

    public CartController() {
        connector = new DBConnector();
        bookController = new BookController();
    }

    public List<Cart> getCustomerShoppingCart(Integer customer_id){
        String query = "select c.id, b.title, b.description, c.quantity, s.name status, (b.price * c.quantity) cost\n" +
                        "from cart c\n" +
                        "join book b on b.id = c.book_id\n" +
                        "join status s on s.id = c.status_id\n" +
                        "where c.status_id =1 and c.user_id = %s;".formatted(customer_id);

        List<Cart> carts = new ArrayList<>();

        try {
            ResultSet resultSet = connector.runSQLStatement(query);

            while (resultSet.next()){
                carts.add(
                        Cart.builder()
                                .id(resultSet.getInt("id"))
                                .book(Book.builder().title(resultSet.getString("title")).description(resultSet.getString("description")).build())
                                .status(resultSet.getString("status"))
                                .quantity(resultSet.getInt("quantity"))
                                .cost(resultSet.getInt("cost"))
                                .build()
                );
            }

        }catch (Exception exception){
            System.out.println(exception);
        }

        return carts;
    }

    public Cart getShoppingCart(Integer id){
        String query = "select c.id, b.title, b.description, c.quantity, s.name status, (b.price * c.quantity) cost\n" +
                "from cart c\n" +
                "join book b on b.id = c.book_id\n" +
                "join status s on s.id = c.status_id\n" +
                "where c.status_id =1 and c.id = %s;".formatted(id);
        Cart cart = null;
        try {
            ResultSet resultSet = connector.runSQLStatement(query);

            while (resultSet.next()){
                      cart = Cart.builder()
                                .id(resultSet.getInt("id"))
                                .book(Book.builder().title(resultSet.getString("title")).description(resultSet.getString("description")).build())
                                .status(resultSet.getString("status"))
                                .quantity(resultSet.getInt("quantity"))
                                .cost(resultSet.getInt("cost"))
                                .build();

            }

        }catch (Exception exception){
            System.out.println(exception);
        }

        return cart;
    }

    public void createShoppingCart(Integer book_id, Integer user_id, Integer quantity){
        String query = "INSERT INTO cart (book_id, quantity, status_id, user_id) VALUES\n" +
                "(%s, %s, %s, %s);".formatted(book_id, quantity, 1, user_id);

        try {
            connector.runSQLStatement(query);
        }catch (Exception exception){
            System.out.println(exception);
        }
    }

    public void updateShoppingCart(Integer cart_id, Integer quantity){
        String query = "update cart set quantity = quantity + %s where id = %s;".formatted(quantity, cart_id);

        try {
            connector.runSQLStatement(query);
        }catch (Exception exception){
            System.out.println(exception);
        }
    }

    public void processShoppingCart(Integer cart_id){
        String query = "update cart set status_id=2 where id = %s;".formatted(cart_id);

        try {
            connector.runSQLStatement(query);
            query = "select * from cart where id=%s".formatted(cart_id);
            ResultSet resultSet = connector.runSQLStatement(query);

            while (resultSet.next()){
                Integer user_id = resultSet.getInt("user_id"),
                        quantity = resultSet.getInt("quantity"), book_id = resultSet.getInt("book_id");
                Book book = bookController.getBook(book_id);

                query = "INSERT INTO orders (status_id, user_id, book_id, quantity, order_date, cost) VALUES" +
                        "(3, %s, %s, %s, NOW(), %s)".formatted(user_id, book_id, quantity, quantity*book.getPrice());
                connector.runSQLStatement(query);
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
    }
}
