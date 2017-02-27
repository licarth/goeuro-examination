package com.goeuro.examination.serialization;

import com.goeuro.examination.serialization.pojo.AuthorPOJO;

import org.junit.Test;

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
}