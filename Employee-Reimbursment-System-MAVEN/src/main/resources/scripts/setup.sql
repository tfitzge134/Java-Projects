create table departments(
	dept_id SERIAL primary key,
	dept_name VARCHAR(50) not null,
	monthly_budget NUMERIC(7,2)
);

create table if not exists employees(
	empl_id SERIAL primary key,
	empl_name VARCHAR(50) unique,
	monthly_salary NUMERIC(6,2),
	empl_position VARCHAR(50),
	manager_id INTEGER references employees(empl_id),
	dept_id INTEGER references departments(dept_id)
);

insert into departments (dept_name, monthly_budget) values ('HR', 3000);
