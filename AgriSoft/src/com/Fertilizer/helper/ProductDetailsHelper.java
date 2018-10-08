package com.Fertilizer.helper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.Fertilizer.bean.GetProductDetailPO;
import com.Fertilizer.bean.GetProductDetails;
import com.Fertilizer.bean.ProductDetailsForReports;
import com.Fertilizer.dao.ProductDetailsDao;
import com.Fertilizer.hibernate.GoodsReceiveBean;
import com.Fertilizer.hibernate.ProductDetailsBean;
import com.Fertilizer.hibernate.ProductStockDetailsBean;
import com.Fertilizer.utility.HibernateUtility;

public class ProductDetailsHelper {

	Long barcodeNo;
	BigInteger productId;
	public void productDetails(HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("In helper");

		String fk_cat_id = request.getParameter("fk_cat_id");
		System.out.println("==========Category Id===="+fk_cat_id);
		String fk_unit_id = request.getParameter("fk_unit_id");
		String productName = request.getParameter("productName");
		String manufacturingCompany = request.getParameter("manufacturingCompany");
		String buyPrice = request.getParameter("buyPrice");
		String salePrice = request.getParameter("salePrice");
		String weight = request.getParameter("weight");
		String creditSalePrice = request.getParameter("creditSalePrice");
		String fkTaxId = request.getParameter("fkTaxId");
		String taxPercentage = request.getParameter("taxPercentage");
		String mrp = request.getParameter("mrp");
		String hsn = request.getParameter("hsn");

		ProductDetailsDao pd = new ProductDetailsDao();
		List stkList  = pd.getAllStockEntry();

		// If Stock Is Empty  

		if(stkList.size() == 0){
			ProductDetailsBean pdb = new ProductDetailsBean();	
			if(!"".equals(buyPrice)){
				pdb.setBuyPrice(Double.parseDouble(buyPrice));
			} else
			{
				pdb.setBuyPrice(Double.parseDouble("00"));
			}

			if(!"".equals(salePrice)){
				pdb.setSalePrice(Double.parseDouble(salePrice));
			} else
			{
				pdb.setSalePrice(Double.parseDouble("00"));
			}

			if(!"".equals(hsn)){
				pdb.setHsn(hsn);
			} else
			{
				pdb.setHsn("N/A");
			}

			SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			Date dateobj = new Date();
			System.out.println(dateFormat1.format(dateobj));

			pdb.setInsertDate(dateobj);

			if(fkTaxId == null || fkTaxId.equals("selected")){
				pdb.setFkTaxId(0l);
			}
			else{
				pdb.setFkTaxId(Long.parseLong((fkTaxId)));
			}

			if(taxPercentage == null){
				pdb.setTaxPercentage(0.0);
			}
			else{
				pdb.setTaxPercentage(Double.parseDouble(taxPercentage));
			}

			if(mrp == null){
				pdb.setMrp(0.0);
			}
			else{
				pdb.setMrp(Double.parseDouble(mrp));
			}
			if(fk_unit_id == null){
				pdb.setFk_unit_id(0l);
			}
			else{
				pdb.setFk_unit_id(Long.parseLong(fk_unit_id));
			}


			pdb.setManufacturingCompany(manufacturingCompany);
			pdb.setFk_cat_id(Long.parseLong(fk_cat_id));
			pdb.setProductName(productName);

			pdb.setWeight(Double.parseDouble(weight));

			if(creditSalePrice == null){
				pdb.setCreditSalePrice(0.0d);
			}
			else{
				pdb.setCreditSalePrice(Double.parseDouble(creditSalePrice));
			}


			ProductDetailsDao pdd = new ProductDetailsDao();
			pdd.productDetail(pdb);
		}
		else

			/*To Update Stock Table If It is Not Empty */

		{

			for(int i=0;i<stkList.size();i++){

				ProductDetailsBean st = (ProductDetailsBean)stkList.get(i);
				Long ItemId = st.getProdctId();
				String ProductName = st.getProductName();
				String Comapny = st.getManufacturingCompany();
				Double Weight = st.getWeight();

				/*If ItemName Is Already Exists In Stock Table */ 

				if(productName.equals(ProductName) && Comapny.equals(manufacturingCompany) && Weight.equals(Double.parseDouble(weight))){
					break;
				}
				else
				{
					/*ItemName is Not Exists */

					if(i+1 == stkList.size()){

						ProductDetailsBean pdb1 = new ProductDetailsBean();

						if(!"".equals(buyPrice)){
							pdb1.setBuyPrice(Double.parseDouble(buyPrice));
						} else
						{
							pdb1.setBuyPrice(Double.parseDouble("00"));
						}
						if(!"".equals(salePrice)){
							pdb1.setSalePrice(Double.parseDouble(salePrice));
						} else
						{
							pdb1.setSalePrice(Double.parseDouble("00"));
						}

						SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
						Date dateobj = new Date();
						System.out.println(dateFormat1.format(dateobj));

						pdb1.setInsertDate(dateobj);

						if(fkTaxId == null){
							pdb1.setFkTaxId(0l);
						}
						else{
							pdb1.setFkTaxId(Long.parseLong((fkTaxId)));
						}

						if(taxPercentage == null){
							pdb1.setTaxPercentage(0.0);
						}
						else{
							pdb1.setTaxPercentage(Double.parseDouble(taxPercentage));
						}

						if(mrp == null){
							pdb1.setMrp(0.0);
						}
						else{
							pdb1.setMrp(Double.parseDouble(mrp));
						}

						if(!"".equals(hsn)){
							pdb1.setHsn(hsn);
						} else
						{
							pdb1.setHsn("N/A");
						}

						pdb1.setManufacturingCompany(manufacturingCompany);
						pdb1.setFk_cat_id(Long.parseLong(fk_cat_id));
						pdb1.setProductName(productName);
						pdb1.setFk_unit_id(Long.parseLong(fk_unit_id));
						pdb1.setWeight(Double.parseDouble(weight));

						if(creditSalePrice == ""){
							pdb1.setCreditSalePrice(0.0d);
						}
						else{
							pdb1.setCreditSalePrice(Double.parseDouble(creditSalePrice));
						}

						ProductDetailsDao pdd1 = new ProductDetailsDao();
						pdd1.productDetail(pdb1);
					}

				}
			}
		}		

	}

	public Map getAllProduct(String supplierId) {

		ProductDetailsDao dao = new ProductDetailsDao();
		List list= dao.getAllProductBySuppliers(supplierId);
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetProductDetails bean = new GetProductDetails();
			System.out.println(Arrays.toString(o));
			bean.setProduct(o[0].toString());;
			bean.setInsertDate(o[1].toString());
			System.out.println("***************"+o[0]);
			map.put(bean.getProduct(),bean);

		}
		return map;
	}

	public Map getAllProductByCatAndBySup(String category ) {

		ProductDetailsDao dao = new ProductDetailsDao();
		List list= dao.getAllProductBySCategoryAndBySuppliers(category );

		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetProductDetails bean = new GetProductDetails();
			System.out.println(Arrays.toString(o));
			bean.setProduct(o[0].toString());;
			bean.setInsertDate(o[1].toString());
			bean.setManufacturer(o[2].toString());
			bean.setWeight(Double.parseDouble(o[3].toString()));
			System.out.println("***************"+o[0]);
			map.put(bean.getProduct(),bean);
		}
		return map;
	}

	public Map getAllProductByCatForadvance(String category ) {
		int count = 1;
		ProductDetailsDao dao = new ProductDetailsDao();
		List list= dao.getAllProductBySCategoryAndBySuppliers(category );

		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetProductDetails bean = new GetProductDetails();
			System.out.println(Arrays.toString(o));
			bean.setProduct(o[0].toString());;
			bean.setInsertDate(o[1].toString());
			bean.setManufacturer(o[2].toString());
			bean.setWeight(Double.parseDouble(o[3].toString()));
			bean.setUnitName(o[4].toString());
			System.out.println("***************"+o[0]);
			map.put(count,bean);
			count++;
		}
		return map;
	}
	
	
	public Map getAllSupplerBillNo(String fkSupplierId ) {
		int count = 1;
		ProductDetailsDao dao = new ProductDetailsDao();
		List list= dao.getAllBillNoAgaintsSupller(fkSupplierId);

		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			
			GoodsReceiveBean bean=new GoodsReceiveBean();
			bean.setBillNum(o[0].toString());
			bean.setSupplier(Long.parseLong(o[1].toString()));
			
			System.out.println("***************"+o[0]);
			map.put(count,bean);
			count++;
		}
		return map;
	}

	public Map getProductDetailsForPO(String productId) {

		ProductDetailsDao dao = new ProductDetailsDao();
		List list = dao.getProductDetails(productId);
		System.out.println(list.size());
		Map  map1 =  new HashMap();

		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetProductDetailPO bean = new GetProductDetailPO();

			bean.setProductID((BigInteger)o[0]);
			bean.setProductName((String)o[1]);
			bean.setBuyPrice((BigDecimal)o[2]);
			bean.setSalePrice((BigDecimal)o[3]);
			bean.setWeight((BigDecimal)o[4]);
			bean.setMrp((BigDecimal)o[5]);
			bean.setQuantity(0l);
			map1.put(bean.getProductID(),bean);
		}
		return map1;
	}

	public Map getProductDetailsForGoodsReceiveForTax(String proName, String company, String weight) {

		ProductDetailsDao dao = new ProductDetailsDao();
		List list = dao.getProductDetailsForTaxGridInGoodsReceive(proName,company,weight);
		System.out.println(list.size());
		Map  map1 =  new HashMap();

		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetProductDetailPO bean = new GetProductDetailPO();

			bean.setProductID((BigInteger)o[0]);
			bean.setProductName((String)o[1]);
			bean.setBuyPrice((BigDecimal)o[2]);
			bean.setSalePrice((BigDecimal)o[3]);
			bean.setWeight((BigDecimal)o[4]);
			bean.setMrp((BigDecimal)o[5]);
			Double tax = Double.parseDouble(o[6].toString());
			Double halfTax = tax/2;
			bean.setcGst(halfTax);
			bean.setsGst(halfTax);
			bean.setManufacturer((String)o[7]);
			bean.setCatId(o[8].toString());
			bean.setHsn(o[9].toString());
			bean.setUnitName(o[10].toString());
			bean.setQuantity(0l);
			bean.setiGst(0d);
			map1.put(bean.getProductID(),bean);
		}
		return map1;
	}

	public Map getProductDetailsforbill(String productId) {

		ProductDetailsDao dao = new ProductDetailsDao();
		List list = dao.getProductDetailsforbill(productId);
		System.out.println(list.size());
		Map  map1 =  new HashMap();

		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetProductDetailPO bean = new GetProductDetailPO();

			bean.setProductID((BigInteger)o[0]);
			bean.setProductName((String)o[1]);
			bean.setBuyPrice((BigDecimal)o[2]);
			bean.setSalePrice((BigDecimal)o[3]);
			bean.setWeight((BigDecimal)o[4]);
			String batchNo = (String)o[5];
			if (batchNo!=null) {

				bean.setBatchNo(batchNo);
			}
			else {
				bean.setBatchNo(batchNo);
			}
			java.sql.Date expiryDate = (java.sql.Date)o[6];

			if (expiryDate==null) {

				bean.setExpireDate("N/A");
			}
			else {

				bean.setExpireDate(o[6].toString());

			}

			bean.setTaxPercentage((BigDecimal)o[7]);
			bean.setQuantity(1l);
			map1.put(bean.getProductID(),bean);
		}
		return map1;
	}
	public Map getProductDetailsforseedANdPestisidebill(String productId) {

		ProductDetailsDao dao = new ProductDetailsDao();
		List list = dao.getProductDetailsforseedANdPestisidebill(productId);
		System.out.println(list.size());
		Map  map1 =  new HashMap();

		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			System.out.println(Arrays.toString(o));
			GetProductDetailPO bean = new GetProductDetailPO();

			String batchNo = (String)o[0];

			bean.setBatchNo(batchNo);
			bean.setProductID((BigInteger)o[1]);
			bean.setQuantityForBatchNo((BigDecimal)o[2]);
			map1.put(bean.getBatchNo(),bean);
		}
		return map1;
	}

	public Map getProductDetailsByBatchNo(String batchNo1) {

		ProductDetailsDao dao = new ProductDetailsDao();
		List list = dao.getProductDetailsByBatchNo(batchNo1);
		System.out.println(list.size());
		Map  map1 =  new HashMap();

		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetProductDetailPO bean = new GetProductDetailPO();

			bean.setProductID((BigInteger)o[0]);
			bean.setProductName((String)o[1]);
			bean.setBuyPrice((BigDecimal)o[2]);
			bean.setSalePrice((BigDecimal)o[3]);
			bean.setWeight((BigDecimal)o[4]);
			String batchNo = (String)o[5];

			if (batchNo!=null) {

				bean.setBatchNo(batchNo);
			}
			else {
				bean.setBatchNo(batchNo);
			}
			java.sql.Date expiryDate = (java.sql.Date)o[6];

			if (expiryDate==null) {

				bean.setExpireDate("N/A");
			}
			else {

				bean.setExpireDate(o[6].toString());

			}
			bean.setTaxPercentage((BigDecimal)o[7]);
			bean.setQuantity(1l);
			System.out.println("***************"+o[0]+"\t"+o[5]);
			map1.put(bean.getBatchNo(),bean);
		}
		return map1;

	}

	public List getProductDetailForReportAsPerCategory(HttpServletRequest request,
			HttpServletResponse response) {

		String cat = request.getParameter("cat");
		System.out.println(cat+"Selected Category");

		Map<Long,ProductDetailsForReports> map = new HashMap<Long,ProductDetailsForReports>();

		ProductDetailsDao dao = new ProductDetailsDao();
		List<ProductDetailsForReports> productList = dao.getProductDetailForReportAsPerCat(cat);

		return productList;
	}

	public Map getAllProductByGodown(String fk_godown_id) {
		ProductDetailsDao dao = new ProductDetailsDao();
		List list= dao.getAllProductByGodown(fk_godown_id );
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetProductDetails bean = new GetProductDetails();
			System.out.println(Arrays.toString(o));
			bean.setProduct(o[0].toString());;
			bean.setInsertDate(o[1].toString());
			System.out.println("***************"+o[0]);
			map.put(bean.getProduct(),bean);

		}
		return map;
	}

	public Map getProductDetailsGodowm(String productId) {

		ProductDetailsDao dao = new ProductDetailsDao();
		List list = dao.getProductDetailsGodowm(productId);
		System.out.println(list.size());
		Map  map1 =  new HashMap();

		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetProductDetailPO bean = new GetProductDetailPO();

			bean.setProductID((BigInteger)o[0]);
			bean.setProductName((String)o[1]);
			bean.setBuyPrice((BigDecimal)o[2]);
			bean.setSalePrice((BigDecimal)o[3]);
			bean.setWeight((BigDecimal)o[4]);
			bean.setQuantity(1l);
			String batchNo = (String)o[5];
			if (batchNo.equals("")) {
				bean.setBatchNo("0");
			}
			else {
				bean.setBatchNo(batchNo);
			}
			java.sql.Date expiryDate = (java.sql.Date)o[6];

			if (expiryDate==null) {

				bean.setExpireDate("N/A");
			}
			else {

				bean.setExpireDate(o[6].toString());

			}
			map1.put(bean.getProductID(),bean);
		}
		return map1;
	}


	public Map getProductDetailsforbillByBarcode(String barcode) {

		ProductDetailsDao dao = new ProductDetailsDao();
		List list = dao.getProductDetailsforbillAsPerBarcode(barcode);
		System.out.println(list.size());
		Map  map1 =  new HashMap();

		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetProductDetailPO bean = new GetProductDetailPO();

			bean.setProductID((BigInteger)o[0]);
			bean.setProductName((String)o[1]);
			bean.setBuyPrice((BigDecimal)o[2]);
			bean.setSalePrice((BigDecimal)o[3]);
			bean.setWeight((BigDecimal)o[4]);
			String batchNo = (String)o[5];
			if (batchNo!=null) {

				bean.setBatchNo(batchNo);
			}
			else {
				bean.setBatchNo(batchNo);
			}
			java.sql.Date expiryDate = (java.sql.Date)o[6];

			if (expiryDate==null) {

				bean.setExpireDate("N/A");
			}
			else {

				bean.setExpireDate(o[6].toString());

			}
			bean.setQuantity(1l);
			map1.put(bean.getProductID(),bean);
		}
		return map1;
	}

	public Map getProductDetailsforseedANdPestisidebillAsPerBarcode(String barcode) {

		ProductDetailsDao dao = new ProductDetailsDao();
		List list = dao.getProductDetailsforseedANdPestisidebillAsPerBarcodeNum(barcode);
		System.out.println(list.size());
		Map  map1 =  new HashMap();

		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			System.out.println(Arrays.toString(o));
			GetProductDetailPO bean = new GetProductDetailPO();

			String batchNo = (String)o[0];

			bean.setBatchNo(batchNo);
			bean.setProductID((BigInteger)o[1]);
			bean.setQuantityForBatchNo((BigDecimal)o[2]);
			map1.put(bean.getBatchNo(),bean);
		}
		return map1;
	}

	public Map getProductDetailsForEdit(Long productID2) {

		ProductDetailsDao dao1 = new ProductDetailsDao();
		List catList = dao1.getAllEmployeeDetailsForEdit(productID2);

		Map  map =  new HashMap();
		for(int i=0;i<catList.size();i++)
		{
			Object[] o = (Object[])catList.get(i);

			GetProductDetails bean = new GetProductDetails();
			bean.setManufacturer(o[0].toString());
			bean.setTaxPercentage(Double.parseDouble(o[1].toString()));
			bean.setTaxType(o[2].toString());
			bean.setUnitName(o[3].toString());
			bean.setBuyPrice(Double.parseDouble(o[4].toString()));
			bean.setMrp(Double.parseDouble(o[5].toString()));
			bean.setSalePrice(Double.parseDouble(o[6].toString()));
			bean.setCreditSalePrice(Double.parseDouble(o[7].toString()));
			bean.setWeight(Double.parseDouble(o[8].toString()));
			bean.setHsn(Long.parseLong(o[9].toString()));

			map.put(bean.getManufacturer(),bean);
		}
		System.out.println("out of helper return map : "+map);
		return map;
	}


	public void editProductDetail(HttpServletRequest request,
			HttpServletResponse response) {

		String productId = request.getParameter("productId");
		String unitId = request.getParameter("unitId");
		String manufacturingCompany = request.getParameter("manufacturingCompany");
		String weight = request.getParameter("weight");
		String fk_tax_id = request.getParameter("fk_tax_id");
		String taxPercentage = request.getParameter("taxPercentage");
		String buyPrice = request.getParameter("buyPrice");
		String mrp = request.getParameter("mrp");
		String salePrice = request.getParameter("salePrice");
		String creditSalePrice = request.getParameter("creditSalePrice");
		String existedTax = request.getParameter("existedTax");
		String existedTaxPercentage = request.getParameter("existedTaxPercentage");
		String hsn = request.getParameter("hsn");

		HibernateUtility hbu=null;
		Session session = null;
		Transaction transaction = null;

		hbu = HibernateUtility.getInstance();
		session = hbu.getHibernateSession();
		transaction = session.beginTransaction();

		long productID =Long.parseLong(productId);
		ProductDetailsBean det = (ProductDetailsBean) session.get(ProductDetailsBean.class, productID);

		if(buyPrice == null){
			det.setBuyPrice(Double.parseDouble("N/A"));
		}
		else{
			det.setBuyPrice(Double.parseDouble(buyPrice));
		}

		if(creditSalePrice == null){
			det.setCreditSalePrice(Double.parseDouble("N/A"));
		}
		else{
			det.setCreditSalePrice(Double.parseDouble(creditSalePrice));
		}
		
		if(hsn == null){
			det.setHsn("N/A");
		}
		else{
			det.setHsn(hsn);
		}

		if(salePrice == null){
			det.setSalePrice(Double.parseDouble("N/A"));
		}
		else{
			det.setSalePrice(Double.parseDouble(salePrice));
		}
		if(mrp == null){
			det.setMrp(Double.parseDouble("N/A"));
		}
		else{
			det.setMrp(Double.parseDouble(mrp));
		}
		if(taxPercentage == null){

			det.setTaxPercentage(Double.parseDouble(existedTaxPercentage));
		}
		else{
			det.setTaxPercentage(Double.parseDouble(taxPercentage));
		}

		if(fk_tax_id == null){

			det.setFkTaxId(Long.parseLong(existedTax));
		}
		else{
			det.setFkTaxId(Long.parseLong(fk_tax_id));
		}

		if(weight == null){
			det.setWeight(Double.parseDouble(("N/A")));
		}
		else{
			det.setWeight(Double.parseDouble(weight));
		}


		if(manufacturingCompany == null){
			det.setManufacturingCompany("N/A");
		}
		else{
			det.setManufacturingCompany(manufacturingCompany);
		}

		if(unitId == null){
			det.setFk_unit_id(Long.parseLong(("N/A")));
		}
		else{
			det.setFk_unit_id(Long.parseLong(unitId));
		}

		session.saveOrUpdate(det);
		transaction.commit();

		System.out.println("Record updated successfully.");
	}

	public Map getAllProductByCatAndBySupForGoodsReceive(String category,
			String supplier) {

		ProductDetailsDao dao = new ProductDetailsDao();
		List list= dao.getAllProductBySCategoryAndBySuppliersForGoodsReceive(category , supplier);
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetProductDetails bean = new GetProductDetails();
			System.out.println(Arrays.toString(o));
			bean.setProduct(o[0].toString());;
			bean.setInsertDate(o[1].toString());
			System.out.println("***************"+o[0]);
			map.put(bean.getProduct(),bean);

		}
		return map;
	}

	public Map getBookedProductDetails(String productId2, String supplier, String category) {
		ProductDetailsDao dao = new ProductDetailsDao();
		List list = dao.getAdvanceBookedProductDetailsForGoodsReceive(productId2,category,supplier);
		System.out.println(list.size());
		Map  map1 =  new HashMap();

		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetProductDetailPO bean = new GetProductDetailPO();

			bean.setProductID((BigInteger)o[0]);
			bean.setProductName((String)o[1]);
			bean.setBuyPrice((BigDecimal)o[2]);
			bean.setSalePrice((BigDecimal)o[3]);
			bean.setWeight((BigDecimal)o[4]);
			bean.setMrp((BigDecimal)o[5]);
			bean.setQuantity(0l);
			map1.put(bean.getProductID(),bean);
		}
		return map1;
	}

	public Map getBookedProductDetailsWithTax(String productId3, String supplier,
			String category) {

		ProductDetailsDao dao = new ProductDetailsDao();
		List list = dao.getAdvanceBookedProductDetailsForGoodsReceiveWithTax(productId3,category,supplier);
		System.out.println(list.size());
		Map  map1 =  new HashMap();

		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetProductDetailPO bean = new GetProductDetailPO();

			bean.setProductID((BigInteger)o[0]);
			bean.setProductName((String)o[1]);
			bean.setBuyPrice((BigDecimal)o[2]);
			bean.setSalePrice((BigDecimal)o[3]);
			bean.setWeight((BigDecimal)o[4]);
			bean.setTaxPercentage((BigDecimal)o[5]);
			bean.setMrp((BigDecimal)o[6]);
			bean.setQuantity(0l);
			map1.put(bean.getProductID(),bean);
		}
		return map1;
	}

	public Map getpreviousDetsil(String productId2) {
		ProductDetailsDao dao = new ProductDetailsDao();
		List list = dao.getpreviousDetsil(productId2);
		System.out.println(list.size());
		Map  map1 =  new HashMap();

		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetProductDetailPO bean = new GetProductDetailPO();

			bean.setDate((Date)o[0]);
			bean.setProductName((String)o[1]);
			bean.setBuyPrice((BigDecimal)o[2]);
			bean.setSalePrice((BigDecimal)o[3]);
			bean.setWeight((BigDecimal)o[4]);
			bean.setMrp((BigDecimal)o[5]);
			bean.setQuantity(0l);
			map1.put(bean.getProductID(),bean);
		}
		return map1;
	}

	public Map getProductDetailsForAdvanceBooking(String proName, String company,
			String weight) {

		ProductDetailsDao dao = new ProductDetailsDao();
		List list = dao.getProductDetailsForAdvanceBook(proName,company,weight);
		System.out.println(list.size());
		Map  map1 =  new HashMap();

		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetProductDetailPO bean = new GetProductDetailPO();

			bean.setProductID((BigInteger)o[0]);
			bean.setProductName((String)o[1]);
			bean.setBuyPrice((BigDecimal)o[2]);
			bean.setSalePrice((BigDecimal)o[3]);
			bean.setWeight((BigDecimal)o[4]);
			bean.setMrp((BigDecimal)o[5]);
			bean.setManufacturer(o[6].toString());
			bean.setUnitName(o[7].toString());
			bean.setCatId(o[8].toString());
			bean.setQuantity(0l);
			map1.put(bean.getProductID(),bean);
		}
		return map1;
	}

	public Map getAllProductByCatAndBySupForGoodsReceiveNew(String category,
			String supplier) {
		int count = 1;
		ProductDetailsDao dao = new ProductDetailsDao();
		List list= dao.getAllProductBySCategoryAndBySuppliersForGoodsReceiveNew(category , supplier);
		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetProductDetails bean = new GetProductDetails();
			System.out.println(Arrays.toString(o));
			bean.setProduct(o[0].toString());;
			bean.setInsertDate(o[1].toString());
			bean.setManufacturer(o[2].toString());
			bean.setWeight(Double.parseDouble(o[3].toString()));
			bean.setUnitName(o[4].toString());
			System.out.println("***************"+o[0]);
			map.put(count,bean);
			count++;
		}
		return map;
	}

	public Map getBookedProductDetailsNew(String proName, String supplier,
			String category, String company, String weight) {

		ProductDetailsDao dao = new ProductDetailsDao();
		List list = dao.getAdvanceBookedProductDetailsForGoodsReceiveNew(proName,category,supplier,company,weight);
		System.out.println(list.size());
		Map  map1 =  new HashMap();

		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetProductDetailPO bean = new GetProductDetailPO();

			bean.setProductID((BigInteger)o[0]);
			bean.setProductName((String)o[1]);
			bean.setBuyPrice((BigDecimal)o[2]);
			bean.setSalePrice((BigDecimal)o[3]);
			bean.setWeight((BigDecimal)o[4]);
			bean.setMrp((BigDecimal)o[5]);
			bean.setManufacturer((String)o[6]);
			bean.setQuantity(Long.parseLong(o[7].toString()));
			bean.setCatId(o[8].toString());
			map1.put(bean.getProductID(),bean);
		}
		return map1;
	}

	public Map getBookedProductDetailsWithTax(String proName, String supplier,
			String category, String company, String weight) {

		ProductDetailsDao dao = new ProductDetailsDao();
		List list = dao.getAdvanceBookedProductDetailsForGoodsReceiveWithTaxNew(proName,category,supplier,company,weight);
		System.out.println(list.size());
		Map  map1 =  new HashMap();

		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetProductDetailPO bean = new GetProductDetailPO();

			bean.setProductID((BigInteger)o[0]);
			bean.setProductName((String)o[1]);
			bean.setBuyPrice((BigDecimal)o[2]);
			bean.setSalePrice((BigDecimal)o[3]);
			bean.setWeight((BigDecimal)o[4]);
			bean.setcGst(0.0);
			bean.setiGst(0.0);
			bean.setsGst(0.0);
			bean.setMrp((BigDecimal)o[6]);
			bean.setManufacturer((String)o[7]);
			bean.setHsn(o[8].toString());
			bean.setCatId(o[9].toString());
			bean.setQuantity(0l);
			map1.put(bean.getProductID(),bean);
		}
		return map1;
	}

	public Map getAllProductByCatForFertiBill(String category) {

		int count = 1;
		ProductDetailsDao dao = new ProductDetailsDao();
		List list= dao.getAllProductBySCategoryForFertilizerBill(category );

		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			
			ProductStockDetailsBean bean=new ProductStockDetailsBean();
			bean.setProduct_name(o[0].toString());
			bean.setCompanyname(o[1].toString());
			bean.setPacking(Double.parseDouble(o[2].toString()));
			bean.setQuantity(Long.parseLong(o[3].toString()));
			//bean.setExpiryDate((Date)o[4]);
			
			
			
			map.put(count,bean);
			count++;
		}
		return map;
	}

	public Map getAllProductByCatForSeedAndPestiBill(String category1) {
		int count = 1;
		ProductDetailsDao dao = new ProductDetailsDao();
		List list= dao.getAllProductBySCategoryForSeedAndPesticideBill(category1);

		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetProductDetails bean = new GetProductDetails();
			System.out.println(Arrays.toString(o));
		
			
			bean.setProduct(o[0].toString());
			bean.setBatchNum(o[1].toString());
			bean.setExpDate((Date)o[2]);
			bean.setQuantity(Long.parseLong(o[3].toString()));
			//bean.setTotalQty(Double.parseDouble(o[4].toString()));
			bean.setWeight(Double.parseDouble(o[4].toString()));
			
			System.out.println("***************"+o[0]);
			map.put(count,bean);
			count++;
		}
		return map;
	}
	
	public Map getAllProductByCatForPestiBill(String category1) {
		int count = 1;
		ProductDetailsDao dao = new ProductDetailsDao();
		List list= dao.getAllProductBySCategoryForPesticideBill(category1);

		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetProductDetails bean = new GetProductDetails();
			System.out.println(Arrays.toString(o));
		
			
			bean.setProduct(o[0].toString());
			bean.setBatchNum(o[1].toString());
			bean.setExpDate((Date)o[2]);
			bean.setQuantity(Long.parseLong(o[3].toString()));
			//bean.setTotalQty(Double.parseDouble(o[4].toString()));
			bean.setWeight(Double.parseDouble(o[4].toString()));
			bean.setUnitName(o[5].toString());
			
			System.out.println("***************"+o[0]);
			map.put(count,bean);
			count++;
		}
		return map;
	}
	
	public Map getAllPestiBillProduct(String category1) {
		int count = 1;
		ProductDetailsDao dao = new ProductDetailsDao();
		List list= dao.getAllPesticideBillProduct(category1);

		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetProductDetails bean = new GetProductDetails();
			System.out.println(Arrays.toString(o));
		
			
			bean.setProduct(o[0].toString());
			bean.setBatchNum(o[1].toString());
			bean.setExpDate((Date)o[2]);
			bean.setQuantity(Long.parseLong(o[3].toString()));
			//bean.setTotalQty(Double.parseDouble(o[4].toString()));
			bean.setWeight(Double.parseDouble(o[4].toString()));
			bean.setUnitName(o[5].toString());
			
			System.out.println("***************"+o[0]);
			map.put(count,bean);
			count++;
		}
		return map;
	}
	
	

	public Map getAllBatchNumAndStockForSeedBilling(String proName, String company,
			String weight) {

		int count = 1;
		ProductDetailsDao dao = new ProductDetailsDao();
		List list= dao.getAllBatchAndStockForSeedAndPesticideBill(proName,company,weight);

		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			GetProductDetails bean = new GetProductDetails();
			System.out.println(Arrays.toString(o));
			bean.setBatchNum(o[0].toString());
			bean.setStock(Double.parseDouble(o[1].toString()));
			System.out.println("***************"+o[0]);
			map.put(count,bean);
			count++;
		}
		return map;
	}

	public Map getAllProductByCatForStockReport() {
		int count = 1;
		ProductDetailsDao dao = new ProductDetailsDao();
		List list= dao.getAllProductBySCategoryForStockReport();

		Map  map =  new HashMap();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = (Object[])list.get(i);
			
			GoodsReceiveBean bean=new GoodsReceiveBean();
			bean.setProductName(o[0].toString());
			bean.setBatchNo(o[1].toString());
/*			GetProductDetails bean = new GetProductDetails();
			System.out.println(Arrays.toString(o));
			bean.setProduct(o[0].toString());;
			bean.setManufacturer(o[1].toString());
			bean.setWeight(Double.parseDouble(o[2].toString()));
			bean.setUnitName(o[3].toString());
			System.out.println("***************"+o[0]);
*/			map.put(count,bean);
			count++;
		}
		return map;
	}
}
