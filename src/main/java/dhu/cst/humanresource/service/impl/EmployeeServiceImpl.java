package dhu.cst.humanresource.service.impl;


import dhu.cst.humanresource.dao.HrMapper;
import dhu.cst.humanresource.entity.Hr;
import dhu.cst.humanresource.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private HrMapper HrMapper;

    @Override
    public Hr selectById(int id) {
        return HrMapper.selectByPrimaryKey(id);
    }
    @Override
    public int deleteemployee(int id) {
        return HrMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateauthority(int id, String authority) {
        if (authority.equals("总经理")) {
            HrMapper.updateauth(id, authority, 4);
        } else if (authority.equals("经理"))
        {
            HrMapper.updateauth(id, authority, 3);
        }
        else if (authority.equals("组长"))
        {
            HrMapper.updateauth(id, authority, 2);
        }
        else if(authority.equals("员工"))
        {
            HrMapper.updateauth(id, authority, 1);
        }
        else if(authority.equals("实习员工"))
        {
            HrMapper.updateauth(id, authority, 0);
        }

    }

    @Override
    public List<Hr> selectAllemployee() {
        return HrMapper.selectAll();
    }

    @Override
    public List<Hr> selectmysubordinate(int rank,int id) {
        return HrMapper.selectmysubordinate(rank,id);
    }

    @Override
    public void updatesalary(int id, double salary) {
        HrMapper.updatesalary(id,salary);
    }

    @Override
    public List<Integer> selectauth(String authritory) {
        return HrMapper.selectauth(authritory);
    }
}
