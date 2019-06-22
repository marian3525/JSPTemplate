package servlets;

import model.Company;
import model.Person;
import repository.DBRepo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "TestDB", urlPatterns = {"/JSPTemplate_war_exploded/servlet/test"})
public class TestDB extends HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        DBRepo repo = new DBRepo();

        Company c = new Company();
        c.setName("company1");

        Person p1 = new Person();
        p1.setName("person1");
        p1.setEmployeeCompany(c);

        Person p2 = new Person();
        p2.setName("person2");
        p2.setEmployeeCompany(c);

        Person p3 = new Person();
        p3.setName("person3");
        p3.setEmployeeCompany(c);

        Set<Person> emps = new HashSet<>();
        emps.add(p1);
        emps.add(p2);
        emps.add(p3);

        c.setEmployees(emps);

        repo.save(c);
        /*A a = new A(request.getParameter("username") + "A");
        B b = new B(request.getParameter("username") + "B");
        repo.save(a);
        repo.save(b);
        */

        response.setContentType("text/html");
        PrintWriter pr = response.getWriter();

        Company pers_c = repo.getCompanies().get(0);

        var persons = pers_c.getEmployees();

        //List<A> as = repo.getAs();
        //List<B> bs = repo.getBs();

        persons.forEach(pr::println);
        //pr.println(bs.get(0).getPropertyB());
        //pr.flush();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}