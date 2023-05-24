package org.app;

import org.app.data.User;
import org.app.view.login_screen.AuthView;
import org.app.view.customer_screen.CustomerView;

public class Main {

    public static void main(String[] args) {
        /*
        UserController userController = new UserController();
        Object obj = userController.loginUser("john@example.com", "password123");
        System.out.println(obj);

         */

        new CustomerView(new User(1, "john@example.com", "John Do", "Customer"));
        //new AuthView();

    }
}
