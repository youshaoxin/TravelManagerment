package you.shaoxin.Dao;

import org.apache.ibatis.annotations.*;
import you.shaoxin.domin.Role;
import you.shaoxin.domin.UserInfo;

import java.util.List;

/**
 * 功能:
 * 创建时间: 2019-08-14 09:13 --游菜花
 */
public interface UserDao {

    /*
     * 功能:用户登录
    **/
    @Select("select * from users where username=#{username}")
    UserInfo findByUsername(String username) throws Exception;

    /*
     * 功能:查找所有用户
    **/
    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    /*
     * 功能:增加用户
    **/
    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo user) throws Exception;


    /*
     * 功能:根据ID查询用户
    **/
    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many= @Many(select = "you.shaoxin.Dao.RoleDao.findRoleById"))
    })
    UserInfo findById(String id) throws Exception;

    /*
     * 功能:查询用户和用户可以添加的角色
     **/
    @Select("select * from role where id not in (select roleid from user_role where userid = #{userId})")
    List<Role> findOtherRoles(String userId);

    /*
     * 功能:为用户添加角色
     **/
    @Insert("insert into user_role(userid,roleid) values(#{userid},#{roleid})")
    void addRoleToUser(@Param("userid") String userid,@Param("roleid") String roleid);

    /*
     * 功能:根据用户名查找该用户拥有的所有角色
    **/
    @Select("select * from role where id in (select roleid from user_role where userid = (select id from users where username = #{username}))")
    List<Role> findIdByUsername(String username);
}
