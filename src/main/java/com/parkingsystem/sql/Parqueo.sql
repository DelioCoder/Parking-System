CREATE DATABASE dbparqueo;

USE dbparqueo;


CREATE TABLE conductor (  
    id_cond INT IDENTITY(1,1) PRIMARY KEY,  
    nombre_cond VARCHAR(100),  
    apellido_cond VARCHAR(100),  
    dni_cond INT,  
    telefono_cond INT  
);  

CREATE TABLE Vehiculo (
    id_veh INT IDENTITY(1,1) PRIMARY KEY,  
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
    id_abo INT IDENTITY(1,1) PRIMARY KEY,  
    fecha_inicio_abo DATETIME,
    fecha_fin_abo DATETIME,
    tipo_abo VARCHAR(15),
    monto_abo DECIMAL(8, 4)
);

CREATE TABLE Tarjeta_Abonado (
    id_tarj_abo INT IDENTITY(1,1) PRIMARY KEY,  
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
    id_piso_est INT IDENTITY(1,1) PRIMARY KEY,  
    numero_piso_est INT,
    capacidad_piso_est INT
);

CREATE TABLE Zona_Estacionamiento (
    id_zona_est INT IDENTITY(1,1) PRIMARY KEY,  
    numero_espacio INT,
    estado_espacio VARCHAR(15),
    id_piso_est INT
);

ALTER TABLE Zona_Estacionamiento
ADD CONSTRAINT FK_Zona_Estacionamiento_Piso_estacionamiento
FOREIGN KEY (id_piso_est) REFERENCES Piso_estacionamiento(id_piso_est);

CREATE TABLE Ticket_Estacionamiento (
    id_ticket INT IDENTITY(1,1) PRIMARY KEY,  
    hora_entrada DATETIME,
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
    id_boleta_pago INT IDENTITY(1,1) PRIMARY KEY,  
    fecha_pago DATETIME,
    monto_pago DECIMAL(8, 4),
    metodo_pago VARCHAR(15),
    hora_salida DATETIME,
    id_ticket INT
);

ALTER TABLE Boleta_Pago
ADD CONSTRAINT FK_Boleta_Pago_Ticket_Estacionamiento
FOREIGN KEY (id_ticket) REFERENCES Ticket_Estacionamiento(id_ticket);



/********** INSERTS *************/


INSERT INTO conductor VALUES ('Pedro', 'Ramirez', 72310841, 987361375);
INSERT INTO conductor VALUES ('Alexis', 'Sotelo', 72310842, 987361371);  
INSERT INTO conductor VALUES ('Jimena', 'Luna', 72310843, 987361379);  

select * from conductor;


INSERT INTO Vehiculo (placa_veh, color_veh, marca_veh, modelo_veh, id_cond, año_veh)  
VALUES   
    ('ABC123', 'Rojo', 'Toyota', 'Corolla', 1, '2020-01-15'),  -- Vehículo asociado al conductor con id_cond = 1  
    ('DEF456', 'Azul', 'Honda', 'Civic', 2, '2021-06-10'),     -- Vehículo asociado al conductor con id_cond = 2  
    ('GHI789', 'Negro', 'Ford', 'Focus', 3, '2019-03-22');      -- Vehículo asociado al conductor con id_cond = 3

select * from Vehiculo;

INSERT INTO Piso_estacionamiento VALUES (1, 30);
INSERT INTO Piso_estacionamiento VALUES (2, 30);
INSERT INTO Piso_estacionamiento VALUES (3, 30);

select * from Piso_estacionamiento


INSERT INTO Zona_Estacionamiento (numero_espacio, estado_espacio, id_piso_est)  
VALUES   
    (1, 'Disponible', 1),   -- Espacio 1 en el piso 1  
    (2, 'Ocupado', 2),      -- Espacio 2 en el piso 2  
    (3, 'Disponible', 3);   -- Espacio 3 en el piso 3  

select * from Zona_Estacionamiento;


INSERT INTO Ticket_Estacionamiento (hora_entrada, estado_ticket, id_veh, id_zona_est)  
VALUES   
    ('2024-10-29 08:00:00', 'Activo', 1, 1),  -- Ticket para el vehículo con id_veh = 1 en la zona con id_zona_est = 1  
    ('2024-10-29 09:30:00', 'Activo', 2, 2),  -- Ticket para el vehículo con id_veh = 2 en la zona con id_zona_est = 2  
    ('2024-10-29 10:15:00', 'Activo', 3, 3);  -- Ticket para el vehículo con id_veh = 3 en la zona con id_zona_est = 3  


select * from Ticket_Estacionamiento;


INSERT INTO Boleta_Pago (fecha_pago, monto_pago, metodo_pago, hora_salida, id_ticket)  
VALUES   
    ('2024-10-29 12:00:00', 20.00, 'Efectivo', '2024-10-29 12:00:00', 1),  -- Boleta para el ticket con id_ticket = 1  
    ('2024-10-29 12:30:00', 45.50, 'Tarjeta', '2024-10-29 12:30:00', 2),  -- Boleta para el ticket con id_ticket = 2  
    ('2024-10-29 13:00:00', 30.50, 'Yape', '2024-10-29 13:00:00', 3);  -- Boleta para el ticket con id_ticket = 3

select * from Boleta_Pago;