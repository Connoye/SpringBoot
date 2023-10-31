package com.example.stock.management;

import org.hibernate.boot.model.TypeContributions;
import org.hibernate.dialect.DatabaseVersion;
import org.hibernate.dialect.PostgreSQLDialect;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.SqlTypes;
import org.hibernate.type.descriptor.jdbc.BinaryJdbcType;
import org.hibernate.type.descriptor.jdbc.spi.JdbcTypeRegistry;

import java.sql.Types;

public class AxonPostgreSQLDialect extends PostgreSQLDialect {

    private static final String BYTEA = "BYTEA";

    public AxonPostgreSQLDialect() {
    super(DatabaseVersion.make(9, 5));
        }

        @Override
        protected String columnType(int sqlTypeCode) {
            if (sqlTypeCode == SqlTypes.BLOB){
                return "bytea";
            }
            return super.columnType(sqlTypeCode);
        }

        @Override
        protected String castType(int sqlTypeCode) {
            if (sqlTypeCode == SqlTypes.BLOB){
                return "bytea";
            }
            return super.castType(sqlTypeCode);
        }

        @Override
        public void contributeTypes(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
            super.contributeTypes(typeContributions, serviceRegistry);
            JdbcTypeRegistry jdbcTypeRegistry = typeContributions.getTypeConfiguration().getJdbcTypeRegistry();
            jdbcTypeRegistry.addDescriptor(Types.BLOB, BinaryJdbcType.INSTANCE);
        }
    }

