package Modelo;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Imagen extends javax.swing.JPanel {
    
    String ruta;

   public Imagen(int x, int y, String ruta) {
      this.setSize(x, y); //se selecciona el tamaño del panel
      this.ruta = ruta;
   }

//Se crea un método cuyo parámetro debe ser un objeto Graphics
   public void paint(Graphics grafico) {
      Dimension height = getSize();
       System.out.println(ruta);
//Se selecciona la imagen que tenemos en el paquete de la //ruta del programa
      ImageIcon img = new ImageIcon(getClass().getResource(ruta));

//se dibuja la imagen que tenemos en el paquete Images //dentro de un panel
      grafico.drawImage(img.getImage(), 0, 0, height.width, height.height, null);

      setOpaque(false);
      super.paintComponent(grafico);
   }
}
