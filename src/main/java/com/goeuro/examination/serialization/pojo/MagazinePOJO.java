package com.goeuro.examination.serialization.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDate;
import java.util.List;

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
    @JsonDeserialize(using = CommaSeparatedListDeserializer.class)
    private List<String> authors;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd.MM.yyyy", timezone="CET")
    private LocalDate publishedAt;

}
