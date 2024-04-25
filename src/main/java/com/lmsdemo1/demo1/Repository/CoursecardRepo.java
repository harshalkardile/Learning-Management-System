package com.lmsdemo1.demo1.Repository;

import com.lmsdemo1.demo1.model.Coursecard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursecardRepo extends JpaRepository<Coursecard,Integer> {

    @Query("Select Count(*) from Coursecard")
    long countByCcId();

    List<Coursecard> findByOrderByCctitleDesc();

    List<Coursecard> findByOrderByCctitle();

}
