package resource.Tabla;

public interface TablaModel {
    Object getColumClass();
    int getColumCount();
    int getRowCount();
    String getColumName();
    Object getValueAt(int i, int j);
    boolean isCellEditable();
    void setValueAt();
    void removeTablaModelListener();
}
