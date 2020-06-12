package dhu.cst.humanresource.controller;

import dhu.cst.humanresource.service.StatisticsService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class StatisticsController {
    @Resource
    StatisticsService statisticsService;

    @GetMapping("/statistics")
    public String gotoStatistics(HttpSession session)
    {
        if ((Integer)(session.getAttribute("rank"))<4)
            return "index";
            //model.addAttribute("worktime",attendanceService.findWorkOnTime());
        else
            return "statistics";
    }


    @RequestMapping("/data.json")
    @ResponseBody
    public Map<String,Integer> statistics_hrnum(HttpServletRequest request)
    {
        Map<String,Integer> map = new HashMap<String,Integer>(statisticsService.getDepartment_Num());
        return map;
    }

    @RequestMapping("/statistics_avgsalary.json")
    @ResponseBody
    public Map<String, BigDecimal> statistics_avgsalary(HttpServletRequest request)
    {
        Map<String,BigDecimal> map = new HashMap<String,BigDecimal>();
        map=statisticsService.getDepartment_AVG_salary();
        return map;
    }

    @RequestMapping("/statistics_maxsalary.json")
    @ResponseBody
    public Map<String,BigDecimal> statistics_maxsalary(HttpServletRequest request)
    {
        Map<String,BigDecimal> map = new HashMap<String,BigDecimal>();
        map=statisticsService.getDepartment_MAX_salary();
        return map;
    }

    @RequestMapping("/statistics_minsalary.json")
    @ResponseBody
    public Map<String,BigDecimal> statistics_minsalary(HttpServletRequest request)
    {
        Map<String,BigDecimal> map = new HashMap<String,BigDecimal>();
        map=statisticsService.getDepartment_MIN_salary();
        return map;
    }

    /*
    @PostMapping ("/toExcel")
    @ResponseBody
    public String toExcel() throws IOException {
// create a new file
        FileOutputStream out = new FileOutputStream("workbook.xls");
        //Create Blank workbook
        Workbook workbook = new XSSFWorkbook();
        //Create file system using specific name
        //write operation workbook using file out object
        workbook.write(out);
        out.close();
        System.out.println("createworkbook.xlsx written successfully");
        return out;
    }*/
    @RequestMapping("/export.do")
    public @ResponseBody void export(HttpServletResponse response){
        response.setContentType("application/binary;charset=UTF-8");
        try{
            ServletOutputStream out=response.getOutputStream();
            String fileName=new String(("HrInfo "+ new SimpleDateFormat("yyyy-MM-dd").format(new Date())).getBytes(),"UTF-8");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
            String[] titles = { "员工编号", "员工姓名","昵称","性别" ,"权限","密码","入职时间", "手机号码", "邮箱地址","工资","部门编号","职务", "职业技能", "从业经历"};
            statisticsService.export(titles, out);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
