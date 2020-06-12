package dhu.cst.humanresource.controller;

import com.mysql.cj.util.StringUtils;
import dhu.cst.humanresource.entity.Department;
import dhu.cst.humanresource.entity.History;
import dhu.cst.humanresource.entity.Hr;
import dhu.cst.humanresource.entity.Wage;
import dhu.cst.humanresource.service.EmployeeService;
import dhu.cst.humanresource.service.impl.DepartmentServiceImpl;
import dhu.cst.humanresource.service.impl.HistoryServiceImpl;
import dhu.cst.humanresource.service.impl.WageServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import dhu.cst.humanresource.config.LoadProperty;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller

public class EmployeeController {
    @Resource
    EmployeeService employeeservice;
    @Resource
    WageServicelmpl wageservice;
    @Resource
    HistoryServiceImpl historyservice;

    History history=new History();
    @Autowired
    LoadProperty basicw;

    @Resource
    DepartmentServiceImpl departmentservice;

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
             }
        Matcher isNum = pattern.matcher(bigStr); // matcher是全匹配
        if (!isNum.matches())
        {
            return false;
        }
        return true;
    }
    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }
    @GetMapping("/employeemanagement")
    public String gomanagement(Model model, HttpSession session){
        if(employeeservice.selectById((Integer.parseInt(String.valueOf(session.getAttribute("userId"))))).getAuthority().equals("总经理"))
        {
            model.addAttribute("records",employeeservice.selectAllemployee());
        }
        else
        {
            model.addAttribute("records",employeeservice.selectmysubordinate(employeeservice.selectById((Integer.parseInt(String.valueOf(session.getAttribute("userId"))))).getRank(),employeeservice.selectById((Integer.parseInt(String.valueOf(session.getAttribute("userId"))))).getDid()));
        }
        model.addAttribute("authority",employeeservice.selectById(Integer.parseInt(String.valueOf(session.getAttribute("userId")))).getAuthority());
        model.addAttribute("ran",employeeservice.selectById(Integer.parseInt(String.valueOf(session.getAttribute("userId")))).getRank());
        model.addAttribute("dLists",departmentservice.getdepartment());
        return "employeemanagement";
    }
    @PostMapping(value="/authchange")
    public String specialCase(Model model, HttpSession session,@RequestParam("emid") String employeeid,@RequestParam("auth") String authority) {
        if(employeeservice.selectById(Integer.parseInt(employeeid))==null)
        {
            return "changefail";
        }
        if(employeeservice.selectById(Integer.parseInt(String.valueOf(session.getAttribute("userId")))).getRank()>employeeservice.selectById(Integer.parseInt(employeeid)).getRank())
        {
            if(employeeservice.selectById(Integer.parseInt(String.valueOf(session.getAttribute("userId")))).getAuthority().equals("总经理")||employeeservice.selectById(Integer.parseInt(String.valueOf(session.getAttribute("userId")))).getDid()==employeeservice.selectById(Integer.parseInt(employeeid)).getDid())
            {
                employeeservice.updateauthority(Integer.parseInt(employeeid),authority);


                    if(authority.equals("总经理"))
                    {
                        wageservice.updatebasicwage(Integer.parseInt(employeeid),BigDecimal.valueOf(Double.valueOf(basicw.getGemanager())));
                        wageservice.updatewage(Integer.parseInt(employeeid),BigDecimal.valueOf(Double.valueOf(basicw.getGemanager())).add(wageservice.selectwageid(Integer.parseInt(employeeid)).getBonus()));
                        employeeservice.updatesalary(Integer.parseInt(employeeid),wageservice.selectwageid(Integer.parseInt(employeeid)).getWage().doubleValue());
                    }
                    else if(authority.equals("经理"))
                    {
                        wageservice.updatebasicwage(Integer.parseInt(employeeid),BigDecimal.valueOf(Double.valueOf(basicw.getManager())));
                        wageservice.updatewage(Integer.parseInt(employeeid),BigDecimal.valueOf(Double.valueOf(basicw.getManager())).add(wageservice.selectwageid(Integer.parseInt(employeeid)).getBonus()));
                        employeeservice.updatesalary(Integer.parseInt(employeeid),wageservice.selectwageid(Integer.parseInt(employeeid)).getWage().doubleValue());
                    }
                    else if(authority.equals("组长"))
                    {
                        wageservice.updatebasicwage(Integer.parseInt(employeeid),BigDecimal.valueOf(Double.valueOf(basicw.getGrleader())));
                        wageservice.updatewage(Integer.parseInt(employeeid),BigDecimal.valueOf(Double.valueOf(basicw.getGrleader())).add(wageservice.selectwageid(Integer.parseInt(employeeid)).getBonus()));
                        employeeservice.updatesalary(Integer.parseInt(employeeid),wageservice.selectwageid(Integer.parseInt(employeeid)).getWage().doubleValue());
                    }
                    else if(authority.equals("员工"))
                    {
                        wageservice.updatebasicwage(Integer.parseInt(employeeid),BigDecimal.valueOf(Double.valueOf(basicw.getGroupmember())));
                        wageservice.updatewage(Integer.parseInt(employeeid),BigDecimal.valueOf(Double.valueOf(basicw.getGroupmember())).add(wageservice.selectwageid(Integer.parseInt(employeeid)).getBonus()));
                        employeeservice.updatesalary(Integer.parseInt(employeeid),wageservice.selectwageid(Integer.parseInt(employeeid)).getWage().doubleValue());
                    }

                return "changesuccess";
            }
        }

        return "changefail";
    }
    @PostMapping(value="/bonuschange")
    public String bonuschange1(Model model,HttpSession session,@RequestParam("emid") String employeeid,@RequestParam("bonus")String bonus)
    {
        if(employeeservice.selectById(Integer.parseInt(employeeid))==null)
        {
            return "changefail";
        }
        if(bonus==null&&isNumeric(bonus)==false)
        {
            return "changefail";
        }
        if(employeeservice.selectById(Integer.parseInt(String.valueOf(session.getAttribute("userId")))).getRank()>employeeservice.selectById(Integer.parseInt(employeeid)).getRank())
        {
            if(employeeservice.selectById(Integer.parseInt(String.valueOf(session.getAttribute("userId")))).getAuthority().equals("总经理")||employeeservice.selectById(Integer.parseInt(String.valueOf(session.getAttribute("userId")))).getDid()==employeeservice.selectById(Integer.parseInt(employeeid)).getDid())
            {

                wageservice.upbonus(Integer.parseInt(employeeid), BigDecimal.valueOf(Double.parseDouble(bonus)));
                BigDecimal num1=wageservice.selectwageid(Integer.parseInt(employeeid)).getWage().add(BigDecimal.valueOf(Double.parseDouble(bonus)));
                wageservice.updatewage(Integer.parseInt(employeeid),num1);
                employeeservice.updatesalary(Integer.parseInt(employeeid),wageservice.selectwageid(Integer.parseInt(employeeid)).getWage().doubleValue());
                return "changesuccess";
            }
        }
        return "changefail";
    }
    @PostMapping(value="/deletechange")
    public String bonuschange1(Model model,HttpSession session,@RequestParam("emid") String employeeid) {
        if(employeeservice.selectById(Integer.parseInt(employeeid))==null)
        {
            return "changefail";
        }
        if(employeeservice.selectById(Integer.parseInt(String.valueOf(session.getAttribute("userId")))).getAuthority().equals("总经理")){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Hr hr=employeeservice.selectById(Integer.parseInt(employeeid));
            history.setId(hr.getId());
            history.setResignation(new Date());
            history.setAuthority(hr.getRank());
            history.setSkill(hr.getSkill());
            history.setDepartmentname(hr.getDid());
            history.setEmail(hr.getEmail());
            history.setEntrytime(hr.getEntrytime());
            history.setExperience(new String(hr.getExperience()));
            history.setName(hr.getName());
            history.setPhone(hr.getPhone());
            history.setSalary(hr.getSalary());
            history.setRank(hr.getAuthority());
            historyservice.inserthistory(history);
            wageservice.deletewage(Integer.parseInt(employeeid));
            employeeservice.deleteemployee(Integer.parseInt(employeeid));
            return "changesuccess";

        }
        return "changefail";
    }
    @PostMapping(value="/basicwagechange")
    public String basicwagechange(Model model, HttpSession session,@RequestParam("auth") String employeeid,@RequestParam("basicwage") String authority) {
        if(authority==null&&isNumeric(authority)==false)
        {
            return "changefail";
        }
        if(employeeservice.selectById(Integer.parseInt(String.valueOf(session.getAttribute("userId")))).getAuthority().equals("总经理"))
        {
            List<Integer> templist= employeeservice.selectauth(employeeid);
            if(employeeid.equals("总经理"))
            {
                basicw.setGemanager(authority);
            }
            else if(employeeid.equals("经理"))
            {
                basicw.setManager(authority);
            }
            else if(employeeid.equals("组长"))
            {
                basicw.setGrleader(authority);
            }
            else if(employeeid.equals("员工"))
            {
                basicw.setGroupmember(authority);
            }


            for(int i=0;i<=templist.size()-1;i++)
            {
                wageservice.updatebasicwage(templist.get(i),BigDecimal.valueOf(Double.parseDouble(authority)));
                BigDecimal bigdecimal=wageservice.selectwageid(templist.get(i)).getBonus().add(BigDecimal.valueOf(Double.parseDouble(authority)));
                wageservice.updatewage(templist.get(i),bigdecimal);
                employeeservice.updatesalary(templist.get(i),bigdecimal.doubleValue());
            }


            return "changesuccess";
        }
        return "changefail";

    }

    @PostMapping(value = "/departmentadd")
    public String departadd(Model model,HttpSession session,@RequestParam ("dname")String name)
    {
        if(employeeservice.selectById(Integer.parseInt(String.valueOf(session.getAttribute("userId")))).getAuthority().equals("总经理")) {
            System.out.println(name);
            Department dep = new Department();
            dep.setDepartmentname(name);
            departmentservice.insertdepartment(dep);
        }
        return "redirect:/employeemanagement";
    }
    @PostMapping(value = "/departmentdel")
    public String departdel(HttpSession session,@RequestParam ("did")String did)
    {
        if(employeeservice.selectById(Integer.parseInt(String.valueOf(session.getAttribute("userId")))).getAuthority().equals("总经理"))
        {
            departmentservice.deldepartment(Integer.parseInt(did));
        }
        return "redirect:/employeemanagement";
    }
}
