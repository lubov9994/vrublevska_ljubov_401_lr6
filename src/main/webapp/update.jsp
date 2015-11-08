<jsp:include page="header.jsp"/>

<%@ page import="javaee.diststudy.dao.TeacherDAO"%>
<%@ page import="javaee.diststudy.entity.Teacher"%>
<%@ page import="java.util.List"%>
   
        <h1>Table updating</h1>
       
        <table class='table-striped' style="text-align: center; width: 75%;">
    <%
            
    javaee.diststudy.dao.TeacherDAO  tdao = new TeacherDAO();
    java.util.List<javaee.diststudy.entity.Teacher> list = tdao.getAll();
        for(Teacher t : list ) {
    %>
         <tr> 
         
                <td>
                    <%=t.getFirstName()%>
                </td>
                <td>
                    <%=t.getSecondName()%>
                </td>
                <td>
                    <%=t.getLastName()%>
                </td>
                <td>
                    <%=t.getDegree()%>
                </td>
                <td>
                    <%=t.getKafedra()%>
                </td>
                <td>
                    <form action="UpdateServlet" method="GET" style="margin: 5px 0;">
                        <input type="submit" class="btn btn-info" value="update">
                        <input type="hidden" name="id_upd" value="<%=t.getId()%>">
                    </form>

                </td>
                <td>
                    <form action="UpdateServlet" method="GET">
                        <input class="btn btn-danger" type="submit" value="delete">
                        <input type="hidden" name="id_del" value="<%=t.getId()%>">
                    </form>
                </td>
                    
         </tr>
        <% } %>
        </table>
    
<jsp:include page="footer.jsp"/>