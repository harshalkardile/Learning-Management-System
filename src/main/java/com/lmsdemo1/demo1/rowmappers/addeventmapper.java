//package com.lmsdemo1.demo1.rowmappers;
//
//
//
//import com.lmsdemo1.demo1.model.AddEvent;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class addeventmapper implements RowMapper<AddEvent> {
//
//    @Override
//    public AddEvent mapRow(ResultSet rs, int rowNum) throws SQLException {
//        AddEvent event = new AddEvent();
//        event.setAddeventid(rs.getInt("ADDEVENTID"));
//        event.setClass1(rs.getString("CLASS_NAME"));
//        event.setTopic(rs.getString("TOPIC"));
//        event.setFaculty(rs.getString("FACULTY_NAME"));
//        event.setDuration(rs.getString("DURATION"));
//        event.setLink(rs.getString("ZOOM_LINK"));
//        event.setStatus(rs.getString("STATUS"));
//        event.setCreatedAt(rs.getTimestamp("CREATED_AT").toLocalDateTime());
//        event.setCreatedBy(rs.getString("CREATED_BY"));
//
//        if(null!=rs.getTimestamp("UPDATED_AT")){
//            event.setUpdatedAt(rs.getTimestamp("UPDATED_AT").toLocalDateTime());
//        }
//        event.setUpdatedBy(rs.getString("UPDATED_BY"));
//        return event;
//    }
//
//
//
//
//
//}
//
//
