
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

import resource.DataSetMunicipios;
import resource.Municipio;
import resource.VentanaTablaDatos;

public class Ejercicio06_03 {
	
	private static DataSetMunicipios dataset;
	public static JFrame ventana;
	public static void main(String[] args) {
		ventana = new JFrame( "Ejercicio 6.3" );
		ventana.setLocationRelativeTo( null );
		ventana.setSize( 200, 80 );
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton bCargaMunicipios = new JButton( "Carga municipios > 200k" );
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
			dataset = new DataSetMunicipios( "municipios200k.txt" );
			//System.out.println( "Cargados municipios:" );
			for (Municipio m : dataset.getListaMunicipios() ) {
				//System.out.println( "\t" + m );
			}
			new VentanaTablaDatos(dataset, ventana);
			// TODO Resolver el ejercicio 6.3
		} catch (IOException e) {
			System.err.println( "Error en carga de municipios" );
		}
	}
}
