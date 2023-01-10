package com.example.feignclient;

import com.example.feignclient.clients.StudentClient;
import com.example.feignclient.pojos.JsonPatchDto;
import com.example.feignclient.pojos.StudentDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FeignClientTest {

    @Autowired
    private StudentClient studentClient;

    @Test
    void findAll() {
        List<StudentDto> studentList = studentClient.findAll();
        Assertions.assertFalse(studentList.isEmpty());
        studentList.forEach(System.out::println);
    }

    @Test
    void patch() {
        JsonPatchDto jsonPatchDto = JsonPatchDto.builder().op("replace").path("/email").value("e.zicola@gmail.com").build();
        StudentDto studentDtoPatched = studentClient.patch(1L, List.of(jsonPatchDto));

        Assertions.assertNotNull(studentDtoPatched);
        System.out.println(studentDtoPatched);
    }

}
