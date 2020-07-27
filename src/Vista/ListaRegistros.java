/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.Conexion;
import Modelo.ConsultaEstudiante;
import Modelo.ConsultaLibro;
import Modelo.ConsultaListaEstudiantes;
import Modelo.ConsultaListaLibros;
import Modelo.ConsultaListaRegistros;
import Modelo.Estudiante;
import Modelo.Libro;
import Modelo.Registro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rant AA
 */
public class ListaRegistros extends javax.swing.JFrame {

    DefaultTableModel modelo = new DefaultTableModel();

    /**
     * Creates new form ListaRegistros
     */
    public ListaRegistros() {
        initComponents();
        inicio();
    }

    private void inicio() {
        try {
            Estudiante es;
            Libro lib;
            ConsultaLibro conslib;
            ConsultaEstudiante conses;
            modelo = new DefaultTableModel();
            jtRegistros.setModel(modelo);

            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConnection();

            String sql = "SELECT id,fecha_pedido,fecha_entrega,idEstudiante,idLibro,estadoEntregado FROM registros";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("ID");
            modelo.addColumn("Fecha de apartado");
            modelo.addColumn("Fecha de Entrega");
            modelo.addColumn("Nombre ");
            modelo.addColumn("Apellidos");
            modelo.addColumn("Nc");
            modelo.addColumn("Titulo");
            modelo.addColumn("Autor");
            modelo.addColumn("ISBN");
            modelo.addColumn("Estado");
            conses = new ConsultaEstudiante();
            conslib = new ConsultaLibro();
            while (rs.next()) {
                Object[] filas = new Object[10];
                filas[0]=rs.getString("id");
                filas[1] = rs.getDate("fecha_pedido");
                filas[2] = rs.getDate("fecha_entrega");
                es = conses.getEstudiantePorId(rs.getInt("idEstudiante"));
                filas[3] = es.getNomEs();
                filas[4] = es.getApp() + " " + es.getApm();
                filas[5] = es.getNc();
                lib = conslib.getLibroPorId(rs.getInt("idLibro"));
                filas[6] = lib.getTitulo();
                filas[7] = lib.getAutor();
                filas[8] = lib.getIsbn();
                if (rs.getBoolean("estadoEntregado")) {
                    filas[9] = "Entregado";
                } else {
                    filas[9] = "Pendiente";
                }
                //filas[8]=rs.getBoolean("estadoEntregado");
                modelo.addRow(filas);
            }

        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }

    public void RellenadoGrupoRegistro(ArrayList<Registro> reg) {
        DefaultTableModel dtf = new DefaultTableModel();
        String[] tit = {"id","Fecha de apartado", "Fecha de entrega", "Deudor", "N.Control", "Titulo", "Autor", "ISBN", "Estado"};
        modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(tit);

        for (int i = 0; i < reg.size(); i++) {
            Object[] Fila = new Object[9];
            Fila[0]=reg.get(i).getIdRegistro();
            Fila[1] = reg.get(i).getFechapedido();
            Fila[2] = reg.get(i).getFechaentrega();
            ConsultaListaEstudiantes conslises = new ConsultaListaEstudiantes();
            Estudiante es = conslises.buscarPorId(reg.get(i).getIdEstudiante());
            Fila[3] = es.getNomEs();
            Fila[4] = es.getNc();

            ConsultaListaLibros conslislib = new ConsultaListaLibros();
            Libro lib = conslislib.buscarPorId(reg.get(i).getIdLibro());
            Fila[5] = lib.getTitulo();
            Fila[6] = lib.getAutor();
            Fila[7] = lib.getIsbn();
            if (reg.get(i).isEstado()) {
                Fila[8] = "Entregado";
            } else {
                Fila[8] = "Pendiente";
            }
            System.out.println("Datos Fila  " + i + " Fecha Apartado " + Fila[0] + " Fecha entrega" + Fila[1] + " Nombre" + Fila[2] + " " + Fila[3]);
            modelo.addRow(Fila);
            System.out.println(modelo.getRowCount());

            //System.out.println(modelo.getValueAt(0, 0));
        }

        jtRegistros.setModel(modelo);
    }

    public void RellenadoGrupoEst(ArrayList<Estudiante> estudiantes) {
        DefaultTableModel dtf = new DefaultTableModel();
        String[] tit = {"id","Fecha de apartado", "Fecha de entrega", "Deudor", "N.Control", "Titulo", "Autor", "ISBN", "Estado"};
        modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(tit);

        for (int i = 0; i < estudiantes.size(); i++) {
            Object[] Fila = new Object[9];
            ConsultaListaRegistros conslis=new ConsultaListaRegistros();
            Registro reg=conslis.buscarIndividual(String.valueOf(estudiantes.get(i).getId()), 3);
            Fila[0]=reg.getIdRegistro();
            Fila[1] = reg.getFechapedido();
            Fila[2] = reg.getFechaentrega();
            
            
            Fila[3] = estudiantes.get(i).getNomEs();
            Fila[4] = estudiantes.get(i).getNc();

            ConsultaListaLibros conslislib = new ConsultaListaLibros();
            Libro lib = conslislib.buscarPorId(reg.getIdLibro());
            
            Fila[5] = lib.getTitulo();
            Fila[6] = lib.getAutor();
            Fila[7] = lib.getIsbn();
            if (reg.isEstado()) {
                Fila[8] = "Entregado";
            } else {
                Fila[8] = "Pendiente";
            }
            System.out.println("Datos Fila  " + i + " Fecha Apartado " + Fila[0] + " Fecha entrega" + Fila[1] + " Nombre" + Fila[2] + " " + Fila[3]);
            modelo.addRow(Fila);
            System.out.println(modelo.getRowCount());

            //System.out.println(modelo.getValueAt(0, 0));
        }

        jtRegistros.setModel(modelo);
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtRegistros = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cbxBusquedaRegistrosLista = new javax.swing.JComboBox<>();
        txtbusquedaRegistro = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        btnBuscarRegistro = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Yu Gothic Light", 0, 24)); // NOI18N
        jLabel1.setText("Control de Registros");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        jtRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtRegistros);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 730, 280));

        jLabel2.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jLabel2.setText("Buscar por:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        cbxBusquedaRegistrosLista.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        cbxBusquedaRegistrosLista.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Selecciona>", "Fecha de apartado", "Fecha de entrega", "Nombre de Estudiante", "N. Control", "Titulo", "Autor", "ISBN", "Estado" }));
        getContentPane().add(cbxBusquedaRegistrosLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 140, -1));

        txtbusquedaRegistro.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        getContentPane().add(txtbusquedaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 180, -1));

        btnEliminar.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 70, -1, -1));

        btnBuscarRegistro.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        btnBuscarRegistro.setText("Buscar");
        getContentPane().add(btnBuscarRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, -1, -1));

        btnModificar.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        btnModificar.setText("Modificar");
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 70, -1, -1));

        jButton1.setText("Refrescar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        inicio();
    }//GEN-LAST:event_jButton1ActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListaRegistros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaRegistros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaRegistros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaRegistros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaRegistros().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnBuscarRegistro;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JComboBox<String> cbxBusquedaRegistrosLista;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jtRegistros;
    public javax.swing.JTextField txtbusquedaRegistro;
    // End of variables declaration//GEN-END:variables
}
