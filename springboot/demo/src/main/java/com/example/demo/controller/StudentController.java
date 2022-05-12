package com.example.demo.controller;

import java.util.List;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stu")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @RequestMapping("/list")
    public List<Student> getStus(){
        logger.info("从数据库读取Student集合");
        return studentService.getList();
    }
}