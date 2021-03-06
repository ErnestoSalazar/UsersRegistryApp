package org.ernesto.servs;

import org.ernesto.controllers.LoginController;
import org.ernesto.controllers.PersonController;
import org.ernesto.utils.UtilFaces;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Login extends HttpServlet {
    LoginController loginController = LoginController.getInstance();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(loginController.loginValidator(request.getParameter("nickname"), request.getParameter("password-e"))){
            response.sendRedirect("home");
        }
        else{
            response.sendRedirect("login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletContext context = getServletContext();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        UtilFaces.readAndPrintHtml("/index.html", context, out);
        UtilFaces.readAndPrintHtml("partials/login.html", context, out);
        UtilFaces.readAndPrintHtml("/footer.html", context, out);
    }

}
