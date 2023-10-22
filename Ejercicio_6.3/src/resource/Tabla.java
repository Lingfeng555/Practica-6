package resource;

import javax.swing.JTable;

public class Tabla extends JTable implements TablaModel{

    public Tabla ( Object[][] data, Object[] fields){
        super(data, fields);
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
    public Object getValueAt() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getValueAt'");
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

    @Override
    public void removeTablaModelListener() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeTablaModelListener'");
    }
    
}
