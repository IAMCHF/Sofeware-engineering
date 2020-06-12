package dhu.cst.humanresource.service;
import dhu.cst.humanresource.entity.Wage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

public interface WageService {
    void upbonus(int id, BigDecimal num);
    void insertwage(Wage newwage);
    Wage selectwageid(int id);

    void deletewage( int id);
    void updatebasicwage(int id,BigDecimal num);
    void updatewage(int id, BigDecimal num);


}
