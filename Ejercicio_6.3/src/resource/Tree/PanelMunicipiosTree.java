package resource.Tree;

import javax.swing.JScrollPane;

import resource.VentanaTree;

public class PanelMunicipiosTree extends JScrollPane{
    private static MunicipiosTree municipiosTree;
    private VentanaTree parent;
    public PanelMunicipiosTree (VentanaTree ventanaTree){
        super(municipiosTree = new MunicipiosTree(ventanaTree));
        parent = ventanaTree;
    }
    public static MunicipiosTree getMunicipiosTree() {
        return municipiosTree;
    }
}
