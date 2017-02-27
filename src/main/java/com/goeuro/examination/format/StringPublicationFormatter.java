package com.goeuro.examination.format;

import com.goeuro.examination.model.Publication;

import java.util.List;


/**
 * Formats the list of publications.
 * Usually used for console printouts.
 */
public interface StringPublicationFormatter {
    String format(List<Publication> publicationList);
}
