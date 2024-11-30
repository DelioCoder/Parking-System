/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  John
 * Created: 28 nov. 2024
 */

CREATE PROCEDURE spActualizarConductor
    @id_cond INT,
    @nombre_cond NVARCHAR(100),
    @apellido_cond NVARCHAR(100),
    @dni_cond INT,
    @telefono_cond BIGINT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @output_message NVARCHAR(100);

    -- Verificar si el registro existe
    IF EXISTS (SELECT 1 FROM conductor WHERE id_cond = @id_cond)
    BEGIN
        UPDATE conductor
        SET nombre_cond = @nombre_cond,
            apellido_cond = @apellido_cond,
            dni_cond = @dni_cond,
            telefono_cond = @telefono_cond
        WHERE id_cond = @id_cond;

        SET @output_message = 'El registro se actualizó exitosamente.';
    END
    ELSE
    BEGIN
        SET @output_message = 'No se encontró ningún registro con el ID proporcionado.';
    END

    PRINT 'ID Cond: ' + CAST(@id_cond AS VARCHAR);
    PRINT @output_message;
END;



CREATE PROCEDURE spCrearConductor
    @nombre_cond NVARCHAR(40),
    @apellido_cond NVARCHAR(40),
    @dni_cond NVARCHAR(10),
    @telefono_cond NVARCHAR(10),
    @estado BIT -- Estado del conductor (activo o inactivo)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @output_message NVARCHAR(100);

    -- Insertar un nuevo conductor en la tabla (sin incluir id_cond, ya que es auto-generado)
    INSERT INTO conductor (nombre_cond, apellido_cond, dni_cond, telefono_cond, estado)
    VALUES (@nombre_cond, @apellido_cond, @dni_cond, @telefono_cond, @estado);

    -- Confirmar la inserción
    SET @output_message = 'El conductor se creó exitosamente.';

    -- Imprimir el mensaje de confirmación
    PRINT @output_message;
END;


CREATE PROCEDURE ActualizarEstadoConductor
    @id_cond INT,       -- ID del conductor que se desea actualizar
    @nuevo_estado BIT   -- Nuevo valor para la columna estado (0 o 1)
AS
BEGIN
    -- Manejo de errores y asegurando una transacción consistente
    BEGIN TRY
        BEGIN TRANSACTION;

        -- Actualización del estado del conductor
        UPDATE conductor
        SET estado = @nuevo_estado
        WHERE id_cond = @id_cond;

        -- Verificar si se actualizó alguna fila
        IF @@ROWCOUNT = 0
        BEGIN
            -- Si no se encuentra el conductor, se arroja un error personalizado
            THROW 50001, 'Conductor no encontrado con el ID especificado.', 1;
        END

        -- Confirmar la transacción
        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        -- Si ocurre un error, se revierte la transacción
        ROLLBACK TRANSACTION;

        -- Lanzar el error para que el cliente lo reciba
        THROW;
    END CATCH
END;



/************ VEHICULO ********/

CREATE PROCEDURE spObtenerVehiculosPorPlaca
    @placa_veh VARCHAR(10) -- Parámetro para filtrar por patrón de placa
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
        c.id_cond,          -- Incluimos el ID del conductor
        c.nombre_cond, 
        c.apellido_cond, 
        c.dni_cond
    FROM 
        Vehiculo v
    JOIN 
        Conductor c
    ON 
        v.id_cond = c.id_cond
    WHERE 
        v.placa_veh LIKE '%' + @placa_veh + '%' -- Búsqueda parcial con comodines
        AND v.estado = 1; -- Solo vehículos activos

END;

CREATE PROCEDURE spCrearVehiculo
    @placa_veh VARCHAR(10),   -- Placa del vehículo
    @color_veh VARCHAR(50),   -- Color del vehículo
    @marca_veh VARCHAR(50),   -- Marca del vehículo
    @modelo_veh VARCHAR(50),  -- Modelo del vehículo
    @año_veh VARCHAR(50),        -- Año del vehículo
    @id_cond INT,             -- ID del conductor asociado
    @estado BIT               -- Estado del vehículo (0 = Inactivo, 1 = Activo)
AS
BEGIN
    SET NOCOUNT ON;

    -- Mensaje de salida para confirmar si la operación fue exitosa
    DECLARE @output_message NVARCHAR(100);

    BEGIN TRY
        -- Insertar un nuevo vehículo en la tabla
        INSERT INTO Vehiculo (placa_veh, color_veh, marca_veh, modelo_veh, año_veh, id_cond, estado)
        VALUES (@placa_veh, @color_veh, @marca_veh, @modelo_veh, @año_veh, @id_cond, @estado);

        SET @output_message = 'El vehículo se creó exitosamente.';
    END TRY
    BEGIN CATCH
        -- Manejo de errores
        SET @output_message = 'Error al intentar crear el vehículo: ' + ERROR_MESSAGE();
    END CATCH

    -- Imprimir mensaje de salida
    PRINT @output_message;
END;


CREATE PROCEDURE spActualizarVehiculo
    @id_veh INT,              -- ID del vehículo a actualizar
    @placa_veh VARCHAR(10),   -- Nueva placa del vehículo
    @color_veh VARCHAR(50),   -- Nuevo color del vehículo
    @marca_veh VARCHAR(50),   -- Nueva marca del vehículo
    @modelo_veh VARCHAR(50),  -- Nuevo modelo del vehículo
    @año_veh VARCHAR(50),        -- Nuevo año del vehículo
    @id_cond INT              -- Nuevo ID del conductor asociado
AS
BEGIN
    SET NOCOUNT ON;

    -- Mensaje de salida para confirmar si la operación fue exitosa
    DECLARE @output_message NVARCHAR(100);

    BEGIN TRY
        -- Verificar si el registro existe
        IF EXISTS (SELECT 1 FROM Vehiculo WHERE id_veh = @id_veh)
        BEGIN
            -- Actualizar los datos del vehículo
            UPDATE Vehiculo
            SET 
                placa_veh = @placa_veh,
                color_veh = @color_veh,
                marca_veh = @marca_veh,
                modelo_veh = @modelo_veh,
                año_veh = @año_veh,
                id_cond = @id_cond -- Asociar el nuevo conductor
            WHERE 
                id_veh = @id_veh;

            SET @output_message = 'El vehículo se actualizó exitosamente.';
        END
        ELSE
        BEGIN
            -- Si no existe el vehículo, devolver un mensaje de error
            SET @output_message = 'No se encontró ningún vehículo con el ID proporcionado.';
        END
    END TRY
    BEGIN CATCH
        -- Manejo de errores
        SET @output_message = 'Error al intentar actualizar el vehículo: ' + ERROR_MESSAGE();
    END CATCH

    -- Imprimir mensaje de salida
    PRINT @output_message;
END;

CREATE PROCEDURE ActualizarEstadoVehiculo
    @id_veh INT,       -- ID del vehículo que se desea actualizar
    @nuevo_estado BIT  -- Nuevo valor para la columna estado (0 o 1)
AS
BEGIN
    -- Manejo de errores y asegurando una transacción consistente
    BEGIN TRY
        BEGIN TRANSACTION;

        -- Actualización del estado del vehículo
        UPDATE Vehiculo
        SET estado = @nuevo_estado
        WHERE id_veh = @id_veh;

        -- Verificar si se actualizó alguna fila
        IF @@ROWCOUNT = 0
        BEGIN
            -- Si no se encuentra el vehículo, se arroja un error personalizado
            THROW 50001, 'Vehículo no encontrado con el ID especificado.', 1;
        END

        -- Confirmar la transacción
        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        -- Si ocurre un error, se revierte la transacción
        ROLLBACK TRANSACTION;

        -- Lanzar el error para que el cliente lo reciba
        THROW;
    END CATCH
END;

