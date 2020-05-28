package jp.co.scc_kk.kensyu.new_employee_training_framework.util;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class SnakeBeanMapperFactory extends BeanMapperFactory {

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public ResultSetMapper<?> mapperFor(Class type, StatementContext ctx) {
        return new SnakeBeanMapper(type);
    }
}
