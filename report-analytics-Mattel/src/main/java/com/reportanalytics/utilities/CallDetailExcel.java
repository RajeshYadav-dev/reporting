package com.reportanalytics.utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class CallDetailExcel {

	private SXSSFWorkbook workbook;

	public CallDetailExcel() {
		workbook = new SXSSFWorkbook(100);
	}

	public <T> void writeToExcel(String fileName, List<T> data) {
		try {
			Sheet sheet = workbook.createSheet();
			List<String> fieldNames = getFieldNamesForClass(data.get(0).getClass());
			fieldNames.remove("serialVersionUID");
			CellStyle style = workbook.createCellStyle();
			Font font = workbook.createFont();
			font.setBold(true);
			font.setFontHeightInPoints((short) 14);
			style.setFont(font);

			int rowCount = 0;
			int columnCount = 0;
			Row row = sheet.createRow(rowCount++);
			for (String fieldName : fieldNames) {
//				sheet.autoSizeColumn(columnCount);
				Cell cell = row.createCell(columnCount++);
				cell.setCellValue(capitalize(fieldName));
				cell.setCellStyle(style);
			}
//			sheet.autoSizeColumn(columnCount);
			Class<? extends Object> classz = data.get(0).getClass();

			for (T t : data) {
				row = sheet.createRow(rowCount++);
				columnCount = 0;
				for (String fieldName : fieldNames) {
					Cell cell = row.createCell(columnCount);
					Method method = null;
					try {
						method = classz.getMethod("get" + capitalize(fieldName));
					} catch (NoSuchMethodException nme) {
						method = classz.getMethod("get" + fieldName);
					}
					Object value = method.invoke(t, (Object[]) null);
					if (value != null) {
						if (value instanceof String) {
							cell.setCellValue((String) value);
						} else if (value instanceof Long) {
							cell.setCellValue((Long) value);
						} else if (value instanceof Integer) {
							cell.setCellValue((Integer) value);
						} else if (value instanceof Double) {
							cell.setCellValue((Double) value);
						} else if (value instanceof Date) {
							SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
							cell.setCellValue((String) sdf.format(value));
						}
					}
					columnCount++;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("fail to import the data to Excel file: " + e.getMessage());
		}
	}

	/*
	 * retrieve field names from a POJO class
	 */
	private static List<String> getFieldNamesForClass(Class<?> clazz) throws Exception {
		List<String> fieldNames = new ArrayList<String>();
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fieldNames.add(fields[i].getName());
		}
		return fieldNames;
	}

	/*
	 * capitalize the first letter of the field name
	 */
	private static String capitalize(String s) {
		if (s.length() == 0)
			return s;
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	public <T> ByteArrayInputStream export(String sheetName, List<T> data) throws IOException {
		writeToExcel(sheetName, data);

		try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to the Excel file: " + e.getMessage());
		}
	}
}
