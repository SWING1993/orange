package com.swing.orange.mapper;

import com.swing.orange.entity.Status;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StatusMapper {

    @Insert("INSERT INTO status_tbl " +
            "(uid, type, content, imageUrls, vedioUrl, created, fromDevice) " +
            "VALUES " +
            "(#{uid}, #{type}, #{content}, #{imageUrls}, #{vedioUrl}, #{created}, #{fromDevice})")
    void insert(Status status);

    @Select("SELECT * FROM status_tbl WHERE id = #{id}")
    Status selectById(long id);

    /*
    select * from status_tbl,user_tbl where status_tbl.uid=user_tbl.id

    交叉连接cross join多表查询
    select * from status_tbl cross join user_tbl on status_tbl.uid=user_tbl.id

    内连接inner join多表查询
    select * from status_tbl inner join user_tbl on status_tbl.uid=user_tbl.id

    外左连接 (外左连接不仅会查询出两表中同事符合条件记录的组合，还会将“left outer join”左侧的表中不符合条件的记录展示出来，由于左侧表中的这一部分记录并不符合连接条件。所以这一部分记录使用空记录进行连接)
    select * from status_tbl left outer join user_tbl on status_tbl.uid=user_tbl.id

     */
    @Select("SELECT * FROM status_tbl INNER JOIN user_tbl ON status_tbl.uid=user_tbl.id")
    List<Status> getAll();

    @Select("SELECT * FROM status_tbl INNER JOIN user_tbl ON status_tbl.uid=user_tbl.id WHERE uid = #{uid}")
    List<Status> selectByUid(long uid);

    @Delete("DELETE FROM status_tbl WHERE id = #{id}")
    void deleteById(long id);

}
