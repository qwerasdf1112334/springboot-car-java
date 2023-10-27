package cn.changge.base.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExcelUtil {

    public static void exportExcel(List<?> dataList, String sheetName, String title, Class<?> clazz, String fileName, HttpServletResponse response) throws IOException {
        ExportParams exportParams = new ExportParams(title, sheetName);
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, clazz, dataList);
        response.setHeader("content-Type", "application/vnd.ms-excel");
       // response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + ".xls");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "UTF-8") + ".xls");
        workbook.write(response.getOutputStream());
    }
}