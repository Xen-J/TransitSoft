package pe.edu.pucp.transitsoft.bo;

import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.transitsoft.estrategias.AnalizadorDeVelocidad;
import pe.edu.pucp.transitsoft.estrategias.Estrategia;
import pe.edu.pucp.transitsoft.estrategias.EstrategiaViaRapida;
import pe.edu.pucp.transitsoft.estrategias.EstrategiaViaRegular;
import pe.edu.pucp.transitsoft.modelo.Captura;
import pe.edu.pucp.transitsoft.dao.CapturaDAO;
import pe.edu.pucp.transitsoft.daoimpl.CapturaDAOImpl;

public class CapturaBOImpl implements CapturaBO {
    private final CapturaDAO capturaDao;
    private final EstrategiaViaRapida estrategiaViaRapida;
    private final EstrategiaViaRegular estrategiaViaRegular;
    private static final List<String> CAMARAS_VIA_RAPIDA = 
            List.of("CAM-LIM-002");
    
    public CapturaBOImpl() {
        this.capturaDao = new CapturaDAOImpl();
        this.estrategiaViaRapida = new EstrategiaViaRapida();
        this.estrategiaViaRegular = new EstrategiaViaRegular();
    }
    
    @Override
    public List<Captura> obtenerCapturasConExcesoDeVelocidad() {
        ArrayList<Captura> capturas = capturaDao.listarTodos();
        ArrayList<Captura> capturasConExceso = new ArrayList<>();
        for(Captura c: capturas){
            AnalizadorDeVelocidad aVel = analizador(c);
            if(aVel.evaluarExceso(c))
                capturasConExceso.add(c);
            actualizar(c);
            } 
        return capturasConExceso;
    }
    
    @Override
    public void actualizar(Captura captura) {
        capturaDao.actualizar(captura);
    }
    
    protected AnalizadorDeVelocidad analizador(Captura captura) {
        String codigo = captura.getCamara().getCodigoSerie();
        Estrategia estrategia = CAMARAS_VIA_RAPIDA.contains(codigo)
                ? estrategiaViaRapida
                : estrategiaViaRegular;

        return new AnalizadorDeVelocidad(estrategia);
    }
}
