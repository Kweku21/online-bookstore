package org.app.controllers;

import lombok.AllArgsConstructor;
import org.app.config.DBConnector;
import org.app.data.Book;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class BookController {
    private final DBConnector connector;

    public List<Book> getAllBooks(){
        String query = "select b.title, b.description, b.price, b.quantity, bg.name genre, a.name author from book b" +
                        "join book_genre bg on bg.id = b.genre_id"+
                        "join book_genre bg on bg.id = b.genre_id" +
                        "join book_author ba on b.id = ba.book_id" +
                        "join author a on a.id = ba.author_id" +
                        "order by b.id desc;";

        List<Book> books = new ArrayList<>();

        try {
            ResultSet resultSet = connector.runSQLStatement(query);

            while (resultSet.next()){
                books.add(
                        Book.builder()
                                .id(resultSet.getInt("id")).title(resultSet.getString("title"))
                                .description(resultSet.getString("description")).price(resultSet.getInt("price"))
                                .quantity(resultSet.getInt("quantity")).genre(resultSet.getString("genre"))
                                .author(resultSet.getString("author"))
                                .build()
                );
            }

        }catch (Exception exception){
            System.out.println(exception);
        }

        return books;
    }

    public Book getBook(Integer id){

        String query = String.format("select b.id, b.title, b.description, b.price, b.quantity, bg.name genre, a.name author" +
                                    "from book b" +
                                    "join book_genre bg on bg.id = b.genre_id" +
                                    "join book_author ba on b.id = ba.book_id" +
                                    "join author a on a.id = ba.author_id" +
                                    "where b.id = %s;", id);
        Book book = null;

        try {
            ResultSet resultSet = connector.runSQLStatement(query);

            while (resultSet.next()){
                       book =  Book.builder()
                                .id(resultSet.getInt("id")).title(resultSet.getString("title"))
                                .description(resultSet.getString("description")).price(resultSet.getInt("price"))
                                .quantity(resultSet.getInt("quantity")).genre(resultSet.getString("genre"))
                                .author(resultSet.getString("author"))
                                .build();

            }

        }catch (Exception exception){
            System.out.println(exception);
        }

        return  book;
    }

    public void createBook(String title, String description, Integer price, Integer quantity, Integer genre_id, List<String> authors){



    }

    public List<Book> searchBooks(String keyword){
        String query = "select  b.id, b.title, b.description, b.price, b.quantity, bg.name genre, a.name author" +
                "from book b" +
                "join book_genre bg on bg.id = b.genre_id" +
                "join author a on b.id = a.book_id" +
                "where b.title like '%" + keyword + "%'" +
                "or b.description like '%" + keyword + "%'" +
                "or a.name like '%" + keyword + "%'" +
                "order by b.id desc ;";

        List<Book> books = new ArrayList<>();

        try {
            ResultSet resultSet = connector.runSQLStatement(query);
            while (resultSet.next()){
                books.add(
                        Book.builder()
                                .id(resultSet.getInt("id")).title(resultSet.getString("title"))
                                .description(resultSet.getString("description")).price(resultSet.getInt("price"))
                                .quantity(resultSet.getInt("quantity")).genre(resultSet.getString("genre"))
                                .author(resultSet.getString("author"))
                                .build()
                );
            }

        }catch (Exception exception){
            System.out.println(exception);
        }

        return books;


    }

}
