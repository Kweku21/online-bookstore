package org.app.view.customer_screen;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.app.controllers.OrderController;
import org.app.data.Order;
import org.app.data.User;
import org.app.view.utils.ContentPanel;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrdersPanel extends ContentPanel {

    JTable table;

    private OrderController orderController;
    public OrdersPanel(User user){
        super(user);
        orderController = new OrderController();

        List<Order> orders = orderController.getCustomerOrders(user.getId());
        String[][] data = new String[orders.size()][];
        for (int i = 0; i < orders.size(); i++) {
            data[i] = orders.get(i).toCustomerString();
        }

        String[] columnNames = { "Id","Book Title", "Quantity", "Cost" };

        System.out.println(Arrays.deepToString(data));

        table = new JTable(data, columnNames);
        table.setBounds(30, 40, 300, 300);
//        JScrollPane scrollPane = new JScrollPane(table);
        add(table);

        setSize(1500,1000);
    }

}
