package com.example.web;

import com.example.repository.EmployeeRepository;
import com.example.repository.JdbcEmployeeRepository;
import com.example.entity.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    EmployeeRepository jdbcEmpRepo = new JdbcEmployeeRepository();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //read input parameters from http-request
        String uName = req.getParameter("username");
        String pw = req.getParameter("password");

        boolean b = jdbcEmpRepo.authenticate(uName, pw); System.out.println(b);
        if (!b) {
            resp.sendRedirect("index.html");
        } else {
            Optional<Employee> emp = jdbcEmpRepo.findByEmpEmail(uName);
            HttpSession session = req.getSession();

            session.setAttribute("current-user", uName);

            if (emp.get().getRole().toString().equals("EMPLOYEE")) {
                req.getRequestDispatcher("employeeHome.html").forward(req, resp);
            } else if (emp.get().getRole().toString().equals("MANAGER")) {
                req.getRequestDispatcher("managerHome.html").forward(req, resp);
            } else {
                resp.sendRedirect("ERS/index.html");
            }



        }



    }



}
