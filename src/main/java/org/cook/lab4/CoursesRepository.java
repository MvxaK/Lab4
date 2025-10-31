package org.cook.lab4;

import org.cook.lab4.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepository extends JpaRepository<Courses, Long> {

}
