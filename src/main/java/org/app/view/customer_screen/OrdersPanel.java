package org.app.view.customer_screen;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.app.data.User;
import org.app.view.utils.ContentPanel;

import javax.swing.*;

@Data
public class OrdersPanel extends ContentPanel {

    public OrdersPanel(User user){
        super(user);
        System.out.println(user);
    }

}
