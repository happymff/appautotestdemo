package logo.file;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import jxl.read.biff.BiffException;
import logo.module.ElementExist;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import sun.security.krb5.KrbException;


import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by LiuChuanan on 2017/5/24.
 */
public class AutoTestExcelFile {
    private POIFSFileSystem fileSystem;
    private HSSFWorkbook workbook;
    private HSSFSheet sheet;
    private HSSFRow row;
    private HSSFCell cell;
    ElementExist el = new ElementExist();
    public String[] readExcelTitleContent(InputStream is) throws KrbException {
        try{
            fileSystem = new POIFSFileSystem(is);
            workbook = new HSSFWorkbook(fileSystem);
        }catch (IOException e){
            e.printStackTrace();
        }
        sheet = workbook.getSheet(logo.module.Config.getInstance().getCfg("My"));
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        String[] title = new String[colNum];
        for (int i=0; i < colNum; i++){
            title[i] = getStringCellValue(row.getCell((short)i));
        }
        return title;
    }
    public Map<Integer,String> readExcelContent(InputStream is){
        Map<Integer, String> content = new HashMap<Integer, String>();
        String str = "";
        try{
            fileSystem = new POIFSFileSystem(is);
            workbook = new HSSFWorkbook(fileSystem);
        }catch (IOException e){
            e.printStackTrace();
        }
        sheet = workbook.getSheet(logo.module.Config.getInstance().getCfg("My"));
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        String[] title = new String[colNum];
        for (int i=1; i < colNum; i++){
            row = sheet.getRow(i);
            int j =0;
            while (j<colNum){
                str+= getStringCellValue(row.getCell((short)j)).trim()+"-";
                j++;
            }
            content.put(i,str);
            str = "";
        }
        return content;
    }
    public String getStringCellValue(HSSFCell cell){
        String strCell = "";
        if(cell==null){
            return "";
        }else {
            switch (cell.getCellType()){
                case HSSFCell.CELL_TYPE_STRING:
                    strCell = cell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_NUMERIC:
                    String strCellNumber = String.valueOf(cell.getNumericCellValue());
                    DecimalFormat df= new DecimalFormat("0");
                    strCell = df.format(cell.getNumericCellValue());
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    strCell = String.valueOf(cell.getBooleanCellValue());
                    break;
                case HSSFCell.CELL_TYPE_BLANK:
                    strCell = "";
                    break;
                default:
                    strCell = "";
                    break;
            }
            if (strCell.equals("")||strCell==null){
                return "";
            }
            return strCell;
        }
    }
    private String getDateCellValue(HSSFCell cell){
        String result = "";
        try{
            int cellType = cell.getCellType();
            if(cellType==HSSFCell.CELL_TYPE_NUMERIC){
                Date date = cell.getDateCellValue();
                result = (date.getYear()+1900)+"-"+(date.getMonth())+"-"+(date.getDate());
            }else if(cellType==HSSFCell.CELL_TYPE_STRING){
                String date = getStringCellValue(cell);
                result = date.replaceAll("[年月]","-").replace("日","").trim();
            }else if(cellType==HSSFCell.CELL_TYPE_BLANK){
                result="";
            }
        }catch (Exception e){
            System.out.println("日期格式不正确");
            e.printStackTrace();
        }
        return result;
    }
    public List<String> ReadTitle() throws FileNotFoundException, KrbException {
        List<String> list = new ArrayList<String>();
        InputStream is= new FileInputStream(logo.module.Config.getInstance().getCfg("filepath"));
        AutoTestExcelFile excelReader = new AutoTestExcelFile();
        String [] title =  excelReader.readExcelTitleContent(is);
        System.out.println("获得Excel表格的标题");
        for (String s:title){
            list.add(s);
        }
        return list;
    }
    public List<String> ReadContent() throws FileNotFoundException{
        List<String> list = new ArrayList<String>();
        AutoTestExcelFile excelReader = new AutoTestExcelFile();
        InputStream is2 = new FileInputStream(logo.module.Config.getInstance().getCfg("filepath"));
        Map<Integer,  String> map = excelReader.readExcelContent(is2);
        for (int i =1; i<map.size(); i++){
            list.add(map.get(i));
        }
        return list;
    }
    public String ReadTitleContent(int i, int j) throws BiffException,IOException{
        InputStream is= new FileInputStream(logo.module.Config.getInstance().getCfg("filepath"));
        try{
         fileSystem = new POIFSFileSystem(is);
         workbook = new HSSFWorkbook(fileSystem);
        }catch (IOException e){
            e.printStackTrace();
        }
        sheet = workbook.getSheet(logo.module.Config.getInstance().getCfg("filepath"));
        row = sheet.getRow(i);
        String content = getStringCellValue(row.getCell((short) j));
        return  content;
    }
    private void saveWorkBook(HSSFWorkbook wb){
        try{
            FileOutputStream fileOut= new FileOutputStream(logo.module.Config.getInstance().getCfg("filepath"));
            wb.write(fileOut);
        }catch (FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    private HSSFCell getCell(HSSFSheet sheet, int rowIndex, short columnIndex){
        HSSFRow row = sheet.getRow(rowIndex);
        if (row==null){
            row = sheet.createRow(rowIndex);
        }
        HSSFCell cell = row.getCell(columnIndex);
        if (cell==null){
            cell = row.createCell((short)columnIndex);
        }
        return cell;
    }
    public void WriteTitleContent(int i, short j) throws BiffException,IOException{
        try{
            InputStream is= new FileInputStream(logo.module.Config.getInstance().getCfg("filepath"));
            fileSystem = new POIFSFileSystem(is);
            workbook =new HSSFWorkbook(fileSystem);
            is.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        sheet = workbook.getSheet(logo.module.Config.getInstance().getCfg("My"));
        row = sheet.getRow(i);
        HSSFCell cell = getCell(sheet,i,j);
        HSSFRichTextString hts = new HSSFRichTextString(el.result);
        cell.setCellValue(hts);
        saveWorkBook(workbook);
    }
    public void SetContentInit(short j) throws  BiffException, IOException{
        try{
            InputStream is= new FileInputStream(logo.module.Config.getInstance().getCfg("filepath"));
            Map<Integer,String> map = readExcelContent(is);
            for (int k =1; k <map.size(); k++){
                sheet = workbook.getSheet(logo.module.Config.getInstance().getCfg("My"));
                row = sheet.getRow(k);
                HSSFCell cell= getCell(sheet,k,j);
                HSSFRichTextString hts = new HSSFRichTextString("");
                cell.setCellValue(hts);

            }
            saveWorkBook(workbook);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
 }
