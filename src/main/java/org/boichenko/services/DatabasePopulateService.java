package org.boichenko.services;

import org.boichenko.Database;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabasePopulateService {
    public static final String SQL_POPULATE_DB = "sql/populate_db.sql";

    public static void main(String[] args) {

        Connection connection = Database.getInstance().getConnection();

        String sql = getSqlPopulateFromFile();

        poputateDb(connection, sql);


        Database.getInstance().closeConnection();
    }

    private static void poputateDb(Connection connection, String sql) {
        PreparedStatement statement;
        try{
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static String getSqlPopulateFromFile() {
        StringBuilder sql = new StringBuilder();

        try (FileReader fr = new FileReader(SQL_POPULATE_DB);){
            int reader = fr.read();
            while (reader != -1){
                sql.append((char) reader);
                reader = fr.read();
            }
        } catch (IOException e) {
            return null;
        }

        return sql.toString();
    }
}
