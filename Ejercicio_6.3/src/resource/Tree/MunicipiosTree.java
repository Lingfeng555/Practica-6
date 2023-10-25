package resource.Tree;

import javax.swing.JTree;

import resource.DataSet.DataSetMunicipios;

public class MunicipiosTree extends JTree {
    private DataSetMunicipios dataSetMunicipios = DataSetMunicipios.getDataSetMunicipios();

    public MunicipiosTree (){
        super(DataSetMunicipios.getDataSetMunicipios().getmunicipiosHashTable());
    }
}
