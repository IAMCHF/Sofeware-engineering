package dhu.cst.humanresource.service.impl;

import dhu.cst.humanresource.dao.DepartmentMapper;
import dhu.cst.humanresource.dao.HrMapper;
import dhu.cst.humanresource.entity.Department;
import dhu.cst.humanresource.service.DepartmentService;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;
    @Override
    public String get_departmentNamebyId(int id) {
        return departmentMapper.selectByPrimaryKey(id).getDepartmentname();
    }

    @Override
    public void insertdepartment(Department dep) {
        departmentMapper.insert(dep);
    }

    @Override
    public void deldepartment(int id) {
        departmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Department> getdepartment() {
        return departmentMapper.selectAll();
    }
}
