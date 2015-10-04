<ul class="pagination center-block">
	<li id="pre"><a
		href="<c:url value="${urlSubfix }/${page.currentIndex-1<1?1:page.currentIndex-1 }"/>"
	/>&laquo;</a></li>
	<c:forEach items="${page.navItems }" var="item">
		<li><a
			href="<c:url value="${urlSubfix }/${item }" />"
		>${item }</a></li>
	</c:forEach>
	<li id="next"><a
		href="<c:url value="${urlSubfix }/${page.currentIndex+1>page.pageCount?page.pageCount:page.currentIndex+1 }"/>"
	>&raquo;</a></li>
</ul>
<script>
	// 初始化分页器
	var currentIndex = "${page.currentIndex}";
	var pageCount = "${page.pageCount}";
	var pages = parseInt("${fn:length(page.navItems)}");
	
	$(".pagination").css("width", ((pages + 2) * 35) + "px");
	$(function() {
		if(pages <= 1) {
			$(".pagination").hide();
			return;
		}
		$(".pagination li").each(function() {
			var text = $(this).children("a").text();
			if (text == currentIndex)
				$(this).addClass("active");
		});
		if (parseInt(currentIndex) <= 1)
			$("#pre").addClass("disabled");
		if (parseInt(currentIndex) >= parseInt(pageCount))
			$("#next").addClass("disabled");
	});
</script>