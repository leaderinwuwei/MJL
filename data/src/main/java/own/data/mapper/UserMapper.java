package own.data.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import own.data.model.UserMysql;

import java.util.List;

public interface UserMapper {

    @Select("SELECT * FROM users")
    List<UserMysql> getAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    UserMysql getOne(Long id);

    @Insert("INSERT INTO users(user_name,pass_word,user_sex) VALUES(#{user_name}, #{pass_word}, #{user_sex})")
    void insert(UserMysql user);

    @Update("UPDATE users SET user_name=#{user_name},nick_name=#{nick_name} WHERE id =#{id}")
    void update(UserMysql user);

    @Delete("DELETE FROM users WHERE id =#{id}")
    void delete(Long id);

}