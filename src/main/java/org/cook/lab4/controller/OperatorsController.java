package org.cook.lab4.controller;

import lombok.RequiredArgsConstructor;
import org.cook.lab4.entity.ApplicationRequest;
import org.cook.lab4.entity.Operators;
import org.cook.lab4.Services;
import org.cook.lab4.mapper.OperatorsMapper;
import org.cook.lab4.mapper.RequestMapper;
import org.cook.lab4.model.OperatorsModel;
import org.cook.lab4.model.RequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operators")
@RequiredArgsConstructor
public class OperatorsController {

    @Autowired
    private final Services services;

    @Autowired
    private final RequestMapper requestMapper;

    @Autowired
    private final OperatorsMapper operatorsMapper;

    @GetMapping
    public ResponseEntity<List<OperatorsModel>> getAllOperators(){
        List<OperatorsModel> operators = services.getAllOperators().stream()
                .map(operatorsMapper::toModel).toList();

        return ResponseEntity.ok(operators);
    }

    @PostMapping
    public ResponseEntity<OperatorsModel> createOperators(@RequestBody Operators operatorsToCreate){
        Operators operators = services.createOperators(operatorsToCreate);

        return ResponseEntity.ok(operatorsMapper.toModel(operators));
    }

    @PutMapping("/{id}/assign/{requestId}")
    public ResponseEntity<RequestModel> assignOperatorToRequest(@PathVariable Long id, @PathVariable Long requestId){
        ApplicationRequest request = services.assignOperatorToRequest(id, requestId);

        return ResponseEntity.ok(requestMapper.toModel(request));
    }

}
