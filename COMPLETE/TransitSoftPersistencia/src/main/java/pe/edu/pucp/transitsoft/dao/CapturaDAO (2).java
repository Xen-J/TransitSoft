package pe.edu.pucp.transitsoft.dao;

import java.util.List;
import pe.edu.pucp.transitsoft.modelo.Captura;

// TODO: Definir Interfaz CapturaDAO
public interface CapturaDAO extends IDAO<Captura>{
    public List<Captura> leerTodos();
//    public boolean actualizar(Captura captura);
}
