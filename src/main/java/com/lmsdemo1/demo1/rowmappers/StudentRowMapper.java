//package rowmappers;
//
//import com.lmsdemo1.LearnMS.model.RegisterSt;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class StudentRowMapper implements RowMapper<RegisterSt> {
//    @Override
//    public RegisterSt mapRow(ResultSet rs, int rowNum) throws SQLException {
//        RegisterSt registerSt = new RegisterSt();
//        registerSt.setId(rs.getInt("student_id"));
//        registerSt.setFname(rs.getString("Sfirstname"));
//        registerSt.setLname(rs.getString("Slastname"));
//        registerSt.setMob(rs.getString("Smobno"));
//        registerSt.setEmail(rs.getString("Smailid"));
//        registerSt.setGender(rs.getString("Sgender"));
//        registerSt.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
//        registerSt.setCreatedBy(rs.getString("created_by"));
//
//        if(null!=rs.getTimestamp("updated_at")){
//            registerSt.setUpdatedAt(rs.getTimestamp("UPDATED_AT").toLocalDateTime());
//        }
//        registerSt.setUpdatedBy(rs.getString("updated_by"));
//        return registerSt;
//    }
//}

