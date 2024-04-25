package com.lmsdemo1.demo1.Repository;

import com.lmsdemo1.demo1.model.AddEvent;
//import com.lmsdemo1.demo1.rowmappers.addeventmapper;
import jdk.jfr.Event;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.Timestamp;
import java.time.LocalDateTime;



@Repository
public interface AddEventRepo extends CrudRepository<AddEvent,Integer> {
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public AddEventRepo(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public int saveEvent(AddEvent event){
//        String sql = "INSERT INTO addevent (class_name,topic,faculty_name,duration,zoom_link,status," +
//                "created_at,created_by) VALUES (?,?,?,?,?,?,?,?)";
//        return jdbcTemplate.update(sql,event.getClass1(),event.getTopic(),
//                event.getFaculty(),event.getDuration(),event.getLink(),
//                event.getStatus(),event.getCreatedAt(),event.getCreatedBy());
//    }
//
//    public  List<AddEvent> findMsgsWithStatus(String status) {
//        String sql = "SELECT * FROM addevent  WHERE status = ?";
//        return jdbcTemplate.query(sql,new PreparedStatementSetter() {
//            public void setValues(PreparedStatement preparedStatement) throws SQLException {
//                preparedStatement.setString(1, status);
//            }
//        },new addeventmapper());
//    }
//
//    public int updateMsgStatus(int contactId, String status,String updatedBy) {
//        String sql = "UPDATE addevent SET status = ?, updated_by = ?,updated_at =? WHERE addeventid = ?";
//        return jdbcTemplate.update(sql,new PreparedStatementSetter() {
//            public void setValues(PreparedStatement preparedStatement) throws SQLException {
//                preparedStatement.setString(1, status);
//                preparedStatement.setString(2, updatedBy);
//                preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
//                preparedStatement.setInt(4, contactId);
//            }
//        });
//    }

    List<AddEvent> findByStatus(String status);







}
