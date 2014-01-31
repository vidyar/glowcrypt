package com.xnrand.glowcrypt.cli.dev;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

/**
 * GUI wrapper for the DevCLI
 * 
 * This is for easier testing on windows machines
 * 
 * @author xnrand <http://xnrand.com/> <https://github.com/xnrand>
 */
@SuppressWarnings("serial")
public class DevCLIGUIWrapper extends JFrame {

	private JPanel contentPane;
	private final Action runAction = new RunAction();
	private JTextArea txtrOutput;
	private JTextArea txtrInput;
	private JTextField tfCommand;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DevCLIGUIWrapper frame = new DevCLIGUIWrapper();
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
	public DevCLIGUIWrapper() {
		setResizable(false);
		setTitle("glowcrypt - dev");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 883, 626);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tfCommand = new JTextField();
		tfCommand.setBounds(95, 12, 643, 19);
		contentPane.add(tfCommand);
		tfCommand.setColumns(10);

		JLabel lblCommand = new JLabel("Command:");
		lblCommand.setBounds(12, 14, 81, 15);
		contentPane.add(lblCommand);

		JButton btnRun = new JButton("Run");
		btnRun.setAction(runAction);
		btnRun.setBounds(750, 9, 117, 25);
		contentPane.add(btnRun);

		JScrollPane spOutput = new JScrollPane();
		spOutput.setBounds(12, 40, 855, 319);
		contentPane.add(spOutput);

		txtrOutput = new JTextArea();
		txtrOutput.setEditable(false);
		txtrOutput.setText("Output");
		txtrOutput.setLineWrap(true);
		txtrOutput.setWrapStyleWord(false);
		spOutput.setViewportView(txtrOutput);

		JScrollPane spInput = new JScrollPane();
		spInput.setBounds(12, 371, 855, 216);
		contentPane.add(spInput);

		txtrInput = new JTextArea();
		txtrInput.setText("Input");
		spInput.setViewportView(txtrInput);
	}

	private class RunAction extends AbstractAction {
		public RunAction() {
			putValue(NAME, "Run");
			putValue(SHORT_DESCRIPTION, "Run the program");
		}

		public void actionPerformed(ActionEvent e) {
			txtrOutput.setText("running, this could take a while, depending on the command you entered...");
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					ByteArrayOutputStream runout = new ByteArrayOutputStream();
					PrintStream runoutps = new PrintStream(runout);
					ByteArrayInputStream runin = new ByteArrayInputStream(
							txtrInput.getText().getBytes());

					try {
						new DevCLI(tfCommand.getText().split(" "), runin,
								runoutps, runoutps).devcli();
					} catch (Exception ex) {
						ex.printStackTrace(runoutps);
						runoutps.print("Error: " + ex.getMessage());
					}

					txtrOutput.setText(runout.toString());
				}
			});

		}
	}
}
