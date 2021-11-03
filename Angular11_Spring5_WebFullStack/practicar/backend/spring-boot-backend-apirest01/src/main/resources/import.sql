INSERT INTO regiones(id,nombre)	VALUES(1,'Sudamerica');
INSERT INTO regiones(id,nombre)	VALUES(2,'Centroamenrica');
INSERT INTO regiones(id,nombre)	VALUES(3,'Norteamerica');
INSERT INTO regiones(id,nombre)	VALUES(4,'Europa');
INSERT INTO regiones(id,nombre)	VALUES(5,'Asia');
INSERT INTO regiones(id,nombre)	VALUES(6,'Africa');
INSERT INTO regiones(id,nombre)	VALUES(7,'Oceania');
INSERT INTO regiones(id,nombre)	VALUES(8,'Antartida');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at)VALUES(1,'Mishel','Perez','mishel.Perez@gmail.com','1994-03-02');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at)VALUES(3,'Lennin','Davila','lennin.Davila@gmail.com','1984-08-01');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at)VALUES(1,'Samira','Benazar','Samira.Benazar@gmail.com','1988-06-06');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at)VALUES(3,'Dora','Sedano','dora.Sedano@gmail.com','1953-02-02');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at)VALUES(4,'Nelson','Davila','nelson.Davila@gmail.com','1953-01-29');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at)VALUES(7,'Pilar','Leano','pilar.Leano@gmail.com','1987-06-30');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at)VALUES(8,'Vanessa','Ecos','vanessa.Ecos@gmail.com','1984-05-09');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at)VALUES(5,'Jessica','Nunez','Jessica.Nunez@gmail.com','1988-06-06');

INSERT INTO usuarios(username,password,enabled,nombre,apellido,email) VALUES('lennin','$2a$10$Tc6TQvXIt7aKfWPkjUHucOC6jK.pM2AfpCmC3THCXRv8EKf0KLrJe',1,'lennin','davila','lennin12@hotmail.com');
INSERT INTO usuarios(username,password,enabled,nombre,apellido,email) VALUES('admin','$2a$10$k/t1I.dROMoLzXmJHxRQF.danADXAYvCXByyDh3hsHDKBIgD9f1h2',1,'adm','adm','adm@hotmail.com');

INSERT INTO roles(nombre) VALUES('ROLE_USER');
INSERT INTO roles(nombre) VALUES('ROLE_ADMIN');

INSERT INTO usuarios_roles(usuario_id,role_id) VALUES(1,1);
INSERT INTO usuarios_roles(usuario_id,role_id) VALUES(2,2);
INSERT INTO usuarios_roles(usuario_id,role_id) VALUES(2,1);

/* Populate tabla productos */
INSERT INTO productos (nombre, precio, create_at) VALUES('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Camara digital DSC-W320B', 123490, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Apple iPod shuffle', 1499990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Notebook Z110', 37990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Mica Comoda 5 Cajones', 299990, NOW());

/* Creamos algunas facturas */
INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura equipos de oficina', null, 1, NOW());

INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 5);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 7);

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura Bicicleta', 'Alguna nota importante!', 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(3, 2, 6);