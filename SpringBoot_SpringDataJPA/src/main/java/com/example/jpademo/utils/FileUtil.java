package com.example.jpademo.utils;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文件工具
 */
public class FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 获取Sheet 导出xlsx文件
     * @param wb 工作簿对象
     * @param sheetName sheet名
     * @param title sheet标题数组
     * @return Sheet
     */
    public static Sheet getSheet(Workbook wb, String sheetName, String[] title) {
        Sheet sheet = wb.createSheet(sheetName);
        //获取表头行
        Row titleRow = sheet.createRow(0);
        CellStyle style = wb.createCellStyle();
        // 设置字体
        Font headfont = wb.createFont();
        headfont.setFontHeightInPoints((short) 12);// 字体大小
        headfont.setBoldweight(Font.BOLDWEIGHT_BOLD);// 加粗
        sheet.createFreezePane(0, 1);// 第一行冻结
        style.setAlignment(CellStyle.ALIGN_CENTER); // 创建一个居中格式
        style.setFont(headfont);

        style.setFillForegroundColor(HSSFColor.WHITE.index);
        style.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
        // 填充模式
        style.setFillPattern(CellStyle.FINE_DOTS);
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        style.setBorderLeft((short) 1);// 边框的大小
        style.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
        style.setBorderRight((short) 1);// 边框的大小
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        style.setBorderBottom((short) 1);
        style.setTopBorderColor(HSSFColor.BLACK.index);
        style.setBorderTop((short) 1);

        Cell cell = null;
        //把已经写好的标题行写入excel文件中
        for(int i=0; i<title.length; i++){
            cell = titleRow.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //设置单元格宽度自适应，在此基础上把宽度调至1.5倍 for (int i = 0; i < title.length; i++) {
        for (int i = 0; i < title.length; i++) {
            sheet.autoSizeColumn(i, true);
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 15 / 10);
        }
        return sheet;
    }


    /**
     * 循环读取文件下的文件
     */
    public static void readFile(File file, Map<String, List<File>> fileMap) {
        if (file != null && file.exists()) {
            if (file.isDirectory()) {
                File[] tempList = file.listFiles();
                for (File f : tempList) {
                    readFile(f, fileMap);
                }
            } else {
                String parentPath = file.getParentFile().getName();
                if (fileMap.containsKey(parentPath)) {
                    fileMap.get(parentPath).add(file);
                } else {
                    List<File> files = new ArrayList<File>();
                    files.add(file);
                    fileMap.put(parentPath, files);
                }
            }
        }
    }

    /**
     * 下载文件
     * 
     * @param filePath(带文件名) 文件绝对路径
     * @param fileName 下载文件名称
     * @param response 响应
     * @throws IOException 异常
     */
    public static void downFile(String filePath, String fileName, HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        OutputStream os = null;
        InputStream in = null;
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            os = response.getOutputStream();
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setContentType("application/octet-stream; charset=UTF-8");
            in = new FileInputStream(filePath);
            byte[] b = new byte[in.available()];
            in.read(b);
            os.write(b);
            os.flush();
        } catch (Exception e) {
            logger.error("down file error,filePath=" + filePath + ",fileName=" + fileName, e);
        } finally {
            if (os != null)
                os.close();
            if (in != null)
                in.close();
        }
    }

    /**
     * 
     * 
     */
    public static void downFileNew(String filePath, String fileName, HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {
        response.reset();
        response.setContentType("multipart/form-data");
        response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        try {
            InputStream inputStream = new FileInputStream(new File(filePath + File.separator + fileName));
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            os.flush();
            os.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
