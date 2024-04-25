package com.lmsdemo1.demo1.Repository;


import com.lmsdemo1.demo1.model.Issued;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Issuedbookrepo extends JpaRepository<Issued, Integer> {


    List<Issued> findAllByFirstname(String fname);


//    Issued findByIbid(int ibid);
Long countByAddBookModelAddbookid(int id);
    Issued findByAddBookModelAddbookid(int addbookid);
}
