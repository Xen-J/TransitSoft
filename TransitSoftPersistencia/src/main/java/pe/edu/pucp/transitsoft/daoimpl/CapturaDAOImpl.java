package pe.edu.pucp.transitsoft.daoimpl;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.transitsoft.config.DBManager;
import pe.edu.pucp.transitsoft.dao.CapturaDAO;
import pe.edu.pucp.transitsoft.modelo.Captura;
//import pe.edu.pucp.transitsoft.modelo.EstadoCaptura;

public class CapturaDAOImpl implements CapturaDAO{

    private ResultSet rs;
    
    
    /* por que hay cerraer conexion en listar todos y no en insertar
    o en modificar?
    */
    
    @Override
    public boolean actualizar(Captura captura) {
        int idCap = captura.getId();
        int resultado =0; //por defecto
        if (obtenerPorId(idCap) != null){ //obtenerPorId maneja las excepciones
            Map<Integer, Object> parametrosEntrada = new HashMap<>();
            parametrosEntrada.put(1, captura.getEstado().toString());
            parametrosEntrada.put(2, idCap);
            resultado = DBManager.getInstance().ejecutarProcedimiento(
                    "modificarEstadoCaptura", parametrosEntrada, null);
            //Si se actualiza a 1, se encontró y se modificó
        }
        return resultado == 1; //Se modificó una captura
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
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("listarCapturas", null);
        System.out.println("Lectura de empleados...");
        try{
            while(rs.next()){
                if(capturas == null) capturas = new ArrayList<>();
                Captura e = new Captura();
//                e.setIdPersona(rs.getInt("id_empleado"));
//                e.setDni(rs.getString("DNI"));
//                e.setNombre(rs.getString("nombre"));
//                e.setApellidoPaterno(rs.getString("apellido_paterno"));
//                e.setSexo(rs.getString("sexo").charAt(0));
//                e.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
//                e.setCargo(rs.getString("cargo"));
//                e.setSueldo(rs.getDouble("sueldo"));
                
//                Area area = new Area();
//                area.setIdArea(rs.getInt("id_area"));
//                area.setNombre(rs.getString("nombre_area"));
//                
//                e.setArea(area);
                capturas.add(e);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return capturas;
    }
    
    
//    @Override
//    public ArrayList<Empleado> listarTodos() {
//        ArrayList<Empleado> empleados = null;
//        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_EMPLEADOS_TODOS", null);
//        System.out.println("Lectura de empleados...");
//        try{
//            while(rs.next()){
//                if(empleados == null) empleados = new ArrayList<>();
//                Empleado e = new Empleado();
//                e.setIdPersona(rs.getInt("id_empleado"));
//                e.setDni(rs.getString("DNI"));
//                e.setNombre(rs.getString("nombre"));
//                e.setApellidoPaterno(rs.getString("apellido_paterno"));
//                e.setSexo(rs.getString("sexo").charAt(0));
//                e.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
//                e.setCargo(rs.getString("cargo"));
//                e.setSueldo(rs.getDouble("sueldo"));
//                
//                Area area = new Area();
//                area.setIdArea(rs.getInt("id_area"));
//                area.setNombre(rs.getString("nombre_area"));
//                
//                e.setArea(area);
//                empleados.add(e);
//            }
//        }catch(SQLException ex){
//            System.out.println(ex.getMessage());
//        }finally{
//            DBManager.getInstance().cerrarConexion();
//        }
//        return empleados;
//    }
    
    
}
