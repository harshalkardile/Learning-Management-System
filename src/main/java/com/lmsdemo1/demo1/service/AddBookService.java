package com.lmsdemo1.demo1.service;

import com.lmsdemo1.demo1.Repository.AddBookRepo;
import com.lmsdemo1.demo1.model.AddBookModel;
import com.lmsdemo1.demo1.model.Coursecard;
import com.lmsdemo1.demo1.model.RegisterSt;
import com.lmsdemo1.demo1.model.Roles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
//@RequestScope
@SessionScope
public class AddBookService {

    /**
     * Save Contact Details into DB
     * @param //contact
     * @return boolean
     */

    @Autowired
    private AddBookRepo addBookRepo;

    private int counter=0;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public AddBookService() {
        System.out.println("contact bean service  intialized");
    }

    public boolean saveMessageDetails(AddBookModel addBookModel){
        boolean isSaved = true;
        //TODO - Need to persist the data into the DB table
        log.info(addBookModel.toString());
        return isSaved;
    }

    public List<AddBookModel> displaybook(){
        // List<Coursecard> cardMsgs=coursecardRepo.findAll();
        return addBookRepo.findAll(Sort.by("auther").ascending());
    }

    public Optional<AddBookModel> displayreadbook(int id){

        return addBookRepo.findById(id);
    }




    public AddBookModel uploadimg(AddBookModel addBookModel) {

        return addBookRepo.save(addBookModel);
    }



    public void bookdelete(Integer id)
    {
//        Long count=addBookRepo.countByCcId();
//        if(count==null || count==0){
//
//            System.out.println(count);
//
//        }
        addBookRepo.deleteById(id);
    }

    public Boolean updatebook(AddBookModel addBookModel)
    {
        boolean isSaved = false;


        String url=addBookModel.getBook();
        int endIndex = url.lastIndexOf("/");
        String cutUrl = url.substring(0, endIndex)+"/preview";

        addBookModel.setBook(cutUrl);

        addBookModel = addBookRepo.save(addBookModel);

        if (null != addBookModel && addBookModel.getAddbookid() > 0) {
            isSaved = true;

        }

        return isSaved;
    }




}