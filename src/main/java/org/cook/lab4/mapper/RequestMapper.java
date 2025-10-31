package org.cook.lab4.mapper;

import org.cook.lab4.entity.ApplicationRequest;
import org.cook.lab4.entity.Operators;
import org.cook.lab4.model.RequestModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RequestMapper {

    public RequestModel toModel(ApplicationRequest request){
        RequestModel requestModel = new RequestModel();
        requestModel.setId(request.getId());
        requestModel.setUserName(request.getUserName());
        requestModel.setCommentary(request.getCommentary());
        requestModel.setPhone(request.getPhone());
        requestModel.setHandled(request.isHandled());
        requestModel.setCourseId(request.getCourse().getId());

        if(request.getOperators() != null){
            requestModel.setOperatorsId(request.getOperators().stream()
                    .map(Operators::getId).toList());
        }else
            requestModel.setOperatorsId(new ArrayList<>());

        return requestModel;
    }
}
