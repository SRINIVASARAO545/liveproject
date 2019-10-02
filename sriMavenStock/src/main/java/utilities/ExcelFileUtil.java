package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil
{
	Workbook wb;
	public ExcelFileUtil() throws Throwable
	{
		FileInputStream fis=new FileInputStream("D:\\srinivas\\sriMavenStock\\Testinputs\\InputSheet.xlsx");
		wb=WorkbookFactory.create(fis);
	}
	//row count
	public int rowCount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();
	}
	//col count
	public int colCount(String sheetname,int row)
	{
		return wb.getSheet(sheetname).getRow(row).getLastCellNum();
		
	}
	//reading data from excel file
	public String getData(String sheetname,int row,int column)
	{
		String data="";
		//if the cell type is numeric value
		if (wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			//converting double data into integer value
			int celldata=(int) wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			//converting integer into string
			data=String.valueOf(celldata);
			
		}else
		{
			data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
	}
	//writing data into xlFile pass or fail and not exCuted
	public void setData(String sheetname,int row,int column,String status) throws Throwable
	{
		Sheet sh=wb.getSheet(sheetname);
		Row rownum=sh.getRow(row);
		Cell cell=rownum.createCell(column);
		cell.setCellValue(status);
		if (status.equalsIgnoreCase("PASS"))
		{
			//create cell style
			CellStyle style=wb.createCellStyle();
			//create font
         Font font=wb.createFont();
          //apply color to the text
          font.setColor(IndexedColors.GREEN.index);
          //apply bold to the text
          font.setBold(true);
          //set font
          style.setFont(font);
          //set cell style
          rownum.getCell(column).setCellStyle(style);
		}else
			if (status.equalsIgnoreCase("FAIL"))
			{
				//create cell style
				CellStyle style=wb.createCellStyle();
				//create font
	          org.apache.poi.ss.usermodel.Font font=wb.createFont();
	          //apply color to the text
	          font.setColor(IndexedColors.RED.index);
	          //apply bold to the text
	          font.setBold(true);
	          //set font
	          style.setFont(font);
	          //set cell style
	          rownum.getCell(column).setCellStyle(style);
			}else
			if(status.equalsIgnoreCase("BLOCKED"))
				{
					//create cell style
					CellStyle style=wb.createCellStyle();
					//create font
		          org.apache.poi.ss.usermodel.Font font=wb.createFont();
		          //apply color to the text
		          font.setColor(IndexedColors.BLUE.index);
		          //apply bold to the text
		          font.setBold(true);
		          //set font
		          style.setFont(font);
		          //set cell style
		          rownum.getCell(column).setCellStyle(style);
							
				}
				FileOutputStream fos=new FileOutputStream("D:\\srinivas\\sriMavenStock\\TestOutput\\Output.xlsx");
				wb.write(fos);
				fos.close();	
		}
		
	
	
	}
	