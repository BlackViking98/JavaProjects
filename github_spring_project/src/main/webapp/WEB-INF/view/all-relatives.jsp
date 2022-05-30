<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<style>
table, th, td {
  border:1px solid black;
  border-collapse: collapse;
}
th, td {
  border-color: #708090;
  background-color: #DCDCDC;
}
th, td {
  padding-top: 5px;
  padding-bottom: 5px;
  padding-left: 10px;
  padding-right: 10px;
}
th {
  text-align: left;
}
</style>

<body>
<nav>
	    <input type="button" value="HOME"
        style="color: #fff; background-color: #29c5e6; border: none; height: 32px; font-family: 'Oswald', sans-serif;"
        onclick="window.location.href='${bioButton}'"/>

		<input type="button" value="FAST PARSE"
        style="color: #fff; background-color: #29c5e6; border: none; height: 32px; font-family: 'Oswald', sans-serif;"
        onclick="window.location.href='/github_spring_project/goParseRelative'"/>
</nav>

<h2> Relatives table v.1.0 </h2>

<br>
<table>
<caption></caption>
    <tr>
        <th>Фото</th>
        <th>Инфо</th>
        <th>Операции</th>
    </tr>
    <c:forEach var="rel" items="${allRelatives}">

        <c:url var="updateButton" value = "/updateRelative">
            <c:param name="relativeId" value="${rel.id}"/>
        </c:url>

        <c:url var="deleteButton" value = "/deleteRelative">
            <c:param name="relativeId" value="${rel.id}"/>
        </c:url>

        <c:url var="bioButton" value = "/bioRelative">
            <c:param name="relativeId" value="${rel.id}"/>
        </c:url>

        <tr>
            <td> <figure>
                    <img src="${rel.imageLink}" height="200" alt="Photo"> <figcaption></figcaption>
                </figure> </td>

            <td>
                <b>${rel.surname} ${rel.name} ${rel.middleName}</b>
                <br>Дата рождения: ${rel.dateOfBirth}
                <br>Город: ${rel.city}
                <br><br>
                <input type="button" value="См.Биографию"
                style="color: #fff; background-color: #29c5e6; border: none; height: 32px; font-family: 'Oswald', sans-serif;"
                onclick="window.location.href='${bioButton}'"/>

            </td>
            <td>
            <br>
                ID: ${rel.id}
            <br><br>
                <input type="button" value="Редактировать"
                style="color: #000000; background-color: #FFD700; border: none; height: 25px; font-family: 'Oswald', sans-serif;"
                onclick="window.location.href='${updateButton}'"/>

                <input type="button" value="Удалить"
                style="color: #fff; background-color: #FA8072; border: none; height: 25px; font-family: 'Oswald', sans-serif;"
                onclick="window.location.href='${deleteButton}'"/>
            </td>
       </tr>
    </c:forEach>

</table>
<br>
<br>
<input type="button" value ="Добавить родственника" style="color: #fff; background-color: #29c5e6; border: none; height: 32px; font-family: 'Oswald', sans-serif;" onclick="window.location.href='addNewRelative'"/>
<br><br>

<a href="http://skylandinn.tilda.ws/"><img width = "20" src='<c:url value="/files/logoskylandinn.png"></c:url>' /></a>
 © skylandinn


</body>
</html>
