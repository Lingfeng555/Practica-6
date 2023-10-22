package resource;

public interface TablaModel {
    Object getColumClass();
    int getColumCount();
    int getRowCount();
    String getColumName();
    Object getValueAt();
    boolean isCellEditable();
    void setValueAt();
    void removeTablaModelListener();
}
