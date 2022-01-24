package cn.bunk.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

public class POITest {

    @Test
    public void test01() throws Exception{

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();


        for(int j = 0;j < 500;j++){
            XSSFRow row = sheet.createRow(j);

            Set red = new HashSet<Long>();
            while (red.size() != 6){
                long random = Math.round((Math.random() * 33) + 1);
                red.add(random);
            }
            List ball = new ArrayList<Long>(red);
            ball.add(Math.round((Math.random() * 16) + 1));

            for(int i = 0;i < ball.size();i++){
                row.createCell(i).setCellValue(ball.get(i).toString());
            }
        }

        FileOutputStream out = new FileOutputStream("C:\\Users\\hongbin.liang\\Desktop\\Award2.xlsx");
        workbook.write(out);
        out.flush();
        out.close();
        workbook.close();
    }

    public static void main(String[] args) {
        Map red = new HashMap<Double, Integer>();
        for(double i = 1;i <= 33;i++){
            red.put(i,0);
        }
        Map blue = new HashMap<Double, Integer>();
        for(double i = 1;i <= 16;i++){
            blue.put(i,0);
        }
        try {
            //30,8,33,3,21,32,12,
            XSSFWorkbook book = new XSSFWorkbook(new FileInputStream(new File("C:\\Users\\hongbin.liang\\Desktop\\Award.xlsx")));
            XSSFSheet sheet = book.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for(int i = 0;i <= lastRowNum;i++){
                XSSFRow row = sheet.getRow(i);
                short lastCellNum = row.getLastCellNum();
                for(int j = 0;j < lastCellNum;j++){
                    if(j > 0 && j <= 5){
                        double value = row.getCell(j).getNumericCellValue();
                        int fre = (int)red.get(value);
                        red.put(value, fre + 1);
                    }else if(j == 6){
                        double value = row.getCell(j).getNumericCellValue();
                        int fre = (int)blue.get(value);
                        blue.put(value, fre + 1);
                    }
                }
            }
        }catch (Exception e){
        }
        String redCode = test03(red, 6);
        String blueCode = test03(blue, 1);
        System.out.println(redCode + blueCode);

    }

    public static String test03(Map map, int index){
        int size = map.size();
        List<Map.Entry<Double, Integer>> list = new ArrayList<Map.Entry<Double, Integer>>(size);
        list.addAll(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Double, Integer>>() {
            public int compare(Map.Entry<Double, Integer> o1, Map.Entry<Double, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            };
        });
        StringBuffer s = new StringBuffer("");
        int i = 0;
        for (Map.Entry<Double, Integer> entry : list){
            i++;
            s.append(entry.getKey().intValue() + ",");
            if(i == index){
                break;
            }
        }
        return new String(s);
    }

}


