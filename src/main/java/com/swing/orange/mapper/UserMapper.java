package com.swing.orange.mapper;

import com.swing.orange.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {


    @Insert("INSERT INTO user_tbl (phone, email, password, created) VALUES (#{phone}, #{email}, #{password}, #{created})")
    void insert(User user);

    @Select("SELECT * FROM user_tbl WHERE id = #{id}")
    User selectById(long id);

    @Select("SELECT * FROM user_tbl WHERE phone = #{phone}")
    User selectByPhone(String phone);

    @Select("SELECT * FROM user_tbl WHERE email = #{email}")
    User selectByEmail(String email);

    @Select("SELECT * FROM user_tbl")
    List<User> getAll();

    @Delete("DELETE FROM user_tbl WHERE id = #{id} ")
    void deleteById(long id);

    @Update("UPDATE user_tbl SET email = #{email}, nickname = #{nickname}, sex = #{sex}, avatarUrl = #{avatarUrl}, userDesc = #{userDesc}, updated = #{updated} WHERE id = #{id}")
    void update(User user);

}
