/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author hoang
 */
@WebServlet(name="RegisterController", urlPatterns={"/register"})
public class RegisterController extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String day = request.getParameter("day");
        String month = request.getParameter("month");
        String year = request.getParameter("year");
        String dob = year + "-" + month + "-" + day;
        String gender = request.getParameter("gender");
        String profilePicture = request.getParameter("profilePicture");
        
        User u = new User(firstName, lastName, email, password, dob, gender, profilePicture);
        UserDAO ud = new UserDAO();
        if (ud.register(u)) {
            //return to login page
            request.setAttribute("msg", "Register successful. You will be direct to the Login page.");
            request.setAttribute("confirm", "1");
            
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            //Return message
            request.removeAttribute("confirm");
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("email", email);
            request.setAttribute("day", day);
            request.setAttribute("month", month);
            request.setAttribute("year", year);
            request.setAttribute("gender", gender);
            request.setAttribute("profilePicture", profilePicture);
            
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
