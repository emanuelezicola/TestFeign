package com.example.studentapp.services;

import com.example.studentapp.pojos.InsertStudentDto;
import com.example.studentapp.pojos.StudentDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import java.util.List;

public interface StudentService {

    StudentDto save(InsertStudentDto studentDto);

    List<StudentDto> findAll();

    StudentDto patch(Long id, JsonPatch patch) throws JsonPatchException, JsonProcessingException;

}
