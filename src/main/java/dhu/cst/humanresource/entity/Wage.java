package dhu.cst.humanresource.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Wage implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wage.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wage.basicwage
     *
     * @mbg.generated
     */
    private BigDecimal basicwage;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wage.bonus
     *
     * @mbg.generated
     */
    private BigDecimal bonus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wage.wage
     *
     * @mbg.generated
     */
    private BigDecimal wage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table wage
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wage.id
     *
     * @return the value of wage.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wage.id
     *
     * @param id the value for wage.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wage.basicwage
     *
     * @return the value of wage.basicwage
     *
     * @mbg.generated
     */
    public BigDecimal getBasicwage() {
        return basicwage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wage.basicwage
     *
     * @param basicwage the value for wage.basicwage
     *
     * @mbg.generated
     */
    public void setBasicwage(BigDecimal basicwage) {
        this.basicwage = basicwage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wage.bonus
     *
     * @return the value of wage.bonus
     *
     * @mbg.generated
     */
    public BigDecimal getBonus() {
        return bonus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wage.bonus
     *
     * @param bonus the value for wage.bonus
     *
     * @mbg.generated
     */
    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wage.wage
     *
     * @return the value of wage.wage
     *
     * @mbg.generated
     */
    public BigDecimal getWage() {
        return wage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wage.wage
     *
     * @param wage the value for wage.wage
     *
     * @mbg.generated
     */
    public void setWage(BigDecimal wage) {
        this.wage = wage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wage
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", basicwage=").append(basicwage);
        sb.append(", bonus=").append(bonus);
        sb.append(", wage=").append(wage);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}