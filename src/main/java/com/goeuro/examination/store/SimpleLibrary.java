package com.goeuro.examination.store;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import com.goeuro.examination.model.Author;
import com.goeuro.examination.model.Publication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

public class SimpleLibrary implements Library{


    //isbns are unique
    private final HashMap<String, Publication> publicationsByIsbn = new HashMap<>();
    //authors can have multiple publications
    private final Multimap<String, Publication> publicationsByAuthorEmail = ArrayListMultimap.create();
    private final List<Author> authors = new ArrayList<>();

    @Override
    public List<Publication> getPublicationsSortedByTitle() {
        return publicationsByIsbn.values().stream()
                .sorted((p1, p2) -> p1.getTitle().compareTo(p2.getTitle()))
                .collect(Collectors.toList());
    }

    @Override
    public Publication getPublicationByIsbn(String isbn) {
        return publicationsByIsbn.get(isbn);
    }

    @Override
    public List<Publication> getPublicationsByAuthor(String authorEmail) {
        return unmodifiableList(publicationsByAuthorEmail.get(authorEmail).stream().collect(Collectors.toList()));
    }

    @Override
    public void insert(Publication publication) {
        this.publicationsByIsbn.put(publication.getIsbn(), publication);
        publication.getAuthors()
                .forEach(authorEmail -> publicationsByAuthorEmail.put(authorEmail, publication));
    }

    @Override
    public void insert(Author author) {
        this.authors.add(author);
    }
}
