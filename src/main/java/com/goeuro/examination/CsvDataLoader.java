package com.goeuro.examination;

import com.goeuro.examination.model.Author;
import com.goeuro.examination.model.Publication;
import com.goeuro.examination.serialization.FeedReader;
import com.goeuro.examination.store.Library;

import java.io.IOException;
import java.net.URISyntaxException;

import lombok.RequiredArgsConstructor;
import rx.Observable;

import static com.goeuro.examination.model.PublicationType.BOOK;
import static com.goeuro.examination.model.PublicationType.MAGAZINE;

/**
 * Service that initally loads Data from POJOs.
 */
@RequiredArgsConstructor
public class CsvDataLoader {

    private final FeedReader feedReader;
    private final Library library;

    public Observable<Void> loadCsvResources() throws URISyntaxException, IOException {
        //Read authors first, cause books and magazines reference them.
        return Observable.merge(
                loadAuthors(),
                loadBooks(),
                loadMagazines()
        );
    }

    private Observable<Void> loadAuthors() throws IOException, URISyntaxException {
        return feedReader
                .readAuthors(getClass().getClassLoader().getResource("com/goeuro/examination/data/authors.csv").toURI())
                .doOnNext(a -> library.insert(Author.builder()
                        .email(a.getEmail())
                        .firstName(a.getFirstname())
                        .lastName(a.getLastname())
                        .build()
                ))
                .flatMap(a -> Observable.<Void>empty());
    }

    private Observable<Void> loadBooks() throws IOException, URISyntaxException {
        return feedReader
                .readBooks(getClass().getClassLoader().getResource("com/goeuro/examination/data/books.csv").toURI())
                .doOnNext(b -> library.insert(
                        Publication.builder()
                                .publicationType(BOOK)
                                .authors(b.getAuthors())
                                .isbn(b.getIsbnNumber())
                                .title(b.getTitle())
                                .description(b.getDescription())
                                .build()
                ))
                .flatMap(a -> Observable.<Void>empty());
    }


    private Observable<Void> loadMagazines() throws IOException, URISyntaxException {
        return feedReader
                .readMagazines(getClass().getClassLoader().getResource("com/goeuro/examination/data/magazines.csv").toURI())
                .doOnNext(m -> library.insert(
                        Publication.builder()
                                .publicationType(MAGAZINE)
                                .authors(m.getAuthors())
                                .isbn(m.getIsbnNumber())
                                .title(m.getTitle())
                                .publishedAt(m.getPublishedAt())
                                .build()
                ))
                .flatMap(a -> Observable.<Void>empty());
    }

}
