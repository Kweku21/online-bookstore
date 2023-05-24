package org.app.view.customer_screen;

import org.app.controllers.BookController;
import org.app.data.Book;


import javax.swing.*;

import java.awt.*;
import java.util.List;

    public class BooksPanel extends JFrame {
        private JList<Book> bookList;
        private DefaultListModel<Book> bookListModel;
        private JButton getAllBooksButton;
        private JButton searchBooksButton;
        private JTextField searchField;

        private BookController bookController;

        public BooksPanel(BookController bookController) {
            this.bookController = bookController;

            setTitle("Book Manager");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(400, 300);
            setLayout(new BorderLayout());



            bookListModel = new DefaultListModel<>();
            bookList = new JList<>(bookListModel);

            JScrollPane scrollPane = new JScrollPane(bookList);
            add(scrollPane, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel(new FlowLayout());
            getAllBooksButton = new JButton("Get All Books");
            searchBooksButton = new JButton("Search Books");
            searchField = new JTextField(15);

            buttonPanel.add(getAllBooksButton);
            buttonPanel.add(searchField);
            buttonPanel.add(searchBooksButton);

            add(buttonPanel, BorderLayout.SOUTH);

            getAllBooksButton.addActionListener(e -> getAllBooks());
            searchBooksButton.addActionListener(e -> searchBooks());

            setVisible(true);
        }

        private void getAllBooks() {
            List<Book> books = bookController.getAllBooks();
            refreshBookList(books);
        }

        private void searchBooks() {
            String keyword = searchField.getText();
            List<Book> books = bookController.searchBooks(keyword);
            refreshBookList(books);
        }

        private void refreshBookList(List<Book> books) {
            bookListModel.clear();
            for (Book book : books) {
                bookListModel.addElement(book);
            }
        }
}
