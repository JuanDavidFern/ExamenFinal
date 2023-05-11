package view;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.x.protobuf.MysqlxCrud.Find;

import controller.MarcaController;
import controller.PortatilController;
import model.Marca;
import model.MiRadio;
import model.Portatil;

import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	public static Principal singuerton = null;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField jtfId;
	private JComboBox<Marca> jcbMarca;
	private JTextField jtfModelo;
	private JTextField jtfNumeroDeSerie;
	private JTextField jtfFechaVenta;
	private JCheckBox check;
	private JButton btnCount;
	private JButton btnPop;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private List<MiRadio> lista = new ArrayList<MiRadio>();
	private JDialog dialogo;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	private Portatil ultimo;

	public static Principal getIstanse() {
		if (singuerton == null)
			singuerton = new Principal();
		return singuerton;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal.getIstanse().setVisible(true);
					singuerton.setMinimumSize(new Dimension(800, 600));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 352);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenuItem btnPri = new JMenuItem("<<");
		btnPri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findFirst();
			}
		});
		menuBar.add(btnPri);

		JMenuItem brnAtr = new JMenuItem("<");
		brnAtr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (PortatilController.findPrevius(Integer.parseInt(jtfId.getText())) != null) {
					findPrevius();
				}

			}
		});
		menuBar.add(brnAtr);

		JMenuItem btnAlan = new JMenuItem(">");
		btnAlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (PortatilController.findNext(Integer.parseInt(jtfId.getText())) != null) {
					findNext();
				}

			}
		});
		menuBar.add(btnAlan);

		JMenuItem btnUlt = new JMenuItem(">>");
		btnUlt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findLast();
			}
		});
		
		menuBar.add(btnUlt);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Nuevo");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crear();
			}
		});
		menuBar.add(mntmNewMenuItem_4);

		JMenuItem btnGuardar = new JMenuItem("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		menuBar.add(btnGuardar);

		JMenuItem btnBorrar = new JMenuItem("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrar();
				findPrevius();
			}
		});
		menuBar.add(btnBorrar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0 };
//		gbl_contentPane.columnWidths = new int[]{0};
//		gbl_contentPane.rowHeights = new int[]{0};
//		gbl_contentPane.columnWeights = new double[]{Double.MIN_VALUE};
//		gbl_contentPane.rowWeights = new double[]{Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		lblNewLabel = new JLabel("Gestión de Ordenadores Portátiles");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 5;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("id");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 3;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		jtfId = new JTextField();
		jtfId.setEnabled(false);
		GridBagConstraints gbc_jtfId = new GridBagConstraints();
		gbc_jtfId.gridwidth = 2;
		gbc_jtfId.insets = new Insets(0, 0, 5, 0);
		gbc_jtfId.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfId.gridx = 3;
		gbc_jtfId.gridy = 1;
		contentPane.add(jtfId, gbc_jtfId);
		jtfId.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Marca");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 3;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);

		jcbMarca = new JComboBox<Marca>();
		GridBagConstraints gbc_jcbMarca = new GridBagConstraints();
		gbc_jcbMarca.insets = new Insets(0, 0, 5, 5);
		gbc_jcbMarca.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbMarca.gridx = 3;
		gbc_jcbMarca.gridy = 2;
		contentPane.add(jcbMarca, gbc_jcbMarca);

		btnPop = new JButton("Ver marca");
		btnPop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirNuevoDialogo();
			}
		});
		GridBagConstraints gbc_btnPop = new GridBagConstraints();
		gbc_btnPop.insets = new Insets(0, 0, 5, 0);
		gbc_btnPop.gridx = 4;
		gbc_btnPop.gridy = 2;
		contentPane.add(btnPop, gbc_btnPop);

		JLabel lblNewLabel_3 = new JLabel("Modelo");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.gridwidth = 3;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);

		jtfModelo = new JTextField();
		GridBagConstraints gbc_jtfModelo = new GridBagConstraints();
		gbc_jtfModelo.gridwidth = 2;
		gbc_jtfModelo.insets = new Insets(0, 0, 5, 0);
		gbc_jtfModelo.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfModelo.gridx = 3;
		gbc_jtfModelo.gridy = 3;
		contentPane.add(jtfModelo, gbc_jtfModelo);
		jtfModelo.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Serial number");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.gridwidth = 3;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 4;
		contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);

		jtfNumeroDeSerie = new JTextField();
		GridBagConstraints gbc_jtfNumeroDeSerie = new GridBagConstraints();
		gbc_jtfNumeroDeSerie.gridwidth = 2;
		gbc_jtfNumeroDeSerie.insets = new Insets(0, 0, 5, 0);
		gbc_jtfNumeroDeSerie.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfNumeroDeSerie.gridx = 3;
		gbc_jtfNumeroDeSerie.gridy = 4;
		contentPane.add(jtfNumeroDeSerie, gbc_jtfNumeroDeSerie);
		jtfNumeroDeSerie.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Num procesadores");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.gridwidth = 3;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 5;
		contentPane.add(lblNewLabel_5, gbc_lblNewLabel_5);

		MiRadio rdbtnNewRadioButton = new MiRadio("1 Procesador", 1);
		buttonGroup.add(rdbtnNewRadioButton);
		lista.add(rdbtnNewRadioButton);
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton.gridx = 3;
		gbc_rdbtnNewRadioButton.gridy = 5;
		contentPane.add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);

		MiRadio rdbtnNewRadioButton_1 = new MiRadio("2 Procesadores", 2);
		buttonGroup.add(rdbtnNewRadioButton_1);
		lista.add(rdbtnNewRadioButton_1);
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNewRadioButton_1.gridx = 4;
		gbc_rdbtnNewRadioButton_1.gridy = 5;
		contentPane.add(rdbtnNewRadioButton_1, gbc_rdbtnNewRadioButton_1);

		MiRadio rdbtnNewRadioButton_2 = new MiRadio("3 Procesadores", 3);
		buttonGroup.add(rdbtnNewRadioButton_2);
		lista.add(rdbtnNewRadioButton_2);
		GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton_2.gridx = 3;
		gbc_rdbtnNewRadioButton_2.gridy = 6;
		contentPane.add(rdbtnNewRadioButton_2, gbc_rdbtnNewRadioButton_2);

		MiRadio rdbtnNewRadioButton_3 = new MiRadio("4 Procesadores", 4);
		buttonGroup.add(rdbtnNewRadioButton_3);
		lista.add(rdbtnNewRadioButton_3);
		GridBagConstraints gbc_rdbtnNewRadioButton_3 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_3.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNewRadioButton_3.gridx = 4;
		gbc_rdbtnNewRadioButton_3.gridy = 6;
		contentPane.add(rdbtnNewRadioButton_3, gbc_rdbtnNewRadioButton_3);

		JLabel lblNewLabel_6 = new JLabel("Vendido");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.gridwidth = 3;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 7;
		contentPane.add(lblNewLabel_6, gbc_lblNewLabel_6);

		check = new JCheckBox("");
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check.isSelected()) {
					jtfFechaVenta.setEnabled(true);
				} else {
					jtfFechaVenta.setEnabled(false);
				}

			}
		});
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.anchor = GridBagConstraints.WEST;
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox.gridx = 3;
		gbc_chckbxNewCheckBox.gridy = 7;
		contentPane.add(check, gbc_chckbxNewCheckBox);

		JLabel lblNewLabel_7 = new JLabel("Fecha venta");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 1;
		gbc_lblNewLabel_7.gridy = 8;
		contentPane.add(lblNewLabel_7, gbc_lblNewLabel_7);

		jtfFechaVenta = new JTextField();
		GridBagConstraints gbc_jtfFechaVenta = new GridBagConstraints();
		gbc_jtfFechaVenta.insets = new Insets(0, 0, 5, 0);
		gbc_jtfFechaVenta.gridwidth = 2;
		gbc_jtfFechaVenta.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfFechaVenta.gridx = 3;
		gbc_jtfFechaVenta.gridy = 8;
		contentPane.add(jtfFechaVenta, gbc_jtfFechaVenta);
		jtfFechaVenta.setColumns(10);

		btnCount = new JButton("Número total portatiles");
		btnCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCont();
			}
		});
		GridBagConstraints gbc_btnCount = new GridBagConstraints();
		gbc_btnCount.insets = new Insets(0, 0, 0, 5);
		gbc_btnCount.gridx = 3;
		gbc_btnCount.gridy = 9;
		contentPane.add(btnCount, gbc_btnCount);

		llenarMarcas();
		findFirst();
	}

	public void borrar() {
		PortatilController.remove(ultimo);
	}

	public void crear() {
		jtfFechaVenta.setText("");
		jtfId.setText("");
		jtfModelo.setText("");
		jtfNumeroDeSerie.setText("");
		check.setSelected(false);
		for (MiRadio miRadio : lista) {
			miRadio.setSelected(false);
		}

	}

	public void guardar() {
		Portatil p = new Portatil();

		Marca m = (Marca) jcbMarca.getSelectedItem();
		p.setMarca(m);
		p.setSn(jtfNumeroDeSerie.getText());
		p.setModelo(jtfModelo.getText());
		for (MiRadio miRadio : lista) {
			if (miRadio.isSelected()) {
				p.setNumProcesadores(miRadio.getI());
			}
		}
		p.setVendido(check.isSelected());
		if (check.isSelected()) {
			try {
				p.setFechaVenta(sdf.parse(jtfFechaVenta.getText()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (!jtfId.getText().isBlank()) {
			p.setId(Integer.parseInt(jtfId.getText()));
			PortatilController.update(p);
		} else {
			PortatilController.insert(p);

		}

	}

	public void btnCont() {
		JOptionPane.showInternalMessageDialog(null, "Total de portátiles:" + PortatilController.count());
	}

	public void llenarMarcas() {
		jcbMarca.removeAllItems();
		for (Marca m : MarcaController.findAll()) {
			jcbMarca.addItem(m);
		}

	}

	public void despuesDeEncontrar(Portatil p) {
		jtfId.setText(p.getId() + "");
		jcbMarca.setSelectedItem(p.getMarca());
		jtfModelo.setText(p.getModelo());
		jtfNumeroDeSerie.setText(p.getSn());
		for (MiRadio miRadio : lista) {
			if (p.getNumProcesadores() == miRadio.getI()) {
				miRadio.setSelected(true);
			}
		}
		if (p.getVendido()) {
			check.setSelected(true);
		} else {
			check.setSelected(false);
		}
		if (p.getFechaVenta() != null) {
			jtfFechaVenta.setText(p.getFechaVenta() + "");
		}
		if (check.isSelected()) {
			jtfFechaVenta.setEnabled(true);
		} else {
			jtfFechaVenta.setEnabled(false);
		}
		ultimo = p;
		System.out.println(ultimo.toString());
	}

	public void findFirst() {
		Portatil p = PortatilController.findFirst();
		despuesDeEncontrar(p);

	}

	public void findLast() {
		Portatil p = PortatilController.findLast();
		despuesDeEncontrar(p);
	}

	public void findNext() {
		Portatil p = PortatilController.findNext(Integer.parseInt(jtfId.getText()));
		despuesDeEncontrar(p);

	}

	public void findPrevius() {
		Portatil p = PortatilController.findPrevius(Integer.parseInt(jtfId.getText()));
		despuesDeEncontrar(p);

	}

	public void abrirNuevoDialogo() {
		dialogo = new JDialog();
		// El usuario no puede redimensionar el di�logo
		dialogo.setResizable(true);
		// t�tulo del d�alogo
		dialogo.setTitle("Gestión de empresas");
		// Introducimos el panel creado sobre el di�logo
		dialogo.setContentPane(new PanelMarcas());
		// Empaquetar el di�logo hace que todos los componentes ocupen el espacio que
		// deben y el lugar adecuado
		dialogo.pack();
		// El usuario no puede hacer clic sobre la ventana padre, si el Di�logo es modal
		dialogo.setModal(true);
		// Centro el di�logo en pantalla
		dialogo.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - dialogo.getWidth() / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - dialogo.getHeight() / 2);
		// Muestro el di�logo en pantalla
		dialogo.setVisible(true);
	}

	public JDialog getDialog() {
		return dialogo;
	}

	public JComboBox<Marca> getJcbMarcas() {
		return jcbMarca;
	}

}
