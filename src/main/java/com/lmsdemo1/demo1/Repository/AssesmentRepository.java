package com.lmsdemo1.demo1.Repository;


import com.lmsdemo1.demo1.model.AssesmentT;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.*;

public interface AssesmentRepository extends JpaRepository<AssesmentT, Integer> {

    @Override
    Optional<AssesmentT> findById(Integer id);
    List<AssesmentT> findAll();
}
