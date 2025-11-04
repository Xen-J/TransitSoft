package pe.edu.pucp.transitsoft.dao;

import pe.edu.pucp.transitsoft.modelo.Captura;

public interface CapturaDAO extends IDAO<Captura>{
//    public List<Captura> leerTodos(); Equivalente a listarTodos(), así que no lo colocamos
    public boolean actualizar(Captura captura);
}
