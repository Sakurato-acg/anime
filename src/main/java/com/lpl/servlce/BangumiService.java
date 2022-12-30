package com.lpl.servlce;


import com.lpl.pojo.Bangumi;
import com.lpl.pojo.Page;


public interface BangumiService {
    Page<Bangumi> selectByCondition(Bangumi bangumi , int start, int count);

    String addBangumi (Bangumi bangumi);


    void  update(Bangumi bangumi);

    void deleteSingle(int id);

    void deleteByIds(int []ids);
}
