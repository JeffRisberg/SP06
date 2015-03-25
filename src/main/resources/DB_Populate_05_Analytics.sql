-- analytics

INSERT INTO dimensions (id, name, table_name, group_field, category_field, date_created, last_updated)
values (1, 'Donor', 'users', 'id', 'email', '2015-01-01', '2015-01-01');

INSERT INTO dimensions (id, name, table_name, group_field, category_field, date_created, last_updated)
values (2, 'Charity', 'charities', 'id', 'title', '2015-01-01', '2015-01-01');

INSERT INTO dimensions (id, name, table_name, group_field, category_field, date_created, last_updated)
values (3, 'Vendor', 'vendors', 'id', 'name', '2015-01-01', '2015-01-01');

INSERT INTO measures (id, name, expression, datatype, date_created, last_updated)
values (1, 'Amount', 'sum(donations.amount)', 'double', '2015-01-01', '2015-01-01');

INSERT INTO measures (id, name, expression, datatype, date_created, last_updated)
values (2, 'Count', 'count(donations.id)', 'integer', '2015-01-01', '2015-01-01');

INSERT INTO measures (id, name, expression, datatype, date_created, last_updated)
values (3, 'Average', 'avg(donations.amount)', 'double', '2015-01-01', '2015-01-01');

INSERT INTO measures (id, name, expression, datatype, date_created, last_updated)
values (4, 'Maximum', 'max(donations.amount)', 'float', '2015-01-01', '2015-01-01');
