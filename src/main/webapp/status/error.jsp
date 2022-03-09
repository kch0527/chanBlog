<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Message - chan' blog</title>
    <%@include file="../main/header.jsp"%>
</head>
<body>
<!-- Navigation -->
<%@include file="../main/nav.jsp"%>
<!-- Page Header-->
<header class="masthead" style="background-image: url('../assets/img/elsa.jpeg')">
    <div class="container position-relative px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10 col-lg-8 col-xl-7">
                <div class="page-heading">
                    <h1>Error! </h1>
                    <span class="subheading"> ${msg}</span>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- Main Content-->
<main class="mb-4">
    <div class="container px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10 col-lg-8 col-xl-7">

            </div>
        </div>
    </div>
</main>
<!-- Footer-->
<%@include file="../main/footer.jsp"%>
</body>
</html>
