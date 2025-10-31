package org.cook.lab4.mapper;

import org.cook.lab4.entity.ApplicationRequest;
import org.cook.lab4.entity.Courses;
import org.cook.lab4.model.CoursesModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CoursesMapper {

    public CoursesModel toModel(Courses courses){
        CoursesModel coursesModel = new CoursesModel();
        coursesModel.setId(courses.getId());
        coursesModel.setName(courses.getName());
        coursesModel.setDescription(courses.getDescription());
        coursesModel.setPrice(courses.getPrice());

        if(courses.getRequests() != null){
            coursesModel.setRequestsId(courses.getRequests().stream()
                    .map(ApplicationRequest::getId).toList());
        }else
            coursesModel.setRequestsId(new ArrayList<>());

        return coursesModel;
    }
}
