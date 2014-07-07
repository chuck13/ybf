$().ready( function() {

	var $allCheck = $(".list input.allCheck");// 全选复选框
	var $idsCheck = $(".list input[name='ids']");// ID复选框
	var $deleteButton =$(".deleteButton");
	var $editButton =$(".editButton");
	var $listForm = $(".list form");// 列表表单
	var $searchButton =  $("#searchButton");// 查询按钮
	var $pageNumber = $("#pageNumber");// 当前页码
	var $pageSize = $("#pageSize");// 每页显示数
	var $sort = $(".list .sort");// 排序
	var $orderBy = $("#orderBy");// 排序方式
	var $order = $("#order");// 排序字段
	
	
	// 全选
	$allCheck.live('click', function() {
		var $idsCheck = $(".list input[name='ids']");

		if ($(this).attr("checked") == "checked") {
			$idsCheck.attr("checked", true);
			$deleteButton.attr("disabled", false);
			$(".batchButton").attr("disabled", false);
		} else {
			$idsCheck.attr("checked", false);
			$deleteButton.attr("disabled", true);
			$(".batchButton").attr("disabled", true);
		}
	});
	
	// 无复选框被选中时,删除按钮不可用
	$idsCheck.click( function() {
		var $idsChecked = $(".list input[name='ids']:checked");
		if ($idsChecked.size() > 0) {
			$deleteButton.attr("disabled", false);
			$(".batchButton").attr("disabled", false);
		} else {
			$deleteButton.attr("disabled", true);
			$(".batchButton").attr("disabled", true);
		}
		
	});
	
	// 删除url
	$deleteButton.click( function() {
		var url = $(this).attr("url");
		if (confirm("您确定要删除吗？") == true) {
			location.href=url;
		}
	});

	
	// 编辑url
	$editButton.click( function() {
			location.href=$(this).attr("url");
	});
	// 查找
	$searchButton.click( function() {
		$pageNumber.val("1");
		$listForm.submit();
	});

	// 每页显示数
	$pageSize.change( function() {
		$pageNumber.val("1");
		$listForm.submit();
	});

	// 排序
	$sort.click( function() {
		var $currentOrderBy = $(this).attr("name");
		if ($orderBy.val() == $currentOrderBy) {
			if ($order.val() == "") {
				$order.val("asc");
			} else if ($order.val() == "desc") {
				$order.val("asc");
			} else if ($order.val() == "asc") {
				$order.val("desc");
			}
		} else {
			$orderBy.val($currentOrderBy);
			$order.val("asc");
		}
		$pageNumber.val("1");
		$listForm.submit();
	});

	// 排序图标效果
	sortStyle();
	function sortStyle() {
		var orderByValue = $orderBy.val();
		var orderValue = $order.val();
		if (orderByValue != "" && orderValue != "") {
			$(".sort[name='" + orderByValue + "']").after('<span class="' + orderValue + 'Sort">&nbsp;</span>');
		}
	}
	
	// 页码跳转
	$.gotoPage = function(id) {			    
			$pageNumber.val(id);
			$listForm.submit();	
	};
	
});