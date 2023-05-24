package org.app.view.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.data.User;

import java.awt.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContentPanel extends Panel {
    private User user;
}
