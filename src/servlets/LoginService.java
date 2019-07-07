package servlets;

import model.SiteUser;
import repository.DBRepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/loginService")
public class LoginService extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.doPost(request, response);

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        DBRepo repo = new DBRepo();

        List<SiteUser> all = repo.getUsers();

        boolean foundUser = all.stream().anyMatch(user ->
                                                    user.getUsername().equals(username) &&
                                                    user.getPassword().equals(password));


        if(foundUser) {
            request.getSession().setAttribute("username", username);
            response.sendRedirect("/JSPTemplate_war_exploded/home.jsp");
        }
        else {
            response.getWriter().println("user not found");
        }
    }
}