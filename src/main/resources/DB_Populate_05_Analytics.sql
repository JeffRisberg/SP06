-- analytics

INSERT INTO dimensions (id, name, table_name, group_field, label_field, date_created, last_updated)
values (1, 'Donor', 'users', 'id', 'email', '2015-01-01', '2015-01-01');

INSERT INTO dimensions (id, name, table_name, group_field, label_field, date_created, last_updated)
values (2, 'Charity', 'charities', 'id', 'title', '2015-01-01', '2015-01-01');

INSERT INTO dimensions (id, name, table_name, group_field, label_field, date_created, last_updated)
values (3, 'Vendor', 'vendors', 'id', 'name', '2015-01-01', '2015-01-01');

INSERT INTO measures (id, name, expression, date_created, last_updated)
values (1, 'Amount', 'sum(d.amount)', '2015-01-01', '2015-01-01');

INSERT INTO measures (id, name, expression, date_created, last_updated)
values (2, 'Count', 'count(d.id)', '2015-01-01', '2015-01-01');

INSERT INTO measures (id, name, expression, date_created, last_updated)
values (3, 'Average', 'avg(d.amount)', '2015-01-01', '2015-01-01');

INSERT INTO measures (id, name, expression, date_created, last_updated)
values (4, 'Maximum', 'max(d.amount)', '2015-01-01', '2015-01-01');
