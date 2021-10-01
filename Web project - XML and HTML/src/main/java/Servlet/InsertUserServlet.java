package Servlet;

import user1.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static user1.User.*;


public class InsertUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        try {
            String email = req.getParameter("email");
            deledteUser(email);
        } catch (Exception ex) {
            System.out.println("error");
        }

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<style>");
        out.println("input[type=submit] {\n" +
                "  width: 100%;\n" +
                "  background-color: #4CAF50;\n" +
                "  color: white;\n" +
                "  padding: 14px 20px;\n" +
                "  margin: 8px 0;\n" +
                "  border: none;\n" +
                "  border-radius: 4px;\n" +
                "  cursor: pointer;\n" +
                "}");
        out.println("input[type=submit]:hover {\n" +
                "  background-color: #4CAF50;\n" +
                "}\n");
        out.println("input[type=text], select {\n" +
                "  width: 100%;\n" +
                "  padding: 12px 20px;\n" +
                "  margin: 8px 0;\n" +
                "  display: inline-block;\n" +
                "  border: 1px solid #ccc;\n" +
                "  border-radius: 4px;\n" +
                "  box-sizing: border-box;\n" +
                "}");


        out.println("input[type=email], select {\n" +
                "  width: 100%;\n" +
                "  padding: 12px 20px;\n" +
                "  margin: 8px 0;\n" +
                "  display: inline-block;\n" +
                "  border: 1px solid #ccc;\n" +
                "  border-radius: 4px;\n" +
                "  box-sizing: border-box;\n" +
                "}");


        out.println("input[type=password], select {\n" +
                "  width: 100%;\n" +
                "  padding: 12px 20px;\n" +
                "  margin: 8px 0;\n" +
                "  display: inline-block;\n" +
                "  border: 1px solid #ccc;\n" +
                "  border-radius: 4px;\n" +
                "  box-sizing: border-box;\n" +
                "}");


        out.println("\n" +
                "div {\n" +
                "  border-radius: 5px;\n" +
                "  background-color: #f2f2f2;\n" +
                "  padding: 20px;\n" +
                "}\n");

        out.println("#users {\n" +
                "  font-family: Arial, Helvetica, sans-serif;\n" +
                "  border-collapse: collapse;\n" +
                "  width: 100%;\n" +
                "}");
        out.println("#users td, #users th {\n" +
                "  border: 1px solid #ddd;\n" +
                "  padding: 8px;\n" +
                "}");

        out.println("#users tr:nth-child(even){background-color: #f2f2f2;}");

        out.println("#users tr:hover {background-color: #ddd;}");
        out.println("#users th {\n" +
                "  padding-top: 12px;\n" +
                "  padding-bottom: 12px;\n" +
                "  text-align: left;\n" +
                "  background-color: #4CAF50;\n" +
                "  color: white;\n" +
                "}");
        out.println(".button {\n" +
                "  background-color: #4CAF50; /* Green */\n" +
                "  border: none;\n" +
                "  color: white;\n" +
                "   padding: 10px 25px;\n" +
                "  text-align: center;\n" +
                "  text-decoration: none;\n" +
                "  display: inline-block;\n" +
                "  font-size: 16px;\n" +
                "}");

        out.println("</style>");
        out.println("<title>Servlet MyServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println(" <form action = \"InsertUserServlet\" method=\"post\">");

        out.println("<center><h2>Create Users</h2></center>");
        out.println("<div>");
        out.println("<td>First name:</td>\n"
                + "<td><input type=\"text\" name=\"firstName\"  required placeholder=\"Your first name..\"><br></td>");
        out.println("</tr>");

        out.println(" <tr>\n"
                + "                <td>Last name:</td>\n"
                + "                <td><input type=\"text\" name=\"lastName\" required placeholder=\"Your last name..\"><br></td>\n"
                + "            </tr>\n"
                + "            <tr>\n"
                + "                <td>Address:</td>\n"
                + "                <td><input type=\"text\" name=\"address\"  required placeholder=\"Your address..\"><br></td>\n"
                + "            </tr>\n"
                + "            <tr>\n"
                + "                <td>City:</td>\n"
                + "                <td><input type=\"text\" name=\"city\" required placeholder=\"Your city..\"><br></td>\n"
                + "            </tr>\n"
                + "            <tr>\n"
                + "                <td>Country:</td>\n"
                + "                <td><select name=\"country\"  required>\n"
                + "                        <option value=\"United States\">United States</option>\n"
                + "                        <option value=\"Canada\">Canada</option>\n"
                + "                        <option value=\"United Kingdom\">United Kingdom</option>\n"
                + "                        <option value=\"France\">France</option>\n"
                + "                        <option value=\"Germany\">Germany</option>\n"
                + "                        <option value=\"China\">China</option>\n"
                + "                        <option value=\"Japan\">Japan</option>\n"
                + "                     </select><br></td>\n"
                + "            </tr>\n"
                + "            <tr>\n"
                + "                <td>E-Mail:</td>\n"
                + "                <td><input type=\"text\" name=\"email\"  required placeholder=\"Your email..\"><br></td>\n"
                + "            </tr>\n"
                + "            <tr>\n"
                + "                <td>Password:</td>\n"
                + "                <td><input type=\"password\" name=\"password\"  required placeholder=\"Your password..\"><br></td>\n"
                + "            </tr>\n"
                + "            <tr>\n"
                + "                <td><br></td>\n"
                + "                <td><input type=\"submit\" value=\"Register\"><br></td>\n"
                + "            </tr>\n"
                + "        </form>");
        out.println("</div>");

        out.println("<br><br>");

        out.println("<center><h2>Users</h2></center>");
        out.println("<table id=\"users\">\n"
                + "            <tr>\n"
                + "                <td>FullName </td>\n"
                + "                <td>Address </td>\n"
                + "                <td>City </td>\n"
                + "                <td>Country </td>\n"
                + "                <td>E-mail </td>\n"
                + "                <td>Action</td>\n"
                + "           </tr>\n");


        List<User> list = parseXML();
        for (int i = 0; i < list.size(); i++) {
            out.println("<tr>");
            out.println("<td>" + list.get(i).first_name + " " + list.get(i).last_name + "</td>");
            out.println("<td>" + list.get(i).address + "</td>");
            out.println("<td>" + list.get(i).city + "</td>");
            out.println("<td>" + list.get(i).country + "</td>");
            out.println("<td>" + list.get(i).email + "</td>");
            out.print("<td><a href=\"InsertUserServlet?email=" + list.get(i).email + "\"><button class=\"button\">Delete</button></a></td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }


    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        String firstname = request.getParameter("firstName");
        String lastname = request.getParameter("lastName");
        String adres = request.getParameter("address");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = new User();
        user.setAddress(adres);
        user.setCity(city);
        user.setCountry(country);
        user.setEmail(email);
        user.setFirst_name(firstname);
        user.setLast_name(lastname);
        user.setPassword(password);
        boolean eCheck = saveUser(user);



        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<style>");
        out.println("input[type=submit] {\n" +
                "  width: 100%;\n" +
                "  background-color: #4CAF50;\n" +
                "  color: white;\n" +
                "  padding: 14px 20px;\n" +
                "  margin: 8px 0;\n" +
                "  border: none;\n" +
                "  border-radius: 4px;\n" +
                "  cursor: pointer;\n" +
                "}");
        out.println("input[type=submit]:hover {\n" +
                "  background-color: #4CAF50;\n" +
                "}\n");

        out.println("input[type=text], select {\n" +
                "  width: 100%;\n" +
                "  padding: 12px 20px;\n" +
                "  margin: 8px 0;\n" +
                "  display: inline-block;\n" +
                "  border: 1px solid #ccc;\n" +
                "  border-radius: 4px;\n" +
                "  box-sizing: border-box;\n" +
                "}");

        out.println("input[type=email], select {\n" +
                "  width: 100%;\n" +
                "  padding: 12px 20px;\n" +
                "  margin: 8px 0;\n" +
                "  display: inline-block;\n" +
                "  border: 1px solid #ccc;\n" +
                "  border-radius: 4px;\n" +
                "  box-sizing: border-box;\n" +
                "}");


        out.println("input[type=password], select {\n" +
                "  width: 100%;\n" +
                "  padding: 12px 20px;\n" +
                "  margin: 8px 0;\n" +
                "  display: inline-block;\n" +
                "  border: 1px solid #ccc;\n" +
                "  border-radius: 4px;\n" +
                "  box-sizing: border-box;\n" +
                "}");

        out.println("\n" +
                "div {\n" +
                "  border-radius: 5px;\n" +
                "  background-color: #f2f2f2;\n" +
                "  padding: 20px;\n" +
                "}\n");

        out.println("#users {\n" +
                "  font-family: Arial, Helvetica, sans-serif;\n" +
                "  border-collapse: collapse;\n" +
                "  width: 100%;\n" +
                "}");
        out.println("#users td, #users th {\n" +
                "  border: 1px solid #ddd;\n" +
                "  padding: 8px;\n" +
                "}");

        out.println("#users tr:nth-child(even){background-color: #f2f2f2;}");

        out.println("#users tr:hover {background-color: #ddd;}");
        out.println("#users th {\n" +
                "  padding-top: 12px;\n" +
                "  padding-bottom: 12px;\n" +
                "  text-align: left;\n" +
                "  background-color: #4CAF50;\n" +
                "  color: white;\n" +
                "}");

        out.println(".button {\n" +
                "  background-color: #4CAF50; /* Green */\n" +
                "  border: none;\n" +
                "  color: white;\n" +
                "   padding: 10px 25px;\n" +
                "  text-align: center;\n" +
                "  text-decoration: none;\n" +
                "  display: inline-block;\n" +
                "  font-size: 16px;\n" +
                "}");
        out.println("</style>");
        out.println("<title>Servlet MyServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println(" <form action = \"InsertUserServlet\" method=\"post\">");

        out.println("<center><h2>Create Users</h2> </center>");
        out.println("<div>");
        out.println("<td>First name:</td>\n"
                + "<td><input type=\"text\" required name=\"firstName\" placeholder=\"Your first name..\"><br></td>");
        out.println("</tr>");

        out.println(" <tr>\n"
                + "                <td>Last name:</td>\n"
                + "                <td><input type=\"text\" name=\"lastName\" required placeholder=\"Your last name..\"><br></td>\n"
                + "            </tr>\n"
                + "            <tr>\n"
                + "                <td>Address:</td>\n"
                + "                <td><input type=\"text\" name=\"address\" required placeholder=\"Your address..\"><br></td>\n"
                + "            </tr>\n"
                + "            <tr>\n"
                + "                <td>City:</td>\n"
                + "                <td><input type=\"text\" name=\"city\" required placeholder=\"Your city..\"><br></td>\n"
                + "            </tr>\n"
                + "            <tr>\n"
                + "                <td>Country:</td>\n"
                + "                <td><select name=\"country\"  required>\n"
                + "                        <option value=\"United States\">United States</option>\n"
                + "                        <option value=\"Canada\">Canada</option>\n"
                + "                        <option value=\"United KIngdom\">United Kingdom</option>\n"
                + "                        <option value=\"France\">France</option>\n"
                + "                        <option value=\"Germany\">Germany</option>\n"
                + "                        <option value=\"China\">China</option>\n"
                + "                        <option value=\"Japan\">Japan</option>\n"
                + "                     </select><br></td>\n"
                + "            </tr>\n"
                + "            <tr>\n"
                + "                <td>E-Mail:</td>\n"
                + "                <td><input type = \"email\" required type=\"email\" name=\"email\" placeholder=\"Your email..\"><br></td>\n"
                + "            </tr>\n"
                + "            <tr>\n"
                + "                <td>Password:</td>\n"
                + "                <td><input type=\"password\" required name=\"password\" placeholder=\"Your password..\"><br></td>\n"
                + "            </tr>\n"
                + "            <tr>\n"
                + "                <td><br></td>\n"
                + "                <td><input type=\"submit\" value=\"Register\"><br></td>\n"
                + "            </tr>\n"
                + "        </form>");
        out.println("</div>");

        out.println("<br><br>");

        out.println("<center><h2>Users</h2></center>");


        if (!eCheck) {
            out.println("<h2>Account with this email already exists!</h2>");
        }

        out.println("<table id=\"users\">\n"
                + "            <tr>\n"
                + "                <td>FullName </td>\n"
                + "                <td>Address </td>\n"
                + "                <td>City </td>\n"
                + "                <td>Country </td>\n"
                + "                <td>E-mail </td>\n"
                + "                <td>Action</td>\n"
                + "           </tr>\n");


        List<User> list = User.parseXML();
        for (int i = 0; i < list.size(); i++) {
            out.println("<tr>");
            out.println("<td>" + list.get(i).getFirst_name() + " " + list.get(i).getLast_name() + "</td>");
            out.println("<td>" + list.get(i).getAddress() + "</td>");
            out.println("<td>" + list.get(i).getCity() + "</td>");
            out.println("<td>" + list.get(i).getCountry() + "</td>");
            out.println("<td>" + list.get(i).getEmail() + "</td>");
            out.print("<td><a href=\"InsertUserServlet?email=" + list.get(i).getEmail() + "\"><button class=\"button\">Delete</button></a></td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");


    }
}
