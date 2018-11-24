package com.ford.mediadata.mgt.web.export;

import io.dabing.common.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public class ExcelExport {
    private static Logger logger = LoggerFactory.getLogger(ExcelExport.class);

    /**
     * 生产excel数据流
     * PS:dataList中得map的key 必须和nameDataMap的key相同
     *
     * @param keys                数据集
     * @param columnNames         header数据集
     * @param mapData             与keys对应起来的数据集合
     * @param fileName            下载文件名
     * @param httpServletResponse 响应对象
     */
    public static void doExportExcel(String keys[], String columnNames[], List<Map<String, Object>> mapData, String fileName, HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.reset();
            httpServletResponse.setContentType("application/vnd.ms-excel;charset=utf-8");
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
            OutputStream outputStream = httpServletResponse.getOutputStream();
            ExcelUtil.createWorkBook(mapData, keys, columnNames).write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException ex) {
            logger.error("Excel导出失败! message:{}", ex);
            Assert.isTrue(true, "Excel导出失败!");
        }
    }
}
