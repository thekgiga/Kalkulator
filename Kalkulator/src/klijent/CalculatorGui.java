package klijent;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JLabel;

public class CalculatorGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	static String tempRezultat = "";
	static String komanda = "";
	static String konacanRezultat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculatorGui frame = new CalculatorGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CalculatorGui() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 241, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[50][50][50][50]", "[35][][30][30][45.00][45][45][45]"));

		JComboBox comboBox = new JComboBox();
		comboBox.addFocusListener(new FocusAdapter() {

			public void focusGained(FocusEvent arg0) {
				if (postojiZnakUStringu(tempRezultat)) {
					comboBox.setEnabled(false);
				}
			}
		});

		JLabel lblIzaberiteOperaciju = new JLabel("Izaberite operaciju :");
		lblIzaberiteOperaciju.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel.add(lblIzaberiteOperaciju, "cell 0 1 2 1");

		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Sabiranje", "Oduzimanje", "Mnozenje", "Deljenje" }));
		comboBox.setToolTipText("Potrebno je odabrati operaciju, pre pocetka racunanja.");
		panel.add(comboBox, "cell 0 2 4 1,grow");

		textField = new JTextField();
		textField.setBackground(new Color(255, 255, 255));
		textField.setEditable(false);
		textField.setText(tempRezultat);
		textField.setHorizontalAlignment(SwingConstants.RIGHT);

		panel.add(textField, "cell 0 0 4 1,grow");
		textField.setColumns(10);

		JButton button_7 = new JButton("7");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempRezultat = tempRezultat + "7";
				textField.setText(tempRezultat);
			}
		});

		JButton btnNewButton_4 = new JButton("AC");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tempRezultat = ukloniSve(tempRezultat);
				comboBox.setEnabled(true);
				textField.setText(tempRezultat);
			}
		});
		panel.add(btnNewButton_4, "cell 0 3 2 1,growx");

		JButton btnNewButton_5 = new JButton("DEL");
		btnNewButton_5.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				tempRezultat = ukloniPoslednjiKarakter(tempRezultat);

				// if(tempRezultat.equals("") || tempRezultat.equals(" ")){
				// comboBox.setEnabled(true);
				// }
				if (postojiZnakUStringu(tempRezultat)) {
					comboBox.setEnabled(false);
				} else {
					comboBox.setEnabled(true);
				}
				textField.setText(tempRezultat);

			}
		});
		panel.add(btnNewButton_5, "cell 2 3 2 1,growx");
		button_7.setBackground(new Color(204, 255, 204));
		panel.add(button_7, "cell 0 4,grow");

		JButton button_8 = new JButton("8");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempRezultat = tempRezultat + "8";
				textField.setText(tempRezultat);
			}
		});
		button_8.setBackground(new Color(204, 255, 204));
		panel.add(button_8, "cell 1 4,grow");

		JButton button_9 = new JButton("9");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempRezultat = tempRezultat + "9";
				textField.setText(tempRezultat);
			}
		});
		button_9.setBackground(new Color(204, 255, 204));
		panel.add(button_9, "cell 2 4,grow");

		JButton btnNewButton = new JButton("/");
		btnNewButton.setBackground(new Color(204, 204, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem().toString().equals("Deljenje") && (!tempRezultat.contains("-")
						|| !tempRezultat.contains("+") || !tempRezultat.contains("*"))) {
					tempRezultat = tempRezultat + "/";
					tempRezultat = dvaZnaka(tempRezultat, "//");
					textField.setText(tempRezultat);
				} else {
					JOptionPane.showMessageDialog(null, "Dozvoljeno je unosenje samo jednog tipa operacija!");

				}
			}
		});
		panel.add(btnNewButton, "cell 3 4,grow");

		JButton button_4 = new JButton("4");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempRezultat = tempRezultat + "4";
				textField.setText(tempRezultat);
			}
		});
		button_4.setBackground(new Color(204, 255, 204));
		panel.add(button_4, "cell 0 5,grow");

		JButton button_5 = new JButton("5");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempRezultat = tempRezultat + "5";
				textField.setText(tempRezultat);
			}
		});
		button_5.setBackground(new Color(204, 255, 204));
		panel.add(button_5, "cell 1 5,grow");

		JButton button_6 = new JButton("6");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempRezultat = tempRezultat + "6";
				textField.setText(tempRezultat);
			}
		});
		button_6.setBackground(new Color(204, 255, 204));
		panel.add(button_6, "cell 2 5,grow");

		JButton btnNewButton_1 = new JButton("*");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().toString().equals("Mnozenje") && (!tempRezultat.contains("-")
						|| !tempRezultat.contains("/") || !tempRezultat.contains("+"))) {
					tempRezultat = tempRezultat + "*";
					tempRezultat = dvaZnaka(tempRezultat, "**");
					textField.setText(tempRezultat);
				} else {
					JOptionPane.showMessageDialog(null, "Dozvoljeno je unosenje samo jednog tipa operacija!");

				}
			}
		});
		btnNewButton_1.setBackground(new Color(204, 204, 255));
		panel.add(btnNewButton_1, "cell 3 5,grow");

		JButton button_1 = new JButton("1");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempRezultat = tempRezultat + "1";
				textField.setText(tempRezultat);
			}
		});
		button_1.setBackground(new Color(204, 255, 204));
		panel.add(button_1, "cell 0 6,grow");

		JButton button_2 = new JButton("2");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempRezultat = tempRezultat + "2";
				textField.setText(tempRezultat);
			}
		});
		button_2.setBackground(new Color(204, 255, 204));
		panel.add(button_2, "cell 1 6,grow");

		JButton button_3 = new JButton("3");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempRezultat = tempRezultat + "3";
				textField.setText(tempRezultat);
			}
		});
		button_3.setBackground(new Color(204, 255, 204));
		panel.add(button_3, "cell 2 6,grow");

		JButton btnNewButton_2 = new JButton("-");
		btnNewButton_2.setBackground(new Color(204, 204, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().toString().equals("Oduzimanje") && (!tempRezultat.contains("+")
						|| !tempRezultat.contains("/") || !tempRezultat.contains("*"))) {
					tempRezultat = tempRezultat + "-";
					tempRezultat = dvaZnaka(tempRezultat, "--");
					textField.setText(tempRezultat);
				} else {
					JOptionPane.showMessageDialog(null, "Dozvoljeno je unosenje samo jednog tipa operacija!");

				}
			}
		});
		panel.add(btnNewButton_2, "cell 3 6,grow");

		JButton button_0 = new JButton("0");
		button_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				tempRezultat = tempRezultat + "0";
				textField.setText(tempRezultat);
			}
		});

		button_0.setBackground(new Color(204, 255, 204));
		panel.add(button_0, "cell 0 7,grow");

		JButton btnIzracunaj = new JButton("Izracunaj");
		btnIzracunaj.setBackground(new Color(255, 102, 102));
		btnIzracunaj.setForeground(new Color(0, 0, 0));
		btnIzracunaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				komanda = (String) comboBox.getSelectedItem();

			}
		});
		panel.add(btnIzracunaj, "cell 1 7 2 1,grow");

		JButton btnNewButton_3 = new JButton("+");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().toString().equals("Sabiranje") && (!tempRezultat.contains("-")
						|| !tempRezultat.contains("/") || !tempRezultat.contains("*"))) {
					tempRezultat = tempRezultat + "+";
					tempRezultat = dvaZnaka(tempRezultat, "++");
					textField.setText(tempRezultat);
				} else {
					JOptionPane.showMessageDialog(null, "Dozvoljeno je unosenje samo jednog tipa operacija!");

				}
			}
		});
		btnNewButton_3.setBackground(new Color(204, 204, 255));
		panel.add(btnNewButton_3, "cell 3 7,grow");

	}

	// METODE METODE METODE METODE METODE METODE METODE METODE METODE METODE
	// METODE

	public static String ukloniPoslednjiKarakter(String str) {
		if (str == null || str.equals("") || str.equals(" ")) {
			return str;
		}
		return str.substring(0, str.length() - 1);
	}

	public static String ukloniSve(String str) {
		if (str == null || str.equals("") || str.equals(" ")) {
			return str;
		}
		return "";
	}

	public static boolean postojiZnakUStringu(String str) {
		if (!(str.contains("+") || str.contains("-") || str.contains("/") || str.contains("*"))) {
			return false;
		}
		return true;
	}

	public static String dvaZnaka(String tekst, String znak) {

		if (tekst.endsWith(znak)) {
			return tekst.substring(0, tekst.length() - 1);
		}
		return tekst;
	}
}
