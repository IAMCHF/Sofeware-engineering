package dhu.cst.humanresource.controller;

import dhu.cst.humanresource.config.LoadProperty;
import dhu.cst.humanresource.entity.Hr;
import dhu.cst.humanresource.entity.Wage;
import dhu.cst.humanresource.service.DepartmentService;
import dhu.cst.humanresource.service.RegisterService;
import dhu.cst.humanresource.service.WageService;
import dhu.cst.humanresource.service.impl.WageServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RegisterController {
    @Resource
    private RegisterService registerService;
    //只接受post方式的请求
    @Resource
    private WageServicelmpl wageservice;
    @Resource
    private DepartmentService departmentService;
    @Autowired
    LoadProperty basicw;


    @RequestMapping("/register")
    public String to_register()
    {
        return "register";
    }

    @RequestMapping("/department_data.json")
    @ResponseBody
    public Map<String,String> department_select()
    {
        Map<String,String> map = new HashMap<String,String>(registerService.getDepartment_and_id());
        return map;
    }

    @PostMapping("/register_valid")
    public String registerValid(Model model, HttpSession session,
                                @RequestParam(value = "id",required = true) String id,
                                @RequestParam("name")String name,
                                @RequestParam("phone")String phone,
                                @RequestParam("entrytime") String entrytime,
                                @RequestParam(value = "email",required=false,defaultValue = "")String email,
                                @RequestParam("sex")String sex,
                                @RequestParam(value = "skill",required=false,defaultValue = "")String skill,
            /*@RequestParam(value = "salary",required=false,defaultValue = "null")String salary,*/
                                @RequestParam(value = "did")String did,
                                /*@RequestParam(value = "authority",required=false,defaultValue = "null")String authority,*/
                                @RequestParam(value = "rank")String rank,
                                @RequestParam("passwd")String passwd,
                                @RequestParam(value = "nickname",required=false,defaultValue = "")String nickname,
                                @RequestParam(value = "experience",required=false,defaultValue = "")String experience) throws ParseException {
        //System.out.println(entrytime);
        //System.out.println(did);
        //System.out.println(authority);
        Hr hr=new Hr();
        //return "register_success";
        hr.setId(Integer.parseInt(String.valueOf(id)));
        hr.setName(name);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//yyyy-mm-dd, 会出现时间不对, 因为小写的mm是代表: 秒
        Date utilDate = sdf.parse(entrytime);
        hr.setEntrytime(utilDate);
        hr.setPhone(phone);
        hr.setEmail(email);
        hr.setSex(sex);
        hr.setSkill(skill);
        /*这里填写salary默认值*/
        hr.setSalary(BigDecimal.valueOf(Double.valueOf(basicw.getGroupmember())));
        if (!String.valueOf(did).contentEquals("")&&departmentService.get_departmentNamebyId(Integer.parseInt(String.valueOf(did)))!=null)
        {
            //register页面填写部门号did不为空，且该部门存在
            //System.out.println("did为null");
            hr.setDid(Integer.parseInt(String.valueOf(did)));
        }
        if (!String.valueOf(rank).contentEquals(""))
        {
            //System.out.println("rank为null");
            hr.setRank(Integer.parseInt(String.valueOf(rank)));
        }
        hr.setPasswd(passwd);
        hr.setAuthority("员工");
        hr.setNickname(nickname);
        byte[] byteArray = String.valueOf(experience).getBytes();
        hr.setExperience(byteArray);
        if(registerService.insertById(hr.getId(),hr))//数据库中wage有外键，必须存在该员工才能设置工资
        {
            Wage wage=new Wage();
            wage.setId(Integer.parseInt(String.valueOf(id)));
            wage.setBasicwage(BigDecimal.valueOf(Double.valueOf(basicw.getGroupmember())));
            wage.setBonus(BigDecimal.valueOf(0));
            wage.setWage(BigDecimal.valueOf(Double.valueOf(basicw.getGroupmember())));
            wageservice.insertwage(wage);
            return "register_success";//注册成功
        }
        else
        {
            return "register_failure";//注册失败
        }
    }
}
