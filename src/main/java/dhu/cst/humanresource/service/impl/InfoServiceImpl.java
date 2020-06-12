package dhu.cst.humanresource.service.impl;

import dhu.cst.humanresource.dao.HrMapper;
import dhu.cst.humanresource.service.InfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Base64;

@Service
public class InfoServiceImpl implements InfoService {
    @Resource
    private HrMapper hrMapper;
    @Override
    public void info_change(Integer id, String name, String phone, String email, String sex, String skill, String nickname, String experience) {
        try {
            //初始 byte[]
            byte[] bytes = experience.getBytes();
            //Base64 编码
            String encoded = Base64.getEncoder().encodeToString(bytes);
            //Base64 解码
            byte[] decoded = Base64.getDecoder().decode(encoded);
            //输出转换后内容
            System.out.println( new String(decoded) );
            hrMapper.updateInfo(id,name,phone,email,sex,skill,nickname,decoded);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void passwd_change(Integer id, String passwd) {//修改hr.id=id的员工账号密码
        hrMapper.updatePasswd(id,passwd);
    }
}
