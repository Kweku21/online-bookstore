package org.app.view.customer_screen;

import org.app.controllers.BookController;
import org.app.data.User;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class CustomerView extends JFrame {
    private User user;
    private JPanel left,right;


    private ProfilePanel profilePanel;

    private BooksPanel booksPanel;

    private OrdersPanel ordersPanel;

    private JButton profile, books, orders;
    private BookController bookController;

    public CustomerView(User user) throws HeadlessException {
        this.user = user;
        this.bookController = bookController;

        this.left = new JPanel();
        this.right = new JPanel();
        this.profilePanel = new ProfilePanel(user);
        this.booksPanel = new BooksPanel(bookController);
        this.ordersPanel = new OrdersPanel();
        this.profile = new JButton("Profile");
        this.books = new JButton("Books");
        this.orders = new JButton("Orders");




        left.setLayout(new GridLayout(3,1));
        left.add(profile);
        left.add(books);
        left.add(orders);


        right.add(profilePanel);

        setLayout(new BorderLayout());
        add(left,BorderLayout.WEST);
        add(right,BorderLayout.CENTER);


        setTitle("User Management");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);


        profile.addActionListener((event) -> {
            right.remove(right.getComponent(0));
            right.add(profilePanel);
        });

        books.addActionListener((event) -> {
            right.remove(right.getComponent(0));
            right.add(booksPanel);
        });

        orders.addActionListener((event) -> {
            right.remove(right.getComponent(0));
            right.add(ordersPanel);
        });


    }
}
