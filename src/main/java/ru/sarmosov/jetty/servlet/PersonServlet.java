package ru.sarmosov.jetty.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.sarmosov.dao.PersonDAO;
import ru.sarmosov.entity.Person;
import ru.sarmosov.util.MappingUtils;

import java.io.IOException;
import java.util.List;

@WebServlet("/lab4/*")
public class PersonServlet extends HttpServlet {

    private PersonDAO dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = new PersonDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Person> list = dao.getAllPersons();
        req.setAttribute("list", list);
        req.getRequestDispatcher("view/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        if (command != null) {
            if (command.equals("DELETE")) {
                doDelete(req, resp);
            } else doPut(req, resp);
            return;
        }
        String personString = req.getParameter("person");
        Person person = MappingUtils.parsePerson(personString);
        dao.createPerson(person);
        resp.sendRedirect("/lab4");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String idString = pathInfo.substring(1);
        int id = Integer.parseInt(idString);
        String personString = MappingUtils.removeFirstWord(req.getParameter("command"));
        Person person = MappingUtils.parsePerson(personString);
        person.setId(id);
        dao.updatePerson(person);
        resp.sendRedirect("/lab4");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String idString = pathInfo.substring(1);
        int id = Integer.parseInt(idString);
        dao.deletePerson(id);
        resp.sendRedirect("/lab4");
    }
}
