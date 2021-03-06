package dhu.cst.humanresource.dao;

import dhu.cst.humanresource.entity.History;
import java.util.List;

public interface HistoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history
     *
     * @mbg.generated
     */
    int insert(History record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history
     *
     * @mbg.generated
     */
    History selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history
     *
     * @mbg.generated
     */
    List<History> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(History record);
}