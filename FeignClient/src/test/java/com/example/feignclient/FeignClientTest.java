package com.example.feignclient;

import com.example.feignclient.clients.StudentClient;
import com.example.feignclient.pojos.JsonPatchDto;
import com.example.feignclient.pojos.StudentDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FeignClientTest {
    StudentDto student;
    @Autowired
    private StudentClient studentClient;

    @BeforeEach
    void setUp() {
        student = StudentDto.builder().name("testName").surname("testSurname").email("test@test.com").build();
        student = studentClient.insertStudent(student);
    }

    @Test
    void findAll() {
        List<StudentDto> studentList = studentClient.findAll();
        Assertions.assertFalse(studentList.isEmpty());
        studentList.forEach(System.out::println);
    }


    @Test
    void patch() {
        Assertions.assertEquals("testName", student.getName());

        student.setName("newName");
        student.setSurname("newSurname");
        student.setEmail("newEmail");

        StudentDto studentDtoPatched = studentClient.patch(student);

        Assertions.assertNotNull(studentDtoPatched);
        Assertions.assertEquals("newName", studentDtoPatched.getName());

        System.out.println(studentDtoPatched);
    }

    @Test
    void patchJsonPatch() {
        JsonPatchDto jsonPatchDto = JsonPatchDto.builder().op("replace").path("/name").value("emanuele").build();

        Assertions.assertEquals("testName", student.getName());

        StudentDto studentDtoPatched = studentClient.patchJsonPatch(student.getId(), List.of(jsonPatchDto));

        Assertions.assertNotNull(studentDtoPatched);
        Assertions.assertEquals("emanuele", studentDtoPatched.getName());

        System.out.println(studentDtoPatched);
    }

    @AfterEach
    void tearDown() {

    }
}
