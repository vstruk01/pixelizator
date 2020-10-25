package world.ucode.model;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/Serv")
public class Serv  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get method");
        resp.setStatus(200);
        resp.setContentType("text/html;charset=UTF-8");
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
        resp.encodeRedirectURL("/");
        resp.encodeURL("/");
        String uploadPath = getServletContext().getRealPath("") + File.separator + "newFile";
        File uploadDir = new File(uploadPath);
        ServletFileUpload upload;
        resp.setStatus(200);
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write("Nice");

        if (!uploadDir.exists())
            uploadDir.mkdir();
        for (Part part : req.getParts()) {
            String fileName = getFileName(part);
            part.write(uploadPath + File.separator + fileName);
        }
        if (ServletFileUpload.isMultipartContent(req)) {
            System.out.println("yes MultipartContent");
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024 * 1024);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(1024 * 1024 * 5);
            upload.setSizeMax(1024 * 1024 * 5 * 5);
            uploadPath = getServletContext().getRealPath("") + File.separator + "newFile";
            uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            List<FileItem> formItems = null;
            try {
                formItems = upload.parseRequest(req);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
	            if (!item.isFormField()) {
	                String fileName = new File(item.getName()).getName();
	                String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                    try {
                        item.write(storeFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    req.setAttribute("message", "File "
                          + fileName + " has uploaded successfully!");
	            }
                }
            }
        }
    }
}
