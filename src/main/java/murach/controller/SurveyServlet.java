package murach.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import murach.business.user;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class SurveyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get parameters from the request
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String heardFrom = request.getParameter("heardFrom");
        String wantsUpdates = request.getParameter("wantsUpdates");
        String contactVia = request.getParameter("contactVia");
        String birthStr = request.getParameter("birth");
        String dobString = request.getParameter("dob");
        // Process parameters
        if (heardFrom == null) {
            heardFrom = "NA";
        }
        if (wantsUpdates == null) {
            wantsUpdates = "No";
        }

        // Convert birthStr to Date
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dob = null;
        if (dobString != "") {
            dob = LocalDate.parse(dobString, formatter);
        }


        // Store data in User object
        user user = new user();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setDob(dob);
        user.setHeardFrom(heardFrom);
        user.setWantsUpdates(wantsUpdates);
        user.setContactVia(contactVia);

        // Store User object in request
        request.setAttribute("user", user);

        // Forward request to JSP
        String url = "/survey.jsp";
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
