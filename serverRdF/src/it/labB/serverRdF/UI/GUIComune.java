package it.labB.serverRdF.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUIComune {
	private JFrame jfr;
	private Color background = new Color(37, 60, 238);
	private Color testo = new Color(189, 200, 194);
	private Color testobackground = new Color(18, 23, 16);
	private Color giallo = new Color(195, 164, 22);
	private Color rosa = new Color(202, 126, 130);
	private Color blu = new Color(0, 175, 146);
	private JLabel llogo;
	private ImageIcon ilogo;

	public JFrame panel(JFrame jfr) {
		jfr.getContentPane().setBackground(background);
		jfr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// jfr.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// frmAccediAlServer.setUndecorated(true);
		jfr.setVisible(true);
		return jfr;
	}

	public JLabel label(JLabel label, String string) {
		label.setText(string);
		label.setForeground(testo);
		label.setFont(new Font("Arial", Font.BOLD, 16));
		return label;
	}

	public JLabel label_eccezione(JLabel label, String string) {
		label.setText(string);
		label.setForeground(Color.white);
		label.setFont(new Font("Arial", Font.BOLD, 16));
		return label;
	}

	public JTextField textfield_concolore(JTextField field, Color color) {
		field.setForeground(testobackground);
		field.setBackground(color);
		field.setFont(new Font("Arial", Font.BOLD, 14));
		field.setColumns(10);
		return field;
	}

	public JTextField textfield(JTextField field) {
		field.setForeground(testobackground);
		field.setBackground(testo);
		field.setFont(new Font("Arial", Font.BOLD, 14));
		field.setColumns(10);
		return field;
	}

	public JPasswordField passwordfield_concolore(JPasswordField field, Color color) {
		field.setForeground(testobackground);
		field.setBackground(color);
		field.setFont(new Font("Arial", Font.BOLD, 14));
		field.setColumns(10);
		return field;
	}

	public JPasswordField passwordfield(JPasswordField field) {
		field.setForeground(testobackground);
		field.setBackground(testo);
		field.setFont(new Font("Arial", Font.BOLD, 14));
		field.setColumns(10);
		return field;
	}

	public JButton button(JButton button, String string) {
		button.setText(string);
		button.setForeground(Color.DARK_GRAY);
		button.setBackground(testo);
		button.setFont(new Font("Arial", Font.BOLD, 11));
		return button;
	}

	public JLabel logo() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getClassLoader().getResource("images/rdflogo2.png"));			
		} catch (IOException e) {
			e.printStackTrace();
		}
		ilogo = new ImageIcon(img);
		llogo = new JLabel("");
		llogo.setIcon(ilogo);
		llogo.setBounds(10, 10, 100, 100);
		return llogo;
	}

	public JFrame getJfr() {
		return jfr;
	}

	public void setJfr(JFrame jfr) {
		this.jfr = jfr;
	}

	public Color getGiallo() {
		return giallo;
	}

	public void setGiallo(Color giallo) {
		this.giallo = giallo;
	}

	public Color getRosa() {
		return rosa;
	}

	public void setRosa(Color rosa) {
		this.rosa = rosa;
	}

	public Color getBlu() {
		return blu;
	}

	public void setBlu(Color blu) {
		this.blu = blu;
	}
}
