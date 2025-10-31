package org.cook.lab4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "Operators")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "requests")
public class Operators {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String department;

    @ManyToMany(mappedBy = "operators")
    private List<ApplicationRequest> requests;
}
