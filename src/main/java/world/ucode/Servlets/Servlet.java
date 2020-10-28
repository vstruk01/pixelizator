package world.ucode.Servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


@WebServlet("/")
public class Servlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OutputStream os = resp.getOutputStream();
        ServletContext sc = getServletContext();
        byte[] buffer = new byte[1024];
        int readBuf = 0;
        InputStream is;

        resp.setStatus(200);
        switch (req.getServletPath()) {
            case "/scriptButton.js": {
                resp.setContentType("script");
                is = sc.getResourceAsStream("scriptButton.js");
                break;
            }
            case "/script.js": {
                resp.setContentType("script");
                is = sc.getResourceAsStream("script.js");
                break;
            }
            case "/style.css": {
                resp.setContentType("stylesheet");
                is = sc.getResourceAsStream("style.css");
                break;
            }
            case "/s.png": {
                resp.setContentType("image");
                is = sc.getResourceAsStream("s.png");
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + req.getServletPath());
        }
        if (is == null) {
            System.out.println("error !");
            return;
        }
        while ((readBuf = is.read(buffer)) != -1 ) {
            os.write(buffer, 0, readBuf);
        }
    }
}
