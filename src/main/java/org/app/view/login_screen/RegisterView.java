package org.app.view.login_screen;

import org.app.controllers.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class RegisterView extends JFrame {
    private JTextField name,email,phone,address;
    private JPasswordField password;
    private JButton create;
    private UserController userController;
    public RegisterView() throws HeadlessException {
        this.name = new JTextField(20);
        this.phone = new JTextField(20);
        this.address = new JTextField(20);
        this.email = new JTextField(20);
        this.password = new JPasswordField(20);
        this.create = new JButton("create");
        this.userController = new UserController();

        setLayout(new GridLayout(6,2));
        add(new JLabel("name"));
        add(name);
        add(new JLabel("email"));
        add(email);
        add(new JLabel("new password"));
        add(password);
        add(new JLabel("phone"));
        add(phone);
        add(new JLabel("address"));
        add(address);
        add(create);

        setTitle("User Registration");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        create.addActionListener((event)->{
            userController.createCustomer(name.getText(),phone.getText(),address.getText(),email.getText(),password.getText());

            WindowEvent closingEvent = new WindowEvent(this, WindowEvent. WINDOW_CLOSING);
            Toolkit.getDefaultToolkit();
        });

    }
}
