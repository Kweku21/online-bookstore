package org.app.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class Order {
    private Integer id;
    private Integer cost;
    private Integer quantity;
    private Book book;
    private User user;

    public String[] toCustomerString(){
        return new String[]{String.valueOf(id), book.getTitle(), String.valueOf(quantity), String.valueOf(cost)};
    }
}
