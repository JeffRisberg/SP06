-- rubrics

INSERT INTO rubric (id, parent_id, seq_num, title, body, date_created, last_updated)
values (1, null, 1, 'Donations',
				'<p><span style="color: rgb(51, 51, 51); font-family: ''Century Gothic'', Quicksand, sans-serif; font-size: 14px; line-height: 20px;">
					 We make it easy to support your favorite causes and donate when, how, and as often as you want to more than 1.8 million charities working throughout the world. Several convenient search tools help you quickly find and learn more about any charity.
					 </span></p>', now(), now());
INSERT INTO rubric (id, parent_id, seq_num, title, body, date_created, last_updated)
values (2, null, 2, 'One-time Donations', '<p>Make a one-time donation to one or more charities.</p>', now(), now());
INSERT INTO rubric (id, parent_id, seq_num, title, body, date_created, last_updated)
values (3, null, 11, null,
				'<p>For any cause you care about, it''s simple to find a charity: large or small, working in your local community or across the world - and donate to support their work.</p>',
				now(), now());
INSERT INTO rubric (id, parent_id, seq_num, title, body, date_created, last_updated)
values (4, null, 12, 'Monthly Giving', '<p>Automatically donate to selected charities on a regular basis.</p>', now(),
				now());
INSERT INTO rubric (id, parent_id, seq_num, title, body, date_created, last_updated)
values
	(5, null, 13, 'Gift Donations', '<p>For every purpose and occasion, charity is the perfect gift.</p>', now(), now());
INSERT INTO rubric (id, parent_id, seq_num, title, body, date_created, last_updated)
values (6, null, 20, 'Rubric6', 'This is a test of rubric6', now(), now());
INSERT INTO rubric (id, parent_id, seq_num, title, body, date_created, last_updated)
values (7, null, 21, 'Rubric7', 'This is a test of rubric7', now(), now());
INSERT INTO rubric (id, parent_id, seq_num, title, body, date_created, last_updated)
values (8, null, 22, 'Rubric8', 'This is a test of rubric8', now(), now());
INSERT INTO rubric (id, parent_id, seq_num, title, body, date_created, last_updated)
values (9, null, 23, 'Rubric9', 'This is a test of rubric9', now(), now());

INSERT INTO box (id, site_id, row_index, col_index, width, title, image_file_name, date_created, last_updated)
values (1, 1, 0, 0, 12, 'ABox00', null, now(), now());
INSERT INTO box (id, site_id, row_index, col_index, width, title, image_file_name, date_created, last_updated)
values (2, 1, 1, 0, 4, 'ABox10', null, now(), now());
INSERT INTO box (id, site_id, row_index, col_index, width, title, image_file_name, date_created, last_updated)
values (3, 1, 1, 1, 4, 'ABox11', null, now(), now());
INSERT INTO box (id, site_id, row_index, col_index, width, title, image_file_name, date_created, last_updated)
values (4, 1, 1, 2, 4, 'ABox12', null, now(), now());

INSERT INTO box (id, site_id, row_index, col_index, width, title, image_file_name, date_created, last_updated)
values (11, 2, 0, 0, 4, 'CBox00', null, now(), now());
INSERT INTO box (id, site_id, row_index, col_index, width, title, image_file_name, date_created, last_updated)
values (12, 2, 0, 1, 4, 'CBox01', null, now(), now());
INSERT INTO box (id, site_id, row_index, col_index, width, title, image_file_name, date_created, last_updated)
values (13, 2, 0, 2, 4, 'CBox02', null, now(), now());
INSERT INTO box (id, site_id, row_index, col_index, width, title, image_file_name, date_created, last_updated)
values (14, 2, 1, 0, 12, 'CBox10', null, now(), now());

INSERT INTO rubric_box (box_id, rubric_id) values (1, 1);
INSERT INTO rubric_box (box_id, rubric_id) values (2, 2);
INSERT INTO rubric_box (box_id, rubric_id) values (2, 3);
INSERT INTO rubric_box (box_id, rubric_id) values (3, 4);
INSERT INTO rubric_box (box_id, rubric_id) values (4, 5);

INSERT INTO rubric_box (box_id, rubric_id) values (11, 4);
INSERT INTO rubric_box (box_id, rubric_id) values (11, 2);
INSERT INTO rubric_box (box_id, rubric_id) values (12, 8);
INSERT INTO rubric_box (box_id, rubric_id) values (12, 5);
INSERT INTO rubric_box (box_id, rubric_id) values (13, 6);
INSERT INTO rubric_box (box_id, rubric_id) values (13, 7);
INSERT INTO rubric_box (box_id, rubric_id) values (14, 9);
