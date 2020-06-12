package dhu.cst.humanresource.service.impl;


import dhu.cst.humanresource.dao.AttendanceMapper;
import dhu.cst.humanresource.entity.Attendance;
import dhu.cst.humanresource.entityextended.HrExtended;
import dhu.cst.humanresource.service.AttendanceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Resource
    private  AttendanceMapper attendanceMapper;


    @Override
    public List<Attendance> findByEmployeeId(int id) {
        return attendanceMapper.selectByHrId(id);
    }

    @Override
    public boolean insertRecord(Attendance attendance) { return attendanceMapper.insert(attendance) > 0; }

    @Override
    public boolean findTodayAttendance(int id) {
        return attendanceMapper.selectArriveByIdAndDate(id) > 0;
    }

    @Override
    public boolean findTodayLeaveAttendance(int id) {
        return attendanceMapper.selectLeaveByIdAndDate(id) > 0;
    }

    @Override
    public Date findWorkOnTime() {
        return attendanceMapper.selectWorkOnTime();
    }

    @Override
    public Date findWorkOffTime() {
        return attendanceMapper.selectWorkOffTime();
    }

    @Override
    public boolean updateWorkOnTime(Date date) {
        return attendanceMapper.updateWorkOn(date) > 0 ;
    }

    @Override
    public boolean updateWorkOffTime(Date date) {
        return attendanceMapper.updateWorkOff(date) > 0 ;
    }

    @Override
    public int queryTodayWorkNumber() { return attendanceMapper.countWorkNumber(); }

    @Override
    public int queryTodayLateWorkNumber() { return attendanceMapper.countLateWorkNumber(); }

    @Override
    public int queryTodayNoWorkNumber() { return attendanceMapper.countNoWorkNumber(); }

    @Override
    public List<HrExtended> findHrForLeave(int rank) { return attendanceMapper.selectPeopleAskForLeaveToday(rank); }

    @Override
    public boolean updateWorkConditionById(String con, int id,Date date) { return attendanceMapper.updateWorkConditionById(con,id,date) > 0; }

    @Override
    public List<HrExtended> findAllAttendance(int rank) { return attendanceMapper.selectAttendanceRecord(rank); }

    @Override
    public boolean insertLeaveRecord(int id,Date leave) { return attendanceMapper.updateLeaveByIdAndNow(id, leave) > 0; }




}
