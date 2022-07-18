package com.project.training.assignment.employeedetails.repository.owner;

import com.project.training.assignment.employeedetails.model.owner.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Integer> {
}
