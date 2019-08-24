package you.shaoxin.Services;

import you.shaoxin.domin.Permission;
import you.shaoxin.domin.Role;

import java.util.List;

/**
 * 游菜花 -
 * 创建时间: 2019-08-21 08:48
 */
public interface RoleService {

    /*
     * 功能:查询所有角色
    **/
    List<Role> findAll() throws Exception;

    /*
     * 功能:新建角色
     **/
    void save(Role role) throws Exception;

    /*
     * 功能:根据id查询用户
    **/
    Role findRoleById(String roleid);

    /*
     * 功能:根据角色id查询可以添加的权限
    **/
    List<Permission> findOtherPermissions(String roleid);


    void addPermissionToRole(String roleId, String[] ids);
}
