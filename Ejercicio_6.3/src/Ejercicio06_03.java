
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

import resource.VentanaTree;
import resource.DataSet.DataSetMunicipios;

public class Ejercicio06_03 {
	
	private static DataSetMunicipios dataset;
	public static JFrame ventana;
	public static void main(String[] args) {
		ventana = new JFrame( "Ejercicio 6.3" );
		ventana.setLocationRelativeTo( null );
		ventana.setSize( 200, 80 );
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton bCargaMunicipios = new JButton( "Carga municipios" );
		ventana.add( bCargaMunicipios );
		
		bCargaMunicipios.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargaMunicipios();
			}
		});
		ventana.setVisible( true );
	}

	private static void cargaMunicipios() {
		ventana.setVisible(false);
		try {
			dataset = new DataSetMunicipios( "Ejercicio_6.3/src/Datos/DatosMunicipios.csv" );
			//System.out.println(dataset.getPoblacionTotal());
			new VentanaTree();
			// TODO Resolver el ejercicio 6.3
		} catch (IOException e) {
			System.err.println( "Error en carga de municipios" );
		}
	}
}
