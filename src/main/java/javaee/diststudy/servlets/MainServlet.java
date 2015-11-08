/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaee.diststudy.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javaee.diststudy.beans.LogBean;
import javaee.diststudy.dao.EntityDAO;
import javaee.diststudy.dao.TeacherDAO;
import javaee.diststudy.dao.TeacherManager;
import javaee.diststudy.entity.Teacher;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Администратор
 */
@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

    Teacher teacher;
    EntityDAO<Teacher> tdao;
    @EJB
    LogBean log;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MainServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MainServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String adding = request.getParameter("add");
           
            
            try (PrintWriter out = response.getWriter()) {
                
                if(adding.compareTo("true") == 0){
                    response.setContentType("text/html;charset=UTF-8");
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet MainServlet</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<a href='http://localhost:8080/DistStydyServletDAO/'>Home page</a>");
                    out.println("<h1> </h1>");
                   // tdao = new TeacherDAO();
                    tdao = new TeacherManager();
                    tdao.create(generateTeacher(request));
                    out.println("<div class='' style='margin: 50px; color: #1D6D03; text-align:center; width:100%; height=60px;border: 1px solid #86E258;'><h1>New field successfull added!</h1></div>");
                    out.println("</body>");
                    out.println("</html>");
                    
                } else {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/SearchServlet");
                    dispatcher.forward(request,response);
                    
                }
                
                
            }
            
        }   catch (SQLException ex) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private Teacher generateTeacher( HttpServletRequest request ){
        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName"); 
        String lastName = request.getParameter("lastName");
        String birthday = request.getParameter("birthday"); 
        String degree = request.getParameter("degree");
        String kafedra = request.getParameter("kafedra"); 
        
        this.teacher = new Teacher();
        log.logMethod("setBirthday");
        this.teacher.setBirthday(birthday);
        log.logMethod("setDegree");
        this.teacher.setDegree(Integer.parseInt(degree));
        log.logMethod("setFirstName");
        this.teacher.setFirstName(firstName);
        log.logMethod("setLastName");
        this.teacher.setLastName(lastName);
        log.logMethod("setSecondName");
        this.teacher.setSecondName(secondName);
        this.teacher.setKafedra(Integer.parseInt(kafedra));

        log.toConsole();

        return this.teacher;
    }
}
