package skat09.ui.gui;

import java.awt.BorderLayout;
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

		button = makeNavigationButton("view-sidebar-symbolic", SETTINGS,
				Messages.getI18n("application.settings"),
				Messages.getI18n("application.settings"));
		toolBar.add(button);

	}

	protected JButton makeNavigationButton(String imageName,
			String actionCommand, String toolTipText, String altText) {
		
		String imgLocation = "img/app/" + imageName + ".svg";
		URL imageURL = this.getClass().getClassLoader().getResource(imgLocation);

		JButton button = new JButton();
		button.setActionCommand(actionCommand);
		button.setToolTipText(toolTipText);
		button.addActionListener(this);

		if (imageURL != null) {
//			Image img = new ImageIcon(imageURL).getImage();
//			Image scaledImg = img.getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH);  
//			button.setIcon(new ImageIcon(scaledImg, altText));
			
			button.setIcon(new ImageIcon(SvgImageProcessing.getImage(imageName), altText));
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