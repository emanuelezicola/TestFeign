package com.example.studentapp.services.impl;

import com.example.studentapp.entities.Student;
import com.example.studentapp.pojos.InsertStudentDto;
import com.example.studentapp.pojos.StudentDto;
import com.example.studentapp.repositories.StudentRepository;
import com.example.studentapp.services.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ObjectMapper objectMapper;

    @Override
    public StudentDto save(InsertStudentDto studentDto) {
        Student student = objectMapper.convertValue(studentDto, Student.class);
        studentRepository.save(student);

        return objectMapper.convertValue(student, StudentDto.class);
    }

    @Override
    public List<StudentDto> findAll() {
        return studentRepository.findAll().stream().map(student -> objectMapper.convertValue(student, StudentDto.class)).toList();
    }


    @Override
    public StudentDto patch(StudentDto studentDto) throws JsonPatchException, JsonProcessingException {
        Student studentToPatch = studentRepository.findById(studentDto.getId()).orElseThrow(EntityNotFoundException::new);
        if(studentDto.getEmail()!=null) {
            studentToPatch.setEmail(studentDto.getEmail());
        }
        if(studentDto.getSurname()!=null) {
            studentToPatch.setSurname(studentDto.getSurname());
        }
        if(studentDto.getName()!=null) {
            studentToPatch.setName(studentDto.getName());
        }
        studentRepository.save(studentToPatch);
        return objectMapper.convertValue(studentToPatch, StudentDto.class);
    }

    @Override
    public StudentDto patchJsonPatch(Long id, JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        Student student = studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Student studentPatched = applyPatchToStudent(patch, student);
        studentRepository.save(studentPatched);
        return objectMapper.convertValue(studentPatched, StudentDto.class);
    }

    private Student applyPatchToStudent(JsonPatch patch, Student targetStudent) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetStudent, JsonNode.class));
        return objectMapper.treeToValue(patched, Student.class);
    }
}
