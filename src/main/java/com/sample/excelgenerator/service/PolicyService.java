package com.sample.excelgenerator.service;

import com.sample.excelgenerator.dto.Policy;
import com.sample.excelgenerator.repository.PolicyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public interface PolicyService {
    public List<Policy> listAll();

}
