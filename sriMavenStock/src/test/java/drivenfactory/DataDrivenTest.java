package drivenfactory;

import commonfunlibrary.StockAcounting;
import utilities.ExcelFileUtil;

public class DataDrivenTest 
{
	public static void main(String[] args) throws Throwable {
		StockAcounting app=new StockAcounting();
		ExcelFileUtil excel=new ExcelFileUtil();
		app.applaunch("http://webapp.qedge.com");
		app.applogin("admin", "master");
		for (int i = 0; i < excel.rowCount("SupplierData"); i++)
		{
			String sName=excel.getData("SupplierData", i, 0);
			String add=excel.getData("SupplierData", i, 1);
			String city=excel.getData("SupplierData", i, 2);
			String country=excel.getData("SupplierData", i, 3);
			String cPerson=excel.getData("SupplierData", i, 4);
			String pNumber=excel.getData("SupplierData", i, 5);
			String email=excel.getData("SupplierData", i, 6);
			String mNumber=excel.getData("SupplierData", i, 7);
			String notes=excel.getData("SupplierData", i, 8);
			String results=app.supplierCreation(sName, add, city, country, cPerson, pNumber, email, mNumber, notes);
			excel.setData("SupplierData", i, 9, results);
		}
		app.appLogout();
		app.appclose();
		
	}

}
