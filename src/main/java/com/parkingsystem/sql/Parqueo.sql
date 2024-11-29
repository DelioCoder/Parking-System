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
    año_veh DATETIME,
    estado BIT
);

ALTER TABLE Vehiculo
ADD CONSTRAINT FK_Vehiculo_Conductor 
FOREIGN KEY (id_cond) REFERENCES conductor(id_cond);

CREATE TABLE Abonado (
    id_abo INT PRIMARY KEY IDENTITY,
    fecha_inicio_abo varchar(15),
    fecha_fin_abo varchar(15),
    tipo_abo VARCHAR(15),
    monto_abo DECIMAL(8, 2)
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


INSERT INTO Ticket_Estacionamiento (fecha_entrada, hora_entrada, estado_ticket, id_veh, id_zona_est)  
VALUES   
    ('2024-10-29', '08:00:00','Activo', 1, 1),  -- Ticket para el veh�culo con id_veh = 1 en la zona con id_zona_est = 1  
    ('2024-10-28', '04:00:00','Activo', 2, 2),  -- Ticket para el veh�culo con id_veh = 2 en la zona con id_zona_est = 2  
    ('2024-10-27', '05:00:00','Activo', 3, 3);  -- Ticket para el veh�culo con id_veh = 3 en la zona con id_zona_est = 3  


select * from Ticket_Estacionamiento;


INSERT INTO Boleta_Pago (fecha_pago, monto_pago, metodo_pago, hora_salida, id_ticket)  
VALUES   
    ('2024-10-29 12:00:00', 20.00, 'Efectivo', '2024-10-29 12:00:00', 1),  -- Boleta para el ticket con id_ticket = 1  
    ('2024-10-29 12:30:00', 45.50, 'Tarjeta', '2024-10-29 12:30:00', 2),  -- Boleta para el ticket con id_ticket = 2  
    ('2024-10-29 13:00:00', 30.50, 'Tarjeta', '2024-10-29 13:00:00', 3);  -- Boleta para el ticket con id_ticket = 3

--Procedimientos almacenados

CREATE PROCEDURE ListarTickets 
    @filter NVARCHAR(50), 
    @id INT = NULL
AS
BEGIN
    IF @filter = 'codigo'
        SELECT * FROM Ticket_Estacionamiento WHERE id_ticket = @id;
    ELSE
        SELECT * FROM Ticket_Estacionamiento;
END;
GO;


CREATE PROCEDURE AgregarTicket
    @fecha_entrada NVARCHAR(15),
    @hora_entrada NVARCHAR(15),
    @estado_ticket NVARCHAR(50),
    @id_veh INT,
    @id_zona_est INT
AS
BEGIN
    INSERT INTO Ticket_Estacionamiento (fecha_entrada, hora_entrada, estado_ticket, id_veh, id_zona_est)
    VALUES (@fecha_entrada, @hora_entrada, @estado_ticket, @id_veh, @id_zona_est);
END;

CREATE PROCEDURE ActualizarTicket
    @id_ticket INT,
    @fecha_entrada DATE,
    @hora_entrada TIME,
    @estado_ticket NVARCHAR(50),
    @id_veh INT,
    @id_zona_est INT
AS
BEGIN
    UPDATE Ticket_Estacionamiento
    SET fecha_entrada = @fecha_entrada,
        hora_entrada = @hora_entrada,
        estado_ticket = @estado_ticket,
        id_veh = @id_veh,
        id_zona_est = @id_zona_est
    WHERE id_ticket = @id_ticket;
END;

CREATE PROCEDURE EliminarTicket
    @id_ticket INT
AS
BEGIN
    DELETE FROM Ticket_Estacionamiento WHERE id_ticket = @id_ticket;
END;

--ALTER TABLE Abonado
--ALTER COLUMN fecha_inicio_abo VARCHAR(15);

--ALTER TABLE Abonado
--ALTER COLUMN fecha_fin_abo VARCHAR(15);
