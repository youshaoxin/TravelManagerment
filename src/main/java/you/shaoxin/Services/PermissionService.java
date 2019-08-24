package you.shaoxin.Services;

import you.shaoxin.domin.Permission;

import java.util.List;

/**
 * 游菜花 -
 * 创建时间: 2019-08-21 17:04
 */
public interface PermissionService {

    List<Permission> findAll() throws Exception;

    void save(Permission permission) throws Exception;
}
