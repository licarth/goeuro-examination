package com.goeuro.examination.serialization;

import com.goeuro.examination.serialization.pojo.AuthorPOJO;
import com.goeuro.examination.serialization.pojo.BookPOJO;
import com.goeuro.examination.serialization.pojo.MagazinePOJO;

import java.io.IOException;
import java.net.URI;

import rx.Observable;

public interface FeedReader {

    Observable<MagazinePOJO> readMagazines(URI uri) throws IOException;
    Observable<BookPOJO> readBooks(URI uri) throws IOException;
    Observable<AuthorPOJO> readAuthors(URI uri) throws IOException;

}
