CREATE PROCEDURE sp_consulta_placa
    @id_veh int
AS
BEGIN


	SELECT   
		c.nombre_cond AS NombreConductor,  
		c.apellido_cond AS ApellidoConductor,  
		c.dni_cond AS DNICoche,  
		c.telefono_cond AS TelefonoConductor,  
    
		v.placa_veh AS PlacaVehiculo,  
		v.color_veh AS ColorVehiculo,  
		v.marca_veh AS MarcaVehiculo,  
		v.modelo_veh AS ModeloVehiculo,  
		v.año_veh AS AñoVehiculo,  

		pe.numero_piso_est AS NumeroPisoEstacionamiento,  
		pe.capacidad_piso_est AS CapacidadPisoEstacionamiento,  

		ze.nombre_espacio AS NombreEspacio,  
		ze.numero_espacio AS NumeroEspacio,  
		ze.estado_espacio AS EstadoEspacio,  

		te.fecha_entrada AS FechaEntrada,  
		te.hora_entrada AS HoraEntrada,  
		te.estado_ticket AS EstadoTicket,  

		bp.fecha_pago AS FechaPago,  
		bp.monto_pago AS MontoPago,  
		bp.metodo_pago AS MetodoPago,  
		bp.hora_salida AS HoraSalida  

	FROM   
		conductor c  
	JOIN   
		Vehiculo v ON c.id_cond = v.id_cond
	JOIN   
		Zona_Estacionamiento ze ON ze.id_zona_est = (SELECT id_zona_est FROM Ticket_Estacionamiento WHERE id_veh = v.id_veh)  
	JOIN   
		Ticket_Estacionamiento te ON te.id_veh = v.id_veh  
	JOIN   
		Boleta_Pago bp ON bp.id_ticket = te.id_ticket  
	JOIN   
		Piso_estacionamiento pe ON ze.id_piso_est = pe.id_piso_est

	where v.id_veh = @id_veh;
END;

EXEC sp_consulta_placa 'ABC123';