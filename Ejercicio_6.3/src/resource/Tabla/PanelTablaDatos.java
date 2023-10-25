package resource.Tabla;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.PageAttributes.OrientationRequestedType;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import resource.DataSet.DataSetMunicipios;
import resource.DataSet.Municipio;

public class PanelTablaDatos extends JPanel{
    
    private JScrollPane scrollPane;
    private JTable table;
    private Model model;
    private JButton anadir;
    private JButton borrar;

    public PanelTablaDatos (){

        this.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(setAnadirButton());
        buttonPanel.add(setBorrarButton());
        scrollPane = new JScrollPane(setTable(DataSetMunicipios.getDataSetMunicipios()));
        
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.add(scrollPane, BorderLayout.CENTER);

        this.setVisible(true);
    }

    private void setModel(DataSetMunicipios municipios){
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
        this.model = new Model(lMunicipios, fields, municipios);
    }
    
    private JButton setBorrarButton(){
        borrar = new JButton("borrar");
        borrar.setSize( 100, 50);
        borrar.addActionListener(e -> borrar());
        return borrar;
    }

    private void borrar() {
        int[] indexes = table.getSelectedRows().clone();
        model.removePackRow(indexes);
    }

    private JButton setAnadirButton(){
        anadir = new JButton("Añadir");
        anadir.setSize( 100, 50);
        anadir.addActionListener(e -> anadir());
        return anadir;
    }

    private void anadir() {
        model.addRow(new Object[model.getColumCount()]);
    }

    private JTable setTable(DataSetMunicipios municipios){
        setModel(municipios);
        table = new JTable(model);
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(2).setMaxWidth(150);
        table.getColumnModel().getColumn(2).setCellRenderer(new TableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                JProgressBar progressBar = new JProgressBar(0, 5000000);
                try{
                    progressBar.setValue((Integer)value);  //Al inicio ya ha sido casteado de int a Objeto
                }catch(java.lang.ClassCastException e){
                    progressBar.setValue(Integer.parseInt((String)value));  //Cuando lo modificas swing lo toma como string y si salta el error es por eso
                }
                progressBar.setToolTipText(String.valueOf(progressBar.getValue()));
                progressBar.setString(String.valueOf(progressBar.getValue()));
                progressBar.setStringPainted(true);
                return progressBar;
            }
            
        }); 

        table.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int i = table.getSelectedRow();
                int j = table.getSelectedColumn();
                cleanColumn(j);
                if(table.getColumnName(j).equalsIgnoreCase("Comunidad Autonoma")){
                    changeColumn(j, (String)table.getValueAt(i, j));
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                return;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                return;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                return;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                return;
            }
            
        });

        return table;
    }

    public void changeColumn(int column_index, String nombre){
        System.out.println(nombre);
        table.getColumnModel().getColumn(column_index).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String comparator = (String)table.getValueAt(row, column);
                if(nombre.equalsIgnoreCase(comparator)){
                    component.setBackground(Color.cyan);
                }else{
                    component.setBackground(Color.WHITE);
                }
                return component;
            }
        });
        table.repaint();
    }

    public void cleanColumn(int column_index){
        table.getColumnModel().getColumn(column_index).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                component.setBackground(Color.WHITE);
                return component;
            }
        });
        table.repaint();
    }
}
