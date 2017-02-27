package com.goeuro.examination.serialization.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookPOJO {

    private String title;
    private String isbnNumber;
    private String[] authors;
    private String description;

}
