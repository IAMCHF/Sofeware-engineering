package dhu.cst.humanresource.service;
import javax.servlet.ServletOutputStream;
import java.math.BigDecimal;
import java.util.Map;

public interface StatisticsService {
    Map<String,Integer> getDepartment_Num();//返回（部门，部门人数）键值对
    Map<String, BigDecimal> getDepartment_AVG_salary();//返回（部门名，部门平均工资）键值对
    Map<String,BigDecimal> getDepartment_MAX_salary();//返回（部门名，最高工资）键值对
    Map<String,BigDecimal> getDepartment_MIN_salary();//返回（部门名，最低工资）键值对
    public void export(String[] titles, ServletOutputStream out);//导出excel功能
    //Map<String,BigDecimal> getDepartment_AVG_bonus();
}
