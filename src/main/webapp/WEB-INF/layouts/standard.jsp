<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
        <tiles:insertAttribute name="title" ignore="true"/>
    </title>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="<c:url value="/resources/styles/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/styles/bootstrap-theme.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/styles/datepicker.css" />" rel="stylesheet" type="text/css" media="screen"/>
    <link href="<c:url value="/resources/styles/styles.css" />" rel="stylesheet" type="text/css" media="screen"/>

    <script type="text/javascript" src="<c:url value="/resources/javascript/jquery-1.11.1.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/javascript/bootstrap.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/javascript/bootstrap-datepicker.js" />"></script>
</head>
<body>

<div id="header">
    <tiles:insertAttribute name="header"/>
</div>
<div id="content">
    <div class="container">
        <tiles:insertAttribute name="body"/>
    </div>
</div>
<div id="footer">
    <tiles:insertAttribute name="footer"/>
</div>

</body>
</html>
