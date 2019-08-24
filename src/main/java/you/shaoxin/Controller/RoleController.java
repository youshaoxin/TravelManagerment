package you.shaoxin.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import you.shaoxin.Services.RoleService;
import you.shaoxin.domin.Permission;
import you.shaoxin.domin.Role;

import java.util.List;

/**
 * 功能:
 * 创建时间: 2019-08-21 16:35 --游菜花
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    /*
     * 功能:查询所有juese
    **/
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        mv.addObject("roleList",roleList);
        mv.setViewName("role-list");
        return mv;
    }

    /*
     * 功能:新建角色
    **/
    @RequestMapping("/save.do")
    public String save(Role role) throws Exception{
        roleService.save(role);
        return "redirect:findAll.do";
    }

    /*
     * 功能:用户添加权限，根据roleid查询role.并且查询出可以添加的权限信息
    **/
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true) String roleid){
        ModelAndView mv = new ModelAndView();
        //roleid查询role
        Role role = roleService.findRoleById(roleid);
        //根据roleid查询可以添加的权限
        List<Permission> otherPermissions = roleService.findOtherPermissions(roleid);
        mv.addObject("role",role);
        mv.addObject("otherPermissions",otherPermissions);
        mv.setViewName("role-permission-add");
        return mv;
    }

    /*
     * 功能:为角色添加权限
    **/
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name="roleId",required = true) String roleId,@RequestParam(name = "ids",required = true) String[] ids){
        roleService.addPermissionToRole(roleId,ids);
        return "redirect:findAll.do";
    }
}
