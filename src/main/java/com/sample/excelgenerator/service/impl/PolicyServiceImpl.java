package com.sample.excelgenerator.service.impl;

import com.sample.excelgenerator.dto.Policy;
import com.sample.excelgenerator.repository.PolicyRepository;
import com.sample.excelgenerator.service.ExcelGeneratorService;
import com.sample.excelgenerator.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    private PolicyRepository repo;

    @Override
    public List<Policy> listAll() {
        /*return List.of(new Policy(1,"expedia","xml"),
                new Policy(2,"VRBO","internet"),
                new Policy(3,"TIPS","tips"));
        */
        return repo.findAll(Sort.by("policyNumber").ascending());
    }

}
