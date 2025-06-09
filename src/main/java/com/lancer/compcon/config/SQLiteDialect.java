package com.lancer.compcon.config;

import java.sql.Types;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.identity.IdentityColumnSupport;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.dialect.function.VarArgsSQLFunction;
import org.hibernate.type.StringType;

/**
 * Classe de Dialeto Personalizada para SQLite.
 * Esta versão final corrige a definição da coluna de identidade para
 * evitar a dupla declaração da chave primária.
 */
public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        // Mapeamento de tipos (continua igual)
        registerColumnType(Types.BIT, "integer");
        registerColumnType(Types.TINYINT, "tinyint");
        registerColumnType(Types.SMALLINT, "smallint");
        registerColumnType(Types.INTEGER, "integer");
        registerColumnType(Types.BIGINT, "bigint");
        registerColumnType(Types.FLOAT, "float");
        registerColumnType(Types.REAL, "real");
        registerColumnType(Types.DOUBLE, "double");
        registerColumnType(Types.NUMERIC, "numeric");
        registerColumnType(Types.DECIMAL, "decimal");
        registerColumnType(Types.CHAR, "char");
        registerColumnType(Types.VARCHAR, "varchar");
        registerColumnType(Types.LONGVARCHAR, "longvarchar");
        registerColumnType(Types.DATE, "date");
        registerColumnType(Types.TIME, "time");
        registerColumnType(Types.TIMESTAMP, "timestamp");
        registerColumnType(Types.BINARY, "blob");
        registerColumnType(Types.VARBINARY, "blob");
        registerColumnType(Types.LONGVARBINARY, "blob");
        registerColumnType(Types.BLOB, "blob");
        registerColumnType(Types.CLOB, "clob");
        registerColumnType(Types.BOOLEAN, "integer");

        registerFunction("concat", new VarArgsSQLFunction(StringType.INSTANCE, "", "||", ""));
        registerFunction("mod", new StandardSQLFunction("mod", null));
        registerFunction("substr", new StandardSQLFunction("substr", StringType.INSTANCE));
        registerFunction("substring", new StandardSQLFunction("substr", StringType.INSTANCE));
    }

    @Override
    public IdentityColumnSupport getIdentityColumnSupport() {
        return new SQLiteIdentityColumnSupport();
    }

    // Outros métodos de dialeto (continuam iguais)
    @Override
    public boolean hasAlterTable() { return false; }

    @Override
    public boolean dropConstraints() { return false; }

    @Override
    public String getDropForeignKeyString() { return ""; }

    @Override
    public String getAddForeignKeyConstraintString(String constraintName, String[] foreignKey, String referencedTable, String[] primaryKey, boolean referencesPrimaryKey) { return ""; }

    @Override
    public String getAddPrimaryKeyConstraintString(String constraintName) {
        // Retorna uma string vazia para impedir que o Hibernate adicione a segunda chave primária
        return "";
    }

    @Override
    public String getForUpdateString() { return ""; }

    @Override
    public String getAddColumnString() { return "add column"; }

    @Override
    public boolean supportsOuterJoinForUpdate() { return false; }

    @Override
    public boolean supportsIfExistsBeforeTableName() { return true; }

    @Override
    public boolean supportsCascadeDelete() { return false; }
}

/**
 * Classe auxiliar necessária para o SQLiteDialect.
 * Esta versão foi corrigida para usar a sintaxe correta do SQLite
 * para colunas de identidade auto-incrementadas.
 */
class SQLiteIdentityColumnSupport extends org.hibernate.dialect.identity.IdentityColumnSupportImpl {
    @Override
    public boolean supportsIdentityColumns() {
        return true;
    }

    @Override
    public boolean hasDataTypeInIdentityColumn() {
        return false;
    }

    @Override
    public String getIdentitySelectString(String table, String column, int type) {
        return "select last_insert_rowid()";
    }

    @Override
    public String getIdentityColumnString(int type) {
        // --- CORREÇÃO PRINCIPAL APLICADA AQUI ---
        // Agora definimos a coluna apenas como auto-incrementada, sem
        // a cláusula 'primary key', que será adicionada pelo Hibernate.
        return "integer";
    }
}
