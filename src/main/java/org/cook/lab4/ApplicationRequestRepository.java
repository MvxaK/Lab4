package org.cook.lab4;

import org.cook.lab4.entity.ApplicationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRequestRepository extends JpaRepository<ApplicationRequest, Long> {

}
