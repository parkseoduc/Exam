INSERT INTO employees (name, salary)
SELECT 'Nguyen Van An', 1200.00
WHERE NOT EXISTS (SELECT 1 FROM employees WHERE name = 'Nguyen Van An');

INSERT INTO employees (name, salary)
SELECT 'Tran Thi Binh', 1500.00
WHERE NOT EXISTS (SELECT 1 FROM employees WHERE name = 'Tran Thi Binh');
