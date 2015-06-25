package skat09.tools;

import java.util.prefs.Preferences;

import skat09.Messages;

/**
 * Represents the application configuration with methods to write
 * it the user home folder.
 * 
 * @since 23.06.2015 21:54:13
 * 
 * @author Sebastian Schlatow <ssc@openmailbox.org>
 *
 */
public class Configuration {
    
	private static Configuration configInstance;

    final String LANG = "language";
    final String PLAYER = "playerName";
    
    private String defaultLanguage = "de";
    private String defaultName = Messages.getI18n("player");
    
    Preferences prefs = Preferences.userNodeForPackage(skat09.Skat09.class);
    
    static {
    	configInstance = new Configuration();
    }

    private Configuration() { 
        // hidden constructor
    }   

    public String getLanguage() {
    	return prefs.get(LANG, defaultLanguage);
    }

    public void setLanguage(String language) {
        prefs.put(LANG, language);
    }
    
    public String getDefaultName() {
    	return prefs.get(PLAYER, defaultName);
    }

    public void setDefaultName(String name) {
    	prefs.put(PLAYER, name);
    }
    
 

    public static Configuration getInstance() {
        return configInstance;
    }
}
