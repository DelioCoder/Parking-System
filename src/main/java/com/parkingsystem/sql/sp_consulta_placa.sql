CREATE PROCEDURE sp_consulta_placa
    @id_veh INT 
AS
BEGIN
    SET NOCOUNT ON;

    SELECT 
        v.id_veh,
        v.placa_veh,
        v.color_veh,
        v.marca_veh,
        v.modelo_veh,
        v.año_veh,
        v.estado AS estado_vehiculo,
        c.id_cond,
        c.nombre_cond,
        c.apellido_cond,
        c.dni_cond,
        c.telefono_cond,
        c.estado AS estado_conductor,
        a.id_abo,
        a.fecha_inicio_abo,
        a.fecha_fin_abo,
        t.id_tipo_abo,
        t.nombre AS nombre_tipo_abonado,
        t.monto AS monto_tipo_abonado,
        p.id_piso_est,
        p.numero_piso_est,
        p.capacidad_piso_est,
        z.id_zona_est,
        z.nombre_espacio,
        z.numero_espacio,
        z.estado_espacio,
        te.id_ticket,
        te.fecha_entrada,
        te.hora_entrada,
        te.estado_ticket,
        b.id_boleta_pago,
        b.fecha_pago,
        b.monto_pago,
        b.metodo_pago,
        b.hora_salida
    FROM Vehiculo v
    LEFT JOIN conductor c ON v.id_cond = c.id_cond
    LEFT JOIN Abonado a ON v.id_veh = a.id_vehiculo
    LEFT JOIN Tipo_Abonado t ON a.id_tipo_abonado = t.id_tipo_abo
    LEFT JOIN Ticket_Estacionamiento te ON v.id_veh = te.id_veh
    LEFT JOIN Zona_Estacionamiento z ON te.id_zona_est = z.id_zona_est
    LEFT JOIN Piso_estacionamiento p ON z.id_piso_est = p.id_piso_est
    LEFT JOIN Boleta_Pago b ON te.id_ticket = b.id_ticket
    WHERE v.id_veh = @id_veh;
END;

EXEC sp_consulta_placa 1;
