package org.app.view.customer_screen;

import org.app.controllers.CartController;
import org.app.data.User;
import org.app.view.utils.ContentPanel;

import java.awt.*;

public class CartPanel extends ContentPanel {

    private Choice cartList;
    private CartController cartController;

    public CartPanel(User user) {
        super(user);

        cartController = new CartController();

        setLayout(new BorderLayout());
        cartList = new Choice();

        cartController.getCustomerShoppingCart(user.getId()).forEach(cart ->
                cartList.add(cart.toString()));
        add(cartList, BorderLayout.CENTER);

        Panel buttonPanel = new Panel(new FlowLayout());

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
