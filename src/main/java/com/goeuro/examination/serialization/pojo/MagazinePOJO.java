package com.goeuro.examination.serialization.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MagazinePOJO {

    //title;isbnNumber;authors;publishedAt

    private String title;
    private String isbnNumber;
    private String[] authors;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd.MM.yyyy", timezone="CET")
    private Date publishedAt;
    
}
