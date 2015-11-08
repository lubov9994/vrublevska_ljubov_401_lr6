<%@page import="java.util.ArrayList"%>
<%@page import="javaee.diststudy.dao.TeacherManager"%>
<jsp:include page="header.jsp"/>

<%@page import="java.util.List"%>
<%@page import="javaee.diststudy.entity.Teacher"%>
<%@page import="javaee.diststudy.dao.TeacherDAO"%>

 <h1>  </h1>
    <form role='form' class='form-inline' action='SearchServlet' method='GET'> 
        <div class='form-group'>firstName: <input class='form-control' style='margin: 0 20px;' type='text' name='firstName'></div>
       
        <div class='form-group'>lastName: <input class='form-control' style='margin: 0 20px;' type='text' name='lastName'></div>
        <div class='form-group'>kafedra: <input class='form-control' style='margin: 0 20px;' type='number' name='kafedra'></div>
        <input type='hidden' name='search' value='true'>
        <input class='btn btn-def' type='submit'  style='margin: 0 20px;'  value="Find" />
    </form>
    <form role='form' class='form-inline' action='SearchServlet' method='GET'> 
       
        <input type='hidden' name='searchall' value='true'>
        <input class='btn btn-def' type='submit'  style='margin: 0 20px;'  value="Find All" />
    </form>
 
    <div style='width: 100%; height: 50px;'></div>

    <div class='' style='margin: 50px; color: #1D6D03; text-align:center; height: 60px; border: 1px solid #86E258;'>
        <h1>Search results</h1>
    </div>
            
    <%
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String kafedra = request.getParameter("kafedra"); 

        TeacherManager tdao = new TeacherManager();

        List<Teacher> searchByParams = null;

            if(kafedra.compareTo("") != 0){
                //searchByParams = tdao.searchByParams( firstName, lastName, -1 );
                 if( searchByParams == null ) {
                     searchByParams = new ArrayList<Teacher>();
                           
                 }
                searchByParams.addAll( tdao.getByKafedra(Integer.parseInt(kafedra)) );

            } 
            if(firstName.compareTo("") != 0){
                if( searchByParams == null ) {
                     searchByParams = new ArrayList<Teacher>();
                           
                }
               searchByParams.addAll( tdao.getByName(firstName) );
            }
            if(lastName.compareTo("") != 0){
                if( searchByParams == null ) {
                     searchByParams = new ArrayList<Teacher>();
                           
                }
               searchByParams.addAll( tdao.getBySurname(lastName) );
            } 

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