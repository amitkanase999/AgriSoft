function getAllCurrentStock()
{
	var params= {};

	params["methodName"] = "getAllCurrentStockReport";

	$.post('/AgriSoft/jsp/utility/controller.jsp',params,function(data)
			{
		var jsonData = $.parseJSON(data);
		var catmap = jsonData.list;
		$.each(catmap,function(i,v)
				{

			$("#list4").jqGrid({
				datatype: "local",
				height: 350,
				colNames:['ItemName', 'Quantity'],
				colModel:[

				          {name:'itemName',
				        	  width:200,
				        	  editable: false
				          },

				          {name:'totalQuantityForCurrentStock',
				        	  width:200,
				        	  editable: false

				          },
				          ],
				          height:300,

				          viewrecords: true,
				          'cellEdit':true,
			});
			$("#list4").jqGrid('filterToolbar', {
				autosearch: true,
				stringResult: true,
				searchOnEnter: false,
				defaultSearch: "cn",
			});
				});
		var mydata = catmap;
		for(var i=0;i<mydata.length;i++){$("#list4").addRowData(i,mydata[i]);
		}
			}).error(function(jqXHR, textStatus, errorThrown){
				if(textStatus==="timeout") {
					$(loaderObj).hide();
					$(loaderObj).find('#errorDiv').show();
				}
			});
}