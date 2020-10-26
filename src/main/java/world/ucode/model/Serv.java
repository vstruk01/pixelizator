package world.ucode.model;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@MultipartConfig
@WebServlet("/Serv")
public class Serv  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get method");
        resp.setStatus(200);
        resp.setContentType("text;charset=UTF-8");
        resp.getWriter().write("Hello my friend");
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        return "def";
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
        resp.setContentType("text");

//        OutputStream os = resp.getOutputStream();
        byte[] buffer = new byte[1024];
        int readBuf = 0;
        File f = new File("src/main/webapp/elli.png");
        FileOutputStream fos = new FileOutputStream(f);
//        StringBuilder s = new StringBuilder();
        String s = "";


        while ((readBuf = is.read(buffer)) != -1 ) {
            s += (new String(Base64.getEncoder().encode(buffer), StandardCharsets.US_ASCII));
            System.out.println(new String(Base64.getEncoder().encode(buffer), StandardCharsets.US_ASCII));
            System.out.println(readBuf);
            fos.write(buffer, 0, readBuf);
//            os.write(buffer, 0, readBuf);
        }
//        resp.getWriter().write("elli.png");
        resp.getWriter().println(s);
        System.out.println("Ok");

//        String uploadPath = getServletContext().getRealPath("") + File.separator + "newFile";
//        File uploadDir = new File(uploadPath);
//        ServletFileUpload upload;
//
//        if (!uploadDir.exists())
//            uploadDir.mkdir();
//        for (Part part : req.getParts()) {
//            String fileName = getFileName(part);
//            part.write(uploadPath + File.separator + fileName);
//        }
//        if (ServletFileUpload.isMultipartContent(req)) {
//            DiskFileItemFactory factory = new DiskFileItemFactory();
//            factory.setSizeThreshold(1024 * 1024);
//            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
//
//            upload = new ServletFileUpload(factory);
//            upload.setFileSizeMax(1024 * 1024 * 5);
//            upload.setSizeMax(1024 * 1024 * 5 * 5);
//            uploadPath = getServletContext().getRealPath("") + File.separator + "newFile";
//            uploadDir = new File(uploadPath);
//            if (!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }
//
//            List<FileItem> formItems = null;
//            try {
//                formItems = upload.parseRequest(req);
//            } catch (FileUploadException e) {
//                e.printStackTrace();
//            }
//            if (formItems != null && formItems.size() > 0) {
//                for (FileItem item : formItems) {
//	                if (!item.isFormField()) {
//	                    String fileName = new File(item.getName()).getName();
//	                    String filePath = uploadPath + File.separator + fileName;
//	                    File storeFile = new File(filePath);
//
//                        try {
//                            System.out.println(item.getFieldName());
//                            item.write(storeFile);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        req.setAttribute("message", "File "
//                              + fileName + " has uploaded successfully!");
//	                }
//                }
//            }
//        }
    }
}
