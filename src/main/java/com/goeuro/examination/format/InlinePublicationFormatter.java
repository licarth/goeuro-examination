package com.goeuro.examination.format;

import com.goeuro.examination.model.Publication;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.Builder;

import static com.google.common.base.Joiner.on;
import static com.google.common.base.Strings.padEnd;
import static com.google.common.base.Strings.repeat;
import static java.util.stream.Stream.of;

public class InlinePublicationFormatter implements StringPublicationFormatter{

    @Override
    public String format(List<Publication> publicationList) {
        Col[] cols = new Col[]{
                Col.col().name("Kind").size(8).toString(p -> p.getPublicationType().toString()).build(),
                Col.col().name("ISBN").size(14).toString(Publication::getIsbn).build(),
                Col.col().name("Title").size(90).toString(Publication::getTitle).build(),
                Col.col().name("Authors").size(80).toString(p -> p.getAuthors().toString()).build(),
                Col.col().name("Description").size(50).toString(Publication::getDescription).build(),
        };

        int totalSize = of(cols).reduce(0, (s, c) -> s + c.size, (a, b) -> a + b);

        String separator = " | ";
        String out = "";
        out += on(separator).join(
                of(cols).map(c -> padded(c.name, c.size)).collect(Collectors.toList())
        );

        out+= "\n";
        out += repeat("-", totalSize);

        for (Publication p : publicationList) {
            out+= "\n";
            out += on(separator).join(of(cols).map(c -> padded(c.toString.apply(p), c.size)).collect(Collectors.toList()));
        }
        out+= "\n\n";
        return out;
    }

    private String padded(String s, int maxLength){
        String s1 = padEnd(s==null ? "" : s, maxLength, ' ');
        return s1.substring(0, Math.min(s1.length(), maxLength));
    }

    @Builder(builderMethodName = "col")
    private static class Col {
        private String name;
        private Function<Publication, String> toString;
        private int size;
    }
}
