package com.lpl.servlce;

import com.lpl.pojo.User;

public interface UserService {

    public User login(User user);

    public boolean register(User user);

    public boolean check(String username);

    public boolean checkEmail(String email);

    public void reSet(User user);
}
