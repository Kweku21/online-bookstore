package org.app;

import org.app.controllers.UserController;

public class Main {



    public static void main(String[] args) {
        UserController userController = new UserController();
        Object obj = userController.loginUser("john@example.com", "password123");
        System.out.println(obj);
    }
}
