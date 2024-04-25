package com.lmsdemo1.demo1.Repository;


import com.lmsdemo1.demo1.model.ClassworkT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassworkRepository extends JpaRepository<ClassworkT, Integer> {

    @Override
    Optional<ClassworkT> findById(Integer id);
    List<ClassworkT> findAll();
}


