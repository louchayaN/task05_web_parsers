<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="paginator" uri="http://corporation.com/custom-tag/paginator"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style>
table {
	border-spacing: 0px;
}

th, td {
	border: 1px solid #333;
	padding: 5px;
}
</style>
</head>

<body>
	<table>
		<tr>
			<th width="3%">№</th>
			<th width="12%">Author</th>
			<th width="13%">Title</th>
			<th width="12%">Genre</th>
			<th width="5%">Price</th>
			<th width="10%">Publish data</th>
			<th width="45%">Description</th>
		</tr>

		<c:forEach var="book" items="${booksView.currentViewBooks}">
			<tr>
				<td>${book.id}</td>
				<td>${book.author}</td>
				<td>${book.title}</td>
				<td>${book.genre}</td>
				<td>${book.price}</td>
				<fmt:formatDate value="${book.publishDate}" var="book_formatted_date" pattern="dd-MM-yyyy" />
				<td>${book_formatted_date}</td>
				<td>${book.description}</td>
			</tr>
		</c:forEach>
	</table>

	<div align="center">
		<paginator:display currentPage="${booksView.currentPage}" totalPageCount="${booksView.totalPageCount}" 
			viewPageCount="4" urlPattern="show_books?command=parseXml" />
	</div>

	<a href="index.jsp">To home page</a>

</body>
</html>