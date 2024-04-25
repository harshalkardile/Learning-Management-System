package com.lmsdemo1.demo1.Repository;

import com.lmsdemo1.demo1.model.AddBookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddBookRepo extends JpaRepository<AddBookModel,Integer> {


    AddBookModel findByAddbookid(int ibid);
}
