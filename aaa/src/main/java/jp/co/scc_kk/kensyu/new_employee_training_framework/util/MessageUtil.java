package jp.co.scc_kk.kensyu.new_employee_training_framework.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class MessageUtil {

    private static final ResourceBundle displayMessageBundle =
            ResourceBundle.getBundle("DisplayMessage", new PropertyControler());
    private static final ResourceBundle logMessageBundle =
            ResourceBundle.getBundle("LogMessage", new PropertyControler());

    private MessageUtil() {
        // Do nothing
    }

    public static String getDisplayMessage(String key, Object... args) {
        return MessageFormat.format(displayMessageBundle.getString(key), args);
    }

    public static String getLogMessage(String key, Object... args) {
        return MessageFormat.format(logMessageBundle.getString(key), args);
    }

}
