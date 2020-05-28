package jp.co.scc_kk.kensyu.new_employee_training_framework.util;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

public class DatabaseConnectionUtil {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnectionUtil.class);

    private static final String PROPERTY_NAME = "DatabaseConnection";
    private static final String DB_NAME = "db_name";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private static final DBI dbi = init();

    private DatabaseConnectionUtil() {
        // Do nothing.
    }

    private static final DBI init() {
        final ResourceBundle bundle = ResourceBundle.getBundle(PROPERTY_NAME, new PropertyControler());

        final String dbName = bundle.getString(DB_NAME);
        final String user = bundle.getString(USER);
        final String password = bundle.getString(PASSWORD);
        if (!validProperty(dbName, user, password)) {
            throw new IllegalArgumentException();
        }

        final String server = "localhost";
        final String url = String.join("", "jdbc:postgresql://", server, "/", dbName);
        return new DBI(url, user, password);
    }

    private static final boolean validProperty(final String dbName, final String user, final String password) {
        // FIXME Update log message
        if (StringUtils.isEmpty(dbName)) {
            logger.error("Invalid Database name");
            return false;
        }
        if (StringUtils.isEmpty(user)) {
            logger.error("Invalid User name");
            return false;
        }
        if (StringUtils.isEmpty(password)) {
            logger.error("Invalid Password");
            return false;
        }
        return true;
    }

    public static <T> T getDaoInstance(Class<T> daoClass) {
        return dbi.onDemand(daoClass);
    }

}
