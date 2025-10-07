package org.cook.lab4;

import org.springframework.stereotype.Component;

@Component
public class ApplicationRequestMapper {
    public ApplicationRequest toModel(ApplicationRequestEntity entity){
        return new ApplicationRequest(entity.getId(),
                entity.getUserName(),
                entity.getCourseName(),
                entity.getCommentary(),
                entity.getPhone(),
                entity.isHandled());
    }

    public static ApplicationRequestEntity toEntity(ApplicationRequest request){
        return new ApplicationRequestEntity(request.getId(),
                request.getUserName(),
                request.getCourseName(),
                request.getCommentary(),
                request.getPhone(),
                request.isHandled());
    }
}
