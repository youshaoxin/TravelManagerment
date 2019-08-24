package you.shaoxin.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import you.shaoxin.Services.UserService;
import you.shaoxin.domin.Role;
import you.shaoxin.domin.UserInfo;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * 功能:
 * 创建时间: 2019-08-14 09:11 --游菜花
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /*
     * 功能:保存用户
    **/
    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) throws Exception {
        System.out.println("##############");
        System.out.println(userInfo.toString());

        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    /*
     * 功能:查找所有用户
    **/
    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll();
        mv.addObject("userList",userList);
        mv.setViewName("user-list");
        return mv;
    }

    /*
     * 功能:根据ID查询用户
    **/
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    /*
     * 功能:查询用户和用户可以添加的角色
    **/
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name="id",required = true) String userid) throws Exception{
        ModelAndView mv = new ModelAndView();
        //根据用户id查询用户
        UserInfo userInfo = userService.findById(userid);
        //根据用户id查询可以添加的角色
        List<Role> otherRoles = userService.findOtherRoles(userid);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",otherRoles);
        mv.setViewName("user-role-add");
        return mv;

    }
    /*
     * 功能:为用户添加角色
    **/
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userid,@RequestParam(name = "ids",required = true) String[] roleIds){
        userService.addRoleToUser(userid,roleIds);
        return "redirect:findAll.do";
    }

}
