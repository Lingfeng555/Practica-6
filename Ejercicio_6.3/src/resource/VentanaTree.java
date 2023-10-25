package resource;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import resource.Tabla.PanelTablaDatos;
import resource.Tree.PanelMunicipiosTree;

public class VentanaTree extends JFrame {
    private PanelTablaDatos panelTablaDatos;
    private PanelMunicipiosTree panelMunicipiosTree;
    private JLabel superiorLabel;

    public VentanaTree (){
        this.setSize(800, 800);
        this.setTitle("Practica6");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.panelMunicipiosTree = new PanelMunicipiosTree();
        this.panelTablaDatos = new PanelTablaDatos();
        this.add(panelMunicipiosTree, BorderLayout.WEST);
        this.add(panelTablaDatos, BorderLayout.CENTER);

        superiorLabel = new JLabel("Aqui en españa nos gusta el jamon");
        superiorLabel.setSize(800, 100);
        this.add(superiorLabel, BorderLayout.NORTH);

        this.setVisible(true);
    }
}