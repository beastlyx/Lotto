package com.mycompany.lotto;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Lotto {

    public static void main(String[] args) {
        
        List<Integer> list = new ArrayList<>();
        
        try (FileInputStream fis = new FileInputStream("/Users/borysb/Desktop/Files/personal/powerball data/powerball_numbers - 23 years.xlsx");
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    list.add(Integer.valueOf(cell.toString()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        List<Integer> num1 = new ArrayList<>();
        List<Integer> num2 = new ArrayList<>();
        List<Integer> num3 = new ArrayList<>();
        List<Integer> num4 = new ArrayList<>();
        List<Integer> num5 = new ArrayList<>();
        List<Integer> powerball = new ArrayList<>();
        
        for (int i = 0; i < list.size() - 5; i+=6) {
            num1.add(list.get(i));
            num2.add(list.get(i+1));
            num3.add(list.get(i+2));
            num4.add(list.get(i+3));
            num5.add(list.get(i+4));
            powerball.add(list.get(i+5));
        }
        //int uncommon1 = uncommon(num1);
        //System.out.println("most common: " + mostCommon(num1));
        
        List<Integer> common1 = new ArrayList<>();
        common1 = mostCommon(num1);
        
        List<Integer> uncommon1 = new ArrayList<>();
        uncommon1 = uncommon(num1);
        
        System.out.println("uncommon: " + uncommon1);
        
        System.out.println("common: " + common1);
    }
    
    public static List<Integer> uncommon(List<Integer> list) {
        int[] bucket = new int[70];
        int min = Integer.MAX_VALUE;
        
        List<Integer> result = new ArrayList<>();
        
        for (int num: list) {
            bucket[num]++;
        }
        
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] != 0) {
                min = Math.min(bucket[i], min);
            }
        }
        
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == min) {
                result.add(i);
            }
        }
        
        return result;
    }
    
    public static List<Integer> mostCommon(List<Integer> list) {
        int[] bucket = new int[70];
        int max = Integer.MIN_VALUE;
        
        List<Integer> result = new ArrayList<>();
        
        for (int num: list) {
            bucket[num]++;
        }
        
        for (int i = 0; i < bucket.length; i++) {
            max = Math.max(bucket[i], max);
        }
        
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == max) {
                result.add(i);
            }
        }
        
        return result;
    }
}
