CREATE DATABASE dbparqueo;

USE dbparqueo;

CREATE TABLE conductor (
    id_cond INT PRIMARY KEY,
    nombre_cond VARCHAR(100),
    apellido_cond VARCHAR(100),
    dni_cond INT,
    telefono_cond INT
);

CREATE TABLE Vehiculo (
    id_veh INT PRIMARY KEY,
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
    id_abo INT PRIMARY KEY,
    fecha_inicio_abo DATETIME,
    fecha_fin_abo DATETIME,
    tipo_abo VARCHAR(15),
    monto_abo DECIMAL(8, 4)
);

CREATE TABLE Tarjeta_Abonado (
    id_tarj_abo INT PRIMARY KEY,
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
    id_piso_est INT PRIMARY KEY,
    numero_piso_est INT,
    capacidad_piso_est INT
);

CREATE TABLE Zona_Estacionamiento (
    id_zona_est INT PRIMARY KEY,
    numero_espacio INT,
    estado_espacio VARCHAR(15),
    id_piso_est INT
);

ALTER TABLE Zona_Estacionamiento
ADD CONSTRAINT FK_Zona_Estacionamiento_Piso_estacionamiento
FOREIGN KEY (id_piso_est) REFERENCES Piso_estacionamiento(id_piso_est);

CREATE TABLE Ticket_Estacionamiento (
    id_ticket INT PRIMARY KEY,
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
    id_boleta_pago INT PRIMARY KEY,
    fecha_pago DATETIME,
    monto_pago DECIMAL(8, 4),
    metodo_pago VARCHAR(15),
    hora_salida DATETIME,
    id_ticket INT
);

ALTER TABLE Boleta_Pago
ADD CONSTRAINT FK_Boleta_Pago_Ticket_Estacionamiento
FOREIGN KEY (id_ticket) REFERENCES Ticket_Estacionamiento(id_ticket);
