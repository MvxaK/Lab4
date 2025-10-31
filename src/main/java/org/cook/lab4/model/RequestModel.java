package org.cook.lab4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestModel {

    private Long id;
    private String userName;
    private String commentary;
    private String phone;
    private boolean handled;
    private Long courseId;
    private List<Long> operatorsId;

}
