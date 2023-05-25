package org.app.view.login_screen;


import org.app.controllers.UserController;
import org.app.data.User;
import org.app.view.admin_screen.AdminView;
import org.app.view.customer_screen.CustomerView;

import javax.swing.*;
import java.awt.*;


// UserView.java
public class AuthView extends JFrame {


    UserController userController = new UserController();
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton createButton;

    public AuthView() {
        this.usernameField = new JTextField(20);
        this.passwordField = new JPasswordField(20);
        /*
        this.roleComboBox = new JComboBox<>();
        UserRole[] roles = UserRole.values();

        for (UserRole role : roles) {
            roleComboBox.addItem(role);
        }

         */
        this.loginButton = new JButton("login");
        this.createButton = new JButton("Create Account");
        this.createButton.addActionListener((event)->{
            new RegisterView();


        });
        this.loginButton.addActionListener((event) -> {
            String username = getUsername();
            String password = getPassword();


            // Perform validation and create a new user
            //System.out.println(username);
            User user = userController.loginUser(username, password);

            clearInput();

            if (user == null) {
                showErrorMessage();
                return;
            }


            switch (user.getType().toUpperCase()) {

                case "ADMIN" -> {
                    setVisible(false);
                    AdminView adminView = new AdminView(user);
                    adminView.setVisible(true);
                }


                case "CUSTOMER" -> {
                    setVisible(false);
                    CustomerView customerView = new CustomerView(user);
                    customerView.setVisible(true);
                }

            }
        });
        // Set up the layout
        setLayout(new FlowLayout());
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        // add(roleComboBox);
        add(loginButton);
        add(createButton);
        // Set up frame properties
        setTitle("User Management");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        /*String currDirectory = System.getProperty("user.dir");
        String pathToImage =
        ImageIcon imageIcon = new ImageIcon("path/to/your/image.png");
        JLabel imageLabel = new JLabel(imageIcon);
        add(imageLabel, BorderLayout.NORTH);

         */
        setVisible(true);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void showSuccessMessage() {
        JOptionPane.showMessageDialog(this, "User added successfully!");
    }

    public void showErrorMessage() {
        JOptionPane.showMessageDialog(this, "Wrong Credentials");
    }

    private void clearInput() {
        usernameField.setText("");
        passwordField.setText("");
    }


}



