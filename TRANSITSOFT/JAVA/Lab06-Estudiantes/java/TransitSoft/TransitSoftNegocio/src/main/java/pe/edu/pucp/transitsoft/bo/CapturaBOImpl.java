package pe.edu.pucp.transitsoft.bo;

import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.transitsoft.dao.CapturaDAO;
import pe.edu.pucp.transitsoft.daoimpl.CapturaDAOImpl;
import pe.edu.pucp.transitsoft.modelo.Captura;

public class CapturaBOImpl implements CapturaBO {
    private final CapturaDAO capturaDao;
    
    public CapturaBOImpl() {
        this.capturaDao = new CapturaDAOImpl();
    }
    
    @Override
    public ArrayList<Captura> obtenerCapturasConExcesoDeVelocidad() {
        ArrayList<Captura> capturas = capturaDao.listarTodos();
        ArrayList<Captura> capturasConExc = new ArrayList<>();
        for(Captura c: capturas){
            if (c.getVelocidad()>50)
                capturasConExc.add(c);
        }
        return capturasConExc;
    }

    @Override
    public void actualizar(Captura modelo) {
        capturaDao.actualizar(modelo);
    }
}
