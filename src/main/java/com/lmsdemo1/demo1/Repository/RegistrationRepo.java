package com.lmsdemo1.demo1.Repository;


import com.lmsdemo1.demo1.model.AddEvent;
import com.lmsdemo1.demo1.model.RegisterSt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepo extends JpaRepository<RegisterSt,Integer> {

    RegisterSt readByEmail(String email);

 // @Query("FROM `registeration`  WHERE  role_id = 2")
    List<RegisterSt> findByRolesRoleId(Integer roleId);

Page<RegisterSt> findByFname(String fname, Pageable pageable);

    Long countById(int id);



}
