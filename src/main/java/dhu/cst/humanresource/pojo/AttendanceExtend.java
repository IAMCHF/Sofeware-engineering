package dhu.cst.humanresource.pojo;

import dhu.cst.humanresource.entity.Attendance;

import java.util.ArrayList;
import java.util.List;

public class AttendanceExtend   {

    public List<Attendance> attendances;
    private List<String> workOnCondition;

    public AttendanceExtend(List<Attendance> attendances) {
        this.attendances=attendances;
        this.workOnCondition = new ArrayList<>();
        for (int i=0;i<attendances.size();i++) {
            if(attendances.get(i).getArrivetime()!=null)
                if((attendances.get(i).getArrivetime().before(attendances.get(i).getWorkontime())))
                    this.workOnCondition.add("准时");
                else
                    this.workOnCondition.add("迟到");
            else if(attendances.get(i).getSpecialcase() !=null)
                this.workOnCondition.add("特殊情况");
            else
                this.workOnCondition.add("缺勤");
        }
    }

    public String getWorkOnCondition(int i) {
        return workOnCondition.get(i);
    }
}
/*
   <td th:text="${record.id}"></td>
                                    <td th:text="${record.arrivetime}"> </td>
                                    <td th:text="${record.leavetime}"> </td>
                                    <td th:text="${record.specialcase}"></td>
                                    <td th:text="${record.specialcase}"></td>
 */
