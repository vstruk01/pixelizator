package world.ucode.Servlets;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;
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

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("file");

        InputStream is = filePart.getInputStream();
        resp.setStatus(200);
        resp.setContentType("image");

        OutputStream os = resp.getOutputStream();
        byte[] buffer = new byte[1024];
        int readBuf = 0;

//        File f = new File("src/main/webapp/elli.png");
//        FileOutputStream fos = new FileOutputStream(f);

//        File reqImage = new File("reqImage");
//        FileOutputStream fri = new FileOutputStream(reqImage);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        while ((readBuf = is.read(buffer)) != -1 ) {
            baos.write(buffer, 0, readBuf);
        }

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        BufferedImage image = ImageIO.read(bais);

        // pixelizate
        int pixel = Integer.parseInt(req.getHeader("size")); // how much pixel pixelizate
        if (req.getHeader("shape").equals("0")) {
            imageToRect(image, req, pixel);
        } else {
            imageToTriangles(image, req, pixel);
        }
        // end pixelizate

        ImageIO.write(image, req.getHeader("format"), os);

//        while ((readBuf = is.read(buffer)) != -1 ) {
//            fri.write((new String(Base64.getEncoder().encode(buffer), StandardCharsets.UTF_8)).getBytes());
//            fos.write(Base64.getDecoder().decode(new String(Base64.getEncoder().encode(buffer), StandardCharsets.UTF_8)), 0, readBuf);
//            os.write(buffer, 0, readBuf);
//        }
    }

    private void imageToRect (BufferedImage image, HttpServletRequest req, int pixel) {
        int[] colorsRGB = new int[3]; // int[0] RED, int[1] GREEN,  int[2] BLUE
        int countPixel = 0;

        for (int y = 0; y < image.getHeight(); y += pixel) {
            for (int x = 0; x < image.getWidth(); x += pixel) {
                for (int ky = 0; ky < pixel && (y + ky) < image.getHeight(); ky++) {
                    for (int kx = 0; kx < pixel && (x + kx) < image.getWidth(); kx++, countPixel++) {
                        Color pRGB = new Color(image.getRGB(x + kx, y + ky));
                        colorsRGB[0] += pRGB.getRed();
                        colorsRGB[1] += pRGB.getGreen();
                        colorsRGB[2] += pRGB.getBlue();
                    }
                }
                colorsRGB[0] /= countPixel;
                colorsRGB[1] /= countPixel;
                colorsRGB[2] /= countPixel;
                Color RGB = new Color(colorsRGB[0], colorsRGB[1], colorsRGB[2]);
                for (int ky = 0; ky < pixel && (y + ky) < image.getHeight(); ky++) {
                    for (int kx = 0; kx < pixel && (x + kx) < image.getWidth(); kx++) {
                        image.setRGB(x + kx, y + ky, RGB.getRGB());
                    }
                }
                colorsRGB[0] = 0;
                colorsRGB[1] = 0;
                colorsRGB[2] = 0;
                countPixel = 0;
            }
        }
    }

    private void imageToTriangles(BufferedImage image, HttpServletRequest req, int pixel) {
        int[] colorFirstTriangle = new int[3]; // int[0] RED, int[1] GREEN,  int[2] BLUE
        int[] colorSecondTriangle = new int[3];
        int countPixelFirst = 0;
        int countPixelSecond = 0;

        for (int y = 0; y < image.getHeight(); y += pixel) {
            for (int x = 0; x < image.getWidth(); x += pixel) {
                for (int ky = 0; ky < pixel && (y + ky) < image.getHeight(); ky++) {
                    for (int kx = 0; kx < pixel && (x + kx) < image.getWidth(); kx++) {
                        Color pRGB = new Color(image.getRGB(x + kx, y + ky));
                        if (kx > ky) {
                            countPixelFirst++;
                            colorFirstTriangle[0] += pRGB.getRed();
                            colorFirstTriangle[1] += pRGB.getGreen();
                            colorFirstTriangle[2] += pRGB.getBlue();
                        } else {
                            countPixelSecond++;
                            colorSecondTriangle[0] += pRGB.getRed();
                            colorSecondTriangle[1] += pRGB.getGreen();
                            colorSecondTriangle[2] += pRGB.getBlue();
                        }
                    }
                }
                if (countPixelFirst != 0) {
                    colorFirstTriangle[0] /= countPixelFirst;
                    colorFirstTriangle[1] /= countPixelFirst;
                    colorFirstTriangle[2] /= countPixelFirst;
                }
                if (countPixelSecond != 0) {
                    colorSecondTriangle[0] /= countPixelSecond;
                    colorSecondTriangle[1] /= countPixelSecond;
                    colorSecondTriangle[2] /= countPixelSecond;
                }
                Color first = new Color(colorFirstTriangle[0], colorFirstTriangle[1], colorFirstTriangle[2]);
                Color second = new Color(colorSecondTriangle[0], colorSecondTriangle[1], colorSecondTriangle[2]);
                for (int ky = 0; ky < pixel && (y + ky) < image.getHeight(); ky++) {
                    for (int kx = 0; kx < pixel && (x + kx) < image.getWidth(); kx++) {
                        if (kx > ky) {
                            image.setRGB(x + kx, y + ky, first.getRGB());
                        } else {
                            image.setRGB(x + kx, y + ky, second.getRGB());
                        }
                    }
                }
                colorSecondTriangle[0] = 0;
                colorSecondTriangle[1] = 0;
                colorSecondTriangle[2] = 0;
                colorFirstTriangle[0] = 0;
                colorFirstTriangle[1] = 0;
                colorFirstTriangle[2] = 0;
                countPixelFirst = 0;
                countPixelSecond = 0;
            }
        }
    }

}
