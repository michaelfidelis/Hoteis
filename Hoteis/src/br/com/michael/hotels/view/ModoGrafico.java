package br.com.michael.hotels.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import br.com.michael.hotels.controller.Control;

public class ModoGrafico extends JFrame {
	private JMenuBar menuBar;
	private JMenu mnArquivo;
	private Control control;
	private JScrollPane scrollPane;
	private JEditorPane editorPane;
	private JFileChooser fileChooser;
	private File arquivo;
	private JMenuItem mntmPreos;

	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		ModoGrafico frame = new ModoGrafico();
		frame.setVisible(true);
	}

	public ModoGrafico() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 300);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnArquivo = new JMenu("Exibir");
		menuBar.add(mnArquivo);

		mntmPreos = new JMenuItem("Preços  ");
		mntmPreos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, control.hoteisString(), "Preços", JOptionPane.PLAIN_MESSAGE);
			}
		});
		mnArquivo.add(mntmPreos);

		editorPane = new JEditorPane();
		editorPane.setFont(new Font("SansSerif", Font.BOLD, 10));
		editorPane.setEditable(false);
		scrollPane = new JScrollPane(editorPane);
		editorPane.setOpaque(false);

		control = new Control();

		fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		int escolha = fileChooser.showOpenDialog(this);

		if (escolha == JFileChooser.CANCEL_OPTION)
			System.exit(0);

		arquivo = fileChooser.getSelectedFile();
		control.setArquivo(arquivo);

		editorPane.setText(control.reservasMaisBaratasToString());
		getContentPane().add(scrollPane, BorderLayout.CENTER);

	}

}
