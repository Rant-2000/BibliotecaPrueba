package reybiblio;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.JOptionPane;

public class RegistroLibros extends javax.swing.JFrame implements Runnable, ThreadFactory {

    private WebcamPanel panel = null;
    private Webcam webcam = null;

    private static final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    private String valor;
    public String fechaActual = java.time.LocalDate.now().toString();
    public Calendar diaHoy;

    public RegistroLibros() {
        initComponents();
        initWebcam();
        txtDIA.setText(fechaActual);
        txtDIA.setEditable(false);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        System.out.println("Current Date: " + sdf.format(cal.getTime()));
        String viejodate = sdf.format(cal.getTime());
        diaHoy = cal;
        try {
            java.util.Date nuevodate = sdf.parse(viejodate);
            jday.setMinSelectableDate(nuevodate);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        }

    }

    public String getValor() {
        return valor;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        result_field = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nomregistro = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        aperegistro = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        carreraregistro = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        ncregistro = new javax.swing.JTextField();
        btnagregarest = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnagregarLibro = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tituloregistro = new javax.swing.JTextField();
        autorregistro = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        editorialregistro = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        isbnregistro = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtanhioRegistroLibro = new javax.swing.JTextField();
        txtTotalRegistro = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtExistenciaRegistro = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        tituloregistro2 = new javax.swing.JTextField();
        autorregistro2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        editorialregistro2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        isbnregistro2 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtanhioRegistroLibro2 = new javax.swing.JTextField();
        txtTotalRegistro2 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtExistenciaRegistro2 = new javax.swing.JTextField();
        jday = new com.toedter.calendar.JCalendar();
        txtDIA = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btnRegistro = new javax.swing.JButton();
        btnEntregar = new javax.swing.JButton();
        btnEscaneoCredencial = new javax.swing.JButton();
        bntEscaneolibro = new javax.swing.JButton();
        bntEscaneolibro1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        result_field.setBorder(null);
        result_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                result_fieldActionPerformed(evt);
            }
        });
        jPanel1.add(result_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 470, 20));

        jSeparator1.setForeground(new java.awt.Color(126, 167, 206));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 470, 10));

        jLabel1.setForeground(new java.awt.Color(105, 105, 105));
        jLabel1.setText("Resultado");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, 20));

        jPanel2.setBackground(new java.awt.Color(250, 250, 250));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 360, 220));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 50, 450, 320));

        jPanel3.setBackground(new java.awt.Color(255, 153, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        jLabel2.setText("Nombre:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        nomregistro.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        jPanel3.add(nomregistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 140, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        jLabel3.setText("Apellidos:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        aperegistro.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        jPanel3.add(aperegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 140, -1));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        jLabel4.setText("Carrera:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

        carreraregistro.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        jPanel3.add(carreraregistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 140, -1));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        jLabel8.setText("N. Control:");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        ncregistro.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        jPanel3.add(ncregistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 140, -1));

        btnagregarest.setText("Agregar ");
        jPanel3.add(btnagregarest, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 320, 300));

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnagregarLibro.setText("Agregar Libro");
        jPanel4.add(btnagregarLibro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        jLabel5.setText("Titulo:");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 40, 30));

        tituloregistro.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        jPanel5.add(tituloregistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 110, -1));

        autorregistro.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        jPanel5.add(autorregistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 110, -1));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        jLabel6.setText("Autor:");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 60, 30));

        editorialregistro.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        jPanel5.add(editorialregistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 110, 20));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        jLabel7.setText("Editorial:");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 60, 20));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        jLabel9.setText("ISBN:");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 40, 20));

        isbnregistro.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        jPanel5.add(isbnregistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 110, -1));

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        jLabel10.setText("Año:");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 60, 20));

        txtanhioRegistroLibro.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        jPanel5.add(txtanhioRegistroLibro, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 110, 20));

        txtTotalRegistro.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        jPanel5.add(txtTotalRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 110, 20));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        jLabel16.setText("Total:");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 60, 20));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        jLabel17.setText("Existencia:");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 60, 20));

        txtExistenciaRegistro.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        jPanel5.add(txtExistenciaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 110, 20));

        jTabbedPane1.addTab("Libro ", jPanel5);

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Yu Gothic Light", 0, 12)); // NOI18N
        jLabel11.setText("Titulo:");
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        tituloregistro2.setFont(new java.awt.Font("Yu Gothic Light", 0, 12)); // NOI18N
        jPanel6.add(tituloregistro2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 100, 20));

        autorregistro2.setFont(new java.awt.Font("Yu Gothic Light", 0, 12)); // NOI18N
        jPanel6.add(autorregistro2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 100, 20));

        jLabel12.setFont(new java.awt.Font("Yu Gothic Light", 0, 12)); // NOI18N
        jLabel12.setText("Autor:");
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        editorialregistro2.setFont(new java.awt.Font("Yu Gothic Light", 0, 12)); // NOI18N
        jPanel6.add(editorialregistro2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 100, 20));

        jLabel13.setFont(new java.awt.Font("Yu Gothic Light", 0, 12)); // NOI18N
        jLabel13.setText("Editorial:");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        jLabel14.setFont(new java.awt.Font("Yu Gothic Light", 0, 12)); // NOI18N
        jLabel14.setText("ISBN:");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        isbnregistro2.setFont(new java.awt.Font("Yu Gothic Light", 0, 12)); // NOI18N
        jPanel6.add(isbnregistro2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 100, 20));

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        jLabel18.setText("Año:");
        jPanel6.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 60, 20));

        txtanhioRegistroLibro2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        jPanel6.add(txtanhioRegistroLibro2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 100, 20));

        txtTotalRegistro2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        jPanel6.add(txtTotalRegistro2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 100, 20));

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        jLabel19.setText("Total:");
        jPanel6.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 60, 20));

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        jLabel20.setText("Existencia:");
        jPanel6.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 60, 20));

        txtExistenciaRegistro2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        jPanel6.add(txtExistenciaRegistro2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 100, 20));

        jTabbedPane1.addTab("+", jPanel6);

        jPanel4.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 280, 300));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 300, 360));
        getContentPane().add(jday, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, -1, -1));
        getContentPane().add(txtDIA, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 80, -1));

        jLabel15.setText("HOY:");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel21.setText("Apartado de Libros");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, 30));

        btnRegistro.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        btnRegistro.setText("Registrar");
        getContentPane().add(btnRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 460, 120, 50));

        btnEntregar.setFont(new java.awt.Font("Yu Gothic Light", 0, 18)); // NOI18N
        btnEntregar.setText("Entregar ");
        getContentPane().add(btnEntregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 460, 140, 50));

        btnEscaneoCredencial.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        btnEscaneoCredencial.setText("Escanear Credencial");
        btnEscaneoCredencial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEscaneoCredencialActionPerformed(evt);
            }
        });
        getContentPane().add(btnEscaneoCredencial, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 400, -1, -1));

        bntEscaneolibro.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        bntEscaneolibro.setText("Escanear LibroQr 1");
        getContentPane().add(bntEscaneolibro, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 400, -1, -1));

        bntEscaneolibro1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 18)); // NOI18N
        bntEscaneolibro1.setText("Escanear LibroQr 2");
        getContentPane().add(bntEscaneolibro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 450, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void result_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_result_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_result_fieldActionPerformed

    private void btnEscaneoCredencialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEscaneoCredencialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEscaneoCredencialActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroLibros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new RegistroLibros().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField aperegistro;
    public javax.swing.JTextField autorregistro;
    public javax.swing.JTextField autorregistro2;
    public javax.swing.JButton bntEscaneolibro;
    public javax.swing.JButton bntEscaneolibro1;
    public javax.swing.JButton btnEntregar;
    public javax.swing.JButton btnEscaneoCredencial;
    public javax.swing.JButton btnRegistro;
    public javax.swing.JButton btnagregarLibro;
    public javax.swing.JButton btnagregarest;
    public javax.swing.JTextField carreraregistro;
    public javax.swing.JTextField editorialregistro;
    public javax.swing.JTextField editorialregistro2;
    public javax.swing.JTextField isbnregistro;
    public javax.swing.JTextField isbnregistro2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    public com.toedter.calendar.JCalendar jday;
    public javax.swing.JTextField ncregistro;
    public javax.swing.JTextField nomregistro;
    public javax.swing.JTextField result_field;
    public javax.swing.JTextField tituloregistro;
    public javax.swing.JTextField tituloregistro2;
    public javax.swing.JTextField txtDIA;
    public javax.swing.JTextField txtExistenciaRegistro;
    public javax.swing.JTextField txtExistenciaRegistro2;
    public javax.swing.JTextField txtTotalRegistro;
    public javax.swing.JTextField txtTotalRegistro2;
    public javax.swing.JTextField txtanhioRegistroLibro;
    public javax.swing.JTextField txtanhioRegistroLibro2;
    // End of variables declaration//GEN-END:variables

    private void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0); //0 is default webcam
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        jPanel2.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 300));

        executor.execute(this);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException e) {
                //No result...
            }
            if (result != null) {
                result_field.setText(result.getText());                
                valor = result.getText();
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }
}
