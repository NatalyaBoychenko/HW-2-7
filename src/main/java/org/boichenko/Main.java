package org.boichenko;

import org.boichenko.services.DatabaseQueryService;

public class Main {
    public static void main(String[] args) {

        DatabaseQueryService databaseQueryService = new DatabaseQueryService();

        System.out.println(databaseQueryService.findMaxProjectsClient());
        System.out.println(databaseQueryService.findLongestProject());
        System.out.println(databaseQueryService.findMaxSalaryWorker());
        System.out.println(databaseQueryService.findYoungestEldestWorker());
        System.out.println(databaseQueryService.printProjectPrices());
    }
}
