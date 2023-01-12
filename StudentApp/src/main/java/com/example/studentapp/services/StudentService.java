package com.example.studentapp.services;

import com.example.studentapp.pojos.InsertStudentDto;
import com.example.studentapp.pojos.StudentDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import java.util.List;

public interface StudentService {
    StudentDto findById(Long id);
    List<StudentDto> findAll();
    StudentDto save(InsertStudentDto studentDto);
    StudentDto patchJsonPatch(Long id, JsonPatch patch) throws JsonPatchException, JsonProcessingException;
    StudentDto patch(StudentDto studentDto) throws JsonPatchException, JsonProcessingException;
    void delete(Long id);
}
