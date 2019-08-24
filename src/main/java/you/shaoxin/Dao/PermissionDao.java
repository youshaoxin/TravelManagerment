package you.shaoxin.Dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import you.shaoxin.domin.Permission;

import java.util.List;

/**
 * 游菜花 -
 * 创建时间: 2019-08-21 10:41
 */
public interface PermissionDao {

    /*
     * 功能:根据id查找权限
    **/
    @Select("select * from permission where id in (select permissionid from role_permission where roleid = #{id})")
    public List<Permission> findPermissionByRoleId(String id) throws Exception;

    /*
     * 功能:查找所有权限
    **/
    @Select("select * from Permission")
    List<Permission> findAll();

    /*
     * 功能:新增权限
    **/
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission) throws Exception;
}
