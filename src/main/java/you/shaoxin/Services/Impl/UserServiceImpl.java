package you.shaoxin.Services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import you.shaoxin.Dao.UserDao;
import you.shaoxin.Services.UserService;
import you.shaoxin.Utils.PasswordEncoderUtils;
import you.shaoxin.domin.Role;
import you.shaoxin.domin.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能:
 * 创建时间: 2019-08-14 09:18 --游菜花
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    //加密类，对提交的密码进行加密
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /*
     * 功能:查找所有用户
    **/
    @Override
    public List<UserInfo> findAll() throws Exception {
        return userDao.findAll();
    }

    /*
     * 功能:保存用户
    **/
    @Override
    public void save(UserInfo userInfo) throws Exception {

        //对提交的密码进行加密
        String password = PasswordEncoderUtils.encodePassword(userInfo.getPassword());
        System.out.println(password);

        //String encode = bCryptPasswordEncoder.encode(user.getPassword());

        userInfo.setPassword(password);
        userDao.save(userInfo);

    }

    /*
     * 功能:根据id查询用户
    **/
    @Override
    public UserInfo findById(String id) throws Exception {

        UserInfo userInfo = userDao.findById(id);
        return userInfo;
    }

    /*
     * 功能:查询用户和用户可以添加的角色
     **/
    @Override
    public List<Role> findOtherRoles(String id) {

        return userDao.findOtherRoles(id);
    }

    /*
     * 功能:为用户添加角色
     **/
    @Override
    public void addRoleToUser(String id,String[] roleids) {
        for (String roleid : roleids) {
            userDao.addRoleToUser(id,roleid);
        }
    }

    /*
     * 功能:interface UserService extends UserDetailsService
     *  security提供的继承UserDetailsService必须实现的方法
    **/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //处理自己的用户对象封装成UserDetails
        //这个User对象是security为我们提供的对象
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),getAuthorty(username));
        return user;
    }

    //作用就是返回一个list集合，集合中装入的是角色描述
    public List<SimpleGrantedAuthority> getAuthorty(String username){

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        //list.add(new SimpleGrantedAuthority("ROLE_GUEST"));

        //根据用户名查找该用户拥有的所有角色
        List<Role> roles = userDao.findIdByUsername(username);
        for (Role role : roles) {
            System.out.println(role);
            list.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return list;
    }



}