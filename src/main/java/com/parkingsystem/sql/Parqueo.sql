CREATE DATABASE dbparqueo;

USE dbparqueo;

CREATE TABLE conductor (
    id_cond INT PRIMARY KEY IDENTITY,
    nombre_cond VARCHAR(100),
    apellido_cond VARCHAR(100),
    dni_cond INT,
    telefono_cond INT
);

CREATE TABLE Vehiculo (
    id_veh INT PRIMARY KEY IDENTITY,
    placa_veh VARCHAR(10),
    color_veh VARCHAR(50),
    marca_veh VARCHAR(50),
    modelo_veh VARCHAR(50),
    id_cond INT,
    año_veh DATETIME
);

ALTER TABLE Vehiculo
ADD CONSTRAINT FK_Vehiculo_Conductor 
FOREIGN KEY (id_cond) REFERENCES conductor(id_cond);

CREATE TABLE Abonado (
    id_abo INT PRIMARY KEY IDENTITY,
    fecha_inicio_abo DATETIME,
    fecha_fin_abo DATETIME,
    tipo_abo VARCHAR(15),
    monto_abo DECIMAL(8, 4)
);

CREATE TABLE Tarjeta_Abonado (
    id_tarj_abo INT PRIMARY KEY IDENTITY,
    fecha_emision_tarj_abo DATETIME,
    fecha_expiracion_tarj_abo DATETIME,
    id_abo INT,
    id_veh INT
);

ALTER TABLE Tarjeta_Abonado
ADD CONSTRAINT FK_Tarjeta_Abonado_Abonado
FOREIGN KEY (id_abo) REFERENCES Abonado(id_abo);

ALTER TABLE Tarjeta_Abonado
ADD CONSTRAINT FK_Tarjeta_Abonado_Vehiculo
FOREIGN KEY (id_veh) REFERENCES Vehiculo(id_veh);

CREATE TABLE Piso_estacionamiento (
    id_piso_est INT PRIMARY KEY IDENTITY,
    numero_piso_est INT,
    capacidad_piso_est INT
);

CREATE TABLE Zona_Estacionamiento (
    id_zona_est INT PRIMARY KEY IDENTITY,
    nombre_espacio varchar(8),
    numero_espacio INT,
    estado_espacio VARCHAR(15),
    id_piso_est INT
);

ALTER TABLE Zona_Estacionamiento
ADD CONSTRAINT FK_Zona_Estacionamiento_Piso_estacionamiento
FOREIGN KEY (id_piso_est) REFERENCES Piso_estacionamiento(id_piso_est);

CREATE TABLE Ticket_Estacionamiento (
    id_ticket INT PRIMARY KEY IDENTITY,
    fecha_entrada varchar(15),
    hora_entrada varchar(15),
    estado_ticket VARCHAR(15),
    id_veh INT,
    id_zona_est INT
);

ALTER TABLE Ticket_Estacionamiento
ADD CONSTRAINT FK_Ticket_Estacionamiento_Vehiculo
FOREIGN KEY (id_veh) REFERENCES Vehiculo(id_veh);

ALTER TABLE Ticket_Estacionamiento
ADD CONSTRAINT FK_Ticket_Estacionamiento_Zona_Estacionamiento
FOREIGN KEY (id_zona_est) REFERENCES Zona_Estacionamiento(id_zona_est);

CREATE TABLE Boleta_Pago (
    id_boleta_pago INT PRIMARY KEY IDENTITY,
    fecha_pago DATETIME,
    monto_pago DECIMAL(8, 4),
    metodo_pago VARCHAR(15),
    hora_salida DATETIME,
    id_ticket INT
);

ALTER TABLE Boleta_Pago
ADD CONSTRAINT FK_Boleta_Pago_Ticket_Estacionamiento
FOREIGN KEY (id_ticket) REFERENCES Ticket_Estacionamiento(id_ticket);

INSERT INTO Piso_estacionamiento (numero_piso_est, capacidad_piso_est)
VALUES
(1, 20),
(2, 25),
(3, 30),
(4, 35),
(5, 40)

INSERT INTO Zona_Estacionamiento (numero_espacio, nombre_espacio, estado_espacio, id_piso_est)
VALUES
(1, 'A1', 'Disponible', 1),
(2, 'A2', 'Ocupado', 1),
(3, 'A3', 'Disponible', 2),
(4, 'A4', 'Ocupado', 2),
(5, 'A5', 'Disponible', 3),
(6, 'A6', 'Ocupado', 3),
(7, 'A7', 'Disponible', 4),
(8, 'A8', 'Ocupado', 4),
(9, 'A9', 'Disponible', 5),
(10, 'A10', 'Ocupado', 5);	

INSERT INTO conductor (nombre_cond, apellido_cond, dni_cond, telefono_cond) VALUES
('Juan', 'Pérez', 12345678, 987654321),
('María', 'González', 87654321, 912345678),
('Carlos', 'Ramírez', 45678912, 923456789),
('Ana', 'López', 34567891, 934567890),
('Luis', 'Torres', 56789123, 945678901);

INSERT INTO vehiculo (placa_veh, color_veh, marca_veh, modelo_veh, año_veh, id_cond) VALUES
('ABC123', 'Rojo', 'Toyota', 'Corolla', '2022', 1),
('XYZ789', 'Azul', 'Honda', 'Civic', '2021', 2),
('LMN456', 'Negro', 'Ford', 'Focus', '2020', 3),
('PQR678', 'Blanco', 'Chevrolet', 'Cruze', '2019', 4),
('GHI321', 'Gris', 'Mazda', '3', '2018', 5);

INSERT INTO Boleta_Pago (fecha_pago, monto_pago, metodo_pago, hora_salida, id_ticket) VALUES
('2024-10-29 01:19', 25.00, 'Efectivo', '2024-10-29 01:19', 1);

INSERT INTO Boleta_Pago (fecha_pago, monto_pago, metodo_pago, hora_salida, id_ticket) VALUES
('2024-10-03 18:45', 20.50, 'Tarjeta', '2024-10-03 18:45', 2);

INSERT INTO Boleta_Pago (fecha_pago, monto_pago, metodo_pago, hora_salida, id_ticket) VALUES
('2024-09-15 10:30', 15.75, 'Efectivo', '2024-09-15 10:30', 1);

INSERT INTO Boleta_Pago (fecha_pago, monto_pago, metodo_pago, hora_salida, id_ticket) VALUES
('2024-10-01 14:00', 18.90, 'Tarjeta', '2024-10-01 14:00', 2);

INSERT INTO Boleta_Pago (fecha_pago, monto_pago, metodo_pago, hora_salida, id_ticket) VALUES
('2024-10-10 09:45', 22.00, 'Efectivo', '2024-10-10 09:45', 2);