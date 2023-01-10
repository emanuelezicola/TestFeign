package com.example.studentapp.pojos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class InsertStudentDto {

    @NonNull
    private String name;

    @NonNull
    private String surname;

    @NonNull
    private String email;

}
