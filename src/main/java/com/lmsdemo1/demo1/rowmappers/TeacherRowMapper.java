//package rowmappers;
//
//import com.lmsdemo1.LearnMS.model.RegisterTe;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class TeacherRowMapper implements RowMapper<RegisterTe> {
//    @Override
//    public RegisterTe mapRow(ResultSet rs, int rowNum) throws SQLException {
//        RegisterTe registerTe = new RegisterTe();
//        registerTe.setIdT(rs.getInt("teacher_id"));
//        registerTe.setFnameT(rs.getString("Tfirstname"));
//        registerTe.setLnameT(rs.getString("Tlastname"));
//        registerTe.setMobT(rs.getString("Tmobno"));
//        registerTe.setEmailT(rs.getString("Tmailid"));
//        registerTe.setGenderT(rs.getString("Tgender"));
//        registerTe.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
//        registerTe.setCreatedBy(rs.getString("created_by"));
//
//        if(null!=rs.getTimestamp("updated_at")){
//            registerTe.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
//        }
//        registerTe.setUpdatedBy(rs.getString("updated_by"));
//        return registerTe;
//    }
//
//}
