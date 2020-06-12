package dhu.cst.humanresource.service.impl;

import dhu.cst.humanresource.dao.DepartmentMapper;
import dhu.cst.humanresource.dao.HrMapper;
import dhu.cst.humanresource.entity.Hr;
import dhu.cst.humanresource.service.StatisticsService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.RichTextString;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Resource
    private HrMapper hrMapper;
    @Resource
    private DepartmentMapper departmentMapper;

    List<Integer> department_index=new ArrayList<Integer>();

    @Override
    public Map<String, Integer> getDepartment_Num() {//最终返回map<部门名,部门员工总数>
        Map<String,Integer> map= new HashMap<String,Integer>();
        department_index=departmentMapper.selectAll_id();//所有部门id列表
        Iterator<Integer> it = department_index.iterator();
        Integer sum=0;
        while (it.hasNext()) {
            Integer department_id=it.next();//部门id
            map.put(departmentMapper.selectName_byPrimaryKey(department_id),hrMapper.getHr_Num_fromSame_Department(department_id));

        }
        //map.put("未定部门",hrMapper.getHrnoDepSum());
        return map;
    }

    @Override
    public Map<String,BigDecimal> getDepartment_AVG_salary() {//最终返回map<部门名,部门平均工资>
        Map<String,BigDecimal> map= new HashMap<String,BigDecimal>();
        department_index=departmentMapper.selectAll_id();//所有部门id列表
        Iterator<Integer> it = department_index.iterator();
        while (it.hasNext()) {
            Integer department_id=it.next();//部门id
            map.put(departmentMapper.selectName_byPrimaryKey(department_id),hrMapper.select_avgSalary_from_sameDep(department_id));
        }
        return map;
    }

    @Override
    public Map<String, BigDecimal> getDepartment_MAX_salary() {//最终返回map<部门名,部门最高工资>
        Map<String,BigDecimal> map= new HashMap<String,BigDecimal>();
        department_index=departmentMapper.selectAll_id();//所有部门id列表
        Iterator<Integer> it = department_index.iterator();
        while (it.hasNext()) {
            Integer department_id=it.next();//部门id
            map.put(departmentMapper.selectName_byPrimaryKey(department_id),hrMapper.select_maxSalary_from_sameDep(department_id));
        }
        return map;
    }

    @Override
    public Map<String, BigDecimal> getDepartment_MIN_salary() {//最终返回map<部门名,部门最低工资>
        Map<String,BigDecimal> map= new HashMap<String,BigDecimal>();
        department_index=departmentMapper.selectAll_id();//所有部门id列表
        Iterator<Integer> it = department_index.iterator();
        while (it.hasNext()) {
            Integer department_id=it.next();//部门id
            map.put(departmentMapper.selectName_byPrimaryKey(department_id),hrMapper.select_minSalary_from_sameDep(department_id));
        }
        return map;
    }

    @Override
    public void export(String[] titles, ServletOutputStream out) {//导出全体员工信息excel文件
        try{
            // 第一步，创建一个workbook，对应一个Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet hssfSheet = workbook.createSheet("sheet1");
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            HSSFRow hssfRow = hssfSheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
            //居中样式
            hssfCellStyle.setAlignment(HorizontalAlignment.CENTER);

            HSSFCell hssfCell = null;
            for (int i = 0; i < titles.length; i++) {
                hssfCell = hssfRow.createCell(i);//列索引从0开始
                hssfCell.setCellValue(titles[i]);//列名1
                hssfCell.setCellStyle(hssfCellStyle);//列居中显示
            }

            // 第五步，写入实体数据
            List<Hr> hrList =new ArrayList<Hr>(hrMapper.selectAll());
            if(hrList != null && !hrList.isEmpty()){
                for (int i = 0; i < hrList.size(); i++) {
                    hssfRow = hssfSheet.createRow(i+1);
                    Hr hr = hrList.get(i);


                    // 第六步，创建单元格，并设置值
                    //设置员工编号
                    if(hr.getId()!=null)
                        hssfRow.createCell(0).setCellValue(hr.getId());
                    //设置员工姓名
                    if(hr.getName()!=null)
                        hssfRow.createCell(1).setCellValue(hr.getName());
                    //设置昵称
                    if(hr.getNickname()!=null)
                        hssfRow.createCell(2).setCellValue(hr.getNickname());
                    //设置性别
                    if(hr.getSex()!=null)
                        hssfRow.createCell(3).setCellValue(hr.getSex());
                    //设置权限
                    if(hr.getRank()!=null)
                        hssfRow.createCell(4).setCellValue(hr.getRank());
                    //设置密码
                    if(hr.getPasswd()!=null)
                         hssfRow.createCell(5).setCellValue(hr.getPasswd());
                    //设置入职时间
                    if(hr.getEntrytime()!=null)
                    {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = formatter.format(hr.getEntrytime());
                        hssfRow.createCell(6).setCellValue(dateString);
                    }

                    //设置手机号码
                    if(hr.getPhone()!=null)
                        hssfRow.createCell(7).setCellValue(hr.getPhone());
                    //设置邮箱地址
                    if(hr.getEmail()!=null)
                        hssfRow.createCell(8).setCellValue(hr.getEmail());
                    //设置工资
                    if(hr.getSalary()!=null)
                         hssfRow.createCell(9).setCellValue(hr.getSalary().stripTrailingZeros().toPlainString());
                    //设置部门编号
                    if(hr.getDid()!=null)
                         hssfRow.createCell(10).setCellValue(hr.getDid());
                    //设置职务
                    if(hr.getAuthority()!=null)
                        hssfRow.createCell(11).setCellValue(hr.getAuthority());
                    //设置职业技能
                    if(hr.getSkill()!=null)
                        hssfRow.createCell(12).setCellValue(hr.getSkill());
                    //设置从业经历
                    if(hr.getExperience()!=null)
                    {
                        hssfRow.createCell(13).setCellValue(new String(hr.getExperience()));
                    }

                }
            }
            try {// 第七步，将文件输出到客户端浏览器
                workbook.write(out);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /*
    @Override
    public Map<String, BigDecimal> getDepartment_AVG_bonus() {
        Map<String,BigDecimal> map= new HashMap<String,BigDecimal>();
        department_index=departmentMapper.selectAll_id();//所有部门id列表
        Iterator<Integer> it = department_index.iterator();
        while (it.hasNext()) {
            Integer department_id=it.next();//部门id

            map.put(departmentMapper.selectName_byPrimaryKey(department_id),);
        }
        return map;
    }
     */
}
