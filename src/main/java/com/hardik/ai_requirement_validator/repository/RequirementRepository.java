package com.hardik.ai_requirement_validator.repository;

import com.hardik.ai_requirement_validator.model.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement,Long> {

    }

