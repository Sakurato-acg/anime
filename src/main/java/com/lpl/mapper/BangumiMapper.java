package com.lpl.mapper;

import com.lpl.pojo.Bangumi;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BangumiMapper {
    List<Bangumi> selectByCondition(@Param("anime") Bangumi anime
            , @Param("start") int start, @Param("count") int count);


    Integer queryForSingleValueByCondition(@Param("anime") Bangumi anime);

    @Update("insert into bangumi(name, type, time, status, year, picture, userId) VALUES (#{anime.name},#{anime.type},#{anime.time},#{anime.status},#{anime.year},#{anime.picture},#{anime.userId})")
    void addBangumi(@Param("anime") Bangumi anime);

    @Update("update anime_library.bangumi set `name`=#{anime.name}, type=#{anime.type} , time=#{anime.time} , `status`=#{anime.status} , `year`=#{anime.year} , picture=#{anime.picture}  where id=#{anime.id}")
    void update(@Param("anime") Bangumi anime);
//
//    @Select("select userId from bangumi where name=#{name}")
//    String getUserIds(@Param("name")String name);

    @Delete("delete from bangumi where id=#{id}")
    void deleteSingle(@Param("id") int id);

    void deleteByIds(@Param("ids")int []ids);
}
