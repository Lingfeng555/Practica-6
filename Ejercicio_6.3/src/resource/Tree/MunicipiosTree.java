package resource.Tree;

import javax.swing.JTree;

import resource.DataSet.DataSetMunicipios;

public class MunicipiosTree extends JTree {

    public MunicipiosTree (){
        super(DataSetMunicipios.getDataSetMunicipios().getmunicipiosHashTable());
        this.setEditable(false);
    }
}
