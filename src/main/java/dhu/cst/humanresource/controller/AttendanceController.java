package dhu.cst.humanresource.controller;

import dhu.cst.humanresource.entity.Attendance;
import dhu.cst.humanresource.entity.Hr;
import dhu.cst.humanresource.service.AttendanceService;
import dhu.cst.humanresource.service.EmployeeService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AttendanceController {

    @Resource
    EmployeeService employeeService;

    @Resource
    AttendanceService attendanceService;

    @GetMapping("/attendanceRecord")
    public String attendanceRecord(Model model, HttpSession session) {

        model.addAttribute("worknumber",attendanceService.queryTodayWorkNumber());
        model.addAttribute("workLatenumber",attendanceService.queryTodayLateWorkNumber());
        model.addAttribute("NoWorknumber",attendanceService.queryTodayNoWorkNumber());
        model.addAttribute("worktime",attendanceService.findWorkOnTime());
        model.addAttribute("worktimeoff",attendanceService.findWorkOffTime());
        model.addAttribute("askForLeaves",attendanceService.findHrForLeave(employeeService.selectById(Integer.parseInt(String.valueOf(session.getAttribute("userId")))).getRank()));
        model.addAttribute("workRecords",attendanceService.findAllAttendance(employeeService.selectById(Integer.parseInt(String.valueOf(session.getAttribute("userId")))).getRank()));
        return "attendanceRecord";
    }

    @GetMapping("/attendance")
    public String attendance(Model model, HttpSession session) {
        model.addAttribute("worktime",attendanceService.findWorkOnTime());
        model.addAttribute("worktimeoff",attendanceService.findWorkOffTime());
        model.addAttribute("records", attendanceService.findByEmployeeId(Integer.parseInt(String.valueOf(session.getAttribute("userId")))));
        return "attendance";
    }

    @GetMapping("/workon")
    public String workon(Model model, HttpSession session) {
        if(attendanceService.findTodayAttendance(Integer.parseInt(String.valueOf(session.getAttribute("userId")))))
        {
            model.addAttribute("records", attendanceService.findByEmployeeId(Integer.parseInt(String.valueOf(session.getAttribute("userId")))));
            model.addAttribute("worktime",attendanceService.findWorkOnTime());
            model.addAttribute("worktimeoff",attendanceService.findWorkOffTime());
            model.addAttribute("msg","已签到");
            return "attendance";
        }
        Hr hr= employeeService.selectById(Integer.parseInt(String.valueOf(session.getAttribute("userId"))));
        Attendance attendance = new Attendance();
        Calendar calendar = Calendar.getInstance();
        //DateTime time = new DateTime(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DATE),8, 30,0);
        Date dateWorkOn = attendanceService.findWorkOnTime();
        Date dateWorkOff = attendanceService.findWorkOffTime();
        DateTime dateTimeWorkOn = new DateTime(dateWorkOn);
        DateTime dateTimeWorkOff = new DateTime(dateWorkOff);
        DateTime dateTimeWorkOnToday = new DateTime(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DATE),dateTimeWorkOn.getHourOfDay(), dateTimeWorkOn.getMinuteOfHour(),0);
        DateTime dateTimeWorkOffToday = new DateTime(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DATE),dateTimeWorkOff.getHourOfDay(), dateTimeWorkOff.getMinuteOfHour(),0);
        DateTime dateTimeWorkOnLateTwoHourToday = dateTimeWorkOnToday.plusHours(2);
        Date date = new Date();
        attendance.setId(hr.getId());
        attendance.setWorkontime(dateTimeWorkOnToday.toDate());
        attendance.setWorkofftime(dateTimeWorkOffToday.toDate());
        attendance.setArrivetime(date);
        if(dateTimeWorkOnToday.toDate().before(date))
        {
            if(dateTimeWorkOnLateTwoHourToday.toDate().before(date))
                attendance.setCondition("缺勤");
            else
                attendance.setCondition("迟到");
        }
        else
            attendance.setCondition("准时");
        attendanceService.insertRecord(attendance);


         model.addAttribute("records", attendanceService.findByEmployeeId(Integer.parseInt(String.valueOf(session.getAttribute("userId")))));
        return "redirect:/attendance";
    }

    @GetMapping("/workoff")
    public String workoff(Model model, HttpSession session) {
        if(attendanceService.findTodayLeaveAttendance(Integer.parseInt(String.valueOf(session.getAttribute("userId")))))
        {
            model.addAttribute("records", attendanceService.findByEmployeeId(Integer.parseInt(String.valueOf(session.getAttribute("userId")))));
            model.addAttribute("worktime",attendanceService.findWorkOnTime());
            model.addAttribute("worktimeoff",attendanceService.findWorkOffTime());
            model.addAttribute("msg","已签退");
            return "attendance";
        }
        attendanceService.insertLeaveRecord(Integer.parseInt(String.valueOf(session.getAttribute("userId"))),new Date());

        model.addAttribute("records", attendanceService.findByEmployeeId(Integer.parseInt(String.valueOf(session.getAttribute("userId")))));
        return "redirect:/attendance";
    }

    @PostMapping(value="/speicalcase")
    public String specialCase(Model model, HttpSession session,@RequestParam("reason") String reason) {
      //  session.setAttribute("userId","1");
      //  session.setAttribute("name",employeeService.selectById(Integer.parseInt(String.valueOf(session.getAttribute("userId")))).getName());


        if(attendanceService.findTodayAttendance(Integer.parseInt(String.valueOf(session.getAttribute("userId")))))
        {
            model.addAttribute("records", attendanceService.findByEmployeeId(Integer.parseInt(String.valueOf(session.getAttribute("userId")))));
            model.addAttribute("msg","已签到");
            return "attendance";
        }
        Attendance attendance = new Attendance();
        attendance.setId(Integer.parseInt(String.valueOf(session.getAttribute("userId"))));
        attendance.setSpecialcase(reason);
        attendance.setArrivetime(new Date());
        attendanceService.insertRecord(attendance);

        model.addAttribute("records", attendanceService.findByEmployeeId(Integer.parseInt(String.valueOf(session.getAttribute("userId")))));
        return "redirect:/attendance";
    }

    @PostMapping(value="/editworkon")
    public String editWorkOn(Model model, HttpSession session,@RequestParam("workon") String workOn)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        try {
            Date date= sdf.parse(workOn);
            attendanceService.updateWorkOnTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "redirect:/attendanceRecord";
    }

    @PostMapping(value="/editworkoff")
    public String editWorkOff(Model model, HttpSession session,@RequestParam("workoff") String workOff)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            Date date= sdf.parse(workOff);
            attendanceService.updateWorkOffTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "redirect:/attendanceRecord";
    }

    @GetMapping("/forleave")
    public String allowForLeaveOrNot(Model model, HttpSession session,@RequestParam("id") int id,@RequestParam("allowed")boolean allowed,@RequestParam("time")String date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        System.out.println(id+"  "+allowed+date);
        SimpleDateFormat sf1 = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy", Locale.ENGLISH);
        Date date1;
        try {
            date1 = sf1.parse(date);
            if(allowed)
                attendanceService.updateWorkConditionById("准时",id,date1);
            else
                attendanceService.updateWorkConditionById("缺勤",id,date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }




        return "redirect:/attendanceRecord";
    }
}

/*

 */
