package com.sample.excelgenerator.repository;

import com.sample.excelgenerator.dto.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<Policy,Integer> {
}
