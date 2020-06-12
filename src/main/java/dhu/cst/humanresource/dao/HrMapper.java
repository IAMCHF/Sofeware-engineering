package dhu.cst.humanresource.dao;

import dhu.cst.humanresource.entity.Hr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;
@Mapper
public interface HrMapper {
    //计算平均工资
    BigDecimal select_avgSalary_from_sameDep(Integer department_id);

    //计算最高工资
    BigDecimal select_maxSalary_from_sameDep(Integer department_id);

    //计算最小工资
    BigDecimal select_minSalary_from_sameDep(Integer department_id);

    //计算来自同一个部门的人数
    int getHr_Num_fromSame_Department(Integer department_id);

    //计算未定部门员工的人数
    int getHrnoDepSum();

    //更新个人信息
    void updateInfo(Integer id, String name,String phone, String email, String sex, String skill, String nickname, byte[] experience);

    //更新密码
    void updatePasswd(Integer id, String passwd);



    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr
     *
     * @mbg.generated
     */
    int insert(Hr record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr
     *
     * @mbg.generated
     */
    Hr selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr
     *
     * @mbg.generated
     */
    @Update("update hr set authority=#{Auth},hr.rank=#{Rank} where id=#{ID}")
    void updateauth(@Param("ID") Integer id,@Param("Auth") String auth,@Param("Rank") Integer rank);
    @Update("update hr set hr.salary=#{Sal} where id=#{ID}")
    void updatesalary(@Param("ID") Integer id,@Param("Sal") double salary);
    @Select("select id from hr where authority=#{auth}")
    List<Integer> selectauth(@Param("auth") String authority);
    List<Hr> selectAll();
    @Select("select * from hr where hr.rank<#{Rank} and did=#{Did}")
    List<Hr>selectmysubordinate(@Param("Rank") Integer rank,@Param("Did") Integer did);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Hr record);
}