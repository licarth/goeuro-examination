package com.goeuro.examination.serialization.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorPOJO {
    private String firstname;
    private String lastname;
    private String email;
}
