package org.app.view.customer_screen;

import org.app.controllers.BookController;
import org.app.data.User;
import org.app.view.utils.ContentPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class CustomerView extends JFrame {
    private User user;
    private JPanel left,right;
    private ContentPanel contentPanel;
    private JButton profile, books, orders, cart;
    private BookController bookController;

    public CustomerView(User user) throws HeadlessException {
        this.user = user;
        this.bookController = bookController;

        this.left = new JPanel();
        this.right = new JPanel();

        contentPanel = new ProfilePanel(user);
        this.profile = new JButton("Profile");
        this.books = new JButton("Books");
        this.orders = new JButton("Orders");
        this.cart = new JButton("Shopping Cart");


        left.setLayout(new GridLayout(4,1));
        left.add(profile);
        left.add(books);
        left.add(cart);
        left.add(orders);


        right.add(contentPanel);

        setLayout(new BorderLayout());
        add(left,BorderLayout.WEST);
        add(right,BorderLayout.CENTER);


        setTitle("User Management");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);


        profile.addActionListener((event) -> {
            addPanel(new ProfilePanel(user));
        });

        books.addActionListener((event) -> {
            addPanel(new BooksPanel(user));
        });

        cart.addActionListener((event) -> {
            addPanel(new CartPanel(user));
        });

        orders.addActionListener((event) -> {
            addPanel(new OrdersPanel(user));
        });
    }

    public void addPanel(ContentPanel panel){
        right.remove(right.getComponent(0));
        right.remove(contentPanel);

        right.add(panel);
        right.revalidate();
        right.repaint();
    }



}
