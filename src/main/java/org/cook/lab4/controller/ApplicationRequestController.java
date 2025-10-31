package org.cook.lab4.controller;

import lombok.RequiredArgsConstructor;
import org.cook.lab4.entity.ApplicationRequest;
import org.cook.lab4.Services;
import org.cook.lab4.mapper.RequestMapper;
import org.cook.lab4.model.RequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class ApplicationRequestController {

    @Autowired
    private final Services services;

    @Autowired
    private final RequestMapper requestMapper;

    @GetMapping
    public ResponseEntity<List<RequestModel>> getAllRequests(){
        List<RequestModel> requests = services.getAllRequests().stream()
                .map(requestMapper::toModel).toList();

        return ResponseEntity.ok(requests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestModel> getRequestById(@PathVariable Long id){
        ApplicationRequest request = services.getById(id);

        return ResponseEntity.ok(requestMapper.toModel(request));
    }

    @PostMapping
    public ResponseEntity<Void> createRequest(@RequestBody ApplicationRequest request, @RequestParam Long courseId, @RequestParam(required = false) List<Long> operatorIds){
        services.createRequest(request, courseId, operatorIds);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRequest(@PathVariable Long id, @RequestBody ApplicationRequest requestToUpdate, @RequestParam Long courseId, @RequestParam(required = false) List<Long> operatorIds){
        services.updateRequest(id, requestToUpdate, courseId, operatorIds);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id){
        services.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
