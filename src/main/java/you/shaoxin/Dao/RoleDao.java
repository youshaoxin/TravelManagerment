package you.shaoxin.Dao;

import org.apache.ibatis.annotations.*;
import you.shaoxin.domin.Permission;
import you.shaoxin.domin.Role;

import java.util.List;

/**
 * 游菜花 -
 * 创建时间: 2019-08-21 08:47
 */
public interface RoleDao {

    /*
     * 功能:根据用户id查找所有对应的角色
    **/
    @Select("select * from role where id in (select roleid from user_role where userid=#{userId})")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "you.shaoxin.Dao.PermissionDao.findPermissionByRoleId"))
    })
    public List<Role> findRoleById(String userId) throws Exception;


    /*
     * 功能:查询所有角色
    **/
    @Select("select * from role")
    List<Role> findAll() throws Exception;

    /*
     * 功能:新建角色
     **/
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;

    /*
     * 功能:根据role id查询所有角色
     **/
    @Select("select * from role where id = #{roleid}")
    Role findRoleByRoleId(String roleid);

    /*
     * 功能:根据角色查询可以添加的权限
    **/
    @Select("select * from permission where id not in (select permissionid from role_permission where roleid = #{roleid})")
    List<Permission> findOtherPermissions(String roleid);


    @Insert("insert into role_permission(roleid,permissionid) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
