package dhu.cst.humanresource.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Hr implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr.entrytime
     *
     * @mbg.generated
     */
    private Date entrytime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr.phone
     *
     * @mbg.generated
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr.email
     *
     * @mbg.generated
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr.sex
     *
     * @mbg.generated
     */
    private String sex;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr.skill
     *
     * @mbg.generated
     */
    private String skill;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr.salary
     *
     * @mbg.generated
     */
    private BigDecimal salary;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr.did
     *
     * @mbg.generated
     */
    private Integer did;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr.authority
     *
     * @mbg.generated
     */
    private String authority;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr.rank
     *
     * @mbg.generated
     */
    private Integer rank;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr.passwd
     *
     * @mbg.generated
     */
    private String passwd;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr.nickname
     *
     * @mbg.generated
     */
    private String nickname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr.experience
     *
     * @mbg.generated
     */
    private byte[] experience;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table hr
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr.id
     *
     * @return the value of hr.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr.id
     *
     * @param id the value for hr.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr.name
     *
     * @return the value of hr.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr.name
     *
     * @param name the value for hr.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr.entrytime
     *
     * @return the value of hr.entrytime
     *
     * @mbg.generated
     */
    public Date getEntrytime() {
        return entrytime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr.entrytime
     *
     * @param entrytime the value for hr.entrytime
     *
     * @mbg.generated
     */
    public void setEntrytime(Date entrytime) {
        this.entrytime = entrytime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr.phone
     *
     * @return the value of hr.phone
     *
     * @mbg.generated
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr.phone
     *
     * @param phone the value for hr.phone
     *
     * @mbg.generated
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr.email
     *
     * @return the value of hr.email
     *
     * @mbg.generated
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr.email
     *
     * @param email the value for hr.email
     *
     * @mbg.generated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr.sex
     *
     * @return the value of hr.sex
     *
     * @mbg.generated
     */
    public String getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr.sex
     *
     * @param sex the value for hr.sex
     *
     * @mbg.generated
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr.skill
     *
     * @return the value of hr.skill
     *
     * @mbg.generated
     */
    public String getSkill() {
        return skill;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr.skill
     *
     * @param skill the value for hr.skill
     *
     * @mbg.generated
     */
    public void setSkill(String skill) {
        this.skill = skill == null ? null : skill.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr.salary
     *
     * @return the value of hr.salary
     *
     * @mbg.generated
     */
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr.salary
     *
     * @param salary the value for hr.salary
     *
     * @mbg.generated
     */
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr.did
     *
     * @return the value of hr.did
     *
     * @mbg.generated
     */
    public Integer getDid() {
        return did;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr.did
     *
     * @param did the value for hr.did
     *
     * @mbg.generated
     */
    public void setDid(Integer did) {
        this.did = did;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr.authority
     *
     * @return the value of hr.authority
     *
     * @mbg.generated
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr.authority
     *
     * @param authority the value for hr.authority
     *
     * @mbg.generated
     */
    public void setAuthority(String authority) {
        this.authority = authority == null ? null : authority.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr.rank
     *
     * @return the value of hr.rank
     *
     * @mbg.generated
     */
    public Integer getRank() {
        return rank;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr.rank
     *
     * @param rank the value for hr.rank
     *
     * @mbg.generated
     */
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr.passwd
     *
     * @return the value of hr.passwd
     *
     * @mbg.generated
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr.passwd
     *
     * @param passwd the value for hr.passwd
     *
     * @mbg.generated
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr.nickname
     *
     * @return the value of hr.nickname
     *
     * @mbg.generated
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr.nickname
     *
     * @param nickname the value for hr.nickname
     *
     * @mbg.generated
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr.experience
     *
     * @return the value of hr.experience
     *
     * @mbg.generated
     */
    public byte[] getExperience() {
        return experience;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr.experience
     *
     * @param experience the value for hr.experience
     *
     * @mbg.generated
     */
    public void setExperience(byte[] experience) {
        this.experience = experience;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr
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
        sb.append(", name=").append(name);
        sb.append(", entrytime=").append(entrytime);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", sex=").append(sex);
        sb.append(", skill=").append(skill);
        sb.append(", salary=").append(salary);
        sb.append(", did=").append(did);
        sb.append(", authority=").append(authority);
        sb.append(", rank=").append(rank);
        sb.append(", passwd=").append(passwd);
        sb.append(", nickname=").append(nickname);
        sb.append(", experience=").append(experience);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}