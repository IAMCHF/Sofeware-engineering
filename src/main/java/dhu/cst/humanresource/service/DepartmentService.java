package dhu.cst.humanresource.service;

import dhu.cst.humanresource.entity.Department;

import java.util.List;

public interface DepartmentService {
    String get_departmentNamebyId(int id);
    void insertdepartment(Department dep);
    void deldepartment(int id);
    List<Department> getdepartment();
}
