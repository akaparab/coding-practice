
create Table Employee (
    id BIGINT NOT NULL AUTO_INCREMENT primary key,
    name varchar(32) NOT NULL,
    salary DECIMAL(10, 4),
    email varchar(32) NOT NULL,
    age INT,
    josnStr JSON,
    description TEXT,

)

Alter table Employee
add constraint name_email UNIQUE (name, email);

create index emp_name_ix on Employee(name);



