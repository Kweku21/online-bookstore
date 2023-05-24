package org.app.view.customer_screen;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.data.User;
import org.app.view.utils.ContentPanel;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends ContentPanel {
    private JTextField name,phone, address,email;
    private JPasswordField password;
    private JButton update;

    public ProfilePanel(User user) {
        super(user);

        this.name = new JTextField(20);
        this.phone = new JTextField(20);
        this.address = new JTextField(20);
        this.email = new JTextField(20);
//        this.password = new JPasswordField(20);
        this.update = new JButton("update");

        this.name.setText(user.getName());
        this.email.setText(user.getEmail());
        this.address.setText(user.getAddress());
        this.phone.setText(user.getPhone());

        setLayout(new GridLayout(6,2));
        add(new JLabel("name"));
        add(name);
        add(new JLabel("email"));
        add(email);
//        add(new JLabel("new password"));
//        add(password);
        add(new JLabel("phone"));
        add(phone);
        add(new JLabel("address"));
        add(address);
        add(update);

        setSize(100,100);


    }
}
