package org.app.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Book {
    private Integer id;
    private String title;
    private String description;
    private Integer price;
    private Integer quantity;
    private String genre;
    private String author;
}
