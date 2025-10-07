package org.cook.lab4;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApplicationRequestService {

    @Autowired
    private ApplicationRequestRepository repository;

    @Autowired
    private ApplicationRequestMapper mapper;

    @Transactional(readOnly = true)
    public ApplicationRequest getById(Long id){
        return repository.findById(id)
                .map(mapper::toModel)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id -> " + id));
    }

    @Transactional(readOnly = true)
    public List<ApplicationRequest> getAllRequests(){
        return repository.findAll().stream()
                .map(mapper::toModel)
                .toList();
    }

    @Transactional
    public void createRequest(ApplicationRequest requestToCreate){
        ApplicationRequestEntity entity = mapper.toEntity(requestToCreate);
        entity.setHandled(false);

        repository.save(entity);
    }

    @Transactional
    public void handleRequest(Long id){
        ApplicationRequestEntity requestEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id -> " + id));

        requestEntity.setHandled(true);

        repository.save(requestEntity);
    }


    @Transactional
    public void updateRequest(Long id, ApplicationRequest requestToUpdate){
        ApplicationRequestEntity requestEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id -> " + id));

        requestEntity.setUserName(requestToUpdate.getUserName());
        requestEntity.setCourseName(requestToUpdate.getCourseName());
        requestEntity.setCommentary(requestToUpdate.getCommentary());
        requestEntity.setPhone(requestToUpdate.getPhone());

        repository.save(requestEntity);
    }


    @Transactional
    public void deleteById(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("There is no request with id -> " + id);
        }
        repository.deleteById(id);
    }
}
