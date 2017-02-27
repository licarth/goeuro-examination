package com.goeuro.examination;

import com.goeuro.examination.format.InlinePublicationFormatter;
import com.goeuro.examination.format.StringPublicationFormatter;
import com.goeuro.examination.serialization.CsvFeedReader;
import com.goeuro.examination.store.Library;
import com.goeuro.examination.store.SimpleLibrary;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MainAppTest {

    @Test
    public void testGetAllPublicationsOfAuthor() throws Exception {
        SimpleLibrary library = new SimpleLibrary();
        new CsvDataLoader(new CsvFeedReader(), library).loadCsvResources()
                .subscribe();

        System.out.println(library.getPublicationsByAuthor("null-lieblich@goeuro.com"));
    }

    @Test
    public void testFormatPublications() throws Exception {

        StringPublicationFormatter formatter = new InlinePublicationFormatter();

        Library library = new SimpleLibrary();

        new CsvDataLoader(new CsvFeedReader(), library)
                .loadCsvResources()
                .toBlocking()
                .subscribe();

        System.out.println(formatter.format(
                library.getPublicationsByAuthor("null-ferdinand@goeuro.com")
        ));
    }

}