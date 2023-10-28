package resource.Tabla;
import java.util.ArrayList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import resource.DataSet.DataSetMunicipios;
import resource.DataSet.Municipio;
public class Model extends DefaultTableModel implements TablaModel {
    private DataSetMunicipios dataset;

    public Model(Object[][] data, Object[] row, DataSetMunicipios dataset){
        super(data, row);
        this.dataset = dataset;

        addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                //System.out.println("Ha cambiado la table UwU");
            }
        });
    }

    @Override
    public Object getColumClass() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getColumClass'");
    }
    @Override
    public int getColumCount() {
        return super.getColumnCount();
    }
    @Override
    public String getColumName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getColumName'");
    }
    @Override
    public Object getValueAt(int i, int j) {
        return super.getValueAt(i, j);
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        //System.out.println("adjh");
        return column == 1 || column==2 ? true : false;
    }
    
    @Override
    public void setValueAt(Object valeuObject,int i, int j) {
        super.setValueAt(valeuObject, i, j);
        Municipio municipio = new Municipio((int)getValueAt(i, 0), (String)getValueAt(i, 1), (int)getValueAt(i, 2), (String)getValueAt(i, 3), (String)getValueAt(i, 4));
        ArrayList<Municipio> arratMunicipios = (ArrayList<Municipio>) dataset.getListaMunicipios();
        arratMunicipios.remove(i-1);
        dataset.removeMunicipio(municipio);
        switch (j) {
            case 1:
                municipio.setNombre((String)valeuObject);
                break;
            case 2:
                municipio.setHabitantes((int) valeuObject);
                break;
        }
        dataset.addMunicipio(municipio);
        if (municipio.isEmpty()){removeRow(i);}
    }
    @Override
    public void removeTablaModelListener() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeTablaModelListener'");
    }
    
    @Override
    public void addRow(Object[] o){
        Municipio municipio = new Municipio((int)o[0], (String)o[1], (int)o[2], (String)o[3], (String)o[4]);
        super.addRow(o);
        dataset.getListaMunicipios().add(municipio);
        dataset.addMunicipio(municipio);
    }

    @Override
    public void removeRow (int i){
        Municipio municipio = new Municipio((int)getValueAt(i, 0), (String)getValueAt(i, 1), (int)getValueAt(i, 2), (String)getValueAt(i, 3), (String)getValueAt(i, 4));
        dataset.removeMunicipio(municipio);
        System.out.println("borrado");
        super.removeRow(i);
        ArrayList<Municipio> arratMunicipios = (ArrayList<Municipio>) dataset.getListaMunicipios();
        arratMunicipios.remove(i-1);
    }
    public void removePackRow (int[] i,int ini){
        this.removeRow(i[ini]);
        if(i[ini] == i[i.length-1]){return;}
        for (int k = ini+1; k < i.length; k++){
            if (i[k] > i[ini]){i[k]--;}
        }
        removePackRow(i, ini++);
    }
    public void removePackRow (int[] i){
        this.removeRow(i[0]);
        if(i[0] == i[i.length-1]){return;}
        for (int k = 0+1; k < i.length; k++){
            if (i[k] > i[0]){i[k]--;}
        }
        removePackRow(i, 1);
    }

    public void removeAll(){
        try{
            while (getRowCount()!=1) {
                super.removeRow(1);
            }
            super.removeRow(0);
        }catch(java.lang.IndexOutOfBoundsException e){
            //System.err.println("Ha terminado de borrar");
        }
    }

    @Override
    public boolean isCellEditable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isCellEditable'");
    }

    @Override
    public void setValueAt() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setValueAt'");
    }


}
