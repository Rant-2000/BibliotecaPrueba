/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author Rant AA
 */
public class QRGenerador {
        private static final int qrTamAncho = 100;
    private static final int qrTamAlto = 100;
    private static final String formato = "png";
    //private static final String ruta = "C:\\Users\\Admin\\Pictures\\WOW\\miCodigoQR3.png";
public String ruta;
    public static void main(String[] args) throws Exception {
        /*System.out.println("Introduce el texto a codificar: ");
        Scanner sc = new Scanner(System.in);
        String data = sc.nextLine();
        System.out.println("Cofificando...");
        BitMatrix matriz;
        Writer writer = new QRCodeWriter();
        try {
            matriz = writer.encode(data, BarcodeFormat.QR_CODE, qrTamAncho, qrTamAlto);
        } catch (WriterException e) {
            e.printStackTrace(System.err);
            return;
        }
        BufferedImage imagen = new BufferedImage(qrTamAncho,
                qrTamAlto, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < qrTamAlto; y++) {
            for (int x = 0; x < qrTamAncho; x++) {
                int valor = (matriz.get(x, y) ? 0 : 1) & 0xff;
                imagen.setRGB(x, y, (valor == 0 ? 0 : 0xFFFFFF));
            }
        }
        FileOutputStream qrCode = new FileOutputStream(ruta);
        ImageIO.write(imagen, formato, qrCode);
        System.out.println("Listo!");
        qrCode.close();*/
    }
    public  void QrAlumno(String nc, String ruta) throws FileNotFoundException, IOException {
        String data=nc;
        BitMatrix matriz;
        Writer writer = new QRCodeWriter();
        try {
            matriz = writer.encode(data, BarcodeFormat.QR_CODE, qrTamAncho, qrTamAlto);
        } catch (WriterException e) {
            e.printStackTrace(System.err);
            return;
        }
        BufferedImage imagen = new BufferedImage(qrTamAncho,
                qrTamAlto, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < qrTamAlto; y++) {
            for (int x = 0; x < qrTamAncho; x++) {
                int valor = (matriz.get(x, y) ? 0 : 1) & 0xff;
                imagen.setRGB(x, y, (valor == 0 ? 0 : 0xFFFFFF));
            }
        }
        this.ruta=ruta;
        FileOutputStream qrCode = new FileOutputStream("C:\\Users\\Admin\\Pictures\\Codigos\\Credenciales\\"+ruta+".png");
        ImageIO.write(imagen, formato, qrCode);
        System.out.println("Listo!");
        qrCode.close();
    }
    public void QrLibro(String isbn, String ruta) throws FileNotFoundException, IOException {
        String data=isbn;
        BitMatrix matriz;
        Writer writer = new QRCodeWriter();
        try {
            matriz = writer.encode(data, BarcodeFormat.QR_CODE, qrTamAncho, qrTamAlto);
        } catch (WriterException e) {
            e.printStackTrace(System.err);
            return;
        }
        BufferedImage imagen = new BufferedImage(qrTamAncho,
                qrTamAlto, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < qrTamAlto; y++) {
            for (int x = 0; x < qrTamAncho; x++) {
                int valor = (matriz.get(x, y) ? 0 : 1) & 0xff;
                imagen.setRGB(x, y, (valor == 0 ? 0 : 0xFFFFFF));
            }
        }
        this.ruta=ruta;
        FileOutputStream qrCode = new FileOutputStream("C:\\Users\\Admin\\Pictures\\Codigos\\Libros\\"+ruta+".png");
        ImageIO.write(imagen, formato, qrCode);
        System.out.println("Listo!");
        qrCode.close();
    }
    
    public String getRuta() {
        return ruta;
    }
    
    
}
