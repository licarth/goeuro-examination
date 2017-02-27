package com.goeuro.examination.serialization;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.goeuro.examination.serialization.pojo.AuthorPOJO;
import com.goeuro.examination.serialization.pojo.BookPOJO;
import com.goeuro.examination.serialization.pojo.MagazinePOJO;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import rx.Observable;

/**
 * Asynchronously reads from the file.
 */
public class CsvFeedReader implements FeedReader {

    @Override
    public Observable<MagazinePOJO> readMagazines(URI uri) throws IOException {
        MappingIterator<MagazinePOJO> it = read(uri, MagazinePOJO.class);

        return Observable
                .from(() -> it); //Turns an iterator into an iterable.
    }

    @Override
    public Observable<BookPOJO> readBooks(URI uri) throws IOException {
        MappingIterator<BookPOJO> it = read(uri, BookPOJO.class);

        return Observable
                .from(() -> it); //Turns an iterator into an iterable.
    }

    @Override
    public Observable<AuthorPOJO> readAuthors(URI uri) throws IOException {
        MappingIterator<AuthorPOJO> it = read(uri, AuthorPOJO.class);

        return Observable
                .from(() -> it); //Turns an iterator into an iterable.
    }

    private <T> MappingIterator<T> read(URI uri, Class<T> klass) throws IOException {
        CsvSchema schema = CsvSchema.emptySchema().withHeader().withColumnSeparator(';');
        return new CsvMapper()
                .readerWithSchemaFor(klass)
                .with(schema)
                .readValues(uri.toURL());
    }
}
