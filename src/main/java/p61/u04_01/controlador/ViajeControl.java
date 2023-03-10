/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p61.u04_01.controlador;


import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import p61.u04_01.modelo.Viaje;
import p61.u04_01.servicio.CiudadServiceImpl;
import p61.u04_01.servicio.ViajeServiceImpl;


/**
 *
 * @author Mauricio Ortiz
 */
public class ViajeControl {
    
    private ViajeServiceImpl viajeServiceImpl  = new ViajeServiceImpl();
    private CiudadServiceImpl ciudadServiceImpl  = new CiudadServiceImpl();
    
    
    public void crear(String [] data){
        try{
        var codigo=Integer.valueOf(data[0]);
        var origen=this.ciudadServiceImpl.buscarPorNombre(data[1]);
        var destino=this.ciudadServiceImpl.buscarPorNombre(data[2]);
        var year=Integer.valueOf(data[3]);
        var mes=Integer.valueOf(data[4]);
        var dia=Integer.valueOf(data[5]);
        var viaje= new Viaje(codigo,origen,destino,LocalDate.of(year, mes, dia));
        if (this.codigoExiste(codigo)) {
            throw new RuntimeException("Código existe");
        }else{
        this.viajeServiceImpl.crear(viaje);}
        }catch(NumberFormatException e1){
            throw new NumberFormatException("Error al convertir el formato");

        }catch(RuntimeException e1){
            throw new RuntimeException("Codigo no valido");
        
        }
    }
      public boolean codigoExiste(int codigoActual) {
        var retorno = false;
        for(var ciudad:this.viajeServiceImpl.listar()){
            if (ciudad.getCodigo()== codigoActual){
                retorno=true;
            }
        
        
        }
        return retorno;
    }
    
    public List<Viaje> listar(){
        return this.viajeServiceImpl.listar();
    }
    
}
