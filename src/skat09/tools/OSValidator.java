package skat09.tools;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import skat09.Messages;

/**
 * Operation system check.
 * 
 * @since 24.06.2015 23:24:30
 * 
 * @author Sebastian Schlatow <ssc@openmailbox.org>
 *
 */
public class OSValidator {
	 
	private static String OS = System.getProperty("os.name").toLowerCase();
 
	public static void setOperatingSystemProperties() {

		System.out.println(OS);

		if (isWindows()) {
			System.out.println("This is Windows");
		} else if (isMac()) {
			System.out.println("This is Mac");

			System.setProperty(
					"com.apple.mrj.application.apple.menu.about.name",
					Messages.getI18n("application.name"));
			System.setProperty("apple.laf.useScreenMenuBar", "true");
		} else if (isUnix()) {
			System.out.println("This is Unix or Linux");
		} else if (isSolaris()) {
			System.out.println("This is Solaris");
		} else {
			System.out.println("Unknown OS");
		}
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			
			System.out.println("Could not apply default system look and feel.");
			
			e.printStackTrace();
		}
	}
 
	public static boolean isWindows() {
 
		return (OS.indexOf("win") >= 0);
 
	}
 
	public static boolean isMac() {
 
		return (OS.indexOf("mac") >= 0);
 
	}
 
	public static boolean isUnix() {
 
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
 
	}
 
	public static boolean isSolaris() {
 
		return (OS.indexOf("sunos") >= 0);
 
	}
 
}
