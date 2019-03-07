package com.swing.orange.mapper;

import com.swing.orange.entity.Message;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface MessageMapper {

    @Insert("INSERT INTO msg_tbl " +
            "(uid, type, content, created) " +
            "VALUES " +
            "(#{uid}, #{type}, #{content}, #{created})")
    void insert(Message message);

    @Select("SELECT * FROM msg_tbl WHERE id = #{id}")
    Message selectById(long id);

    @Select("SELECT * FROM msg_tbl")
    List<Message> getAll();

    @Select("SELECT * FROM msg_tbl WHERE uid = #{uid}")
    List<Message> selectByUid(long uid);


    @Delete("DELETE FROM msg_tbl WHERE id = #{id}")
    void deleteById(long id);
}
