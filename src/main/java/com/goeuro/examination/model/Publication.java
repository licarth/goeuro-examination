package com.goeuro.examination.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Publication {

    private PublicationType publicationType;
    private List<String> authors;
    private String title;
    private String isbn;
    private String description;
    private LocalDate publishedAt;

}
