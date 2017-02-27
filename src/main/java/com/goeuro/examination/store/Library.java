package com.goeuro.examination.store;

import com.goeuro.examination.model.Author;
import com.goeuro.examination.model.Publication;

import java.util.List;

public interface Library {

    List<Publication> getPublicationsSortedByTitle();
    Publication getPublicationByIsbn(String isbn);
    List<Publication> getPublicationsByAuthor(String isbn);

    void insert(Publication publication);
    void insert(Author author);


}
