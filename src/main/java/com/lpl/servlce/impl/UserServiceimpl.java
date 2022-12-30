package com.lpl.servlce.impl;

import com.lpl.Utils.SqlSessionFactoryUtils;
import com.lpl.mapper.UserMapper;
import com.lpl.pojo.User;
import com.lpl.servlce.UserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
@SuppressWarnings("all")
public class UserServiceimpl implements UserService {
    private SqlSessionFactory sqlSessionFactory= SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public User login(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User select = mapper.select(user);
        sqlSession.close();
        return select;
    }
    @Override
    public boolean register(User user) {
        boolean check = check(user.getUsername());
        if (check){
            SqlSession sqlSession = sqlSessionFactory.openSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.update(user);
            sqlSession.commit();
            sqlSession.close();
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean check(String username) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        String i = mapper.selectName(username);
        if (i==null){
            sqlSession.close();
            return true;
        }
        else{
            sqlSession.close();
            return false;
        }
    }

    @Override
    public boolean checkEmail(String email) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        String selectEmail = mapper.selectEmail(email);
        if (selectEmail==null){
            sqlSession.close();
            return false;
        }
        else{
            sqlSession.close();
            return true;
        }
    }

    public void reSet(User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.reSet(user);

        sqlSession.commit();
        sqlSession.close();
    }
}
