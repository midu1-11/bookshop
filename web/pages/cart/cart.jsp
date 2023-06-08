<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%--静态包含base标签，css样式和jQuery--%>
    <%@ include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            $("a.deleteItem").click(function () {
                return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() + "】吗")
            })
            $("#clearCart").click(function () {
                return confirm("你确定要清空购物车吗");
            })
            // 给输入框绑定失去焦点事件
            $(".updateCount").change(function () {
                var name = $(this).parent().parent().find("td:first").text();
                var count = $(this).val();
                var id = $(this).attr("bookId");
                if (confirm("你是否确认将" + name + "的数量修改为" + count + "吗")) {
                    location.href = "http://localhost:8080/bookshop/cartServlet?action=updateCount&id=" + id + "&count=" + count;
                } else {
                    this.value = this.defaultValue;
                }
            })
        })
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>
    <%--静态包含，登录之后的菜单--%>
    <%@ include file="/pages/common/login_success_menu.jsp" %>
</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>
        <c:if test="${not empty sessionScope.cart.items}">
            <c:forEach items="${sessionScope.cart.items.values()}" var="cartItem">
                <tr>
                    <td>${cartItem.name}</td>
                    <td>
                        <input class="updateCount" style="width: 80px;" type="text"
                               bookId="${cartItem.id}" value="${cartItem.count}">
                    </td>
                    <td>${cartItem.price}</td>
                    <td>${cartItem.totalPrice}</td>
                    <td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${cartItem.id}">删除</a></td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty sessionScope.cart.items}">
            <td colspan="5">当前购物车为空！</td>
        </c:if>
    </table>

    <c:if test="${not empty sessionScope.cart.items}">
        <div class="cart_info">
            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span id="clearCart" class="cart_span"><a href="cartServlet?action=clear">清空购物车</a></span>
            <span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
        </div>
    </c:if>

</div>

<%--静态包含页脚内容--%>
<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>