SELECT id AS project_name, DATEDIFF(month, start_date, finish_date) as month_count FROM project
WHERE DATEDIFF(month, start_date, finish_date) IN (
    SELECT DATEDIFF(month, start_date, finish_date) as month_count FROM project
    ORDER BY month_count DESC
    LIMIT 1
    );
