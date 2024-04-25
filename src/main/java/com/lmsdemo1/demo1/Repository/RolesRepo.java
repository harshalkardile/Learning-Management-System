package com.lmsdemo1.demo1.Repository;

import com.lmsdemo1.demo1.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepo extends JpaRepository<Roles, Integer>
{

    Roles getByRoleName(String roleName);



}
