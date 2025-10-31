package org.cook.lab4.controller;

import lombok.RequiredArgsConstructor;
import org.cook.lab4.entity.Courses;
import org.cook.lab4.Services;
import org.cook.lab4.mapper.CoursesMapper;
import org.cook.lab4.model.CoursesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CoursesController {

    @Autowired
    private final Services services;

    @Autowired
    private final CoursesMapper coursesMapper;

    @GetMapping
    public ResponseEntity<List<CoursesModel>> getAllCourses(){
        List<CoursesModel> courses = services.getAllCourses().stream()
                .map(coursesMapper::toModel).toList();

        return ResponseEntity.ok(courses);
    }

    @PostMapping
    public ResponseEntity<CoursesModel> createCourses(@RequestBody Courses coursesToCreate){
        Courses courses = services.createCourses(coursesToCreate);

        return ResponseEntity.ok(coursesMapper.toModel(courses));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourses(@PathVariable Long id ){
        services.deleteCourses(id);

        return ResponseEntity.noContent().build();
    }


}
