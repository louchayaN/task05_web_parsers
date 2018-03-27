<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<body>

	<div align="center">
		<form action="show_books" method="get">
			<input type="hidden" name="currentPage" value="1" /> 
			<input type="hidden" name="command" value="parseXml" /> 
			<input type="radio" name="parserType" value="SAX" checked="checked" />SAX
			<input type="radio" name="parserType" value="StAX" />StAX 
			<input type="radio" name="parserType" value="DOM" />DOM
			<p>
				<input type="submit" value="Parse xml file" />
			</p>
		</form>
	</div>
	
</body>

</html>