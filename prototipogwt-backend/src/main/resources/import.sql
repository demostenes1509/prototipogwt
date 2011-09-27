INSERT INTO permission (id, active, clazz, method) VALUES 
(1, 'S', 'UserProvider', 'get'),
(2, 'S', 'UserProvider', 'find'),
(10, 'S', 'RoleProvider', 'get'),
(11, 'S', 'RoleProvider', 'find'),
(12, 'S', 'RoleProvider', 'save'),
(13, 'S', 'RoleProvider', 'delete'),
(14, 'S', 'RoleProvider', 'update');


INSERT INTO role (id, active, name) VALUES 
(1, 'S', 'admin'),
(2, 'S', 'dummy');

INSERT INTO role_permission (id, active, role_id, permission_id) VALUES 
(01, 'S', 1, 01),
(02, 'S', 1, 02),
(06, 'S', 1, 10),
(07, 'S', 1, 11),
(08, 'S', 1, 12),
(09, 'S', 1, 13),
(10, 'S', 1, 14);

INSERT INTO user (id, active, email, role_id, password) VALUES 
(1, 'S', 'prototipogwt@teracode.com', 1,'cGfO9kaI4O0hsEYmbaGRNw=='),
(2, 'S', 'dummy.delete@gmail.com', 1, null);

INSERT INTO user_session (id, active, device, token, user_id) VALUES 
(1, 'S', 'web', '8898d6ad-bc94-4740-8a58-f0a079102e2d', 1),
(2, 'S', 'web', '6668d6ad-bc94-4740-8a58-f0a079102e2d', 2);

