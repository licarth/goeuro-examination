package com.goeuro.examination.serialization;

import com.goeuro.examination.serialization.pojo.AuthorPOJO;
import com.goeuro.examination.serialization.pojo.BookPOJO;
import com.goeuro.examination.serialization.pojo.MagazinePOJO;

import org.junit.Test;

import rx.Subscriber;
import rx.observers.TestSubscriber;

public class CsvFeedReaderTest {

    @Test
    public void oneAuthorShouldGetParsedCorrectly() throws Exception {
        TestSubscriber<AuthorPOJO> s = new TestSubscriber<>();
        new CsvFeedReader()
                .readAuthors(getClass().getClassLoader().getResource("com/goeuro/examination/serialization/fixtures/authors.csv").toURI())
                .subscribe(s);

        s.awaitTerminalEvent();
        s.assertValueCount(1);
        s.assertValue(new AuthorPOJO("Paul", "Walter", "paul-walter@goeuro.com"));
    }

    @Test
    public void onBookShouldGetSerializedProperly() throws Exception {
        TestSubscriber<BookPOJO> s = new TestSubscriber<>(getSubscriber());
        new CsvFeedReader()
                .readBooks(getClass().getClassLoader().getResource("com/goeuro/examination/serialization/fixtures/books.csv").toURI())
                .subscribe(s);

        s.awaitTerminalEvent();
        //
        s.assertValueCount(1);
    }

    @Test
    public void onMagazineShouldGetSerializedProperly() throws Exception {
        TestSubscriber<MagazinePOJO> s = new TestSubscriber<>(getSubscriber());
        new CsvFeedReader()
                .readMagazines(getClass().getClassLoader().getResource("com/goeuro/examination/serialization/fixtures/magazines.csv").toURI())
                .subscribe(s);

        s.awaitTerminalEvent();
        s.assertValueCount(6);
    }

    private <T> Subscriber<T> getSubscriber() {
        return new Subscriber<T>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(T item) {
                System.out.println(item);
            }
        };
    }
}