package dhu.cst.humanresource.service;


import dhu.cst.humanresource.entity.Hr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeService {

     int deleteemployee(int id);
     void updateauthority(int id,String authority);
     Hr selectById(int id);
     List<Hr> selectAllemployee();
     List<Hr>selectmysubordinate(int rank,int id);
     void updatesalary(int id,  double salary);
     List<Integer>selectauth(String authritory);
}
