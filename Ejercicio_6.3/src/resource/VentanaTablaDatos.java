package resource;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

public class VentanaTablaDatos extends JFrame{
    private JScrollPane scrollPane;
    private JTable table;
    private JButton anadir;
    private JButton borrar;
    private JButton modificar;
    private JFrame ventanaCarga;

    public VentanaTablaDatos (DataSetMunicipios municipios, JFrame ventanaCarga){
        this.setLayout(new BorderLayout());
        this.setSize(600, 600);
        this.setTitle("CRUD MUNICIPIOS");
        
        this.ventanaCarga = ventanaCarga;

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(setAnadirButton());
        buttonPanel.add(setBorrarButton());
        scrollPane = new JScrollPane(setTable(municipios));
        
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.add(scrollPane, BorderLayout.CENTER);

        this.setVisible(true);

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                close();
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }

    private void close() {
        ventanaCarga.setVisible(true);;
    }
    
    private JButton setBorrarButton(){
        borrar = new JButton("borrar");
        borrar.setSize( 100, 50);
        borrar.addActionListener(e -> borrar());
        return borrar;
    }

    private void borrar() {
        int [] indexes = table.getSelectedRows().clone();
        for (int i: indexes){
            for (int j = 0; j <5; j++){
                System.out.println(indexes);
                table.setValueAt(null, i, j);
            }
        }
    }

    private JButton setAnadirButton(){
        anadir = new JButton("Añadir");
        anadir.setSize( 100, 50);
        anadir.addActionListener(e -> anadir());
        return anadir;
    }

    private void anadir() {
        System.out.println("patata");
    }

    private JTable setTable(DataSetMunicipios municipios){
        Object[] fields = {"ID", "Municipio", "Poblacion", "Provincia", "Comunidad Autonoma"};
        ArrayList<Municipio> arrayMunicipios = new ArrayList<>(municipios.getListaMunicipios());
        Object[][] lMunicipios = new Object[arrayMunicipios.size()][5];
        for (int i = 0; i<arrayMunicipios.size(); i++){
            lMunicipios[i][0] = arrayMunicipios.get(i).getCodigo();
            lMunicipios[i][1] = arrayMunicipios.get(i).getNombre();
            lMunicipios[i][2] = arrayMunicipios.get(i).getHabitantes();
            lMunicipios[i][3] = arrayMunicipios.get(i).getProvincia();
            lMunicipios[i][4] = arrayMunicipios.get(i).getAutonomia();
        }
        table = new Tabla(lMunicipios, fields);
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(2).setMaxWidth(150);
        return table;
    }
}
