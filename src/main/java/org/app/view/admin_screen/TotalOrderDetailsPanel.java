package org.app.view.admin_screen;

import org.app.controllers.OrderController;
import org.app.data.Order;
import org.app.data.User;
import org.app.view.customer_screen.OrdersPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TotalOrderDetailsPanel extends OrdersPanel {
    JTable table;
    private List<Order> orders;

    private Choice totalOrderList;

    private OrderController orderController;

    public TotalOrderDetailsPanel(User user) {
        super(user);
        OrderController orderController = new OrderController();

        setLayout(new BorderLayout());

        totalOrderList = new Choice();
        orderController.getAllOrders().forEach(order -> {
            totalOrderList.add(order.toString());
        });
        add(totalOrderList, BorderLayout.CENTER);

        Panel buttonPanel = new Panel(new FlowLayout());

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

}
