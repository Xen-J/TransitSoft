package pe.edu.pucp.transitsoft.bo;

import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.transitsoft.dto.Infraccion;
import pe.edu.pucp.transitsoft.modelo.Captura;

/**
 *
 * @author eric
 */
public interface InfraccionBO {
    List<Infraccion> crearInfracciones(ArrayList<Captura> capturasConExceso);
}
