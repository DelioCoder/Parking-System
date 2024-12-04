/**
 * Author:  david
 * Created: 29 nov. 2024
 */

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

--

CREATE PROCEDURE ListarAbonados
    @filter NVARCHAR(50),
	@id INT = NULL
AS
BEGIN
        
    IF @filter = 'codigo'
        SELECT 
            a.id_abo AS Id_Abonado,
            a.fecha_inicio_abo AS Fecha_Inicio_Abo,
            a.fecha_fin_abo AS Fecha_Fin_Abo,
            v.placa_veh AS Placa_Veh,
            t.nombre AS Abonado
        FROM 
            abonado a
        INNER JOIN 
            vehiculo v ON a.id_vehiculo = v.id_veh
        INNER JOIN 
            tipo_abonado t ON a.id_tipo_abonado = t.id_tipo_abo
        WHERE 
            a.id_abo = @id 
    ELSE
        SELECT 
            a.id_abo AS Id_Abonado,
            a.fecha_inicio_abo AS Fecha_Inicio_Abo,
            a.fecha_fin_abo AS Fecha_Fin_Abo,
            v.placa_veh AS Placa_Veh,
            t.nombre AS Abonado
        FROM 
            abonado a
        INNER JOIN 
            vehiculo v ON a.id_vehiculo = v.id_veh
        INNER JOIN 
            tipo_abonado t ON a.id_tipo_abonado = t.id_tipo_abo;
END;
GO

CREATE PROCEDURE AgregarAbonado
    @fecha_inicio_abo NVARCHAR(50),
    @fecha_fin_abo NVARCHAR(50),
    @id_vehiculo int,
    @id_tipo_abonado int
AS
BEGIN
    INSERT INTO abonado (fecha_inicio_abo, fecha_fin_abo, id_vehiculo, id_tipo_abonado)
    VALUES (@fecha_inicio_abo, @fecha_fin_abo, @id_vehiculo, @id_tipo_abonado);
END;

CREATE PROCEDURE ActualizarAbonado
    @id_abo INT,
    @fecha_inicio_abo NVARCHAR(50),
    @fecha_fin_abo NVARCHAR(50),
    @id_vehiculo int,
    @id_tipo_abonado int
AS
BEGIN
    UPDATE abonado
    SET 
        fecha_inicio_abo = @fecha_inicio_abo,
        fecha_fin_abo = @fecha_fin_abo,
        id_vehiculo = @id_vehiculo,
        id_tipo_abonado = @id_tipo_abonado
    WHERE id_abo = @id_abo;
END;

CREATE PROCEDURE EliminarAbonado
    @id_abo INT
AS
BEGIN
    DELETE FROM abonado WHERE id_abo = @id_abo;
END;


CREATE PROCEDURE ObtenerTipoAbonado
AS
BEGIN
    SELECT id_tipo_abo, nombre, monto
    FROM Tipo_Abonado;
END;

CREATE PROCEDURE ListarBoletas
AS
BEGIN
    SELECT 
        c.nombre_cond AS Conductor,
        c.dni_cond AS DNI,
        c.telefono_cond AS Telefono,
        v.placa_veh AS Placa,
        v.color_veh AS 'Color de vehiculo',
        t.fecha_entrada AS 'Hora de entrada',
        b.hora_salida AS 'Hora de Salida',
        b.monto_pago AS 'Monto',
        z.nombre_espacio AS Zona,
        p.numero_piso_est AS Piso
    FROM 
        Boleta_Pago b
    JOIN 
        Ticket_Estacionamiento t ON b.id_ticket = t.id_ticket
    JOIN 
        Vehiculo v ON t.id_veh = v.id_veh
    JOIN 
        Conductor c ON v.id_cond = c.id_cond
    JOIN 
        Zona_Estacionamiento z ON t.id_zona_est = z.numero_espacio
    JOIN 
        Piso_estacionamiento p ON z.id_piso_est = p.numero_piso_est;
END;

CREATE PROCEDURE AgregarBoleta
    @fecha_pago NVARCHAR(50),
    @hora_salida NVARCHAR(50),
    @monto_pago FLOAT,
    @metodo_pago NVARCHAR(50),
    @id_ticket INT
AS
BEGIN
    INSERT INTO Boleta_Pago (fecha_pago, hora_salida, monto_pago, metodo_pago, id_ticket)
    VALUES (@fecha_pago, @hora_salida, @monto_pago, @metodo_pago, @id_ticket);
END;
GO

CREATE PROCEDURE ActualizarBoleta
    @id_boleta_pago INT,
    @fecha_pago NVARCHAR(50),
    @hora_salida NVARCHAR(50),
    @monto_pago FLOAT,
    @metodo_pago NVARCHAR(50),
    @id_ticket INT
AS
BEGIN
    UPDATE Boleta_Pago
    SET 
        fecha_pago = @fecha_pago,
        hora_salida = @hora_salida,
        monto_pago = @monto_pago,
        metodo_pago = @metodo_pago,
        id_ticket = @id_ticket
    WHERE 
        id_boleta_pago = @id_boleta_pago;
END;
GO

CREATE PROCEDURE EliminarBoleta
    @id_boleta_pago INT
AS
BEGIN
    DELETE FROM Boleta_Pago
    WHERE id_boleta_pago = @id_boleta_pago;
END;

