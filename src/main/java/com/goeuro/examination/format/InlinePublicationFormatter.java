package com.goeuro.examination.format;

import com.goeuro.examination.model.Publication;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Getter;

import static com.google.common.base.Joiner.on;
import static com.google.common.base.Strings.padEnd;
import static com.google.common.base.Strings.repeat;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;

public class InlinePublicationFormatter implements StringPublicationFormatter{

    private static final String V_SEPARATOR = "|";
    private static final String H_SEPARATOR = "-";
    private static final String SPACE = " ";
    private static final int SPACING = SPACE.length();

    @Override
    public String format(List<Publication> publicationList) {

        Col[] cols = new Col[]{
                Col.col().name("Kind").size(8).toString(p -> p.getPublicationType().toString()).build(),
                Col.col().name("ISBN").size(14).toString(Publication::getIsbn).build(),
                Col.col().name("Title").size(40).toString(Publication::getTitle).build(),
                Col.col().name("PublishedAt").size(10).toString(InlinePublicationFormatter::dateToString).build(),
                Col.col().name("Authors").size(80).toString(p -> p.getAuthors().toString()).build(),
                Col.col().name("Description").size(50).toString(Publication::getDescription).build(),
        };

        String out = "";
        //Headers
        out += on(SPACE+V_SEPARATOR+SPACE).join(
                of(cols).map(c -> padded(c.name, c.size)).collect(toList())
        );
        out+= "\n";
        //Horizontal line
        out += on(repeat(H_SEPARATOR, SPACING)+V_SEPARATOR+repeat(H_SEPARATOR, SPACING)).join(of(cols).map(c -> repeat(H_SEPARATOR, c.getSize())).collect(toList()));

        //Values
        for (Publication p : publicationList) {
            out+= "\n";
            out += on(SPACE+V_SEPARATOR+SPACE).join(of(cols).map(c -> padded(c.toString.apply(p), c.size)).collect(toList()));
        }
        out+= "\n\n";
        return out;
    }

    private static String dateToString(Publication p) {
        java.time.LocalDate publishedAt = p.getPublishedAt();
        return publishedAt == null ? "NULL" : ofPattern("dd.MM.yyyy").format(publishedAt);
    }

    private String padded(String s, int maxLength){
        String s1 = padEnd(s==null ? "" : s, maxLength, ' ');
        String paddedString = s1.substring(0, Math.min(s1.length(), maxLength));
        return s1.length() <= maxLength ? paddedString : paddedString.substring(0, maxLength-1) + "…" ;
    }

    @Getter
    @Builder(builderMethodName = "col")
    private static class Col {
        private String name;
        private Function<Publication, String> toString;
        private int size;
    }
}
