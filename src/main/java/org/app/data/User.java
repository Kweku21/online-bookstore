package org.app.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class User {

    private Integer id;
    private String email;
    private String name;
    private String type;
    private String phone;
    private String address;
}
