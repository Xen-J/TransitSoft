package pe.edu.pucp.transitsoft.bo;

import java.util.ArrayList;
import pe.edu.pucp.transitsoft.modelo.Captura;

public interface CapturaBO {
    ArrayList<Captura> obtenerCapturasConExcesoDeVelocidad();
    void actualizar(Captura modelo);
}
