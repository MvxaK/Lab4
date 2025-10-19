package org.cook.lab4;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Services {

    @Autowired
    private final ApplicationRequestRepository requestRepository;

    @Autowired
    private final CoursesRepository coursesRepository;

    @Autowired
    private final OperatorsRepository operatorsRepository;

    @Transactional(readOnly = true)
    public ApplicationRequest getById(Long id){
        return requestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id -> " + id));
    }

    @Transactional(readOnly = true)
    public List<ApplicationRequest> getAllRequests(){
        return requestRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Courses> getAllCourses(){
        return coursesRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Operators> getAllOperators(){
        return operatorsRepository.findAll();
    }

    @Transactional
    public void createRequest(ApplicationRequest requestEntity, Long courseId, List<Long> operatorIds){
        requestEntity.setHandled(false);

        Courses course = coursesRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id -> " + courseId));

        requestEntity.setCourse(course);

        if(operatorIds != null && !operatorIds.isEmpty()){
            List<Operators> operators = operatorsRepository.findAllById(operatorIds);
            requestEntity.setOperators(operators);
        }
        requestRepository.save(requestEntity);
    }

    @Transactional
    public void handleRequest(Long id){
        ApplicationRequest requestEntity = requestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id -> " + id));

        requestEntity.setHandled(true);

        requestRepository.save(requestEntity);
    }


    @Transactional
    public void updateRequest(Long id, ApplicationRequest requestToUpdate, Long courseId, List<Long> operatorIds){
        ApplicationRequest requestEntity = requestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id -> " + id));

        Courses course = coursesRepository.findById(courseId)
                        .orElseThrow(() -> new EntityNotFoundException("Entity not found with id -> " + courseId));

        requestEntity.setUserName(requestToUpdate.getUserName());
        requestEntity.setCommentary(requestToUpdate.getCommentary());
        requestEntity.setPhone(requestToUpdate.getPhone());
        requestEntity.setCourse(course);

        if(operatorIds == null){
            requestEntity.setOperators(new ArrayList<>());
        }else {
            List<Operators> operators = operatorsRepository.findAllById(operatorIds);
            requestEntity.setOperators(operators);
        }
        requestRepository.save(requestEntity);
    }


    @Transactional
    public void deleteById(Long id){
        if(!requestRepository.existsById(id)){
            throw new EntityNotFoundException("There is no request with id -> " + id);
        }
        requestRepository.deleteById(id);
    }
}
