package you.shaoxin.Services;

import org.springframework.security.core.userdetails.UserDetailsService;
import you.shaoxin.domin.Role;
import you.shaoxin.domin.UserInfo;

import java.util.List;

/**
 * 游菜花 -
 * 创建时间: 2019-08-14 09:18
 */
public interface UserService extends UserDetailsService {

    List<UserInfo> findAll() throws Exception;

    void save(UserInfo userInfo) throws Exception;

    UserInfo findById(String id) throws Exception;

    /*
     * 功能:查询用户和用户可以添加的角色
     **/
    List<Role> findOtherRoles(String id);

    /*
     * 功能:为用户添加角色
     **/
    void addRoleToUser(String id,String[] roles);
}
