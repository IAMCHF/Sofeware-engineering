package dhu.cst.humanresource.service;

import dhu.cst.humanresource.entity.Hr;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

public interface RegisterService {
    boolean insertById(int id, Hr hr);//插入员工成功返回true，失败返回false
    Map<String,String> getDepartment_and_id();//获取部门id和部门名，放入注册页面的下拉列表中
}
