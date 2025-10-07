package org.cook.lab4;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "application_request")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", length = 100)
    private String userName;

    @Column(name = "course_name", length = 100)
    private String courseName;

    @Column(name = "comment", length = 100)
    private String commentary;

    @Column(name = "phone_number", length = 20)
    private String phone;

    @Column(name = "handled")
    private boolean handled;
}
