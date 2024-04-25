package com.lmsdemo1.demo1.Repository;

import com.lmsdemo1.demo1.model.cdvideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface cdrepo extends JpaRepository<cdvideo,Integer> {

    @Query("select c from cdvideo c where c.cdid = :id")
    cdvideo findDataByCcid(int id);

}
