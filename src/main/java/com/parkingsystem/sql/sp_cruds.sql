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

