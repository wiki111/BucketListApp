package bucketlistapp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import bucketlistapp.model.AppUser;
import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<User> {
  public static final STRING_BASE_SQL = "select user_id, name, pass from users";

  @Override
  public User mapRow(ResultSet resultSet, int rowNumber) throws SQLException{
    Long id = resultSet.getLong("user_id");
    String name = resultSet.getString("name");
    String encryptedPassword = resultSet.getString("pass");

    return new AppUser(id, name, encryptedPassword);
  }
}
