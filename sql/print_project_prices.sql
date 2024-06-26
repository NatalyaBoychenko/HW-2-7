SELECT project_id, MAX(salary*months) AS price FROM

    (SELECT project_id, SUM(salary) AS salary FROM worker
                                                       JOIN project_worker ON project_worker.worker_id = worker.id
     GROUP BY project_id) t1

        LEFT JOIN

    (SELECT id, DATEDIFF(month, start_date, finish_date) AS months
     FROM project
    ) t2
    ON t1.project_id = t2.id
GROUP BY project_id
ORDER BY price DESC;