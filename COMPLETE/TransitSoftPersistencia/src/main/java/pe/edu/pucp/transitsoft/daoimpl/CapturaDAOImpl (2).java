package pe.edu.pucp.transitsoft.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.transitsoft.config.DBManager;
import pe.edu.pucp.transitsoft.dao.CapturaDAO;
import pe.edu.pucp.transitsoft.modelo.Camara;
import pe.edu.pucp.transitsoft.modelo.Captura;
import pe.edu.pucp.transitsoft.modelo.EstadoCaptura;
import pe.edu.pucp.transitsoft.modelo.Vehiculo;

// TODO: Implementar CapturaDAOImpl
public class CapturaDAOImpl implements CapturaDAO {
    private ResultSet rs;
    
    @Override
    public List<Captura> leerTodos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int modificar(Captura captura) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, captura.getEstado());
        parametrosEntrada.put(2,captura.getId());
        int resultado = DBManager.getInstance().ejecutarProcedimiento("modificarEstadoCaptura", parametrosEntrada, null);
        System.out.println("Se ha realizado la modificacion del captura");
        return resultado;
    }

   
    @Override
    public int insertar(Captura objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

    @Override
    public int eliminar(int idObjeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Captura obtenerPorId(int idObjeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public void listarCapturas() {
        DBManager.getInstance().ejecutarProcedimiento("listarCapturas", null, null);
        System.out.println("Se ha realizado la modificacion del captura");
//        
//        //Implementamos el metodo
//        ArrayList<Captura> capturas = null;
//        
//        rs = DBManager.getInstance().ejecutarProcedimientoLectura("listarCapturas", null);
//        System.out.println("Lectura de capturas...");
//        try{
//            while(rs.next()){
//                if(capturas == null) capturas = new ArrayList<>();
//                Captura e = new Captura();
//                e.setId(rs.getInt("id"));
//                e.setPlaca(rs.getString("placa"));
//                e.setVelocidad(rs.getDouble("velocidad"));
//                e.setFechaCaptura(rs.getDate("fecha_captura"));
//                //Para las clases internas
//                Vehiculo vehiculo = new Vehiculo();
//                vehiculo.setId(rs.getInt("id_vehiculo"));
//                String est= rs.getString("estado");
//                if("PROCESADO".equals(est))
//                    e.setEstado(EstadoCaptura.PROCESADO);
//                else
//                    e.setEstado(EstadoCaptura.REGISTRADO);
//                Camara camara = new Camara();
//                camara.setId(rs.getInt("id_camara"));
//                capturas.add(e);
//            }
//        }catch(SQLException ex){
//            System.out.println(ex.getMessage());
//        }finally{
//            DBManager.getInstance().cerrarConexion();
//        }
//        return capturas;
    }

    @Override
    public ArrayList<Captura> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}