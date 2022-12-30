package com.lpl.servlce.impl;

import com.lpl.Utils.SqlSessionFactoryUtils;
import com.lpl.mapper.BangumiMapper;
import com.lpl.pojo.Bangumi;
import com.lpl.pojo.Page;
import com.lpl.servlce.BangumiService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BangumiServiceimpl implements BangumiService {
    private SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public Page<Bangumi> selectByCondition(Bangumi bangumi, int start, int count) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BangumiMapper mapper = sqlSession.getMapper(BangumiMapper.class);
        if (bangumi != null && bangumi.getName() != null) {
            bangumi.setName("%" + bangumi.getName() + "%");
        }
        List<Bangumi> list = mapper.selectByCondition(bangumi, start, count);
//        for (Bangumi bangumiList : list) {
//            bangumiList.setPicture("https://images.weserv.nl/?url=" + bangumiList.getPicture());
//        }
        int total = mapper.queryForSingleValueByCondition(bangumi);

        Page<Bangumi> page = new Page<>();
        page.setList(list);
        page.setTotal(total);

        return page;
    }

    @Override
    public String addBangumi(Bangumi bangumi)  {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BangumiMapper mapper = sqlSession.getMapper(BangumiMapper.class);
        List<Bangumi> bangumis = mapper.selectByCondition(bangumi, 0, 2);
        if (bangumis==null||bangumis.size()==0){
            mapper.addBangumi(bangumi);
            sqlSession.commit();
            sqlSession.close();
            return "success";
        }else{
            sqlSession.close();
            return "false";
        }

    }

    @Override
    public void update(Bangumi bangumi) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BangumiMapper mapper = sqlSession.getMapper(BangumiMapper.class);
        mapper.update(bangumi);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteSingle(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BangumiMapper mapper = sqlSession.getMapper(BangumiMapper.class);

        mapper.deleteSingle(id);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BangumiMapper mapper = sqlSession.getMapper(BangumiMapper.class);
        mapper.deleteByIds(ids);

        sqlSession.commit();
        sqlSession.close();
    }

}
