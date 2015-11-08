<%@page import="java.util.ArrayList"%>
<%@page import="javaee.diststudy.dao.TeacherManager"%>
<jsp:include page="header.jsp"/>

<%@page import="java.util.List"%>
<%@page import="javaee.diststudy.entity.Teacher"%>
<%@page import="javaee.diststudy.dao.TeacherDAO"%>
<div class='' style='margin: 50px; color: #1D6D03; text-align:center; height: 60px; border: 1px solid #86E258;'>
        <h1>Full list of teachers</h1>
</div>
    <%
 
        TeacherManager tdao = new TeacherManager();
        List<Teacher> searchByParams = tdao.getAll();
     
        %>
        <center>
            <table class='table-striped' style='font-size:14px; width: 70%'>
                <% 
                if( searchByParams == null ) { %>
                    <tr>
                        <td>
                            No results...
                        </td>
                    </tr>
                <% } else {
                for( Teacher t: searchByParams ) { %>
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
                    </tr>
                <% } 
                }%>
            </table>
        </center>

<jsp:include page="footer.jsp"/>