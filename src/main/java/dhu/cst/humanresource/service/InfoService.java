package dhu.cst.humanresource.service;


public interface InfoService {
    //修改个人信息
    void info_change(Integer id,String name,String phone, String email,String sex,String skill,String nickname, String experience);
    //修改密码
    void passwd_change(Integer id,String passwd);
}
