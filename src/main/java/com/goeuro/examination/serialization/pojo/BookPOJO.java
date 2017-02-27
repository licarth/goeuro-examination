package com.goeuro.examination.serialization.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookPOJO {

    //title;isbnNumber;authors;description

    private String title;
    private String isbnNumber;
    private AuthorPOJO[] authors;
    private String description;

}
