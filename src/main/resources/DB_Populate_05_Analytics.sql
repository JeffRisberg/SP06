-- analytics

INSERT INTO dimensions (id, name, table_name, field_name, date_created, last_updated)
values (1, 'Donor', 'donors', 'email', '2015-01-01', '2015-01-01');

INSERT INTO dimensions (id, name, table_name, field_name, date_created, last_updated)
values (2, 'Charity', 'charities', 'title', '2015-01-01', '2015-01-01');

INSERT INTO dimensions (id, name, table_name, field_name, date_created, last_updated)
values (3, 'Vendor', 'vendors', 'name', '2015-01-01', '2015-01-01');

INSERT INTO measures (id, name, field_name, date_created, last_updated)
values (1, 'Amount', ' ', '2015-01-01', '2015-01-01');

INSERT INTO measures (id, name, field_name, date_created, last_updated)
values (2, 'Count', ' ', '2015-01-01', '2015-01-01');
