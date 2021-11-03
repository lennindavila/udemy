INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('lennin','$2a$10$JqOYjkJrnrPka/GFPI/Tk.6LGFzHiVSSpofbBLJHpP5KRuRqhGlkm',true, 'Lennin', 'Davila','lennin.davila@gmail.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('admin','$2a$10$jWEWMuTuYEMy.Y4uMo/E6OzyZjMx.Oy0kWLhSnOcc6rGvkVEQ2z3y',true, 'John', 'Doe','jhon.doe@bolsadeideas.com');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 1);