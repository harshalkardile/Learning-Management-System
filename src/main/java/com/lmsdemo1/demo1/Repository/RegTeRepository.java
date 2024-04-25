package com.lmsdemo1.demo1.Repository;


import com.lmsdemo1.demo1.model.RegisterTe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;



import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public interface RegTeRepository extends JpaRepository<RegisterTe, Integer> {
    List<RegisterTe> findByCreatedBy(String status);
//    RegisterTe findById(int idT);
}
