package com.example.feignclient.pojos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class JsonPatchDto {

    @NonNull
    private String op;

    @NonNull
    private String path;

    private String value;

}
