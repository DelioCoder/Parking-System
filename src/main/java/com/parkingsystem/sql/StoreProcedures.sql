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
    @data NVARCHAR(MAX) = NULL
AS
BEGIN
    IF @filter = 'tipo'
        SELECT * FROM abonado WHERE tipo_abo = @data;
    ELSE IF @filter = 'monto'
        SELECT * FROM abonado WHERE monto_abo = CAST(@data AS FLOAT);
    ELSE
        SELECT * FROM abonado;
END;
GO

CREATE PROCEDURE AgregarAbonado
    @fecha_inicio_abo NVARCHAR(50),
    @fecha_fin_abo NVARCHAR(50),
    @tipo_abo NVARCHAR(50),
    @monto_abo FLOAT
AS
BEGIN
    INSERT INTO abonado (fecha_inicio_abo, fecha_fin_abo, tipo_abo, monto_abo)
    VALUES (@fecha_inicio_abo, @fecha_fin_abo, @tipo_abo, @monto_abo);
END;
GO

CREATE PROCEDURE ActualizarAbonado
    @id_abo INT,
    @fecha_inicio_abo NVARCHAR(50),
    @fecha_fin_abo NVARCHAR(50),
    @tipo_abo NVARCHAR(50),
    @monto_abo FLOAT
AS
BEGIN
    UPDATE abonado
    SET 
        fecha_inicio_abo = @fecha_inicio_abo,
        fecha_fin_abo = @fecha_fin_abo,
        tipo_abo = @tipo_abo,
        monto_abo = @monto_abo
    WHERE id_abo = @id_abo;
END;
GO

CREATE PROCEDURE EliminarAbonado
    @id_abo INT
AS
BEGIN
    DELETE FROM abonado WHERE id_abo = @id_abo;
END;
GO
