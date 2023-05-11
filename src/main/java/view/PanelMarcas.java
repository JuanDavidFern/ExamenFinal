package view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import controller.ContinenteController;
import controller.MarcaController;
import model.Continente;
import model.Marca;
import model.Pai;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.stream.IntStream;
import java.awt.event.ActionEvent;

public class PanelMarcas extends JPanel {
	private JTextField jtfId;
	private JTextField jtfMarca;
	private Marca m;
	private JComboBox<Pai> jcbPais;
	private JComboBox<Continente> jcbContinente;

	/**
	 * Create the panel.
	 */
	public PanelMarcas() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0};
//		gridBagLayout.columnWidths = new int[]{0};
//		gridBagLayout.rowHeights = new int[]{0};
//		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
//		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Gestión Marcas");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("id");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 1;
		add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		jtfId = new JTextField();
		jtfId.setEnabled(false);
		GridBagConstraints gbc_jtfId = new GridBagConstraints();
		gbc_jtfId.insets = new Insets(0, 0, 5, 0);
		gbc_jtfId.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfId.gridx = 2;
		gbc_jtfId.gridy = 1;
		add(jtfId, gbc_jtfId);
		jtfId.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Denominación");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		jtfMarca = new JTextField();
		GridBagConstraints gbc_jtfMarca = new GridBagConstraints();
		gbc_jtfMarca.insets = new Insets(0, 0, 5, 0);
		gbc_jtfMarca.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfMarca.gridx = 2;
		gbc_jtfMarca.gridy = 2;
		add(jtfMarca, gbc_jtfMarca);
		jtfMarca.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Continente");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 3;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		jcbContinente = new JComboBox();
		jcbContinente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paises();
			}
		});
		GridBagConstraints gbc_jcbContinente = new GridBagConstraints();
		gbc_jcbContinente.insets = new Insets(0, 0, 5, 0);
		gbc_jcbContinente.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbContinente.gridx = 2;
		gbc_jcbContinente.gridy = 3;
		add(jcbContinente, gbc_jcbContinente);
		
		JLabel lblNewLabel_4 = new JLabel("Pais");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 4;
		add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		jcbPais = new JComboBox();
		GridBagConstraints gbc_jcbPais = new GridBagConstraints();
		gbc_jcbPais.insets = new Insets(0, 0, 5, 0);
		gbc_jcbPais.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbPais.gridx = 2;
		gbc_jcbPais.gridy = 4;
		add(jcbPais, gbc_jcbPais);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
				
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 3;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 6;
		add(btnNewButton, gbc_btnNewButton);
		
		llenar();
		
		jcbContinente.removeAllItems();
		for (Continente c : ContinenteController.findAll()) {
			jcbContinente.addItem(c);
		}
		jcbContinente.setSelectedItem(m.getPai().getContinente());
		

	}
	
	public void guardar() {
		char[] c = jtfMarca.getText().toCharArray();
		if (c.length > 1) {
			m.setDenominacion(jtfMarca.getText());
			Pai p = (Pai) jcbPais.getSelectedItem();
			m.setPai(p);
			MarcaController.update(m);
			Principal.getIstanse().getDialog().dispose();
			Principal.getIstanse().llenarMarcas();
			Principal.getIstanse().getJcbMarcas().setSelectedItem(m);
		}else {
			System.out.println("La denominacion debe tener mas de dos caracteres");
		}
			
		
		
	}
	
	public void paises() {
		jcbPais.removeAllItems();
		Continente c = (Continente) jcbContinente.getSelectedItem();
		for (Pai p : c.getPais()) {
			jcbPais.addItem(p);
		}
		jcbPais.setSelectedItem(m.getPai());
		
	}
	
	public void llenar() {
		m = (Marca) Principal.getIstanse().getJcbMarcas().getSelectedItem();
		jtfId.setText(m.getId() + "");
		jtfMarca.setText(m.getDenominacion());
		
	}
	
	

}
