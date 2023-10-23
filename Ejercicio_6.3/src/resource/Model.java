package resource;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class Model extends DefaultTableModel implements TablaModel {
    private DataSetMunicipios dataset;

    public Model(Object[][] data, Object[] row, DataSetMunicipios dataset){
        super(data, row);
        this.dataset = dataset;
    }

    @Override
    public Object getColumClass() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getColumClass'");
    }
    @Override
    public int getColumCount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getColumCount'");
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
    public void setValueAt() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setValueAt'");
    }
    @Override
    public boolean isCellEditable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isCellEditable'");
    }
    @Override
    public void setValueAt(Object valeuObject,int i, int j) {
        super.setValueAt(valeuObject, i, j);
        ArrayList<Municipio> arratMunicipios = (ArrayList<Municipio>) dataset.getListaMunicipios();
        Municipio municipio = arratMunicipios.get(i-1);
        switch (j) {
            case 0:
                municipio.setCodigo(0);
                break;
            case 1:
                municipio.setNombre(null);
                break;
            case 2:
                municipio.setHabitantes(0);
                break;
            case 3:
                municipio.setProvincia(null);
                break;
            case 4:
                municipio.setAutonomia(null);
                break;
        }
        if (municipio.isEmpty()){removeRow(i);}
    }
    @Override
    public void removeTablaModelListener() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeTablaModelListener'");
    }

    public void removeRow (int i){
        super.removeRow(i);
        ArrayList<Municipio> arratMunicipios = (ArrayList<Municipio>) dataset.getListaMunicipios();
        arratMunicipios.remove(i-1);
    }
}
