package dhu.cst.humanresource.dao;

import dhu.cst.humanresource.entity.Attendance;
import dhu.cst.humanresource.entityextended.HrExtended;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface AttendanceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attendance
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer rid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attendance
     *
     * @mbg.generated
     */
    int insert(Attendance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attendance
     *
     * @mbg.generated
     */
    Attendance selectByPrimaryKey(Integer rid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attendance
     *
     * @mbg.generated
     */
    List<Attendance> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table attendance
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Attendance record);

    @Select("select * from attendance where id =  #{id}")
    List<Attendance> selectByHrId(@Param("id")int id);


    @Select("select count(1) from attendance  where date_format(arrivetime,'%Y-%m-%d')= date_format(now(),'%Y-%m-%d')  and id = #{id}")
    int selectArriveByIdAndDate(@Param("id")int id);

    @Select("select count(1) from attendance  where date_format(leavetime,'%Y-%m-%d')= date_format(now(),'%Y-%m-%d')  and id = #{id}")
    int selectLeaveByIdAndDate(@Param("id")int id);


    @Update("update attendance set leavetime = #{date} where id = #{id} and date_format(arrivetime,'%Y-%m-%d')= date_format(now(),'%Y-%m-%d')")
    int updateLeaveByIdAndNow(@Param("id")int id, @Param("date")Date date);

    @Select("select workontime from attendance where id = 0 and rid=0")
    Date selectWorkOnTime();

    @Select("select workofftime from attendance where id = 0 and rid=0")
    Date selectWorkOffTime();

    @Update("update attendance set workontime = #{date} where id = 0 ")
    int updateWorkOn(@Param("date")Date date);

    @Update("update attendance set workofftime = #{date} where id = 0 ")
    int updateWorkOff(@Param("date")Date date);

    @Select("select count(1) from attendance  where date_format(arrivetime,'%Y-%m-%d')= date_format(now(),'%Y-%m-%d')")
    int countWorkNumber();

    @Select("select count(1) from attendance  where date_format(arrivetime,'%Y-%m-%d')= date_format(now(),'%Y-%m-%d') AND attendance.`condition` =  '迟到'  ")
    int countLateWorkNumber();

    @Select("select count(1) from hr where id not in (SELECT attendance.id from attendance where date_format(arrivetime,'%Y-%m-%d')= date_format(now(),'%Y-%m-%d') and attendance.`condition` !=  '缺勤') and id != 0")
    int countNoWorkNumber();

    @Select("select hr.name,hr.id,attendance.specialcase,attendance.arrivetime from hr LEFT JOIN attendance on hr.id = attendance.id where  attendance.specialcase is not null and hr.rank < #{rank} and attendance.`condition` is null")
    List<HrExtended> selectPeopleAskForLeaveToday(@Param("rank")int rank);//date_format(arrivetime,'%Y-%m-%d')= date_format(now(),'%Y-%m-%d') and

    @Update("update attendance set attendance.`condition` = #{condition} where id = #{id} and date_format(arrivetime,'%Y-%m-%d')= date_format(#{date},'%Y-%m-%d')")
    int updateWorkConditionById(@Param("condition")String con , @Param("id")int id,@Param("date")Date date);

    @Select("select hr.name,hr.id,attendance.arrivetime,attendance.leavetime,attendance.condition,attendance.specialcase from hr INNER JOIN attendance on hr.id = attendance.id where hr.id != 0 and hr.rank < #{rank}")
    List<HrExtended> selectAttendanceRecord(@Param("rank")int rank);
}