package org.cook.lab4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.cook.lab4.entity.ApplicationRequest;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "requestsId")
public class OperatorsModel {

    private Long id;
    private String name;
    private String surname;
    private String department;
    private List<Long> requestsId;

}
