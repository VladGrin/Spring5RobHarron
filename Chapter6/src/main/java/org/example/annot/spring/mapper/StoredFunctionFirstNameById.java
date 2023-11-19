package org.example.annot.spring.mapper;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlFunction;

import javax.sql.DataSource;
import java.sql.Types;

public class StoredFunctionFirstNameById extends SqlFunction<String> {

    private static final String SQL = "select getFirstNameById(?)";

    public StoredFunctionFirstNameById(DataSource dataSource) {
        super(dataSource, SQL);
        declareParameter(new SqlParameter(Types.INTEGER));
        compile();
    }
}
