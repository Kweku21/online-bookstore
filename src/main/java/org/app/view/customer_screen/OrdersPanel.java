package org.app.view.customer_screen;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.app.controllers.OrderController;
import org.app.data.Order;
import org.app.data.User;
import org.app.view.utils.ContentPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrdersPanel extends ContentPanel {

    JTable table;
    private List<Order> orders;

    private Choice orderList;
    private Button getAllBooksButton;
    private Button searchBooksButton;
    private TextField searchField;
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
