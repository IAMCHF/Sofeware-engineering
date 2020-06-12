package dhu.cst.humanresource.controller;

import dhu.cst.humanresource.dao.HrMapper;
import dhu.cst.humanresource.entity.Hr;
import dhu.cst.humanresource.service.DepartmentService;
import dhu.cst.humanresource.service.EmployeeService;
import dhu.cst.humanresource.service.InfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class IndexController {
    @Resource
    EmployeeService employeeService;
    @Resource
    DepartmentService departmentService;
    @Resource
    InfoService infoService;
    @RequestMapping("/password_change")//跳转到修改密码界面
    public String toPasswdChange(HttpSession session)
    {
        return "password_change";
    }
    @RequestMapping("/password_info")//跳转到修改密码界面
    public String passwd_info(HttpSession session,@RequestParam("passwd")String passwd)//在前端界面已进行了数据验证
    {
        infoService.passwd_change(Integer.parseInt(String.valueOf(session.getAttribute("userId"))),passwd);
        return "redirect:/index";
    }
    @RequestMapping("/info")//修改个人信息
    public String infoChange(HttpSession session,
                             @RequestParam("name")String name,
                             @RequestParam("phone")String phone,
                             @RequestParam(value = "email",required=false,defaultValue = "")String email,
                             @RequestParam("sex")String sex,
                             @RequestParam(value = "skill",required=false,defaultValue = "")String skill,
                             @RequestParam(value = "nickname",required=false,defaultValue = "")String nickname,
                             @RequestParam(value = "experience",required=false,defaultValue = "")String experience)
    {
        try {
            /*不能使用实体，创建实体后不会对数据库进行修改，只有插入新数据时使用实体或者仅仅获取值时
            Hr hr=new Hr();
            hr.setId();
            hr=employeeService.selectById(Integer.parseInt(String.valueOf(session.getAttribute("userId"))));
            hr.setName(name);
            System.out.println("测试hr属性值"+String.valueOf(hr.getId())+String.valueOf(hr.getName()));
            hr.setPhone(phone);
            hr.setEmail(email);
            hr.setSex(sex);
            hr.setSkill(skill);
            hr.setNickname(nickname);
            byte[] byteArray = String.valueOf(experience).getBytes();
            hr.setExperience(byteArray);
            */
            infoService.info_change(Integer.parseInt(String.valueOf(session.getAttribute("userId"))),name
                                    ,phone,email,sex,skill,nickname,experience);
            session.setAttribute("name",name);//重新设置姓名
            session.setAttribute("sex",sex);//重新设置性别
            session.setAttribute("nickname",nickname);//重新设置昵称
            session.setAttribute("phone",phone);//重新设置手机号
            session.setAttribute("email",email);//重新设置邮箱地址
            session.setAttribute("skill",skill);//重新设置技能
            session.setAttribute("experience",experience);//重新设置从业经历
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String refresh(Model model, HttpSession session)
    {
        try {
            Hr hr= new Hr();
            hr=employeeService.selectById(Integer.parseInt(String.valueOf(session.getAttribute("userId"))));
            //System.out.println("输出修改后name"+String.valueOf(hr.getName()));
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
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "index";
    }
}
