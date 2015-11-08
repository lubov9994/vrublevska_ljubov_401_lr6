<%@ include file="header.jsp"%>

<%@ page import="javaee.diststudy.dao.TeacherDAO"%>
<%@ page import="javaee.diststudy.entity.Teacher"%>
<%@ page import="java.util.List"%>
   
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1>Обновдение поля</h1>
  
<%            
    javaee.diststudy.dao.TeacherDAO  tdao = new TeacherDAO();
    Teacher t = tdao.getById( Integer.parseInt(request.getParameter("id_upd")));
%>
    
    <form method="GET" onsubmit="return checkForm(this)">
        FirstName: <input class="form-control"  type="text" name="first" value="<%=t.getFirstName()%>"> <br>
        SecondName: <input class="form-control"  type="text" name="second" value="<%=t.getSecondName()%>"><br>
        LastName: <input class="form-control"  type="text" name="last" value="<%=t.getLastName()%>"><br>
        Degree: <input class="form-control" type="number" name="degree" value="<%=t.getDegree()%>"><br>
        Kafedra: <input class="form-control" type="number" name="kafedra" value="<%=t.getKafedra()%>"><br>
    <br>
        <input type="hidden" name="save" value="<%=t.getId()%>">
        <input type="submit" class="form-control btn btn-info" value="save">
    </form>
        
        
<script>

    function checkForm(form) {

        var el, 
            elName, 
            value, 
            type; 

        var errorList = [];

        var errorText = {
                    1 : "Не заполнено поле 'FirstName'",
                    2 : "Не заполнено поле 'SecondName:'",
                    3 : "Не заполнено поле 'LastName'",
                    4 : "Не заполнено поле 'Degree'",
                    5 : "Не заполнено поле 'Kafedra'"
            }

        for (var i = 0; i < form.elements.length; i++) {
            el = form.elements[i];
            elName = el.nodeName.toLowerCase();
            value = el.value;
            if (elName == "input") { 
                type = el.type.toLowerCase();
                // Разбираем все инпуты по типам и обрабатываем содержимое
                switch (type) {
                    case "text" :
                        if (el.name == "first" && value == "") errorList.push(1);
                        if (el.name == "email" && value == "") errorList.push(2);
                    break;

                    default :
                        // Сюда попадают input-ы, которые не требуют обработки
                        // type = hidden, submit, button, image
                    break;
                }
            } else {
                // Обнаружен неизвестный элемент ;)
            }
        }
        if (!errorList.length) return true;
        var errorMsg = "При заполнении формы допущены следующие ошибки:\n\n";
        for (i = 0; i < errorList.length; i++) {
            errorMsg += errorText[errorList[i]] + "\n";
        }
        alert(errorMsg);

        return false;
    }


</script>
        
<%@ include file="footer.jsp"%>