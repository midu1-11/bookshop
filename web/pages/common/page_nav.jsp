<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2023/6/4
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="page_nav">
    <c:if test="${requestScope.page.pageNo>1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">
                ${requestScope.page.pageNo-1}
        </a>
    </c:if>
    【${requestScope.page.pageNo}】
    <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">
                ${requestScope.page.pageNo+1}
        </a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>
    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">
    <script type="text/javascript">
        $(function () {
            $("#searchPageBtn").click(function () {
                var pageNo = $("#pn_input").val();
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
            })
        })
    </script>
</div>