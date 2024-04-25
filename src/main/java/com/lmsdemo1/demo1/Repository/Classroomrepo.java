package com.lmsdemo1.demo1.Repository;

import com.lmsdemo1.demo1.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Classroomrepo extends JpaRepository<Classroom,Integer> {


}
