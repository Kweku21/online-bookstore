package org.app.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Cart {
    private Integer id;
    private Integer cost;
    private String status;
    private Book book;
    private Integer quantity;
}
