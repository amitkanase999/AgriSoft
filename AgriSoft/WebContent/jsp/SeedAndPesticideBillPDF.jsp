
<%@page import="com.Fertilizer.utility.NumToWord"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.itextpdf.text.Rectangle"%>
<%@page import="com.itextpdf.text.BaseColor"%>
<%@page import="com.itextpdf.text.Font"%>
<%@page import="com.itextpdf.text.FontFactory"%>
<%@page import="java.util.Formatter"%>
<%@page import="javax.sound.midi.Soundbank"%>
<%@page import="com.itextpdf.text.pdf.codec.Base64.OutputStream"%>
<%@page import="java.util.Date"%>
<%@page import="java.io.IOException"%>
<%@page import="com.itextpdf.text.DocumentException"%>
<%@page import="com.itextpdf.text.Paragraph"%>
<%@page import="com.itextpdf.text.pdf.PdfWriter"%>
<%@page import="com.itextpdf.text.Document"%>

<%@ page trimDirectiveWhitespaces="true"%>

<%@page import="java.util.Date"%>
<%@page import="java.io.IOException"%>
<%@page import="com.itextpdf.text.DocumentException"%>
<%@page import="com.itextpdf.text.Paragraph"%>
<%@page import="com.itextpdf.text.pdf.PdfWriter"%>
<%@page import="com.itextpdf.text.Document"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.awt.Desktop"%>
<%@page import="java.io.File"%>
<%@page import="com.itextpdf.text.pdf.PdfPCell"%>
<%@page import="com.itextpdf.text.pdf.PdfPTable"%>
<%@page import="com.itextpdf.text.Paragraph"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="com.itextpdf.text.PageSize"%>
<%@page import="com.itextpdf.text.pdf.PdfWriter"%>
<%@page import="java.util.List"%>
<%@ page import="com.itextpdf.text.Element"%>
<%@page import="java.util.List"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.itextpdf.text.Phrase"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="com.itextpdf.text.Image"%>
<%@page import="com.itextpdf.text.pdf.PdfContentByte"%>
<%@page import="com.itextpdf.text.Image"%>
<%@page import="com.itextpdf.text.pdf.PdfContentByte"%>
<%@page import="com.itextpdf.text.pdf.PdfGState"%>

<%
	response.setContentType("application/pdf");
	Long billno = (Long) session.getAttribute("SeedBillNo");
	String creditCustomerName = (String) session.getAttribute("SeedcreditCustomerName");
	
	int a = 1, b = 0;
	Double sum = 0d;
	Double tax = 0d;
	String taxVal = "";
	Double swachaTax = 0d;
	Double groTOT = 0d;
	String xx1 = "";
	double iGST = 0d;
	double GST = 0d;
	double gstTotal = 0d;
	double halfGstTotal = 0d;
	double igstTotal = 0d;
	String grossT = "";

	String shopname="";
	String Address="";
	String GST_No="";
	String Lic_No_Wholesale="";
	String Lic_No_Fertilizer="";
	String Lic_No_Seed="";
	String Lic_No_pesticide="";
	String Footer_Shop_Name="";
	
	Connection conn = null;

	try {

		// step 1
		Document document = new Document(PageSize.A4);
		document.setMargins(2, 2, 1, 1);

		// step 2

		PdfWriter.getInstance(document, response.getOutputStream());

		// step 3
		document.open();

		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fertilizer", "root", "root");
		Statement stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery("select seed_pesticide_billing.customer_name,seed_pesticide_billing.expiry_date,seed_pesticide_billing.product_name, seed_pesticide_billing.village,seed_pesticide_billing.batch_number,seed_pesticide_billing.company, seed_pesticide_billing.weight,seed_pesticide_billing.unit, seed_pesticide_billing.quantity, seed_pesticide_billing.salePriceWithoutTax, seed_pesticide_billing.total_per_product, seed_pesticide_billing.total_all_product, seed_pesticide_billing.transportation_expense, seed_pesticide_billing.hamali_expense, seed_pesticide_billing.gross_total, seed_pesticide_billing.tax_percentage,seed_pesticide_billing.hsn,seed_pesticide_billing.igstPercentage,seed_pesticide_billing.insert_date from seed_pesticide_billing where bill_no ="+ billno);

		Statement stmt1 = conn.createStatement();
		ResultSet rs1 = stmt1.executeQuery("SELECT Shop_Name,Address,GST_No,Lic_No_Wholesale,Lic_No_Fertilizer,Lic_No_Seed,Lic_No_pesticide,Footer_Shop_Name FROM shop_details");

		
		while (rs1.next()) {
		 shopname=rs1.getString("Shop_Name");
		 Address=rs1.getString("Address");
		 GST_No=rs1.getString("GST_No");
		 Lic_No_Wholesale=rs1.getString("Lic_No_Wholesale");
		 Lic_No_Fertilizer=rs1.getString("Lic_No_Fertilizer");
		 Lic_No_Seed=rs1.getString("Lic_No_Seed");
		 Lic_No_pesticide=rs1.getString("Lic_No_pesticide");
		 Footer_Shop_Name=rs1.getString("Footer_Shop_Name");
		}
		
		rs.first();

		System.out.println("Query Execute");
		Date d1 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("E");
		SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss  ");
		sdf2.setTimeZone(TimeZone.getTimeZone("IST"));

		Date billDate = new Date();
		SimpleDateFormat dateFormater = new SimpleDateFormat("dd-MM-yyyy");
		String StrBillDate = dateFormater.format(billDate);

		String stdver1 = (String) sdf.format(d1);
		String day = sdf1.format(d1.getDate());
		String Time = sdf2.format(d1.getTime());
		String dtfinl = stdver1;

		DecimalFormat df = new DecimalFormat("#.00");

		Font font15Bold = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, BaseColor.BLACK);
		Font font15BoldUnderline = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD | Font.UNDERLINE,
				BaseColor.BLACK);
		Font font12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
		Font font11 = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK);
		Font font12NoBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
		Font font8NoBold = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL, BaseColor.BLACK);
		Font font9NoBold = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL, BaseColor.BLACK);
		Font font10NoBold = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK);
		Font font12BoldUnderline = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD | Font.UNDERLINE,
				BaseColor.BLACK);
		Font font13 = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD, BaseColor.BLACK);
		Font font13Bold = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD, BaseColor.BLACK);
		Font Normalfont11 = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL, BaseColor.BLACK);
		Font Normalfont13 = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL, BaseColor.BLACK);
		Font Normalfont14 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL, BaseColor.BLACK);
		Font ufont14 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.UNDERLINE, BaseColor.BLACK);
		Font font17 = new Font(Font.FontFamily.TIMES_ROMAN, 17, Font.BOLD, BaseColor.BLACK);

		// step 4

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);

		float[] columnWidths = { 0.2f, 0.3f, 0.2f };
		table.setWidths(columnWidths);

		PdfPCell table_cell;

		table_cell = new PdfPCell(new Phrase("Bill Cash/Credit Memorandum", Normalfont11));
		table_cell.setBorder(table_cell.NO_BORDER);
		table.addCell(table_cell);

		table_cell = new PdfPCell(new Phrase("|| SHREE ||", Normalfont11));
		table_cell.setBorder(table_cell.NO_BORDER);
		table_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(table_cell);

		table_cell = new PdfPCell(new Phrase("Lic No (W) : "+Lic_No_Wholesale, font9NoBold));
		table_cell.setBorder(table_cell.NO_BORDER);
		table.addCell(table_cell);

		//-----------------

		table_cell = new PdfPCell(new Phrase("GSTIN - "+GST_No, Normalfont11));
		table_cell.setBorder(table_cell.NO_BORDER);
		table.addCell(table_cell);

		table_cell = new PdfPCell(new Phrase("GST Invoice", Normalfont11));
		table_cell.setBorder(table_cell.NO_BORDER);
		table_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(table_cell);

		table_cell = new PdfPCell(new Phrase("Lic No(F)     : "+Lic_No_Fertilizer, font9NoBold));
		table_cell.setBorder(table_cell.NO_BORDER);
		table_cell.setVerticalAlignment(Element.ALIGN_CENTER);
		table.addCell(table_cell);

		//--------

		table_cell = new PdfPCell(new Phrase("Bill No: " + billno, Normalfont11));
		table_cell.setBorder(table_cell.NO_BORDER);
		table_cell.setPaddingTop(2f);
		table.addCell(table_cell);

		table_cell = new PdfPCell(new Phrase(shopname, font17));
		table_cell.setBorder(table_cell.NO_BORDER);
		table_cell.setPaddingTop(2f);
		table_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(table_cell);

		table_cell = new PdfPCell(new Phrase("Lic No(I)      : "+Lic_No_pesticide, font9NoBold));
		table_cell.setVerticalAlignment(Element.ALIGN_TOP);
		table_cell.setBorder(table_cell.NO_BORDER);
		table.addCell(table_cell);

		//-------------
       
		table_cell = new PdfPCell(new Phrase(""));
		table_cell.setBorder(Rectangle.BOTTOM);
		table_cell.setPaddingBottom(3f);
		table.addCell(table_cell);

		table_cell = new PdfPCell(new Phrase(Address, Normalfont11));
		table_cell.setBorder(Rectangle.BOTTOM);
		table_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table_cell.setPaddingBottom(2f);
		table.addCell(table_cell);

		table_cell = new PdfPCell(new Phrase("Lic No(S)     : "+Lic_No_Seed, font9NoBold));
		table_cell.setBorder(Rectangle.BOTTOM);
		table_cell.setPaddingBottom(2f);
		table.addCell(table_cell);

		document.add(table);

		/* table for customer name, village and date */

		PdfPTable smallTable = new PdfPTable(3);
		smallTable.setWidthPercentage(100);

		float[] smallTablecolumnWidths = { 0.3f, 0.3f, 0.2f };
		smallTable.setWidths(smallTablecolumnWidths);

		PdfPCell table_cell2;

		String custName;

		String cn = String.valueOf(rs.getString("customer_name"));

		if (cn.equals("N/A")) {
			custName = creditCustomerName;
		} else {
			custName = cn;
		}

		table_cell2 = new PdfPCell(new Phrase("Mr/Mrs/Ms. " + custName, font12));
		table_cell2.setBorder(table_cell.NO_BORDER);
		smallTable.addCell(table_cell2);

		String expenses = String.valueOf(rs.getString("transportation_expense"));
		String HamaliExpence = String.valueOf(rs.getString("hamali_expense"));
		String gross_total = String.valueOf(rs.getString("gross_total"));

		table_cell2 = new PdfPCell(new Phrase("Village: " + rs.getString("village")));
		table_cell2.setBorder(table_cell.NO_BORDER);
		table_cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		smallTable.addCell(table_cell2);

		String sbilldate=rs.getString("insert_date");
		table_cell2 = new PdfPCell(new Phrase("Date: " + sbilldate));
		table_cell2.setBorder(table_cell.NO_BORDER);
		table_cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		smallTable.addCell(table_cell2);

		document.add(smallTable);
		document.add(new Paragraph(""));

		//Middle table
		PdfPTable table3 = new PdfPTable(12);
		table3.setWidthPercentage(100);

		float[] columnWidths3 = { 0.3f, 0.2f, 0.1f, 0.2f, 0.3f, 0.2f,0.1f, 0.2f, 0.1f, 0.1f, 0.2f,0.2f };
		table3.setWidths(columnWidths3);

		PdfPCell table_cell3;

		table_cell3 = new PdfPCell(new Phrase("Product Name", font10NoBold));
		table_cell3.setBorderWidth(1f);
		table_cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table_cell3.setBorder(table_cell3.NO_BORDER);
		table_cell3.setPaddingBottom(4f);
		table3.addCell(table_cell3);

		table_cell3 = new PdfPCell(new Phrase("Company", font10NoBold));
		table_cell3.setBorderWidth(1f);
		table_cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table_cell3.setBorder(table_cell3.NO_BORDER);
		table_cell3.setPaddingBottom(4f);
		table3.addCell(table_cell3);

		table_cell3 = new PdfPCell(new Phrase("HSN", font10NoBold));
		table_cell3.setBorderWidth(1f);
		table_cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table_cell3.setBorder(table_cell3.NO_BORDER);
		table_cell3.setPaddingBottom(4f);
		table3.addCell(table_cell3);

		table_cell3 = new PdfPCell(new Phrase("Batch No", font10NoBold));
		table_cell3.setBorderWidth(1f);
		table_cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table_cell3.setBorder(table_cell3.NO_BORDER);
		table_cell3.setPaddingBottom(4f);
		table3.addCell(table_cell3);

		table_cell3 = new PdfPCell(new Phrase("Expiry_Date", font10NoBold));
		table_cell3.setBorderWidth(1f);
		table_cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table_cell3.setBorder(table_cell3.NO_BORDER);
		table_cell3.setPaddingBottom(4f);
		table3.addCell(table_cell3);

		table_cell3 = new PdfPCell(new Phrase("Pkg", font10NoBold));
		table_cell3.setBorderWidth(1f);
		table_cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table_cell3.setBorder(table_cell3.NO_BORDER);
		table_cell3.setPaddingBottom(4f);
		table3.addCell(table_cell3);
		
		table_cell3 = new PdfPCell(new Phrase("unit", font10NoBold));
		table_cell3.setBorderWidth(1f);
		table_cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table_cell3.setBorder(table_cell3.NO_BORDER);
		table_cell3.setPaddingBottom(4f);
		table3.addCell(table_cell3);
		
		table_cell3 = new PdfPCell(new Phrase("Price(gst)", font10NoBold));
		table_cell3.setBorderWidth(1f);
		table_cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table_cell3.setBorder(table_cell3.NO_BORDER);
		table_cell3.setPaddingBottom(4f);
		table3.addCell(table_cell3);

		table_cell3 = new PdfPCell(new Phrase("Qty", font10NoBold));
		table_cell3.setBorderWidth(1f);
		table_cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table_cell3.setBorder(table_cell3.NO_BORDER);
		table_cell3.setPaddingBottom(4f);
		table3.addCell(table_cell3);

		table_cell3 = new PdfPCell(new Phrase("GST %", font10NoBold));
		table_cell3.setBorderWidth(1f);
		table_cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table_cell3.setBorder(table_cell3.NO_BORDER);
		table_cell3.setPaddingBottom(4f);
		table3.addCell(table_cell3);


		table_cell3 = new PdfPCell(new Phrase("Rate", font10NoBold));
		table_cell3.setBorderWidth(1f);
		table_cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table_cell3.setBorder(table_cell3.NO_BORDER);
		table_cell3.setPaddingBottom(4f);
		table3.addCell(table_cell3);
		
		
		table_cell3 = new PdfPCell(new Phrase("Amount", font10NoBold));
		//table_cell3.setBorderWidth(1f);
		table_cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table_cell3.setBorder(table_cell3.NO_BORDER);
		table_cell3.setPaddingBottom(4f);
		table3.addCell(table_cell3);

		rs.beforeFirst();
		document.add(table3);

		//table for particulars

		PdfPTable table4 = new PdfPTable(12);
		table4.setWidthPercentage(100);

		float[] columnWidths4 = {0.3f, 0.2f, 0.1f, 0.2f, 0.3f, 0.2f,0.1f, 0.2f, 0.1f, 0.1f, 0.2f,0.2f };
		table4.setWidths(columnWidths4);

		PdfPTable table5 = new PdfPTable(12);
		table5.setWidthPercentage(100);

		float[] columnWidths5 = {0.3f, 0.2f, 0.1f, 0.2f, 0.3f, 0.2f, 0.1f, 0.2f, 0.1f, 0.1f, 0.2f,0.2f };
		table5.setWidths(columnWidths5);

		PdfPTable table6 = new PdfPTable(12);
		table6.setWidthPercentage(100);

		float[] columnWidths6 = {0.3f, 0.2f, 0.1f, 0.2f, 0.3f, 0.2f,0.1f, 0.2f, 0.1f, 0.1f, 0.2f,0.2f };
		table6.setWidths(columnWidths6);

		PdfPCell table_cell4;
		rs.beforeFirst();

		PdfPCell cell1 = new PdfPCell(new Paragraph(""));
		PdfPCell cell2 = new PdfPCell(new Paragraph(""));
		PdfPCell cell3 = new PdfPCell(new Paragraph(""));
		PdfPCell cell4 = new PdfPCell(new Paragraph(""));
		PdfPCell cell5 = new PdfPCell(new Paragraph(""));
		PdfPCell cell6 = new PdfPCell(new Paragraph(""));
		PdfPCell cell7 = new PdfPCell(new Paragraph(""));
		PdfPCell cell8 = new PdfPCell(new Paragraph(""));
		PdfPCell cell9 = new PdfPCell(new Paragraph(""));
		PdfPCell cell10 = new PdfPCell(new Paragraph(""));
		PdfPCell cell11 = new PdfPCell(new Paragraph(""));
		PdfPCell cell12 = new PdfPCell(new Paragraph(""));

		table4.addCell(cell1);
		table4.addCell(cell2);
		table4.addCell(cell3);
		table4.addCell(cell4);
		table4.addCell(cell5);
		table4.addCell(cell6);
		table4.addCell(cell7);
		table4.addCell(cell8);
		table4.addCell(cell9);
		table4.addCell(cell10);
		table4.addCell(cell11);
		table4.addCell(cell12);

		table5.addCell(cell1);
		table5.addCell(cell2);
		table5.addCell(cell3);
		table5.addCell(cell4);
		table5.addCell(cell5);
		table5.addCell(cell6);
		table5.addCell(cell7);
		table5.addCell(cell8);
		table5.addCell(cell9);
		table5.addCell(cell10);
		table5.addCell(cell11);
		table5.addCell(cell12);

		table6.addCell(cell1);
		table6.addCell(cell2);
		table6.addCell(cell3);
		table6.addCell(cell4);
		table6.addCell(cell5);
		table6.addCell(cell6);
		table6.addCell(cell7);
		table6.addCell(cell8);
		table6.addCell(cell9);
		table6.addCell(cell10);
		table6.addCell(cell11);
		table6.addCell(cell12);

		while (rs.next()) {
			if (b < 10) {
				String product_name = rs.getString("product_name");
				table_cell4 = new PdfPCell(new Phrase(product_name, font10NoBold));
				table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				table_cell4.setBorder(table_cell4.NO_BORDER);
				table_cell4.setBorderWidth(1f);
				table_cell4.setPaddingTop(2);
				table_cell4.setPaddingBottom(2);
				table4.addCell(table_cell4);

				String manufacturing_company = rs.getString("company");
				table_cell4 = new PdfPCell(new Phrase(manufacturing_company, font10NoBold));
				table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				table_cell4.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
				table_cell4.setBorder(table_cell4.NO_BORDER);
				table_cell4.setPaddingTop(2);
				table_cell4.setPaddingBottom(2);
				table4.addCell(table_cell4);

				String hsnNumber = rs.getString("hsn");
				table_cell4 = new PdfPCell(new Phrase(hsnNumber, font10NoBold));
				table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				table_cell4.setBorder(table_cell4.NO_BORDER);
				table_cell4.setBorderWidth(1f);
				table_cell4.setPaddingTop(2);
				table_cell4.setPaddingBottom(2);
				table4.addCell(table_cell4);

				String batch_number = rs.getString("batch_number");
				table_cell4 = new PdfPCell(new Phrase(batch_number, font10NoBold));
				table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				table_cell4.setBorder(table_cell4.NO_BORDER);
				table_cell4.setBorderWidth(1f);
				table_cell4.setPaddingTop(2);
				table_cell4.setPaddingBottom(2);
				table4.addCell(table_cell4);

				String expiry_date = rs.getString("expiry_date");
				table_cell4 = new PdfPCell(new Phrase(expiry_date, font10NoBold));
				table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				table_cell4.setBorder(table_cell4.NO_BORDER);
				table_cell4.setBorderWidth(1f);
				table_cell4.setPaddingTop(2);
				table_cell4.setPaddingBottom(2);
				table4.addCell(table_cell4);

				double weight = rs.getDouble("weight");
				table_cell4 = new PdfPCell(new Phrase(String.valueOf(weight), font10NoBold));
				table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				table_cell4.setBorder(table_cell4.NO_BORDER);
				table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				table_cell4.setBorderWidth(1f);
				table_cell4.setPaddingTop(2);
				table_cell4.setPaddingBottom(2);
				table4.addCell(table_cell4);
				
				String unit = rs.getString("unit");
				table_cell4 = new PdfPCell(new Phrase(String.valueOf(unit), font10NoBold));
				table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				table_cell4.setBorder(table_cell4.NO_BORDER);
				table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				table_cell4.setBorderWidth(1f);
				table_cell4.setPaddingTop(2);
				table_cell4.setPaddingBottom(2);
				table4.addCell(table_cell4);
				
				BigDecimal salePrice = rs.getBigDecimal("salePriceWithoutTax", 2);
				String sale_price = String.valueOf(rs.getString("salePriceWithoutTax"));
				table_cell4 = new PdfPCell(new Phrase(sale_price, font10NoBold));
				table_cell4.setBorder(table_cell4.NO_BORDER);
				table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				table_cell4.setBorderWidth(1f);
				table_cell4.setPaddingTop(2);
				table_cell4.setPaddingBottom(2);
				table4.addCell(table_cell4);

				int quantity = rs.getInt("quantity");
				table_cell4 = new PdfPCell(new Phrase(String.valueOf(quantity), font10NoBold));
				table_cell4.setBorder(table_cell4.NO_BORDER);
				table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				table_cell4.setBorderWidth(1f);
				table_cell4.setPaddingTop(2);
				table_cell4.setPaddingBottom(2);
				table4.addCell(table_cell4);

				GST = rs.getDouble("tax_percentage");
				double half = 2;
				double halfGST = GST / half;

				table_cell4 = new PdfPCell(new Phrase(String.valueOf(GST), font10NoBold));
				table_cell4.setBorder(table_cell4.NO_BORDER);
				table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				table_cell4.setBorderWidth(1f);
				table_cell4.setPaddingTop(2);
				table_cell4.setPaddingBottom(2);
				table4.addCell(table_cell4);
				
				String X =salePrice.toString();
				Double XX =Double.parseDouble(X);
				double taxpergst = (XX * GST)/100;
				double priceWithOutGST = XX- taxpergst;
				long  taxpergst1= Math.round(priceWithOutGST);
				
				
				table_cell4 = new PdfPCell(new Phrase(""+taxpergst1, font10NoBold));
				table_cell4.setBorder(table_cell4.NO_BORDER);
				table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				table_cell4.setBorderWidth(1f);
				table_cell4.setPaddingTop(2);
				table_cell4.setPaddingBottom(2);
				table4.addCell(table_cell4);

				double salep = rs.getDouble("salePriceWithoutTax");
				double total = salep * quantity;
				long  total1= Math.round(total);
				table_cell4 = new PdfPCell(new Phrase(String.valueOf(total1), font10NoBold));
				table_cell4.setBorder(table_cell4.NO_BORDER);
				table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				table_cell4.setBorderWidth(1f);
				table_cell4.setPaddingTop(2);
				table_cell4.setPaddingBottom(2);
				table4.addCell(table_cell4);

				if (iGST == 5 || iGST == 12 || iGST == 18 || iGST == 28) {
					double taxAmount = (iGST / 100) * (salep);
					double igstAmount = taxAmount * quantity;
					igstTotal = igstTotal + igstAmount;
					System.out.println("igstTotal = = =" + igstTotal);
				}
				if (GST == 5.00 || GST == 12.00 || GST == 18.00 || GST == 28.00) {
					double taxAmount = (GST / 100) * (salep);
					double gstAmount = taxAmount * quantity;
					gstTotal = gstTotal + gstAmount;
					System.out.println("gstTotal = = =" + gstTotal);
				}
				b = b + 1;
			} else {
				if (b < 20) {
					String product_name = rs.getString("product_name");
					table_cell4 = new PdfPCell(new Phrase(product_name, font10NoBold));
					table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
					table_cell4.setBorder(table_cell4.NO_BORDER);
					table_cell4.setBorderWidth(1f);
					table_cell4.setPaddingTop(2);
					table_cell4.setPaddingBottom(2);
					table5.addCell(table_cell4);

					String manufacturing_company = rs.getString("company");
					table_cell4 = new PdfPCell(new Phrase(manufacturing_company, font10NoBold));
					table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
					table_cell4.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
					table_cell4.setBorder(table_cell4.NO_BORDER);
					table_cell4.setPaddingTop(2);
					table_cell4.setPaddingBottom(2);
					table5.addCell(table_cell4);

					String hsnNumber = rs.getString("hsn");
					table_cell4 = new PdfPCell(new Phrase(hsnNumber, font10NoBold));
					table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
					table_cell4.setBorder(table_cell4.NO_BORDER);
					table_cell4.setBorderWidth(1f);
					table_cell4.setPaddingTop(2);
					table_cell4.setPaddingBottom(2);
					table5.addCell(table_cell4);

					String batch_number = rs.getString("batch_number");
					table_cell4 = new PdfPCell(new Phrase(batch_number, font10NoBold));
					table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
					table_cell4.setBorder(table_cell4.NO_BORDER);
					table_cell4.setBorderWidth(1f);
					table_cell4.setPaddingTop(2);
					table_cell4.setPaddingBottom(2);
					table5.addCell(table_cell4);

					String expiry_date = rs.getString("expiry_date");
					table_cell4 = new PdfPCell(new Phrase(expiry_date, font10NoBold));
					table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
					table_cell4.setBorder(table_cell4.NO_BORDER);
					table_cell4.setBorderWidth(1f);
					table_cell4.setPaddingTop(2);
					table_cell4.setPaddingBottom(2);
					table5.addCell(table_cell4);

					double weight = rs.getDouble("weight");
					table_cell4 = new PdfPCell(new Phrase(String.valueOf(weight), font10NoBold));
					table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
					table_cell4.setBorder(table_cell4.NO_BORDER);
					table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
					table_cell4.setBorderWidth(1f);
					table_cell4.setPaddingTop(2);
					table_cell4.setPaddingBottom(2);
					table5.addCell(table_cell4);
					
					double weight1 = rs.getDouble("weight");
					table_cell4 = new PdfPCell(new Phrase(String.valueOf(weight1), font10NoBold));
					table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
					table_cell4.setBorder(table_cell4.NO_BORDER);
					table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
					table_cell4.setBorderWidth(1f);
					table_cell4.setPaddingTop(2);
					table_cell4.setPaddingBottom(2);
					table5.addCell(table_cell4);
					
					BigDecimal salePrice = rs.getBigDecimal("salePriceWithoutTax", 2);
					String sale_price = String.valueOf(rs.getString("salePriceWithoutTax"));
					table_cell4 = new PdfPCell(new Phrase(sale_price, font10NoBold));
					table_cell4.setBorder(table_cell4.NO_BORDER);
					table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
					table_cell4.setBorderWidth(1f);
					table_cell4.setPaddingTop(2);
					table_cell4.setPaddingBottom(2);
					table5.addCell(table_cell4);

					int quantity = rs.getInt("quantity");
					table_cell4 = new PdfPCell(new Phrase(String.valueOf(quantity), font10NoBold));
					table_cell4.setBorder(table_cell4.NO_BORDER);
					table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
					table_cell4.setBorderWidth(1f);
					table_cell4.setPaddingTop(2);
					table_cell4.setPaddingBottom(2);
					table5.addCell(table_cell4);

					GST = rs.getDouble("tax_percentage");
					double half = 2;
					double halfGST = GST / half;

					table_cell4 = new PdfPCell(new Phrase(String.valueOf(GST), font10NoBold));
					table_cell4.setBorder(table_cell4.NO_BORDER);
					table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
					table_cell4.setBorderWidth(1f);
					table_cell4.setPaddingTop(2);
					table_cell4.setPaddingBottom(2);
					table5.addCell(table_cell4);

					String X =salePrice.toString();
					Double XX =Double.parseDouble(X);
					double taxpergst = (XX * GST)/100;
					double priceWithOutGST = XX- taxpergst;
					long  taxpergst1= Math.round(priceWithOutGST);
					
					
					table_cell4 = new PdfPCell(new Phrase(""+taxpergst1, font10NoBold));
					table_cell4.setBorder(table_cell4.NO_BORDER);
					table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
					table_cell4.setBorderWidth(1f);
					table_cell4.setPaddingTop(2);
					table_cell4.setPaddingBottom(2);
					table5.addCell(table_cell4);
					
					double salep = rs.getDouble("salePriceWithoutTax");
					double total = salep * quantity;
					long  total1= Math.round(total);
					table_cell4 = new PdfPCell(new Phrase(String.valueOf(total1), font10NoBold));
					table_cell4.setBorder(table_cell4.NO_BORDER);
					table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
					table_cell4.setBorderWidth(1f);
					table_cell4.setPaddingTop(2);
					table_cell4.setPaddingBottom(2);
					table5.addCell(table_cell4);

					if (iGST == 5 || iGST == 12 || iGST == 18 || iGST == 28) {
						double taxAmount = (iGST / 100) * (salep);
						double igstAmount = taxAmount * quantity;
						igstTotal = igstTotal + igstAmount;
						System.out.println("igstTotal = = =" + igstTotal);
					}
					if (GST == 5.00 || GST == 12.00 || GST == 18.00 || GST == 28.00) {
						double taxAmount = (GST / 100) * (salep);
						double gstAmount = taxAmount * quantity;
						gstTotal = gstTotal + gstAmount;
						System.out.println("gstTotal = = =" + gstTotal);
					}
					b = b + 1;
				} else {
					if (b < 30) {
						String product_name = rs.getString("product_name");
						table_cell4 = new PdfPCell(new Phrase(product_name, font10NoBold));
						table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
						table_cell4.setBorder(table_cell4.NO_BORDER);
						table_cell4.setBorderWidth(1f);
						table_cell4.setPaddingTop(2);
						table_cell4.setPaddingBottom(2);
						table6.addCell(table_cell4);

						String manufacturing_company = rs.getString("company");
						table_cell4 = new PdfPCell(new Phrase(manufacturing_company, font10NoBold));
						table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
						table_cell4.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
						table_cell4.setBorder(table_cell4.NO_BORDER);
						table_cell4.setPaddingTop(2);
						table_cell4.setPaddingBottom(2);
						table6.addCell(table_cell4);

						String hsnNumber = rs.getString("hsn");
						table_cell4 = new PdfPCell(new Phrase(hsnNumber, font10NoBold));
						table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
						table_cell4.setBorder(table_cell4.NO_BORDER);
						table_cell4.setBorderWidth(1f);
						table_cell4.setPaddingTop(2);
						table_cell4.setPaddingBottom(2);
						table6.addCell(table_cell4);

						String batch_number = rs.getString("batch_number");
						table_cell4 = new PdfPCell(new Phrase(batch_number, font10NoBold));
						table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
						table_cell4.setBorder(table_cell4.NO_BORDER);
						table_cell4.setBorderWidth(1f);
						table_cell4.setPaddingTop(2);
						table_cell4.setPaddingBottom(2);
						table6.addCell(table_cell4);

						String expiry_date = rs.getString("expiry_date");
						table_cell4 = new PdfPCell(new Phrase(expiry_date, font10NoBold));
						table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
						table_cell4.setBorder(table_cell4.NO_BORDER);
						table_cell4.setBorderWidth(1f);
						table_cell4.setPaddingTop(2);
						table_cell4.setPaddingBottom(2);
						table6.addCell(table_cell4);

						double weight = rs.getDouble("weight");
						table_cell4 = new PdfPCell(new Phrase(String.valueOf(weight), font10NoBold));
						table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
						table_cell4.setBorder(table_cell4.NO_BORDER);
						table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
						table_cell4.setBorderWidth(1f);
						table_cell4.setPaddingTop(2);
						table_cell4.setPaddingBottom(2);
						table6.addCell(table_cell4);
						
						double weight1 = rs.getDouble("weight");
						table_cell4 = new PdfPCell(new Phrase(String.valueOf(weight1), font10NoBold));
						table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
						table_cell4.setBorder(table_cell4.NO_BORDER);
						table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
						table_cell4.setBorderWidth(1f);
						table_cell4.setPaddingTop(2);
						table_cell4.setPaddingBottom(2);
						table6.addCell(table_cell4);
						
						BigDecimal salePrice = rs.getBigDecimal("salePriceWithoutTax", 2);
						String sale_price = String.valueOf(rs.getString("salePriceWithoutTax"));
						table_cell4 = new PdfPCell(new Phrase(sale_price, font10NoBold));
						table_cell4.setBorder(table_cell4.NO_BORDER);
						table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
						table_cell4.setBorderWidth(1f);
						table_cell4.setPaddingTop(2);
						table_cell4.setPaddingBottom(2);
						table6.addCell(table_cell4);

						int quantity = rs.getInt("quantity");
						table_cell4 = new PdfPCell(new Phrase(String.valueOf(quantity), font10NoBold));
						table_cell4.setBorder(table_cell4.NO_BORDER);
						table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
						table_cell4.setBorderWidth(1f);
						table_cell4.setPaddingTop(2);
						table_cell4.setPaddingBottom(2);
						table6.addCell(table_cell4);

						GST = rs.getDouble("tax_percentage");
						double half = 2;
						double halfGST = GST / half;

						table_cell4 = new PdfPCell(new Phrase(String.valueOf(GST), font10NoBold));
						table_cell4.setBorder(table_cell4.NO_BORDER);
						table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
						table_cell4.setBorderWidth(1f);
						table_cell4.setPaddingTop(2);
						table_cell4.setPaddingBottom(2);
						table6.addCell(table_cell4);
						
						String X =salePrice.toString();
						Double XX =Double.parseDouble(X);
						double taxpergst = (XX * GST)/100;
						double priceWithOutGST = XX- taxpergst;
						long  taxpergst1= Math.round(priceWithOutGST);
						
						
						table_cell4 = new PdfPCell(new Phrase(""+taxpergst1, font10NoBold));
						table_cell4.setBorder(table_cell4.NO_BORDER);
						table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
						table_cell4.setBorderWidth(1f);
						table_cell4.setPaddingTop(2);
						table_cell4.setPaddingBottom(2);
						table6.addCell(table_cell4);

						double salep = rs.getDouble("salePriceWithoutTax");
						double total = salep * quantity;
						long  total1= Math.round(total);
						table_cell4 = new PdfPCell(new Phrase(String.valueOf(total1), font10NoBold));
						table_cell4.setBorder(table_cell4.NO_BORDER);
						table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
						table_cell4.setBorderWidth(1f);
						table_cell4.setPaddingTop(2);
						table_cell4.setPaddingBottom(2);
						table6.addCell(table_cell4);

						if (iGST == 5 || iGST == 12 || iGST == 18 || iGST == 28) {
							double taxAmount = (iGST / 100) * (salep);
							double igstAmount = taxAmount * quantity;
							igstTotal = igstTotal + igstAmount;
							System.out.println("igstTotal = = =" + igstTotal);
						}
						if (GST == 5.00 || GST == 12.00 || GST == 18.00 || GST == 28.00) {
							double taxAmount = (GST / 100) * (salep);
							double gstAmount = taxAmount * quantity;
							gstTotal = gstTotal + gstAmount;
							System.out.println("gstTotal = = =" + gstTotal);
						}
						b = b + 1;
					}
				}

			}

		}
		if (b > 0 && b <= 10) {

			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);
			
			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);
			
			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);
			
			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);


			//-----------------------------------

			halfGstTotal = (gstTotal) / 2;

			table_cell4 = new PdfPCell(
					new Phrase("\n\n\n\n\n\n\n\n\n\n\n\n\n\nTransportation Expense:-" + expenses, Normalfont11));
			table_cell4.setColspan(3);
			table_cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);

			table_cell4 = new PdfPCell(
					new Phrase("\n\n\n\n\n\n\n\n\n\n\n\n\n\nCGST Amount:-" + df.format(halfGstTotal), Normalfont11));
			table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setColspan(3);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n\n\n\n\n\n\n\n\n\n\n\n\n\n"));
			table_cell4.setColspan(3);
			table_cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);
			
			table_cell4 = new PdfPCell(new Phrase("\n\n\n\n\n\n\n\n\n\n\n\n\n\n"));
			table_cell4.setColspan(3);
			table_cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);

			table_cell4 = new PdfPCell(
					new Phrase("Hamali Expense            :-" + HamaliExpence, Normalfont11));
			table_cell4.setColspan(3);
			table_cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("SGST Amount:-" + df.format(halfGstTotal), Normalfont11));
			table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setColspan(3);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);

			table_cell4 = new PdfPCell(
					new Phrase("Gross Total(Inc GST):-" + df.format(Double.parseDouble(gross_total)), font12));
			table_cell4.setColspan(5);
			table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("", Normalfont11));
			table_cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setColspan(1);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("", Normalfont11));
			table_cell4.setColspan(7);
			table_cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);
			
			table_cell4 = new PdfPCell(new Phrase("", Normalfont11));
			table_cell4.setColspan(7);
			table_cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);
			
			table_cell4 = new PdfPCell(new Phrase(""));
			table_cell4.setColspan(7);
			table_cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);
			
			table_cell4 = new PdfPCell(new Phrase(""));
			table_cell4.setColspan(7);
			table_cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);
			
			table_cell4 = new PdfPCell(new Phrase(""));
			table_cell4.setColspan(7);
			table_cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);
			
			table_cell4 = new PdfPCell(new Phrase(""));
			table_cell4.setColspan(7);
			table_cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);

			// Gross Total in Words
			long totalInLong = Math.round(Double.parseDouble(gross_total));
			String grossTotal = String.valueOf(totalInLong);
			int grossTotalInInteger = Integer.parseInt(grossTotal);
			NumToWord w = new NumToWord();
			String amtInWord = w.convert(grossTotalInInteger);

			table_cell4 = new PdfPCell(new Phrase("Rupees in words : " + amtInWord + " Only/-", font11));
			table_cell4.setColspan(12);
			table_cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(4f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);
			
			/* table_cell4 = new PdfPCell(new Phrase("Declaration by customer : I have willingly purchased the pesticide mentioned in the bill. I have been given all the information related to its usage and precaution to be taken while spraying. Henceforth I  will be responsible for the pesticide usage and will not hold retailer responsible for any incident related to its usage.",
					font8NoBold));
			table_cell4.setColspan(10);
			table_cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(4f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);
 */
			table_cell4 = new PdfPCell(new Phrase());
			table_cell4.setColspan(12);
			table_cell4.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
			table_cell4.setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.TOP);
			table_cell4.setBorderWidth(1f);
			table4.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase(
					"\n Customer Sign                                                                      "+Footer_Shop_Name,
					Normalfont11));
			table_cell4.setColspan(12);
			table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			table_cell4.setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
			table_cell4.setBorderWidth(1f);
			table_cell4.setPaddingBottom(3);
			table4.addCell(table_cell4);

			document.add(table4);

			//Instructions
			PdfPTable footer = new PdfPTable(1);
			footer.setWidthPercentage(100);
			float[] footercolumnWidths = { 0.9f };
			footer.setWidths(footercolumnWidths);

			PdfPCell table_cell9;

			table_cell9 = new PdfPCell(new Phrase(
					"1) Good Once Sold will not be taken Back.2)for agriculture only.3)subject to yavatmal juridisction.4)Sale label tag no.batch no.weight etc checked and purchased in good condition.                                                                            (Agriculture Use Only).",
					font8NoBold));
			table_cell9.setBorder(table_cell.NO_BORDER);
			footer.addCell(table_cell9);

			document.add(footer);
			document.add(new Paragraph(" "));

		}

		if (b > 10 && b <= 20) {

			document.add(table4);
			document.add(new Paragraph(
					"                                                                                                             Continue On Next Page..."));
			document.add(new Paragraph(
					"					                                   Thank You Visit Again !!!						"));

			document.newPage();

			document.add(new Paragraph("        "));
			document.add(new Paragraph("        "));

			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);
			
			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);
			
			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);
			
			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);

			//-----------------------------------

			halfGstTotal = (gstTotal) / 2;

			table_cell4 = new PdfPCell(
					new Phrase("\n\n\n\n\n\n\n\n\n\n\n\n\n\nTransportation Expense:-" + expenses, Normalfont11));
			table_cell4.setColspan(3);
			table_cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);

			table_cell4 = new PdfPCell(
					new Phrase("\n\n\n\n\n\n\n\n\n\n\n\n\n\nCGST Amount:-" + df.format(halfGstTotal), Normalfont11));
			table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setColspan(3);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n\n\n\n\n\n\n\n\n\n\n\n\n\n"));
			table_cell4.setColspan(3);
			table_cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);
			
			table_cell4 = new PdfPCell(new Phrase("\n\n\n\n\n\n\n"));
			table_cell4.setColspan(3);
			table_cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);

			table_cell4 = new PdfPCell(
					new Phrase("Hamali Expense            :-" + HamaliExpence, Normalfont11));
			table_cell4.setColspan(3);
			table_cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("SGST Amount:-" + df.format(halfGstTotal), Normalfont11));
			table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setColspan(3);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);

			table_cell4 = new PdfPCell(
					new Phrase("Gross Total(Inc GST):-" + df.format(Double.parseDouble(gross_total)), font12));
			table_cell4.setColspan(5);
			table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("", Normalfont11));
			table_cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setColspan(1);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("", Normalfont11));
			table_cell4.setColspan(7);
			table_cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);
			
			table_cell4 = new PdfPCell(new Phrase("", Normalfont11));
			table_cell4.setColspan(7);
			table_cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);
			
			table_cell4 = new PdfPCell(new Phrase("", Normalfont11));
			table_cell4.setColspan(7);
			table_cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);
			
			table_cell4 = new PdfPCell(new Phrase("", Normalfont11));
			table_cell4.setColspan(7);
			table_cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);
			
			table_cell4 = new PdfPCell(new Phrase("", Normalfont11));
			table_cell4.setColspan(7);
			table_cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);
			
			table_cell4 = new PdfPCell(new Phrase("", Normalfont11));
			table_cell4.setColspan(7);
			table_cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);

			// Gross Total in Words
			long totalInLong = Math.round(Double.parseDouble(gross_total));
			String grossTotal = String.valueOf(totalInLong);
			int grossTotalInInteger = Integer.parseInt(grossTotal);
			NumToWord w = new NumToWord();
			String amtInWord = w.convert(grossTotalInInteger);

			table_cell4 = new PdfPCell(new Phrase("Rupees in words : " + amtInWord + " Only/-", font11));
			table_cell4.setColspan(12);
			table_cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(4f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase());
			table_cell4.setColspan(12);
			table_cell4.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
			table_cell4.setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.TOP);
			table_cell4.setBorderWidth(1f);
			table5.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase(
					"\n Customer Sign                                                                      "+Footer_Shop_Name,
					Normalfont11));
			table_cell4.setColspan(12);
			table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			table_cell4.setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
			table_cell4.setBorderWidth(1f);
			table_cell4.setPaddingBottom(3);
			table5.addCell(table_cell4);

			document.add(table5);

			//Instructions
			PdfPTable footer = new PdfPTable(1);
			footer.setWidthPercentage(100);
			float[] footercolumnWidths = { 0.9f };
			footer.setWidths(footercolumnWidths);

			PdfPCell table_cell9;

			table_cell9 = new PdfPCell(new Phrase(
					"1) Good Once Sold will not be taken Back.2)for agriculture only.3)subject to yavatmal juridisction.4)Sale label tag no.batch no.weight etc checked and purchased in good condition.                                                                            (Agriculture Use Only).",
					font8NoBold));
			table_cell9.setBorder(table_cell.NO_BORDER);
			footer.addCell(table_cell9);

			document.add(footer);
			document.add(new Paragraph(" "));

		}

		if (b > 20 && b <= 30) {

			document.add(table4);
			document.add(new Paragraph(
					"                                                                                                             Continue On Next Page..."));
			document.add(new Paragraph(
					"					                                   Thank You Visit Again !!!						"));

			document.newPage();

			document.add(new Paragraph("        "));
			document.add(new Paragraph("        "));

			document.add(table5);

			document.add(new Paragraph(
					"                                                                                                             Continue On Next Page..."));
			document.add(new Paragraph(
					"					                                   Thank You Visit Again !!!						"));

			document.newPage();

			document.add(new Paragraph("        "));
			document.add(new Paragraph("        "));

			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);
			
			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);
			
			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);
			
			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n"));
			table_cell4.setBorder(table_cell4.NO_BORDER);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);

			//-----------------------------------

			halfGstTotal = (gstTotal) / 2;

			table_cell4 = new PdfPCell(
					new Phrase("\n\n\n\n\n\n\n\n\n\n\n\n\n\nTransportation Expense:-" + expenses, Normalfont11));
			table_cell4.setColspan(3);
			table_cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);

			table_cell4 = new PdfPCell(
					new Phrase("\n\n\n\n\n\n\n\n\n\n\n\n\n\nCGST Amount:-" + df.format(halfGstTotal), Normalfont11));
			table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setColspan(3);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n\n\n\n\n\n\n\n\n\n\n\n\n\n"));
			table_cell4.setColspan(3);
			table_cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("\n\n\n\n\n\n\n\n\n\n\n\n\n\n"));
			table_cell4.setColspan(3);
			table_cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);
			
			table_cell4 = new PdfPCell(
					new Phrase("Hamali Expense            :-" + HamaliExpence, Normalfont11));
			table_cell4.setColspan(3);
			table_cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("SGST Amount:-" + df.format(halfGstTotal), Normalfont11));
			table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setColspan(3);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);

			table_cell4 = new PdfPCell(
					new Phrase("Gross Total(Inc GST):-" + df.format(Double.parseDouble(gross_total)), font12));
			table_cell4.setColspan(5);
			table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase("", Normalfont11));
			table_cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setColspan(1);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase(""));
			table_cell4.setColspan(7);
			table_cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);
			
			table_cell4 = new PdfPCell(new Phrase(""));
			table_cell4.setColspan(7);
			table_cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);
			
			table_cell4 = new PdfPCell(new Phrase(""));
			table_cell4.setColspan(7);
			table_cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);
			
			table_cell4 = new PdfPCell(new Phrase(""));
			table_cell4.setColspan(7);
			table_cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase(""));
			table_cell4.setColspan(7);
			table_cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);
			
			table_cell4 = new PdfPCell(new Phrase(""));
			table_cell4.setColspan(7);
			table_cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(1f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);
			
			// Gross Total in Words
			long totalInLong = Math.round(Double.parseDouble(gross_total));
			String grossTotal = String.valueOf(totalInLong);
			int grossTotalInInteger = Integer.parseInt(grossTotal);
			NumToWord w = new NumToWord();
			String amtInWord = w.convert(grossTotalInInteger);

			table_cell4 = new PdfPCell(new Phrase("Rupees in words : " + amtInWord + " Only/-", font11));
			table_cell4.setColspan(12);
			table_cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_cell4.setBorder(Rectangle.NO_BORDER);
			table_cell4.setPaddingBottom(4f);
			table_cell4.setPaddingTop(1f);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase());
			table_cell4.setColspan(12);
			table_cell4.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
			table_cell4.setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.TOP);
			table_cell4.setBorderWidth(1f);
			table6.addCell(table_cell4);

			table_cell4 = new PdfPCell(new Phrase(
					"\n Customer Sign                                                                      "+Footer_Shop_Name,
					Normalfont11));
			table_cell4.setColspan(12);
			table_cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			table_cell4.setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
			table_cell4.setBorderWidth(1f);
			table_cell4.setPaddingBottom(3);
			table6.addCell(table_cell4);

			document.add(table6);

			//Instructions
			PdfPTable footer = new PdfPTable(1);
			footer.setWidthPercentage(100);
			float[] footercolumnWidths = { 0.9f };
			footer.setWidths(footercolumnWidths);

			PdfPCell table_cell9;

			table_cell9 = new PdfPCell(new Phrase(
					"1) Good Once Sold will not be taken Back.2)for agriculture only.3)subject to yavatmal juridisction.4)Sale label tag no.batch no.weight etc checked and purchased in good condition.                                                                            (Agriculture Use Only).",
					font8NoBold));
			table_cell9.setBorder(table_cell.NO_BORDER);
			footer.addCell(table_cell9);

			document.add(footer);
			document.add(new Paragraph(" "));
		}
		// step 5 

		rs.close();
		stmt.close();
		conn.close();
		document.close();

	} catch (DocumentException de) {
		throw new IOException(de.getMessage());
	}
%>
