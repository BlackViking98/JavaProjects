<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<body>

<h2> Быстрое заполнение </h2>

<br>

<form:form action="parseRelative" modelAttribute="info">
Введите данные о всех родственниках сюда:
<br><form:input path="bio"/>


<br><br>
<button type="submit" style="color: #fff; background-color: #3CB371; border: none; height: 32px; font-family: 'Oswald', sans-serif;">Принять</button>

<button type="reset" style="color: #fff; background-color: #FFD700; border: none; height: 32px; font-family: 'Oswald', sans-serif;">Сброс</button>
</form:form>

<br><br>
<input type="button" style="color: #fff; background-color: #29c5e6; border: none; height: 32px; font-family: 'Oswald', sans-serif;"
onclick="window.location.href='/github_spring_project'" value="Назад к таблице"/>
<br><br>
<br><br>
<h3>Инструкция:</h3>
Внимание! Для ввода множества людей следует записывать данные в следующем виде:
<br><i>ФамилияПервого%%ИмяПервого%%ОтчествоПервого%%Город%%ДД.ММ.ГГГГ%%БиографияПервого//ФамилияВторого%%ИмяВторого%%....</i>
<br><br>Например:<br>
<i>Пеньков%%Матвей%%Михайлович%%Санкт-Петербург%%29.10.1998%%Биография с любым наполнением... //Шубин%%Сергей%%.... и т.д.</i>

</body>
</html>
