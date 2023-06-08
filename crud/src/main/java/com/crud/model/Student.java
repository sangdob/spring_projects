package com.crud.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student {
    private Long id;

    private String name;

    private String email;
}
