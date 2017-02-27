package com.goeuro.examination.model;

import java.util.List;

import lombok.Data;

@Data
public class Publication {

    private PublicationType publicationType;
    private List<Author> authors;
    private String title;
    private String isbn;

}
