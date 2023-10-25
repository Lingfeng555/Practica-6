package resource;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Panel;

import javax.swing.JFrame;

import resource.Tabla.PanelTablaDatos;
import resource.Tree.PanelMunicipiosTree;

public class VentanaTree extends JFrame {
    private PanelTablaDatos panelTablaDatos;
    private PanelMunicipiosTree panelMunicipiosTree;

    public VentanaTree (){
        this.setSize(800, 800);
        this.setTitle("Practica6");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.panelMunicipiosTree = new PanelMunicipiosTree();
        this.panelTablaDatos = new PanelTablaDatos();

        this.add(panelMunicipiosTree, BorderLayout.WEST);
        this.add(panelTablaDatos, BorderLayout.CENTER);

        this.setVisible(true);
    }
}
