package world.ucode.model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Serv")
public class Serv  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Yes");
        resp.setStatus(200);
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write("Hello my friend");
    }
}
