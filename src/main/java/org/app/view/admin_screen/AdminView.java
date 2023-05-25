package org.app.view.admin_screen;

import org.app.controllers.BookController;
import org.app.data.User;
import org.app.view.customer_screen.BooksPanel;
import org.app.view.customer_screen.OrdersPanel;
import org.app.view.customer_screen.ProfilePanel;
import org.app.view.utils.ContentPanel;

import javax.swing.*;
import java.awt.*;

        import org.app.controllers.BookController;
        import org.app.data.User;
        import org.app.view.utils.ContentPanel;

        import javax.swing.*;
        import java.awt.*;
        import java.util.Optional;

public class AdminView extends JFrame {
    private User user;
    private JPanel left,right;

    //private ProfilePanel profilePanel;

    private BooksPanel booksPanel;

    private OrdersPanel ordersPanel;
    private ContentPanel contentPanel;

    private JButton profile, books, orders,ordersTotal;
    private BookController bookController;

    public AdminView(User user) throws HeadlessException {
        this.user = user;
        this.bookController = bookController;

        this.left = new JPanel();
        this.right = new JPanel();

        contentPanel = new ProfilePanel(user);
//        this.profilePanel = new ProfilePanel(user);
//        this.booksPanel = new BooksPanel(bookController);
//        this.ordersPanel = new OrdersPanel();
        this.profile = new JButton("Profile");
        this.books = new JButton("Books");
        this.orders = new JButton("Customer Orders");
        this.ordersTotal = new JButton("Total Orders");


        left.setLayout(new GridLayout(4,1));
        left.add(profile);
        left.add(books);
        left.add(orders);
        left.add(ordersTotal);



        right.add(contentPanel);

        setLayout(new BorderLayout());
        add(left,BorderLayout.WEST);
        add(right,BorderLayout.CENTER);


        setTitle("Admin Management");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);


        profile.addActionListener((event) -> {
            right.remove(right.getComponent(0));
            right.remove(contentPanel);
            contentPanel = new ProfilePanel(user);
            right.add(contentPanel);
            right.revalidate();
            right.repaint();
        });

        books.addActionListener((event) -> {
            right.remove(right.getComponent(0));
            right.remove(contentPanel);

            contentPanel = new BooksPanel(user);
            right.add(contentPanel);
            right.revalidate();
            right.repaint();
        });

        orders.addActionListener((event) -> {
            right.remove(right.getComponent(0));
            right.remove(contentPanel);
            contentPanel = new OrdersPanel(user);
            right.add(contentPanel);
        });
        ordersTotal.addActionListener((event) -> {
            right.remove(right.getComponent(0));
            right.remove(contentPanel);
            contentPanel = new TotalOrderDetailsPanel(user);
            right.add(contentPanel);
        });
    }
}
