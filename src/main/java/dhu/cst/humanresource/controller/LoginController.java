package dhu.cst.humanresource.controller;

import dhu.cst.humanresource.entity.Hr;
import dhu.cst.humanresource.service.DepartmentService;
import dhu.cst.humanresource.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
public class LoginController {

    @Resource
    private EmployeeService employeeService;

    @Resource
    private DepartmentService departmentService;

    @RequestMapping("login")
    public String goLogin()
    {
        return "login";
    }
    @RequestMapping("/")
    public String geologic()
    {
        return "login";
    }

    @PostMapping(value="/login")
    public String login(@RequestParam("username") String userid, @RequestParam("password") String password, Map<String,Object> map,
                        HttpSession session){

        //验证用户名和密码，输入正确，跳转到index
        if(Pattern.matches( "(^\\d{0,9}$)",userid)&&!StringUtils.isEmpty(userid)&&employeeService.selectById(Integer.parseInt(userid))!=null&&employeeService.selectById(Integer.parseInt(userid)).getPasswd().equals(password)){
            session.setAttribute("userId",userid);
            Hr hr=new Hr();
            hr=employeeService.selectById(Integer.parseInt(String.valueOf(session.getAttribute("userId"))));
            System.out.println("测试是否正确"+hr.getName());
            session.setAttribute("name",hr.getName());
            session.setAttribute("sex",hr.getSex());
            if(hr.getNickname()!=null)
                session.setAttribute("nickname",hr.getNickname());
            session.setAttribute("entrytime",hr.getEntrytime());
            session.setAttribute("phone",hr.getPhone());
            if(hr.getEmail()!=null)
                session.setAttribute("email",hr.getEmail());
            if(hr.getSalary()!=null)
                session.setAttribute("salary",hr.getSalary());
            if(hr.getDid()!=null)
                session.setAttribute("department",departmentService.get_departmentNamebyId(hr.getDid()));
            if(hr.getAuthority()!=null)
                session.setAttribute("authority",hr.getAuthority());
            if(hr.getRank()!=null)
                session.setAttribute("rank",hr.getRank());
            if(hr.getSkill()!=null)
                session.setAttribute("skill",hr.getSkill());
            if(hr.getExperience()!=null)
                session.setAttribute("experience",new String(hr.getExperience()));
            return "redirect:/index";
        }
        else  //输入错误，清空session，提示用户名密码错误
        {
            session.invalidate();
            map.put("msg","用户名密码错误");
            return "login";
        }
    }

    @RequestMapping("/logout")
    public String loginOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }


}
