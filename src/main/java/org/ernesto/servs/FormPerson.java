package org.ernesto.servs;

import org.ernesto.builders.PersonBuilder;
import org.ernesto.controllers.PersonController;
import org.ernesto.models.Person;
import org.ernesto.utils.UtilFaces;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class FormPerson extends HttpServlet {
    PersonController personController = PersonController.getInstance();
    public static final String SENT = "sent";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Person> personList = personController.getAllPersons();
        int personId;
        if(!request.getParameter(SENT).isEmpty()){ // check if it's edited person
            personId = Integer.parseInt(request.getParameter(SENT));
        }
        else{
            personId = (personList.size() != 0 ) ? personList.get(personList.size()-1).getId() +1 : 1 ;
        }

        Person person = new PersonBuilder()
                        .setId(personId)
                        .setName(request.getParameter("name"))
                        .setAge(Integer.parseInt(request.getParameter("age")))
                        .build();

        if(!request.getParameter(SENT).isEmpty()){ // if it's an edited person
            personController.updatePerson(person); // update it
        }
        else{
            personController.savePerson(person); // if not, create a new one
        }

        response.sendRedirect("home");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Person person = null;

        if(request.getParameter("id") != null){ // if is not null we are editing a person
            int id = Integer.parseInt(request.getParameter("id"));
            person = personController.getPerson(id);
        }

        UtilFaces.readAndPrintHtml("/index.html", context, out);

        out.println("<form action='addperson' method='POST'>");

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
        out.println("<a href='home' class='btn btn-danger'>Cancel</a>");
        out.println("</form>");

        UtilFaces.readAndPrintHtml("/footer.html", context, out);

    }
}
