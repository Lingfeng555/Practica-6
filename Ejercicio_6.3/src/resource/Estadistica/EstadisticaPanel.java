package resource.Estadistica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.*;
import javax.swing.text.Position;

import resource.VentanaTree;
import resource.DataSet.DataSetMunicipios;
import resource.DataSet.Municipio;
import resource.Tree.MunicipiosTree;
import resource.Tree.PanelMunicipiosTree;

public class EstadisticaPanel extends JPanel{
    DataSetMunicipios dataSet;
    Graphics2D graphics2d;
    VentanaTree parent;
    final int BarraEstado = 700;
    String[] currentNodePath;

    public EstadisticaPanel(VentanaTree parent){
        super();
        dataSet = DataSetMunicipios.getDataSetMunicipios();
        this.parent = parent;
        this.setPreferredSize(new Dimension(500, 800));
    }


    @Override
    public void paint(Graphics g){

        graphics2d = (Graphics2D) g;
        int tamañoPoblacionEstado = BarraEstado;
        int anchuraDebarras = 100;
        int posicionX = 100;
        int posicionY = this.getPreferredSize().height - tamañoPoblacionEstado;

        int posicionXComparador = posicionX+200;
        int posicionYComparador = this.getPreferredSize().height - getBarraComparadorTamaño(currentNodePath);
        
        graphics2d.drawString("Total España", posicionX, posicionY-5);
        graphics2d.fillRect(posicionX, posicionY, anchuraDebarras, tamañoPoblacionEstado);

        if(currentNodePath == null){return;}
        graphics2d.drawString(currentNodePath[0], posicionXComparador, posicionYComparador-5);
        drawBarraComparador(graphics2d, posicionXComparador, posicionYComparador, anchuraDebarras, getBarraComparadorTamaño(currentNodePath), currentNodePath);
    }

    public void ActualizarBarras(){
        System.out.println("Se actualiza");
        currentNodePath = parent.getSuperiorLabel().getText().split("/");
        parent.repaint();
    }

    private int getComparadorPoblacion(String[] nodePath){
        if(nodePath == null || nodePath.length == 1){
            return 0; 
        }
        switch (nodePath.length) {
            case 2:
                return dataSet.getAutonomiaPoblacion(nodePath[0]);
            case 3:
                return dataSet.getProvinciaPoblacion(nodePath[1], nodePath[0]);
            case 4:
                return dataSet.getHashTableForTree().get(nodePath[2]).get(nodePath[1]).get(nodePath[0]).getHabitantes();
        }
        return 0;
    }

    private int getBarraComparadorTamaño(String[] nodePath){
        if (dataSet.getPoblacionTotal() == 0){
            return BarraEstado;
        }
        return (int) Math.round((BarraEstado*getComparadorPoblacion(nodePath))/dataSet.getPoblacionTotal());
    }

    private int getMunicipioBarraTamaño(int municipiosHabitantes){
        return (int) Math.round((BarraEstado*municipiosHabitantes)/dataSet.getPoblacionTotal());
    }

    private void drawBarraComparador(Graphics2D graphics2d ,int posicionX, int posicionY, int anchuraDebarras, int TamañoTotal, String[] name){
        if(name == null){ return;}
        parent.getPanelMunicipiosTree();
        TreeSet<Municipio> municipios = PanelMunicipiosTree.getMunicipiosTree().getMunicipiosInNode(name);

        int y = posicionY; 
        for(Municipio municipio: municipios){
            int proporcionDeMunicipio = getMunicipioBarraTamaño(municipio.getHabitantes());
            if (proporcionDeMunicipio==0){proporcionDeMunicipio++;} //Para los municipios con menos abitantes puede dar 0 al redondear, para que no ocuurra una barra flotante lo capamos a un minimo de 1
            Color color = new Color((int)Math.round(Math.random()*255), (int)Math.round(Math.random()*255), (int)Math.round(Math.random()*255));
            graphics2d.setColor(color);
            graphics2d.drawRect(posicionX, y, anchuraDebarras, proporcionDeMunicipio);
            y+=proporcionDeMunicipio;
        }
    }
}
