package com.example.studentapp.pojos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StudentDto {

    private Long id;
    private String name;
    private String surname;
    private String email;

}
