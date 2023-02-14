package com.reportanalytics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reportanalytics.entities.Role;

@Repository
public interface UserRolesRepository extends JpaRepository<Role, Long>{

}
