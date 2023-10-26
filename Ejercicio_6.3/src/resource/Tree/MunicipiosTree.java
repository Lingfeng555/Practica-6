package resource.Tree;

import java.util.Hashtable;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import resource.VentanaTree;
import resource.DataSet.DataSetMunicipios;
import resource.DataSet.Municipio;

public class MunicipiosTree extends JTree {
    private VentanaTree parent;

    public MunicipiosTree (VentanaTree parent){
        super(DataSetMunicipios.getDataSetMunicipios().getHashTableForTree());
        this.parent = parent;
        this.setEditable(false);

        this.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                String[] nodeName = getNodeNameByPath(e);
                TreeSet<Municipio> municipios = getMunicipiosInNode(nodeName);
                parent.getPanelTablaDatos().changeTable(municipios);
            }
        });
    }

    private String[] getNodeNameByPath(TreeSelectionEvent e){
        String[] names = new String[e.getPath().getPathCount()];
        TreePath treePath = e.getPath();
        int i = 0;
        while (treePath != null) {
            names[i] = treePath.getLastPathComponent().toString();
            treePath = treePath.getParentPath();
            i++;
        }
        return(  names );
    }

    private TreeSet<Municipio> getMunicipiosInNode(String[] name){
        DataSetMunicipios dataset = DataSetMunicipios.getDataSetMunicipios();

        switch (name.length) {
            case 4:
                //System.out.println(dataset.getHashTableForTree());
                Municipio municipio = dataset.getHashTableForTree().get(name[2]).get(name[1]).get(name[0]);
                TreeSet<Municipio> municipios = new TreeSet<Municipio>();
                municipios.add(municipio);
                return municipios;
            case 3:
                return dataset.getProvinciaHashTable(name[1], name[0]);
            case 2:
                TreeSet<Municipio> municipiosTotal = new TreeSet<>();
                //System.out.println(name[0]);
                Hashtable <String, TreeSet<Municipio>> autonomia = dataset.getAutonomiaHasTable(name[0]);
                Set<String> provinciasTotales = autonomia.keySet();
                for(String provincia: provinciasTotales){
                    municipiosTotal.addAll(autonomia.get(provincia));
                }
                return municipiosTotal;
        }
        return null;
    }
}
