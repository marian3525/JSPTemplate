package servlets;

import model.SiteUser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import repository.DBRepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
@WebServlet("/homeService")
public class HomeService extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.doPost(request, response);

        response.setContentType("application/json");

        String jsonString = getAll().toJSONString();
        PrintWriter out = new PrintWriter(response.getOutputStream());
        out.println(jsonString);
        out.flush();
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // add user
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        DBRepo db = new DBRepo();

        db.save(new SiteUser(username, password));
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //update user
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int id = Integer.parseInt(request.getParameter("id"));

        DBRepo db = new DBRepo();

        db.updateUser(id, username, password);
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_str = request.getParameter("id");
        try {
            // delete the entity with the given

            int id = Integer.parseInt(id_str);
            DBRepo db = new DBRepo();
            db.deleteUser(id);
        }
        catch (NumberFormatException nfe) {
            System.err.println(nfe.toString());
        }

    }

    private JSONArray getAll() {
        JSONArray jsonArray = new JSONArray();

        DBRepo db = new DBRepo();

        List<JSONObject> users = new ArrayList<>();

        db.getUsers().forEach(user -> {
            JSONObject obj = new JSONObject();
            obj.put("id", user.getId());
            obj.put("username", user.getUsername());
            obj.put("password", user.getPassword());

            users.add(obj);
        });

        jsonArray.addAll(users);

        return jsonArray;
    }
}
