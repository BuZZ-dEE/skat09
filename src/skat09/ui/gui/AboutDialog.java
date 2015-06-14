package skat09.ui.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

import skat09.Messages;

/**
 * The Skat09 about dialog, should show informations about
 * this program.
 * 
 * @since 13.06.2015 00:23:24
 * 
 * @author Sebastian Schlatow <ssc@openmailbox.org>
 *
 */
public class AboutDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public AboutDialog(JFrame parent, String title, String message) {
		super(parent, title);

//		setLocationRelativeTo(null);

		JPanel messagePane = new JPanel();
		messagePane.add(new JLabel(message));
		getContentPane().add(messagePane);

		JPanel buttonPane = new JPanel();
		JButton button = new JButton(Messages.getI18n("application.ok"));
		buttonPane.add(button);
		button.addActionListener(new AboutActionListener());
		getContentPane().add(buttonPane, BorderLayout.PAGE_END);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	public JRootPane createRootPane() {
		JRootPane rootPane = new JRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
		Action action = new AbstractAction() {
			
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		};
		InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(stroke, "ESCAPE");
		rootPane.getActionMap().put("ESCAPE", action);
		return rootPane;
	}

	class AboutActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			dispose();
		}
	}
}