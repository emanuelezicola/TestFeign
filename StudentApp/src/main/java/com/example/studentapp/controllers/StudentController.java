package com.example.studentapp.controllers;

import com.example.studentapp.pojos.InsertStudentDto;
import com.example.studentapp.pojos.StudentDto;
import com.example.studentapp.services.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
@Slf4j
public class StudentController {

    private final StudentService studentService;

    @PostMapping()
    public ResponseEntity<StudentDto> insertStudent(@RequestBody InsertStudentDto studentDto) {
        return ResponseEntity.ok(studentService.save(studentDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentDto>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody JsonPatch patch) {
        try {
            return ResponseEntity.ok(studentService.patch(id, patch));
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
