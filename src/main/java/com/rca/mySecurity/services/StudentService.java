package com.rca.mySecurity.services;

import com.rca.mySecurity.entity.Student;
import com.rca.mySecurity.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class StudentService {
    @Autowired
    private StudentRepository repo;
    public void addStudent(Student st) {
        repo.save(st);
    }
}
