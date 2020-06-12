package dhu.cst.humanresource.service;

import dhu.cst.humanresource.entity.Attendance;
import dhu.cst.humanresource.entityextended.HrExtended;

import java.util.Date;
import java.util.List;

public interface AttendanceService {

   List<Attendance> findByEmployeeId(int id);

   boolean insertRecord(Attendance attendance);

   boolean findTodayAttendance(int id);

   boolean insertLeaveRecord(int id,Date leave);

   boolean findTodayLeaveAttendance(int id);

   Date findWorkOnTime();

   Date findWorkOffTime();

   boolean updateWorkOnTime(Date date);

   boolean updateWorkOffTime(Date date);

   int queryTodayWorkNumber();

   int queryTodayLateWorkNumber();

   int queryTodayNoWorkNumber();

   List<HrExtended> findHrForLeave(int rank);

   boolean updateWorkConditionById(String con,int id,Date date);

   List<HrExtended> findAllAttendance(int rank);
}
