package javaee.diststudy.servlets;


import javaee.diststudy.beans.SimpleAsyncEJB;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@WebServlet("/test")
public class TestServlet extends HttpServlet{
    @EJB
    SimpleAsyncEJB ejb;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter out = resp.getWriter()) {
            Future<Integer> future = ejb.sum(10, 20);
            out.print(future.get(3, TimeUnit.SECONDS));

        } catch (InterruptedException | ExecutionException | TimeoutException ex) {
            ex.printStackTrace();
        }
    }
}
