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

import skat09.Messages;

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

		JToolBar toolBar = new JToolBar(
				Messages.getI18n("application.settings"));
		toolBar.setFloatable(false);
		addButtons(toolBar);

//		setPreferredSize(new Dimension(450, 130));
		add(toolBar, BorderLayout.EAST);
	}

	private void addButtons(JToolBar toolBar) {
		JButton button = null;

		button = makeNavigationButton("settings", SETTINGS,
				Messages.getI18n("application.settings"),
				Messages.getI18n("application.settings"));
		toolBar.add(button);

	}

	protected JButton makeNavigationButton(String imageName,
			String actionCommand, String toolTipText, String altText) {
		
		String imgLocation = "images/" + imageName + ".png";
		URL imageURL = Toolbar.class.getResource(imgLocation);

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
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String description = null;
 
        // Handle each button.
        if (SETTINGS.equals(cmd)) {
            description = "taken you to the settings.";
        }
 
        displayResult("If this were a real app, it would have "
                        + description);
    }
	
    protected void displayResult(String actionDescription) {
        System.out.println(Messages.getI18n("application.settings"));
    }
}