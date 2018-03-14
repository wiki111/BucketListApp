package bucketlistapp.dao;

import javax.sql.DataSource;

import bucketlistapp.mapper.UserMapper;
import bucketlistapp.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDAO extends JdbcDaoSupport{
  @Autowired
  public UserDAO(DataSource dataSource){
    this.setDataSource(dataSource);
  }

  public AppUser findUserAccount(String userName) {
    String sql = UserMapper.STRING_BASE_SQL + " where name = ? ";

    Object[] params = new Object[]{userName};
    UserMapper mapper = new UserMapper();

    try{
      AppUser userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
      return userInfo;
    }catch (EmptyResultDataAccessException e){
      return null;
    }
  }
}
