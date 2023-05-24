package org.app.view.customer_screen;

import lombok.Data;
import org.app.controllers.OrderController;
import org.app.data.User;
import org.app.view.utils.ContentPanel;

import java.awt.*;

@Data
public class OrdersPanel extends ContentPanel {
    private Choice orderList;
    private OrderController orderController;
    public OrdersPanel(User user){
        super(user);
        orderController = new OrderController();

        setLayout(new BorderLayout());

        orderList = new Choice();
        orderController.getCustomerOrders(user.getId()).forEach(order -> {
            orderList.add(order.toString());
        });
        add(orderList, BorderLayout.CENTER);

        Panel buttonPanel = new Panel(new FlowLayout());

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

}
