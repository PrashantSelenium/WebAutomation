package com.cucumber.helpers;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static org.apache.poi.ss.usermodel.Cell.*;

/**

 * Created by pratik on 15/01/17.
 * @author Pratik Barjatiya
 * @version 1.0.0
 */

@SuppressWarnings("ALL")
public class DataHelper {

    public static HashMap<String,String> storeValues = new HashMap();


    public static List<HashMap<String,String>> data()
    {


        List<HashMap<String,String>> mydata = new ArrayList<>();
        try
        {

            FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//testData/TestData-seleniumframework.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fs);
            XSSFSheet sheet = workbook.getSheet("TestData");
            Row HeaderRow = sheet.getRow(0);

            for(int i=1;i<sheet.getPhysicalNumberOfRows();i++)
            {
                Row currentRow = sheet.getRow(i);
                HashMap<String,String> currentHash = new HashMap<>();
                for(int j=0;j<currentRow.getPhysicalNumberOfCells();j++)
                {
                    Cell currentCell = currentRow.getCell(j);

                    //noinspection deprecation
                    switch (currentCell.getCellType())
                    {
                        //noinspection deprecation
                        case CELL_TYPE_STRING:
                            System.out.print(currentCell.getStringCellValue() + "\t");

                            currentHash.put(HeaderRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
                            break;
                    }

                }
                mydata.add(currentHash);
            }

            fs.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return mydata;
    }

}