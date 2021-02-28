package com.luv2code.springdemo.rest;

import com.luv2code.springdemo.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    List<Student> studentList;

    // define @PostContruct to load the student data. We load the data only once
    @PostConstruct
    public void loadData(){
        studentList = new ArrayList<>();

        // Add items to the student list
        studentList.add(new Student("Arno", "Patel"));
        studentList.add(new Student("Jack", "White"));
        studentList.add(new Student("John", "Jackson"));
    }


    // define endpoint for /students - return list of students
    @GetMapping("/students")
    public List<Student> getStudents() {
        // Just return the list, jackson will handle converting the list into Json array
        return studentList;
    }

    //define endpoint for "students/studentId" - return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) throws StudentNotFoundException{

        // Check the studentId against list size, if bad, throw custom exception
        if ((studentId >= studentList.size()) || (studentId < 0) ) {
            throw new StudentNotFoundException("Student id is not found - "  + studentId);
        }

        return studentList.get(studentId);
    }


}
