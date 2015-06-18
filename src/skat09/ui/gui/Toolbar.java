package skat09.ui.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 * Toolbar
 * 
 * @since 19.06.2015 00:12:13
 * 
 * @author Sebastian Schlatow <ssc@openmailbox.org>
 *
 */
public class Toolbar extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 2893537278971199847L;
	static final private String SETTINGS = "settings";

	public Toolbar() {
		super(new BorderLayout());

		JToolBar toolBar = new JToolBar("Still draggable");
		addButtons(toolBar);

		setPreferredSize(new Dimension(450, 130));
		add(toolBar, BorderLayout.PAGE_START);
//		add(scrollPane, BorderLayout.CENTER);
	}

	private void addButtons(JToolBar toolBar) {
		JButton button = null;

		button = makeNavigationButton("Settings", SETTINGS,
				"Back to previous something-or-other", "Previous");
		toolBar.add(button);

	}
	
	protected JButton makeNavigationButton(String imageName,
			String actionCommand, String toolTipText, String altText) {
		// Look for the image.
		String imgLocation = "images/" + imageName + ".gif";
		URL imageURL = Toolbar.class.getResource(imgLocation);

		// Create and initialize the button.
		JButton button = new JButton();
		button.setActionCommand(actionCommand);
		button.setToolTipText(toolTipText);
		button.addActionListener(this);

		if (imageURL != null) {
			button.setIcon(new ImageIcon(imageURL, altText));
		} else {
			button.setText(altText);
			System.err.println("Resource not found: " + imgLocation);
		}

		return button;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}