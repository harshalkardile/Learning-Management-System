package com.lmsdemo1.demo1.Repository;


import com.lmsdemo1.demo1.model.FeedbackSt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedbackRepository extends CrudRepository<FeedbackSt, Integer > {

    @Override
    Optional<FeedbackSt> findById(Integer id);
}
