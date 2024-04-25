package com.lmsdemo1.demo1.Repository;


import com.lmsdemo1.demo1.model.ClassworkS;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassworkRepositoryS extends CrudRepository<ClassworkS, Integer > {

}