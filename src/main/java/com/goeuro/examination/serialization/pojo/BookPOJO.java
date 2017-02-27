package com.goeuro.examination.serialization.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookPOJO {

    private String title;
    private String isbnNumber;
    private List<String> authors;
    private String description;

}
