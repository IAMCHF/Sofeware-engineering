package dhu.cst.humanresource.service.impl;

import dhu.cst.humanresource.dao.DepartmentMapper;
import dhu.cst.humanresource.dao.HrMapper;
import dhu.cst.humanresource.entity.Hr;
import dhu.cst.humanresource.service.RegisterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Resource
    private HrMapper hrMapper;
    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public boolean insertById(int id, Hr hr) {//插入员工信息，成功返回true，失败返回false
        if(hrMapper.selectByPrimaryKey(id)==null)//账号不存在
        {
            hrMapper.insert(hr);//插入员工信息
            return true;//插入信息成功
        }
        else
        {
            return false;//插入信息失败
        }
    }

    //新增
    List<Integer> department_index=new ArrayList<Integer>();
    @Override
    public Map<String, String> getDepartment_and_id() {//最终返回Map<部门id，部门名>
        Map<String,String> map= new HashMap<String,String>();
        department_index=departmentMapper.selectAll_id();//所有部门id列表
        Iterator<Integer> it = department_index.iterator();
        while (it.hasNext()) {
            Integer department_id=it.next();//部门id
            //System.out.println("部门id="+String.valueOf(department_id));
            map.put(departmentMapper.selectName_byPrimaryKey(department_id),String.valueOf(department_id));
        }
        return map;
    }
}
