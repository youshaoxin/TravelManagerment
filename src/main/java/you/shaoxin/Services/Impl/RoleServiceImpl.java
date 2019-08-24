package you.shaoxin.Services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import you.shaoxin.Dao.RoleDao;
import you.shaoxin.Services.RoleService;
import you.shaoxin.domin.Permission;
import you.shaoxin.domin.Role;

import java.util.List;

/**
 * 功能:
 * 创建时间: 2019-08-21 08:48 --游菜花
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    /*
     * 功能:查询所有角色
     **/
    @Override
    public List<Role> findAll() throws Exception {
        List<Role> roles = roleDao.findAll();
        return roles;
    }

    /*
     * 功能:新建角色
     **/
    @Override
    public void save(Role role) throws Exception{
        roleDao.save(role);
    }

    /*
     * 功能:根据角色id查询角色
    **/
    @Override
    public Role findRoleById(String roleid) {
        Role role = roleDao.findRoleByRoleId(roleid);
        return role;
    }

    @Override
    public List<Permission> findOtherPermissions(String roleid) {
        List<Permission> otherPermissions = roleDao.findOtherPermissions(roleid);
        return otherPermissions;
    }


    @Override
    public void addPermissionToRole(String roleId, String[] ids) {
        for (String permissionid : ids) {
            System.out.println(permissionid);
            roleDao.addPermissionToRole(roleId,permissionid);
        }
    }
}
