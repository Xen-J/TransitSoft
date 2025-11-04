package pe.edu.pucp.transitsoft.estrategias;

import pe.edu.pucp.transitsoft.modelo.Captura;

/*
insert into camara(modelo, codigo_serie, latitud, longitud) values
('Hikvision', 'CAM-LIM-001', -12045678, -77034567), -- Vía Expresa Javier Prado
('Dahua', 'CAM-LIM-002', -12049876, -77038901),    -- Panamericana Sur (Surco)
('Axis', 'CAM-LIM-003', -12051234, -77035678);     -- Av. Universitaria (SMP)
*/

public interface Estrategia {
    boolean tieneExceso(Captura captura);
}
