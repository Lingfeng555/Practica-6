package resource.Tree;

import javax.swing.JScrollPane;

public class PanelMunicipiosTree extends JScrollPane{
    private static MunicipiosTree municipiosTree = new MunicipiosTree();

    public PanelMunicipiosTree (){
        super(municipiosTree);
    }
}
