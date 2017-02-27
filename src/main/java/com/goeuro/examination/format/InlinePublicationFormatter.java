package com.goeuro.examination.format;

import com.goeuro.examination.model.Publication;

import java.util.List;

import static com.google.common.base.Joiner.on;
import static com.google.common.base.Strings.padEnd;

public class InlinePublicationFormatter implements StringPublicationFormatter{

    @Override
    public String format(List<Publication> publicationList) {
        String separator = "\t";
        String out = "";
        out += on(separator).join(
                padded("Kind", 10),
                padded("Title", 70),
                padded("Authors", 100)
        );
        for (Publication publication : publicationList) {
            out+= "\n";
            out += on(separator).join(
                    padded(publication.getPublicationType().toString(), 10),
                    padded(publication.getTitle(), 70),
                    padded(publication.getAuthors().toString(), 100));
        }
        return out;
    }

    private String padded(String s, int maxLength){
//        return String.format("%1$"+40+ "s", s);
        String s1 = padEnd(s, maxLength, ' ');
        return s1.substring(0, Math.min(s1.length(), maxLength));
    }

}
