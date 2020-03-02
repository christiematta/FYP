
INSERT INTO users(name, is_deleted, created_on, created_by, modified_on, modified_by) VALUES('Tony Parker','false','2017-11-25','Admin','2020-01-11','Admin');
INSERT INTO users(name, is_deleted, created_on, created_by, modified_on, modified_by) VALUES('chmatta', 'false','2017-04-17','Tony Parker','2020-01-11','Admin');
INSERT INTO users(name, is_deleted, created_on, created_by, modified_on, modified_by) VALUES('Coco Chanel','false','2019-10-25','Admin','2020-01-11','Tony Parker');

INSERT INTO roles(name, is_deleted, created_on, created_by, modified_on, modified_by) VALUES('USER','false','2019-10-25','chmatta','2020-01-11','Coco Chanel');
INSERT INTO roles(name, is_deleted, created_on, created_by, modified_on, modified_by) VALUES('ADMIN','false','2019-10-25','chmatta','2020-01-11','Coco Chanel');


INSERT INTO permissions(name, is_deleted, created_on, created_by, modified_on, modified_by) VALUES('READ','false','2019-10-25','chmatta','2020-01-11','chmatta');
INSERT INTO permissions(name, is_deleted, created_on, created_by, modified_on, modified_by) VALUES('WRITE','false','2019-10-25','chmatta','2020-01-11','chmatta');
INSERT INTO permissions(name, is_deleted, created_on, created_by, modified_on, modified_by) VALUES('EXECUTE','false','2019-10-25','chmatta','2020-01-11','chmatta');


INSERT INTO role_perm(role_id,permission_id) VALUES(1,1);
INSERT INTO role_perm(role_id,permission_id) VALUES(2,3);


INSERT INTO user_role(user_id,role_id) VALUES(1,1);
INSERT INTO user_role(user_id,role_id) VALUES(2,2);
INSERT INTO user_role(user_id,role_id) VALUES(3,1);

