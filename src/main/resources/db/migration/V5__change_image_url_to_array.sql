-- V2__change_image_url_to_array.sql

-- Paso 1: Renombrar la columna actual para conservar datos anteriores (opcional)
ALTER TABLE emeralds RENAME COLUMN image_url TO old_image_url;

-- Paso 2: Crear nueva columna como arreglo
ALTER TABLE emeralds ADD COLUMN image_urls VARCHAR(255)[] DEFAULT '{}';

-- Paso 3: Copiar los datos de image_url en el nuevo campo como arreglo de un solo elemento
UPDATE emeralds
SET image_urls = ARRAY[old_image_url]
WHERE old_image_url IS NOT NULL;

-- Paso 4: Eliminar columna anterior
ALTER TABLE emeralds DROP COLUMN old_image_url;
