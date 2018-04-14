package org.ernesto.servs;

import org.ernesto.controllers.PersonController;
import org.ernesto.models.Person;
import util.Util;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;


public class FormPerson extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Person> personList = PersonController.getAllPersons();
        int personId;
        if(!request.getParameter("sent").isEmpty()){ // check if it's edited person
            personId = Integer.parseInt(request.getParameter("sent"));
        }
        else{
            personId = (personList.size() != 0 ) ? personList.get(personList.size()-1).getId() +1 : 1 ;
        }

        Person person = new Person.PersonBuilder() // create a person
                .id(personId)
                .name(request.getParameter("name"))
                .age(Integer.parseInt(request.getParameter("age")))
                .build();

        if(!request.getParameter("sent").isEmpty()){ // if it's an edited person
            PersonController.updatePerson(person); // update it
        }
        else{
            PersonController.savePerson(person); // if not, create a new one
        }

        response.sendRedirect("/home");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Person person = null;

        if(request.getParameter("id") != null){ // if is not null we are editing a person
            int id = Integer.parseInt(request.getParameter("id"));
            person = PersonController.getPerson(id);
        }

        Util.readAndPrintHtml("/index.html", context, out);

        out.println("<form action='/addperson' method='POST'>");

        if(person != null){ // form to display info from person to edit
            out.println("<input type='text' name='name' placeholder='Name' value='"+person.getName()+"'>");
            out.println("<input type='text' name='age' placeholder='age' value='"+person.getAge()+"'>");
            out.println("<button type='submit' name='sent' value='"+person.getId()+"' class='btn btn-success'>Update</button>");
        }
        else{ // if not just display an empty form
            out.println("<input type='text' name='name' placeholder='Name'>");
            out.println("<input type='text' name='age' placeholder='Age' >");
            out.println("<button type='submit' name='sent' class='btn btn-success'>Add</button>");
        }
        out.println("<a href='/home' class='btn btn-danger'>Cancel</a>");
        out.println("</form>");

        Util.readAndPrintHtml("/footer.html", context, out);

    }
}
