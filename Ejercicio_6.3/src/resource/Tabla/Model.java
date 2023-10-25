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
                System.out.println("Ha cambiado la table UwU");
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
    public void removePackRow (int[] i,int ini){
        super.removeRow(i[ini]);
        if(i[ini] == i[i.length-1]){return;}
        for (int k = ini+1; k < i.length; k++){

            if (i[k] > i[ini]){i[k]--;}

        }
        removePackRow(i, ini++);
    }
    public void removePackRow (int[] i){
        super.removeRow(i[0]);
        if(i[0] == i[i.length-1]){return;}
        for (int k = 0+1; k < i.length; k++){

            if (i[k] > i[0]){i[k]--;}

        }
        removePackRow(i, 1);
    }
}
