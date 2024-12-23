CREATE DATABASE dbparqueo;

USE dbparqueo;

CREATE TABLE conductor (
    id_cond INT PRIMARY KEY IDENTITY, -- Identificador único
    nombre_cond VARCHAR(100),         -- Nombre del conductor
    apellido_cond VARCHAR(100),       -- Apellido del conductor
    dni_cond VARCHAR(20),                     -- DNI del conductor
    telefono_cond VARCHAR(20),                -- Teléfono del conductor
    estado BIT                        -- Estado (0 o 1, donde 0 es falso y 1 es verdadero)
);

CREATE TABLE Vehiculo (
    id_veh INT PRIMARY KEY IDENTITY,
    placa_veh VARCHAR(10),
    color_veh VARCHAR(50),
    marca_veh VARCHAR(50),
    modelo_veh VARCHAR(50),
    id_cond INT,
    año_veh VARCHAR(50),
    estado BIT
);
ALTER TABLE Vehiculo
ADD CONSTRAINT FK_Vehiculo_Conductor 
FOREIGN KEY (id_cond) REFERENCES conductor(id_cond);

CREATE TABLE Tipo_Abonado(
	id_tipo_abo INT PRIMARY KEY NOT NULL IDENTITY,
	nombre varchar(15),
	monto DECIMAL(4, 2)
);

CREATE TABLE Abonado (
    id_abo INT PRIMARY KEY IDENTITY,
    fecha_inicio_abo varchar(15),
    fecha_fin_abo varchar(15),
    tipo_abo VARCHAR(15),
    monto_abo DECIMAL(8, 2),
    id_vehiculo INT,
    id_tipo_abonado INT
);

ALTER TABLE Abonado
ADD CONSTRAINT FK_TipoAbonado
FOREIGN KEY (id_tipo_abonado) REFERENCES Tipo_Abonado(id_tipo_abo);

ALTER TABLE Abonado
ADD CONSTRAINT FK_ABONADO_VEHICULO
FOREIGN KEY (id_vehiculo) REFERENCES Vehiculo(id_veh);


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

ALTER TABLE Abonado
ADD CONSTRAINT FK_VehiculoAbonado
FOREIGN KEY (id_vehiculo) REFERENCES Vehiculo(id_veh);

CREATE TABLE Boleta_Pago (
    id_boleta_pago INT PRIMARY KEY IDENTITY,
    fecha_pago DATETIME,
    monto_pago DECIMAL(8, 4),
    metodo_pago VARCHAR(15),
    hora_salida TIME,
    id_ticket INT
);

ALTER TABLE Boleta_Pago ALTER COLUMN hora_salida TIME;

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

INSERT INTO conductor (nombre_cond, apellido_cond, dni_cond, telefono_cond, estado) VALUES
('Juan', 'Pérez', 12345678, 987654321, 1),
('María', 'González', 87654321, 912345678, 1),
('Carlos', 'Ramírez', 45678912, 923456789, 1),
('Ana', 'López', 34567891, 934567890, 1),
('Luis', 'Torres', 56789123, 945678901, 1);

INSERT INTO vehiculo (placa_veh, color_veh, marca_veh, modelo_veh, año_veh, id_cond, estado) VALUES
('ABC123', 'Rojo', 'Toyota', 'Corolla', '2022', 1, 1),
('XYZ789', 'Azul', 'Honda', 'Civic', '2021', 2, 1),
('LMN456', 'Negro', 'Ford', 'Focus', '2020', 3, 1),
('PQR678', 'Blanco', 'Chevrolet', 'Cruze', '2019', 4, 1),
('GHI321', 'Gris', 'Mazda', '3', '2018', 5, 1);


INSERT INTO Ticket_Estacionamiento (fecha_entrada, hora_entrada, estado_ticket, id_veh, id_zona_est)  
VALUES   
    ('2024-10-29', '08:00:00','ACTIVO', 1, 1),  -- Ticket para el veh�culo con id_veh = 1 en la zona con id_zona_est = 1  
    ('2024-10-28', '04:00:00','ACTIVO', 2, 2),  -- Ticket para el veh�culo con id_veh = 2 en la zona con id_zona_est = 2  
    ('2024-10-27', '05:00:00','PAGADO', 3, 3);  -- Ticket para el veh�culo con id_veh = 3 en la zona con id_zona_est = 3  


INSERT INTO Boleta_Pago (fecha_pago, monto_pago, metodo_pago, hora_salida, id_ticket)  
VALUES   
    ('2024-10-29 12:00:00', 20.00, 'Efectivo', '2024-10-29 12:00:00', 1),  -- Boleta para el ticket con id_ticket = 1  
    ('2024-10-29 12:30:00', 45.50, 'Tarjeta', '2024-10-29 12:30:00', 2),  -- Boleta para el ticket con id_ticket = 2  
    ('2024-10-29 13:00:00', 30.50, 'Tarjeta', '2024-10-29 13:00:00', 3);  -- Boleta para el ticket con id_ticket = 3


INSERT INTO Tipo_Abonado(nombre, monto) VALUES ('mensual', 30), ('trimestral', 15);


