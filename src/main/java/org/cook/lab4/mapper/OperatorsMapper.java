package org.cook.lab4.mapper;

import org.cook.lab4.entity.ApplicationRequest;
import org.cook.lab4.entity.Operators;
import org.cook.lab4.model.OperatorsModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OperatorsMapper {

    public OperatorsModel toModel(Operators operators){
        OperatorsModel operatorsModel = new OperatorsModel();
        operatorsModel.setId(operators.getId());
        operatorsModel.setName(operators.getName());
        operatorsModel.setSurname(operators.getSurname());
        operatorsModel.setDepartment(operators.getDepartment());

        if(operators.getRequests() != null){
            operatorsModel.setRequestsId(operators.getRequests().stream()
                    .map(ApplicationRequest::getId).toList());
        }else
            operatorsModel.setRequestsId(new ArrayList<>());

        return operatorsModel;
    }
}
