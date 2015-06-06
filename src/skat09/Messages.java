package skat09;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Work with messages properties.
 * 
 * @since 06.06.2015 13:24:33
 * 
 * @author Sebastian Schlatow <ssc@openmailbox.org>
 */
public class Messages { 
	
    // property file is: resources/languages/messages.properties
    private static final String BUNDLE_NAME = "languages.message";
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
    
    private Messages() {
    }
    
	/**
	 * Get the translation for the given key for current locale.
	 * 
	 * @param {String} key , the message key
	 * @return {String} the translation
	 * 
	 * @since 06.06.2015 13:26:13
	 * 
	 * @author Sebastian Schlatow <ssc@openmailbox.org>
	 */
    public static String getI18n(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
    
	/**
	 * Get the translation for the given key for current locale and
	 * substitue parameters in message.
	 * 
	 * @param {String} key , the message key
	 * @return {String} the translation
	 * 
	 * @since 06.06.2015 13:26:24
	 * 
	 * @author Sebastian Schlatow <ssc@openmailbox.org>
	 */
    public static String getI18n(String key, Object... params  ) {
        try {
            return MessageFormat.format(RESOURCE_BUNDLE.getString(key), params);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}