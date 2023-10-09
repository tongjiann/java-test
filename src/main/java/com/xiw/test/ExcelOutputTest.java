package com.xiw.test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.xiw.test.entity.ExcelDemo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-08-01 14:22
 */
@Slf4j
public class ExcelOutputTest {


    private static SXSSFWorkbook getWorkbook(List<String> title, List<? extends List<?>> data) {
        SXSSFWorkbook workbook = new SXSSFWorkbook(10_0000);
        // 添加一个sheet
        getSheet(title, data, workbook.createSheet());
        getSheet(title, data, workbook.createSheet());
        getSheet(title, data, workbook.createSheet());
        getSheet(title, data, workbook.createSheet());
        getSheet(title, data, workbook.createSheet());
        return workbook;
    }

    private static void getSheet(List<String> title, List<? extends List<?>> data, Sheet sheet) {
        // 构建title
        final Row titleRow = sheet.createRow(0);
        for (int i = 0; i < title.size(); i++) {
            final Cell titleRowCell = titleRow.createCell(i);
            titleRowCell.setCellValue(title.get(i));
        }
        // 填充数据
        for (int i = 0; i < data.size(); i++) {
            final Row row = sheet.createRow(i + 1);
            final List<?> dataRow = data.get(i);
            for (int j = 0; j < dataRow.size(); j++) {
                final Cell cell = row.createCell(j);
                final Object value = dataRow.get(j);
                cell.setCellValue(value == null ? "" : String.valueOf(value));
            }
        }
    }

    @Test
    public void EasyExcelTest() {
        List<ExcelDemo> data = data();
        List<List<String>> superList = new ArrayList<>();
        superList.add(Collections.singletonList("1"));
        superList.add(Collections.singletonList("2"));
        superList.add(Collections.singletonList("3"));
        long start = System.currentTimeMillis();
        ExcelWriter excelWriter = EasyExcel.write(new File("/Users/xiwang/Documents/tmp/TestExcel2.xlsx")).build();
        WriteSheet mainSheet = EasyExcel.writerSheet(0, "采购单").head(superList).build();
        excelWriter.write(data, mainSheet);
        WriteSheet detailSheet = EasyExcel.writerSheet(1, "采购单明细").head(ExcelDemo.class).build();
        excelWriter.write(data, detailSheet);
        excelWriter.finish();
        long end = System.currentTimeMillis();

        System.out.println(end - start);

    }

    private List<ExcelDemo> data() {
        List<ExcelDemo> list = ListUtils.newArrayList();
        for (int i = 0; i < 100; i++) {
            ExcelDemo data = new ExcelDemo();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }

    @Test
    public void exportBigData() {
        int col = 25;
        int row = 10_0000;
        final List<String> title = IntStream.rangeClosed(1, col).mapToObj(value -> "第" + value + "列").collect(Collectors.toList());

        final List<List<Double>> data = IntStream.range(0, row).mapToObj(value -> IntStream.range(0, col).mapToObj(ignore -> Math.random()).collect(Collectors.toList())).collect(Collectors.toList());

        final LocalDateTime start = LocalDateTime.now();
        final SXSSFWorkbook workbook = getWorkbook(title, data);
        try (OutputStream outputStream = Files.newOutputStream(Paths.get("/Users/xiwang/Documents/tmp/TestExcel2.xlsx"))) {
            workbook.write(outputStream);
            // 丢弃在磁盘上备份此工作簿的临时文件
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final LocalDateTime end = LocalDateTime.now();
        final Duration duration = Duration.between(start, end);
        System.out.println("生成Excel花费时间：" + duration);
    }
}
