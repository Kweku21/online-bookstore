package org.app.view.customer_screen;

import org.app.controllers.BookController;
import org.app.data.Book;
import org.app.data.User;
import org.app.view.utils.ContentPanel;


import javax.swing.*;

import java.awt.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BooksPanel extends ContentPanel {
    private List<Book> books;
    private List<Book> searchResults;

    private Choice bookList;
    private Button getAllBooksButton;
    private Button searchBooksButton;
    private TextField searchField;
    private User user;

    private BookController bookController;

    public BooksPanel(User user, BookController bookController) {
        this.user = user;
        this.bookController = bookController;
        setLayout(new BorderLayout());

        bookList = new Choice();
        add(bookList, BorderLayout.CENTER);

        Panel buttonPanel = new Panel(new FlowLayout());
        getAllBooksButton = new Button("Get All Books");
        searchBooksButton = new Button("Search Books");
        searchField = new TextField(15);

        buttonPanel.add(getAllBooksButton);
        buttonPanel.add(searchField);
        buttonPanel.add(searchBooksButton);

        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);

        getAllBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getAllBooks();
            }
        });

        searchBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBooks();
            }
        });
    }

    private void getAllBooks() {
        books = bookController.getAllBooks();
        refreshBookList(books);
    }

    private void searchBooks() {
        String keyword = searchField.getText();
        searchResults = bookController.searchBooks(keyword);
        refreshBookList(searchResults);
    }

    private void refreshBookList(List<Book> bookList) {
        this.bookList.removeAll();
        for (Book book : bookList) {
            this.bookList.add(book.toString());
        }
    }


}