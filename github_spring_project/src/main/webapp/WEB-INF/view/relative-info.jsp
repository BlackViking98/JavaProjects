<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<body>

<h2> Редактирование информации </h2>

<br>
<br>

<form:form action="saveRelative" modelAttribute="relative">

<form:hidden path="id"/>

Фамилия <form:input path="surname"/>
<form:errors path="surname"/>
<br><br>
Имя  <form:input path="name"/>
<form:errors path="name"/>
<br><br>
Отчество <form:input path="middleName"/>

<br><br>
Город <form:input path="city"/>

<br><br>
Дата рождения <form:input path="dateOfBirth"/>
<form:errors path="dateOfBirth"/>
<br><br>
Возраст <form:input path="age"/>
<form:errors path="age"/>
<br><br>
Поколение в генеалогическом древе: <form:input path="generation"/>
<br><br>
ID отца: <form:input path="fatherID"/>
<br><br>
ID матери: <form:input path="motherID"/>
<br><br>
Ссылка на фото в открытом доступе: <form:input path="imageLink"/>
<br><br>
Биография <form:input path="bio"/>

<br><br>
<button type="submit" style="color: #fff; background-color: #3CB371; border: none; height: 32px; font-family: 'Oswald', sans-serif;">Принять</button>

<button type="reset" style="color: #fff; background-color: #FFD700; border: none; height: 32px; font-family: 'Oswald', sans-serif;">Сброс</button>
</form:form>

<br><br>
<input type="button" style="color: #fff; background-color: #29c5e6; border: none; height: 32px; font-family: 'Oswald', sans-serif;"
onclick="window.location.href='/github_spring_project'" value="Назад к таблице"/>

</body>
</html>
