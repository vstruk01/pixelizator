package world.ucode.model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet("/Serv")
public class Serv  extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get method");
        resp.setStatus(200);
        resp.setContentType("text;charset=UTF-8");
        resp.getWriter().write("Hello my friend");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post method");

        Part filePart = req.getPart("file");
        if (filePart == null) {
            resp.getWriter().write("s.png");
            System.out.println("null");
            return;
        }

        InputStream is = filePart.getInputStream();
        resp.setStatus(202);
        resp.setContentType("image");

        OutputStream os = resp.getOutputStream();
        byte[] buffer = new byte[1024];
        int readBuf = 0;
//        File f = new File("src/main/webapp/elli.png");
//        FileOutputStream fos = new FileOutputStream(f);
//        File reqImage = new File("reqImage");
//        FileOutputStream fri = new FileOutputStream(reqImage);
//        String s = "";
//        int len = 0;


        while ((readBuf = is.read(buffer)) != -1 ) {
//            fri.write((new String(Base64.getEncoder().encode(buffer), StandardCharsets.UTF_8)/* + "\n"*/).getBytes());
//            len += readBuf;
//            System.out.println(readBuf);
//            fos.write(Base64.getDecoder().decode(new String(Base64.getEncoder().encode(buffer), StandardCharsets.UTF_8)), 0, readBuf);
            os.write(buffer, 0, readBuf);
        }
//        fos.write(sb.toString().getBytes(), 0, len);
//        os.write(sb.toString().getBytes(), 0, len);
//        resp.getWriter().write("elli.png");
//        resp.getWriter().println(s);
        System.out.println("Ok");
    }
}
