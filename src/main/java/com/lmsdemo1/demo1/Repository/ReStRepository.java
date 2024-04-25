package com.lmsdemo1.demo1.Repository;


import com.lmsdemo1.demo1.model.RegisterSth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
    public interface ReStRepository extends CrudRepository<RegisterSth, Integer> {
//        List<RegisterSt> findByCreatedBy(String status);

    @Override
    Optional<RegisterSth> findById(Integer id);

    List<RegisterSth> findAll();
    }
    



