package models; // Define el paquete donde se encuentra la clase

import java.sql.Date; // Importa la clase Date de java.sql para manejar fechas

public class Transaccion {
    // Atributos privados de la clase
    private int idTransaccion; // Identificador único de la transacción
    private String tipo; // Tipo de transacción (por ejemplo, ingreso, gasto)
    private Date fecha; // Fecha de la transacción
    private String idproyecto; // Descripción de la transacción
    private String idinversionista;

    // Constructor por defecto
    

    // Constructor con parámetros para inicializar todos los atributos
    public Transaccion(int idTransaccion, String tipo, Date fecha, String idproyecto, String idinversionista) {
        this.idTransaccion = idTransaccion; // Inicializa el id de la transacción
        this.tipo = tipo; // Inicializa el tipo de la transacción
        this.fecha = fecha; // Inicializa la fecha de la transacción
        this.idproyecto = idproyecto; // Inicializa la descripción de la transacción
        this.idinversionista = idinversionista;
    }
    

    public int getIdTransaccion() {
        return idTransaccion; // Devuelve el id de la transacción
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion; // Establece el id de la transacción
    }

    public String getTipo() {
        return tipo; // Devuelve el tipo de la transacción
    }

    public void setTipo(String tipo) {
        this.tipo = tipo; // Establece el tipo de la transacción
    }

    public Date getFecha() {
        return fecha; // Devuelve la fecha de la transacción
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha; // Establece la fecha de la transacción
    }

    public String getIdproyecto() {
        return idproyecto; // Devuelve la descripción de la transacción
    }

    public void setIdproyecto(String idproyecto) {
        this.idproyecto = idproyecto; // Establece la descripción de la transacción
    }

    public String getIdinversionista() {
        return idinversionista; // Devuelve la descripción de la transacción
    }

    public void setIdinversionista(String idinversionista) {
        this.idinversionista = idinversionista; // Establece la descripción de la transacción
    }





    public Transaccion(){
        // No hace nada, solo inicializa una instancia vacía
    }
}





