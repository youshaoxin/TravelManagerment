package you.shaoxin.Services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import you.shaoxin.Dao.PermissionDao;
import you.shaoxin.Services.PermissionService;
import you.shaoxin.domin.Permission;

import java.util.List;

/**
 * 功能:
 * 创建时间: 2019-08-21 17:04 --游菜花
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    /*
     * 功能:查询所有权限
    **/
    @Override
    public List<Permission> findAll() throws Exception {
        List<Permission> list = permissionDao.findAll();
        return list;
    }
    /*
     * 功能:新增权限
    **/
    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }
}
