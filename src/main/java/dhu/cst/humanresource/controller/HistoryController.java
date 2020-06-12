package dhu.cst.humanresource.controller;
import dhu.cst.humanresource.entity.Department;
import dhu.cst.humanresource.service.EmployeeService;
import dhu.cst.humanresource.service.HistoryService;
import dhu.cst.humanresource.service.impl.DepartmentServiceImpl;
import dhu.cst.humanresource.service.impl.HistoryServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.regex.Pattern;
@Controller
public class HistoryController {
    @Resource
    HistoryServiceImpl historyservice;

    @GetMapping("/historyfind")
    public String gomanagement(Model model,HttpSession session){
        if((Integer)(session.getAttribute("rank"))<4)
        {
            return "index";
        }
        model.addAttribute("historyrecords",historyservice.selcetallhistory());
        return "historyfind";
    }
}
