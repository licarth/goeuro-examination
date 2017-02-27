package com.goeuro.examination.store;

import com.goeuro.examination.model.Publication;

import java.util.List;

interface Store {

    List<Publication> getPublicationsSortedByTitle();
    List<Publication> getPublicationsByIsbn(String isbn);
    List<Publication> getPublicationsByAuthor(String isbn);

    void insert(Publication publication);


}
