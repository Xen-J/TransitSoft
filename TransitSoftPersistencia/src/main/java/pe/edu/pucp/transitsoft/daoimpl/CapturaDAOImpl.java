package pe.edu.pucp.transitsoft.daoimpl;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.transitsoft.config.DBManager;
import pe.edu.pucp.transitsoft.dao.CapturaDAO;
import pe.edu.pucp.transitsoft.modelo.Camara;
import pe.edu.pucp.transitsoft.modelo.Captura;
import pe.edu.pucp.transitsoft.modelo.EstadoCaptura;
//import pe.edu.pucp.transitsoft.modelo.EstadoCaptura;

public class CapturaDAOImpl implements CapturaDAO{
    private ResultSet rs;
    
    @Override
    public boolean actualizar(Captura captura) {
        int idCap = captura.getId();
        
        if (obtenerPorId(idCap) != null){ //obtenerPorId maneja las excepciones
            Map<Integer, Object> parametrosEntrada = new HashMap<>();
            parametrosEntrada.put(1, captura.getEstado().toString());
            parametrosEntrada.put(2, idCap);
            rs = DBManager.getInstance().ejecutarProcedimientoLectura(
                    "modificarEstadoCaptura", parametrosEntrada);
            return true; //Se encontró y se modificó
        }else
            return false; //No se encontró
    }
    
    @Override
    public int insertar(Captura captura) {
        Map<Integer,Object> parametrosSalida = new HashMap<>();   
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, captura.getCamara().getId());
        parametrosEntrada.put(3, captura.getPlaca());
        parametrosEntrada.put(4, captura.getVelocidad());
        parametrosEntrada.put(5, captura.getFechaCaptura());
        parametrosEntrada.put(6, captura.getEstado().toString());
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_CAPTURA", parametrosEntrada, parametrosSalida);
        captura.setId((int) parametrosSalida.get(1));
        System.out.println("Se ha realizado el registro del captura");
        return captura.getId();
    }
    
    @Override
    public int modificar(Captura captura) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, captura.getId());
        parametrosEntrada.put(2, captura.getCamara().getId());
        parametrosEntrada.put(3, captura.getPlaca());
        parametrosEntrada.put(4, captura.getVelocidad());
        parametrosEntrada.put(5, captura.getFechaCaptura());
        parametrosEntrada.put(6, captura.getEstado().toString());
        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_CAPTURA", parametrosEntrada, null);
        System.out.println("Se ha realizado la modificacion de la captura");
        return resultado;
    }

    @Override
    public int modificar2(Captura captura) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, captura.getEstado());
        parametrosEntrada.put(2,captura.getId());
        int resultado = DBManager.getInstance().ejecutarProcedimiento("modificarEstadoCaptura", parametrosEntrada, null);
        System.out.println("Se ha realizado la modificacion del captura");
        return resultado;
    }

    @Override
    public int eliminar(int idObjeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Captura obtenerPorId(int idCaptura) {
        Captura captura = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idCaptura);
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("OBTENER_CAPTURA_X_ID", parametrosEntrada);
        System.out.println("Lectura de captura...");
        try{
            while(rs.next()){
                if(captura == null) captura = new Captura();
                captura.setId(rs.getInt("id"));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return captura;
    }

    @Override
    public ArrayList<Captura> listarTodos() {
        ArrayList<Captura> capturas = null;
        DBManager.getInstance().ejecutarProcedimiento("listarCapturas", null, null);
        System.out.println("Listando Capturas...");
        try{
            while(rs.next()){
                if(capturas == null) capturas = new ArrayList<>();
                Captura c = new Captura();
                c.setId(rs.getInt("id"));
                c.setPlaca(rs.getString("placa"));
                c.setVelocidad(rs.getDouble("velocidad"));
                c.setFechaCaptura(rs.getDate("fecha_captura"));
                if("REGISTRADO".equals(rs.getString("estado")))
                    c.setEstado(EstadoCaptura.REGISTRADO);
                else
                    c.setEstado(EstadoCaptura.PROCESADO);
                Camara camara = new Camara();
                camara.setId(rs.getInt("id_camara"));
                c.setCamara(camara);
                capturas.add(c);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return capturas;
    }
    
}
