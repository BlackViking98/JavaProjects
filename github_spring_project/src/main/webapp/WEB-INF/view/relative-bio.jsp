<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<body>

<h2> Информация о родственнике </h2>


<form:form action="saveRelative" modelAttribute="relative">
<form:hidden path="id"/>
<figure>
    <img width = "300" src='<c:url value="${relative.imageLink}"></c:url>' align=”middle” alt="Фото родственника (если отсутствует-внести через редактирование)"/>
</figure>
<h3> ${ relative.surname } ${ relative.name } ${ relative.middleName }</h3>
Дата рождения: ${ relative.dateOfBirth }
<br>
Город: ${ relative.city }
<br><br>

<c:choose>
    <c:when test="${relative.fatherID == 0}">
      <input type="button" value="Отец" disabled="disabled"/>
    </c:when>
    <c:otherwise>
      <input type="button" style="color: #fff; background-color: #29c5e6; border: none; height: 32px; font-family: 'Oswald', sans-serif;"
      value="Отец" onclick="window.location.href='bioRelative?relativeId=${relative.fatherID}'" />
    </c:otherwise>
 </c:choose>

 <c:choose>
     <c:when test="${relative.motherID == 0}">
       <input type="button" value="Мать" disabled="disabled"/>
     </c:when>
     <c:otherwise>
       <input type="button" style="color: #fff; background-color: #29c5e6; border: none; height: 32px; font-family: 'Oswald', sans-serif;"
       value="Мать" onclick="window.location.href='bioRelative?relativeId=${relative.motherID}'" />
     </c:otherwise>
  </c:choose>
  <br><br>

Биография:
<br>
<blockquote>
${ relative.bio }
</blockquote>
</form:form>

<br><br>
<input type="button" style="color: #fff; background-color: #29c5e6; border: none; height: 32px; font-family: 'Oswald', sans-serif;"
onclick="history.back();" value="На предыдущую"/>
<br><br>

<input type="button" style="color: #fff; background-color: #29c5e6; border: none; height: 32px; font-family: 'Oswald', sans-serif;"
onclick="window.location.href='/github_spring_project'" value="Назад к таблице"/>
<br>

</body>
</html>
