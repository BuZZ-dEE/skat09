package skat09.tools;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Represents the applicartion configuration with methods to write
 * it the user home folder.
 * 
 * @since 23.06.2015 21:54:13
 * 
 * @author Sebastian Schlatow <ssc@openmailbox.org>
 *
 */
public class AppConfiguration extends Configuration {
    
	@NotEmpty
    private String defaultLanguage = "de";

    @NotEmpty
    private String defaultName = "Spieler";

    @JsonProperty
    public String getLanguage() {
        return defaultLanguage;
    }

    @JsonProperty
    public void setLanguage(String language) {
        this.defaultLanguage = language;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }
}
