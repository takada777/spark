package jp.co.scc_kk.kensyu.new_employee_training_framework.util;

import com.google.common.base.CaseFormat;
import org.skife.jdbi.v2.BeanMapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class SnakeBeanMapper<T> extends BeanMapper<T> {

    /**
     * Create a new instance with the specified type.
     */
    public SnakeBeanMapper(Class<T> type) {
        super(type);
        try {
            final Map<String, PropertyDescriptor> newProperties = new HashMap<>();
            final Field propertiesFiled = BeanMapper.class.getDeclaredField("properties");
            propertiesFiled.setAccessible(true);
            @SuppressWarnings("unchecked")
            final Map<String, PropertyDescriptor> properties =
                    (Map<String, PropertyDescriptor>) propertiesFiled.get(this);
            for (final PropertyDescriptor pd : properties.values()) {
                final String snakeKey = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, pd.getName());
                newProperties.put(snakeKey, pd);
            }
            propertiesFiled.set(this, newProperties);
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

}
