package com.goeuro.examination.serialization.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MagazinePOJO {
    private String firstname;
    private String lastname;
    private String email;
}
