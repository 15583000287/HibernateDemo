package com.example.jpademo.controller;

import com.example.jpademo.entity.MerchWebAudit;
import com.example.jpademo.exception.AjaxResult;
import com.example.jpademo.exception.MobaoException;
import com.example.jpademo.repository.MerchWebAuditRepository;
import com.example.jpademo.service.impl.MerchWebAuditServiceImpl;
import com.example.jpademo.utils.FileUtil;

import com.example.jpademo.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.core.util.FileUtils;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 商户网站审核记录台账
 */
@Api(description = "商户网站审核记录")
@Log4j2
@RestController
@RequestMapping("/merch/web")
public class MerchWebAuditController {
    @Autowired
    private MerchWebAuditServiceImpl merchWebAuditService;

    @ApiOperation("保存")
    @RequestMapping("/save")
    public AjaxResult add(MerchWebAudit record, HttpSession session){
        //设置文件上传路径
        record.setUploadFile((String) session.getAttribute("filePath"));
        merchWebAuditService.save(record);
        return new AjaxResult("保存成功！");
    }

    @ApiOperation("查找")
    @GetMapping("/findById")
    public AjaxResult findById(Long id){
        return new AjaxResult("成功",merchWebAuditService.findByid(id));
    }

    @ApiOperation("更新")
    @RequestMapping("/update")
    public AjaxResult update(MerchWebAudit record, HttpSession session){
        //更新文件上传路径
        record.setUploadFile((String) session.getAttribute("filePath"));
        merchWebAuditService.update(record);
        return new AjaxResult("更新成功！");
    }

    @ApiOperation("删除")
    @RequestMapping("/delete")
    public AjaxResult delete(Long id){
        merchWebAuditService.delete(id);
        return new AjaxResult("删除成功！");
    }

    @ApiOperation("分页查询")
    @GetMapping("/ajaxQuery")
    public Page<MerchWebAudit> ajaxQuery(Integer page, Integer pageSize, MerchWebAudit record){
        System.out.println(page+", " +pageSize + "  "+record);
        return merchWebAuditService.pageQuery(page,pageSize,record);
    }

    /**
     * TODO 上传下载路径配置
     * 上传文件
     * @param file
     * @return
     */
    @ApiOperation("上传文件")
    @RequestMapping("/upload")
    public AjaxResult uploadFile(MultipartFile file, HttpSession session) {
        File uploadPath = null;
        try{
            //获取classes下 static/upload路径绝对路径
            File upload = ResourceUtils.getFile("classpath:static/upload");
            log.info("文件上传路径："+upload);
            if(!upload.exists()) {
                upload.mkdirs();
            }
            String fileName = System.currentTimeMillis()+file.getOriginalFilename();
            uploadPath = new File(upload.getAbsolutePath()+"\\"+fileName);
            //将文件路径保存到Session中
            session.setAttribute("filePath","upload/"+fileName);
            //保存文件
            file.transferTo(uploadPath);
        }catch(Exception e){
            log.error("上传文件失败! "+e.getMessage());
            throw new MobaoException("上传文件失败！",2);
        }
        log.info("文件上传成功 ："+uploadPath.getAbsolutePath());
        return new AjaxResult("上传成功！");
    }

//    @ApiOperation("下载文件")
//    @GetMapping("/download")
//    public AjaxResult downloadFile(HttpServletRequest request, HttpServletResponse response) {
//        try{
//            FileUtil.downFile(request.getParameter("x"),"新文件",request,response);
//        }catch (Exception e){
//            return new AjaxResult(2,"文件下载错误");
//        }
//        return new AjaxResult("文件下载成功！");
//    }





    /**
     * 自定义 MVC日期数据绑定初始化(局部日期转换)
     * @param binder
     * @param request
     */
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        DateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }


    /**
     * 导出文件excel文件
     */
    @RequestMapping("/export")
    public Map exportExcel(Integer page, Integer pageSize, MerchWebAudit record,HttpServletResponse response) throws IOException {
        //获取要导出数据
        List<MerchWebAudit> list = merchWebAuditService.pageQuery(page,pageSize,record).getContent();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddhhmmss");
        //创建工作簿对象
        Workbook wb = new XSSFWorkbook();
        //抽出行标题字段
        String[] title = {"序号","商户客户号","商户名称","提交日期","所属代理商","网址","网址是否正常打开","商品能否正常下单", "商户基本情况","商品详情",
                "ICP信息", "热线服务或投诉方式","网站经营范围是否符合","备注"};
        //设置sheet名称，并创建新的sheet对象
        String sheetName = "商户网站审核文件";
        //获取sheet对象
        Sheet sheet = FileUtil.getSheet(wb,sheetName,title);
        //把从数据库中取得的数据一一写入excel文件中
        fillData(sheet,list,sdf);

        //获取配置文件中保存对应excel文件的路径，本地也可以直接写成F：excel/stuInfoExcel路径
        //String folderPath = "F:\\download";
        //创建下载文件目录
        //File folder = new File(folderPath);
        //如果文件夹不存在创建对应的文件夹
        //if (!folder.exists()) {
        //设置文件名
        String fileName = sdf1.format(new Date()) + sheetName + ".xlsx";
        //String savePath = folderPath + File.separator + fileName;

        Map<String,Object> map = new HashMap<>();
        OutputStream fileOut = null;
        try{
            //将Excel通过流写在指定磁盘上（这种方式是可以用ajax发送请求的，）
            //fileOut = new FileOutputStream(savePath);
            //wb.write(fileOut);

            //通过浏览器下载的方式
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName,"UTF-8"));
            fileOut = response.getOutputStream();
            wb.write(fileOut);
        }catch(Exception e){
            log.error("导出失败！" );
            map.put("msg","导出失败！");
            return map;
        }finally {
            if(fileOut != null){
                fileOut.close();
            }
        }
//        log.info("导出完成，请查看: "+savePath);
//        map.put("msg","导出完成，请查看: "+savePath);
//        return map;
        return new HashMap();
    }

    /**
     * 把从数据库中取得的数据一一写入excel文件中
     * @param sheet
     * @param list
     * @param sdf
     */
    public void fillData(Sheet sheet,List<MerchWebAudit> list,SimpleDateFormat sdf){
        Row row = null;
        for(int i=0; i<list.size(); i++){
            //创建list.size()行数据
            row = sheet.createRow(i + 1);
            //把值一一写进单元格里
            //设置第一列为自动递增的序号
            row.createCell(0).setCellValue(i + 1);
            row.createCell(1).setCellValue(list.get(i).getMerchCusNo()!=null ? list.get(i).getMerchCusNo() : " ");
            row.createCell(2).setCellValue(list.get(i).getMerchName() != null ? list.get(i).getMerchName() : " ");
            row.createCell(3).setCellValue(list.get(i).getSubmitDate() != null ? sdf.format(list.get(i).getSubmitDate()) : " ");
            row.createCell(4).setCellValue(list.get(i).getOfAgent() != null ? list.get(i).getOfAgent() : "");
            row.createCell(5).setCellValue(codeToEscape(list.get(i).getUrl()));
            row.createCell(6).setCellValue(codeToEscape(list.get(i).getUrlCanOpen()));
            row.createCell(7).setCellValue(codeToEscape(list.get(i).getGoodsCanOrder()));
            row.createCell(8).setCellValue(codeToEscape(list.get(i).getMerchBaseCase()));
            row.createCell(9).setCellValue(codeToEscape(list.get(i).getGoodsDetail()));
            row.createCell(10).setCellValue(codeToEscape(list.get(i).getICPInfo()));
            row.createCell(11).setCellValue(codeToEscape(list.get(i).getHotLineServe()));
            row.createCell(12).setCellValue(codeToEscape(list.get(i).getManageScope()));
            row.createCell(13).setCellValue(list.get(i).getRemark() != null ? list.get(i).getRemark() : "");
        }
    }

    /**导出Excel表代码转换*/
    public String codeToEscape(Object obj){
        if(obj!=null) {
            return "0".equals(obj.toString()) ? "×" : ("1".equals(obj.toString()) ?  "√" : obj.toString());
        }
        return "";
    }


    @Autowired
    private MerchWebAuditRepository merchWebAuditRepository;
    /**
     * TODO 导入文件
     */
    @RequestMapping("/import")
    public AjaxResult importFile(MultipartFile file){
        //判断是否上传文件
        if(StringUtil.isBlank(file)){
            return new AjaxResult(2,"请上传正确的Excel文件");
        }
        //1.保存文件到本地
        String fileOriName = file.getOriginalFilename();//获取原名称
        String fileNowName = System.currentTimeMillis() + fileOriName;//生成唯一名字
        File fileUploadPath = null;
        try{
            //获取classes下 static/upload路径绝对路径
            File upload = ResourceUtils.getFile("classpath:static/upload/");
            log.info("文件上传路径："+upload);
            if(!upload.exists()) {
                upload.mkdirs();
            }
            //文件对象
            fileUploadPath = new File(upload.getAbsolutePath()+"\\"+fileNowName);
            file.transferTo(fileUploadPath);
            log.info("文件上传成功!  " + fileUploadPath);
        }catch (Exception e){
            log.error("导入文件失败！",e.getMessage());
            return new AjaxResult(2,"请上传正确的Excel文件");
        }


        //2.读取文件
        List<MerchWebAudit> listInfo = readExcelData(fileUploadPath);
        if(listInfo != null){
            merchWebAuditRepository.saveAll(listInfo); merchWebAuditRepository.saveAll(listInfo); merchWebAuditRepository.saveAll(listInfo);

            for(MerchWebAudit merchWebAudit: listInfo){
                System.out.println(merchWebAudit);
            }
        }


        return new AjaxResult();
    }

    /**
     * 读取Excel文件内容
     * @param file 文件全路径
     * @return 数据集合
     */
    private List<MerchWebAudit> readExcelData(File file){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        List<MerchWebAudit> datas = null;
        try{
            //获取第一个工作簿
            Workbook workbook = new XSSFWorkbook(new FileInputStream(file));
            //获取第一个sheet
            Sheet sheet = workbook.getSheetAt(0);
            //获取sheet的最后一行
            int firstRow = 1;
            int lastRow = sheet.getLastRowNum();
            if(lastRow < 2){
                throw new MobaoException("文件数据为空！",2);
            }
            datas = new ArrayList<>();//接收数据的集合
            //循环内不要创建对象引用(集合中存的是对象的引用)
            MerchWebAudit merchWebAudit = null;
            for(int i=firstRow;i<=lastRow;i++){
                merchWebAudit = new MerchWebAudit();
                Row row = sheet.getRow(i);
                int lastCol = row.getLastCellNum();//获取当前行的列数
                if(lastCol != 14){
                    //如果不是14列，不读取了这一行了
                    continue;
                }
                for(int j=0;j<lastCol;j++){
                    Cell cell = row.getCell(j);//获取第一个cell
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if(j == 0){
                        continue; //不读序号
                    }
                    if(j == 1){
                        merchWebAudit.setMerchCusNo(cell.getStringCellValue());//商户客户号
                        continue;
                    }
                    if(j == 2){
                        merchWebAudit.setMerchName(cell.getStringCellValue());//商户客名称
                        continue;
                    }
                    if(j == 3){
                        merchWebAudit.setSubmitDate(sdf.parse(cell.getStringCellValue()));//提交日期
                        continue;
                    }
                    if(j == 4) {
                        merchWebAudit.setOfAgent(escapeToCode(cell.getStringCellValue()));//所属代理商
                        continue;
                    }
                    if(j == 5){
                        merchWebAudit.setUrl(escapeToCode(cell.getStringCellValue()));//网址
                        continue;
                    }
                    if(j == 6){
                        merchWebAudit.setUrlCanOpen(escapeToCode(cell.getStringCellValue()));//网址能否正常打开
                        continue;
                    }
                    if(j == 7){
                        merchWebAudit.setGoodsCanOrder(escapeToCode(cell.getStringCellValue()));//商品能否正常下单
                        continue;
                    }
                    if(j == 8){
                        merchWebAudit.setMerchBaseCase(escapeToCode(cell.getStringCellValue()));//商户基本情况
                        continue;
                    }
                    if(j == 9){
                        merchWebAudit.setGoodsDetail(escapeToCode(cell.getStringCellValue()));//商品详情
                        continue;
                    }
                    if(j == 10) {
                        merchWebAudit.setICPInfo(escapeToCode(cell.getStringCellValue()));//ICP信息
                        continue;
                    }
                    if(j == 11){
                        merchWebAudit.setHotLineServe(escapeToCode(cell.getStringCellValue()));//热线服务或投诉方式
                        continue;
                    }
                    if(j == 12){
                        merchWebAudit.setManageScope(escapeToCode(cell.getStringCellValue()));//网站经营范围是否符合
                        continue;
                    }
                    if(j == 13){
                        merchWebAudit.setRemark(cell.getStringCellValue());//备注
                        continue;
                    }
                }
                datas.add(merchWebAudit);//读取一行将数据add到集合
            }
        }catch (Exception e){
            log.error("读取Excel数据失败！"+e.getMessage());
            throw new MobaoException("读取Excel数据失败！",2);
        }
        return  datas;
    }


        /**导入Excel表代码转换*/
        public String escapeToCode(Object obj){
            if(obj!=null) {
                return "×".equals(obj.toString()) ? "0" : ("√".equals(obj.toString()) ? "1" : obj.toString());
            }
            return "";
        }

    }
