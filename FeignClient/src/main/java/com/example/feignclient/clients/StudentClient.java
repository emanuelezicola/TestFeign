package com.example.feignclient.clients;

import com.example.feignclient.pojos.JsonPatchDto;
import com.example.feignclient.pojos.StudentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="student-client", url="localhost:8000/students")
public interface StudentClient {

    @PostMapping
    StudentDto insertStudent(StudentDto studentDto);

    @GetMapping("/all")
    List<StudentDto> findAll();

    @PatchMapping(consumes = "application/json-patch+json", path = "/{studentId}")
    /*@RequestMapping(value = "/{studentId}",
            method = RequestMethod.PATCH,
            consumes = "application/json-patch+json")*/
    StudentDto patchJsonPatch(@PathVariable("studentId") Long studentId, @RequestBody List<JsonPatchDto> jsonPatchDto);

    @PatchMapping
    StudentDto patch(@RequestBody StudentDto studentDto);


}
