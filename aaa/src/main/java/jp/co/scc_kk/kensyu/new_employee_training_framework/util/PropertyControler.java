package jp.co.scc_kk.kensyu.new_employee_training_framework.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

/**
 * Controller class for reading property files as UTF-8.
 *
 * @author yu-oonishi
 *
 */
public class PropertyControler extends Control {

    @Override
    public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
            throws IllegalAccessException, InstantiationException, IOException {
        final String bundleName = toBundleName(baseName, locale);
        final String resourceName = toResourceName(bundleName, "properties");
        try (InputStream is = loader.getResourceAsStream(resourceName)) {
            final InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            final BufferedReader br = new BufferedReader(isr);
            return new PropertyResourceBundle(br);
        }
    }
}
