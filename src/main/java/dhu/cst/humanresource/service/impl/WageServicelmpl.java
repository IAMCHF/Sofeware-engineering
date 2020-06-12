package dhu.cst.humanresource.service.impl;

import dhu.cst.humanresource.dao.WageMapper;
import dhu.cst.humanresource.entity.Wage;
import dhu.cst.humanresource.service.WageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class WageServicelmpl implements WageService
{
    @Resource
    WageMapper mapper;
    @Override
    public void upbonus(int id, BigDecimal num) {
        mapper.updatebonus(id,num);

    }

    @Override
    public void insertwage(Wage newwage) {
        mapper.insert(newwage);

    }

    @Override
    public Wage selectwageid(int id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public void deletewage(int id) {
        mapper.deletewage(id);
    }

    @Override
    public void updatebasicwage(int id, BigDecimal num) {
        mapper.updatebasicwage(id,num);
    }

    @Override
    public void updatewage(int id, BigDecimal num) {
        mapper.updatewage(id,num);
    }
}
