package org.boichenko.services;

import org.boichenko.Database;
import org.boichenko.domain.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    public static final String SQL_FIND_LONGEST_PROJECT = "sql/find_longest_project.sql";
    public static final String SQL_FIND_MAX_PROJECTS_CLIENT = "sql/find_max_projects_client.sql";
    public static final String SQL_FIND_MAX_SALARY_WORKER = "sql/find_max_salary_worker.sql";
    public static final String SQL_FIND_YOUNGEST_ELDEST_WORKERS = "sql/find_youngest_eldest_workers.sql";
    public static final String SQL_PRINT_PROJECT_PRICES = "sql/print_project_prices.sql";

    public List<ProjectPrices> printProjectPrices(){
        List<ProjectPrices> projectPrices = new ArrayList<>();
        String sql = getSqlFromFile(SQL_PRINT_PROJECT_PRICES);

        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                ProjectPrices project = new ProjectPrices();
                project.setProjectId(resultSet.getInt("project_id"));
                project.setPrice(resultSet.getInt("price"));
                projectPrices.add(project);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeAll(resultSet, statement);
        }

        return projectPrices;
    }

    public  List<YoungestEldestWorker> findYoungestEldestWorker(){
        List<YoungestEldestWorker> youngestEldestWorker = new ArrayList<>();
        String sql = getSqlFromFile(SQL_FIND_YOUNGEST_ELDEST_WORKERS);

        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                YoungestEldestWorker worker = new YoungestEldestWorker();
                worker.setType(resultSet.getString("type"));
                worker.setName(resultSet.getString("name"));
                worker.setBirthday(LocalDate.parse(resultSet.getString("birthday")));
                youngestEldestWorker.add(worker);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeAll(resultSet, statement);
        }

        return youngestEldestWorker;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker(){
        List<MaxSalaryWorker> maxSalaryWorkerList = new ArrayList<>();
        String sql = getSqlFromFile(SQL_FIND_MAX_SALARY_WORKER);

        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                MaxSalaryWorker worker = new MaxSalaryWorker();
                worker.setName(resultSet.getString("name"));
                worker.setSalary(resultSet.getInt("salary"));
                maxSalaryWorkerList.add(worker);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeAll(resultSet, statement);
        }

        return maxSalaryWorkerList;
    }

    public List<LongestProject> findLongestProject(){
        List<LongestProject> projectList = new ArrayList<>();
        String sql = getSqlFromFile(SQL_FIND_LONGEST_PROJECT);

        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                LongestProject project = new LongestProject();
                project.setProjectName(resultSet.getInt("project_name"));
                project.setMonthCount(resultSet.getInt("month_count"));
                projectList.add(project);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeAll(resultSet, statement);
        }

        return projectList;
    }

    public List<MaxProjectCountClient> findMaxProjectsClient(){
        List<MaxProjectCountClient> clientsList = new ArrayList<>();
        String sql = getSqlFromFile(SQL_FIND_MAX_PROJECTS_CLIENT);

        Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                MaxProjectCountClient client = new MaxProjectCountClient();
                client.setName(resultSet.getString("name"));
                client.setProjectCount(resultSet.getInt("project_count"));
                clientsList.add(client);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeAll(resultSet, statement);
        }

        return clientsList;
    }

    private static void closeAll(ResultSet resultSet, PreparedStatement statement) {
        try {
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getSqlFromFile(String file){
        StringBuilder sql = new StringBuilder();

        try (FileReader fr = new FileReader(file)){
            int reader = fr.read();
            while (reader != -1){
                sql.append((char) reader);
                reader = fr.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sql.toString();
    }

}
