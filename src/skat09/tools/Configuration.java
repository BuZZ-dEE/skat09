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

    final String DECK = "deck";
    final String LANG = "language";
    final String PLAYER = "playerName";
    final String ADVERSARY_FIRST = "adversary_first";
    final String ADVERSARY_SECOND = "adversary_second";
    final String SKAT_VARIANT = "skat_variant";
    final String SKAT_SIX = "skat_six";

    private String defaultDeck = "de";
    private String defaultLanguage = "de";
    private String defaultName = Messages.getI18n("player");
    private String defaultAdversaryFirst = null;
    private String defaultAdversarySecond = null;
    private String defaultSkatVariant = null;
    private String defaultSkatSix = null;
    
    Preferences prefs = Preferences.userNodeForPackage(skat09.Skat09.class);
    
    static {
    	configInstance = new Configuration();
    }

    private Configuration() { 
        // hidden constructor
    }
    
    /**
     * Get configuration instance.
     * 
     * @return the configuration instance
     */
    public static Configuration getInstance() {
        return configInstance;
    }
    
    public String getDeck() {
    	return prefs.get(DECK, defaultDeck);
    }

    public void setDeck(String deck) {
        prefs.put(DECK, deck);
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

	/**
	 * @return the defaultAdversaryFirst
	 */
	public String getDefaultAdversaryFirst() {
		return prefs.get(ADVERSARY_FIRST, defaultAdversaryFirst);
	}

	/**
	 * @param adversaryFirst the defaultAdversaryFirst to set
	 */
	public void setDefaultAdversaryFirst(String adversaryFirst) {
		prefs.put(ADVERSARY_FIRST, adversaryFirst);
	}

	/**
	 * @return the defaultAdversarySecond
	 */
	public String getDefaultAdversarySecond() {
		return prefs.get(ADVERSARY_SECOND, defaultAdversarySecond);
	}

	/**
	 * @param adversarySecond the defaultAdversarySecond to set
	 */
	public void setDefaultAdversarySecond(String adversarySecond) {
		prefs.put(ADVERSARY_SECOND, adversarySecond);
	}

	/**
	 * @return the defaultSkatVariant
	 */
	public String getDefaultSkatVariant() {
		return prefs.get(SKAT_VARIANT, defaultSkatVariant);
	}

	/**
	 * @param skatVariant the defaultSkatVariant to set
	 */
	public void setDefaultSkatVariant(String skatVariant) {
		prefs.put(SKAT_VARIANT, skatVariant);
	}

	/**
	 * @return the defaultSkatSix
	 */
	public String getDefaultSkatSix() {
		return prefs.get(SKAT_SIX, defaultSkatSix);
	}

	/**
	 * @param skatSix the defaultSkatSix to set
	 */
	public void setDefaultSkatSix(String skatSix) {
		prefs.put(SKAT_SIX, skatSix);
	}
}
