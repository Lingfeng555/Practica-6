package resource;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import resource.Estadistica.EstadisticaPanel;
import resource.Tabla.PanelTablaDatos;
import resource.Tree.PanelMunicipiosTree;

public class VentanaTree extends JFrame {
    private PanelTablaDatos panelTablaDatos;
    private PanelMunicipiosTree panelMunicipiosTree;
    private EstadisticaPanel panelEstadistica;
    private JLabel superiorLabel;

    public VentanaTree (){
        this.setSize(800, 800);
        this.setTitle("Practica6");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.panelMunicipiosTree = new PanelMunicipiosTree(this);
        this.panelTablaDatos = new PanelTablaDatos(this);
        this.panelEstadistica = new EstadisticaPanel(this);

        this.add(panelMunicipiosTree, BorderLayout.WEST);
        this.add(panelTablaDatos, BorderLayout.CENTER);
        this.add(panelEstadistica, BorderLayout.EAST);

        superiorLabel = new JLabel("Aqui en españa nos gusta el jamon");
        superiorLabel.setSize(1920, 1080);
        this.add(superiorLabel, BorderLayout.NORTH);

        this.pack();
        this.setVisible(true);
    }

    public PanelTablaDatos getPanelTablaDatos() {
        return panelTablaDatos;
    }

    public PanelMunicipiosTree getPanelMunicipiosTree() {
        return panelMunicipiosTree;
    }

    public JLabel getSuperiorLabel() {
        return superiorLabel;
    }

    public EstadisticaPanel getPanelEstadistica() {
        return panelEstadistica;
    }
}