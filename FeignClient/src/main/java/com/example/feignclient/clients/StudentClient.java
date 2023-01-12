package com.example.feignclient.clients;

import com.example.feignclient.pojos.JsonPatchDto;
import com.example.feignclient.pojos.StudentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="student-client", url="localhost:8000/students")
public interface StudentClient {

    @PatchMapping
    StudentDto patch(@RequestBody StudentDto studentDto);

    @PostMapping
    StudentDto insertStudent(StudentDto studentDto);

    @GetMapping("/all")
    List<StudentDto> findAll();


    @PatchMapping(consumes = "application/json-patch+json", path = "/{studentId}")
    StudentDto patchJsonPatch(@PathVariable("studentId") Long studentId, @RequestBody List<JsonPatchDto> jsonPatchDto);

}