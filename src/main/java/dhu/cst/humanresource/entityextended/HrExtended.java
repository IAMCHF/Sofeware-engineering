package dhu.cst.humanresource.entityextended;

import java.math.BigDecimal;
import java.util.Date;

public class HrExtended {

    private Integer id;

    private String name;

    private Date entrytime;

    private String phone;

    private String email;

    private String sex;

    private String skill;

    private BigDecimal salary;

    private Integer did;

    private String authority;

    private Integer rank;

    private String passwd;

    private String nickname;

    private byte[] experience;

    private String specialcase;

    private Date arrivetime;

    private Date leavetime;

    private String condition;

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }

    public void setLeavetime(Date leavetime) {
        this.leavetime = leavetime;
    }

    public Date getLeavetime() {
        return leavetime;
    }

    public void setArrivetime(Date arrivetime) {
        this.arrivetime = arrivetime;
    }

    public Date getArrivetime() {
        return arrivetime;
    }

    private static final long serialVersionUID = 1L;

    public void setSpecialcase(String specialcase) {
        this.specialcase = specialcase;
    }

    public String getSpecialcase() {
        return specialcase;
    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }


    public Date getEntrytime() {
        return entrytime;
    }


    public void setEntrytime(Date entrytime) {
        this.entrytime = entrytime;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }



    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }


    public String getSex() {
        return sex;
    }


    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }


    public String getSkill() {
        return skill;
    }


    public void setSkill(String skill) {
        this.skill = skill == null ? null : skill.trim();
    }


    public BigDecimal getSalary() {
        return salary;
    }


    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }


    public Integer getDid() {
        return did;
    }


    public void setDid(Integer did) {
        this.did = did;
    }


    public String getAuthority() {
        return authority;
    }


    public void setAuthority(String authority) {
        this.authority = authority == null ? null : authority.trim();
    }


    public Integer getRank() {
        return rank;
    }


    public void setRank(Integer rank) {
        this.rank = rank;
    }


    public String getPasswd() {
        return passwd;
    }


    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }


    public String getNickname() {
        return nickname;
    }


    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }


    public byte[] getExperience() {
        return experience;
    }


    public void setExperience(byte[] experience) {
        this.experience = experience;
    }


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
        sb.append(", specialcase=").append(specialcase);
        sb.append(", condition=").append(condition);
        sb.append(", arrivetime=").append(arrivetime);
        sb.append(", leavetime=").append(leavetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
