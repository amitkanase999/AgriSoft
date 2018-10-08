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

<%@ page trimDirectiveWhitespaces="true" %>

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
<%@ page import="com.itextpdf.text.Element" %>
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
Long billno=(Long)session.getAttribute("customerBill");  
String ClientName  = (String)session.getAttribute("customerName");
System.out.print(billno +"" +"BILL");
  
Connection conn = null;

try {
	
	 // step 1
    Document document = new Document();
	 
	 
    // step 2
   
    PdfWriter.getInstance(document, response.getOutputStream());
   
    // step 3
    document.open();
    
	Class.forName("com.mysql.jdbc.Driver");
	conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/fertilizer","root","root");
 	Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery("select product_name,sale_price,total_amount from fertilizer_bill WHERE customerBill="+billno);  
	
    System.out.println("Query Execute");
    Date d1 = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
    SimpleDateFormat sdf1 = new SimpleDateFormat("E");
    SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss  ");
     sdf2.setTimeZone(TimeZone.getTimeZone("IST")); 

  	 String stdver1=(String) sdf.format(d1);
  	 String day=sdf1.format(d1.getDate());
     String Time=sdf2.format(d1.getTime());
     String dtfinl=stdver1;
    
    // step 4
     document.add(new Paragraph("        "));
     document.add(new Paragraph("        "));
     document.add(new Paragraph("        "));
     document.add(new Paragraph("        "));
 	 document.add(new Paragraph("        "));
	 document.add(new Paragraph("        "));
	 document.add(new Paragraph("        "));
	 document.add(new Paragraph("        "));
	 document.add(new Paragraph("        "));
	 document.add(new Paragraph("        "));
	 document.add(new Paragraph("        "+ClientName));
	 document.add(new Paragraph("        "));
	 document.add(new Paragraph("                                                                                                                             "+ billno));
	 document.add(new Paragraph("                                                                                                 							                     "+dtfinl ));
	 document.add(new Paragraph("        "));
	 document.add(new Paragraph("        "));
	 document.add(new Paragraph("        "));
	 document.add(new Paragraph("        "));

	
    PdfPTable table = new PdfPTable(2); 
	PdfPTable table1 = new PdfPTable(10);       
    PdfPTable table2 = new PdfPTable(10); 
  
     
    PdfPCell cell1 = new PdfPCell(new Paragraph(""));
    PdfPCell cell2 = new PdfPCell(new Paragraph(""));

	    int a=1;
	    Double sum=0d;
	    Double tax=0d;
	    String taxVal="";
	    Double swachaTax=0d;
	    Double groTOT=0d;
	    String xx1="";
   
   		String grossT="";
   
    while(rs.next()){
	   
	  	String p2 = rs.getString("product_name");
    	PdfPCell t2 = new PdfPCell(new Phrase(p2));
    	
    	System.out.print(p2 +"" +"Items");
        t2.setHorizontalAlignment(Element.ALIGN_LEFT);
        t2.setBorder(0);
        table.addCell(t2); 
        
        String p3 = String.valueOf(rs.getDouble("sale_price"));
    	PdfPCell t3 = new PdfPCell(new Phrase(p3));
    	t3.setHorizontalAlignment(Element.ALIGN_RIGHT);
        t3.setBorder(0);
        table.addCell(t3); 
    	
 
    	System.out.print(p3 +"" +"totalWithExpense");
    	sum =sum+ Double.parseDouble(p3);
    	
    	System.out.print(sum +"" +"grossTotal");
    	 
    }
    
    	document.add(table); 
    	document.add(new Paragraph("        "));
        document.add(new Paragraph("        "));
        document.add(new Paragraph("                                                                           				                                        			        	"+sum));
    	document.add(new Paragraph(""));
        document.add(new Paragraph("					                                   Thank You Visit Again !..................						"));
       
  
    
   
    // step 5
    
        
     rs.close();
     stmt.close();
     conn.close();
     document.close();
   
} catch (DocumentException de) {
    throw new IOException(de.getMessage());
}
%> 
        
 <script type="text/javascript">
		var a = ['','one ','two ','three ','four ', 'five ','six ','seven ','eight ','nine ','ten ','eleven ','twelve ','thirteen ','fourteen ','fifteen ','sixteen ','seventeen ','eighteen ','nineteen '];
		var b = ['', '', 'twenty','thirty','forty','fifty', 'sixty','seventy','eighty','ninety'];

		function inWords (groTOT) {
		    if ((groTOT = groTOT.toString()).length > 9) return 'overflow';
		    n = ('000000000' + groTOT).substr(-9).match(/^(\d{2})(\d{2})(\d{2})(\d{1})(\d{2})$/);
		    if (!n) return; var str = '';
		    str += (n[1] != 0) ? (a[Number(n[1])] || b[n[1][0]] + ' ' + a[n[1][1]]) + 'crore ' : '';
		    str += (n[2] != 0) ? (a[Number(n[2])] || b[n[2][0]] + ' ' + a[n[2][1]]) + 'lakh ' : '';
		    str += (n[3] != 0) ? (a[Number(n[3])] || b[n[3][0]] + ' ' + a[n[3][1]]) + 'thousand ' : '';
		    str += (n[4] != 0) ? (a[Number(n[4])] || b[n[4][0]] + ' ' + a[n[4][1]]) + 'hundred ' : '';
		    str += (n[5] != 0) ? ((str != '') ? 'and ' : '') + (a[Number(n[5])] || b[n[5][0]] + ' ' + a[n[5][1]]) + 'only ' : '';
		    return str;
		    al
		}
</script>  
        