package com.swing.orange.mapper;

import com.swing.orange.entity.Status;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StatusMapper {

    @Insert("INSERT INTO status_tbl " +
            "(uid, type, avatar, avatarUrl, content, imageUrls, vedioUrl, created, fromDevice) " +
            "VALUES " +
            "(#{uid}, #{type}, #{avatar}, #{avatarUrl}, #{content}, #{imageUrls}, #{vedioUrl}, #{created}, #{fromDevice})")
    void insert(Status status);

    @Select("SELECT * FROM status_tbl WHERE id = #{id}")
    Status selectById(long id);

    @Select("SELECT * FROM status_tbl")
    List<Status> getAll();

    @Select("SELECT * FROM status_tbl WHERE uid = #{uid}")
    List<Status> selectByUid(long uid);


    @Delete("DELETE FROM status_tbl WHERE id = #{id}")
    void deleteById(long id);


}
