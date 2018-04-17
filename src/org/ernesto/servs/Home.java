package org.ernesto.servs;

import org.ernesto.controllers.PersonController;
import org.ernesto.models.Person;
import utils.UtilFaces;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;


public class Home extends HttpServlet {
    PersonController personController = PersonController.getInstance();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int personId = Integer.parseInt(request.getParameter("sent"));
        personController.deletePerson(personId);
        response.sendRedirect("/home");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Person> personList = personController.getAllPersons();
        ServletContext context = getServletContext();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        UtilFaces.readAndPrintHtml("/index.html", context, out);
        // print table with the persons data
        out.println("<table class ='table table-striped'");
        for (Person personItem : personList){
            out.println("<tr>");
            out.println("<td> <a href='/addperson?id="+personItem.getId()+"'>"+ personItem.getName() +" </td>");
            out.println("<td> "+ personItem.getAge() +" </td>");
            out.println("<td><form action='/home' method='POST'>");
            out.println("<button type='submit' name='sent' class='btn btn-danger' value='"+personItem.getId()+"'>Eliminar</button>");
            out.println("</form></td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("<a href='/addperson' class='btn btn-primary pull-right'>Add person</a>");

        UtilFaces.readAndPrintHtml("/footer.html", context, out);
    }



}
