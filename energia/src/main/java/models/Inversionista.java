package models;

// Clase que representa a un inversionista
public class Inversionista {
    // Atributos privados de la clase
    private int idInversionista; // Identificador único del inversionista
    private String nombre; // Nombre del inversionista
    private String apellido;
    private String dni;
    private String telefono;
    private String idproyecto;   // Monto de la inversión del inversionista

    // Constructor por defecto
    public Inversionista(){
        
    }
    
    // Constructor con parámetros para inicializar todos los atributos
    public Inversionista(int idInversionista, String nombre, String apellido, String dni, String telefono, String idproyecto) {
        this.idInversionista = idInversionista;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.idproyecto = idproyecto;
    }
   
    // Método getter para obtener el id del inversionista
    public int getIdInversionista() {
        return idInversionista;
    }

    // Método setter para establecer el id del inversionista
    public void setIdInversionista(int idInversionista) {
        this.idInversionista = idInversionista;
    }

    // Método getter para obtener el nombre del inversionista
    public String getNombre() {
        return nombre;
    }

    // Método setter para establecer el nombre del inversionista
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método getter para obtener el apellido del inversionista
    public String getApellido() {
        return apellido;
    }

    // Método setter para establecer el apellido del inversionista
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Método getter para obtener el dni del inversionista
    public String getDni() {
        return dni;
    }

    // Método setter para establecer el dni del inversionista
    public void setDni(String dni) {
        this.dni = dni;
    }

    // Método getter para obtener el telefono del inversionista
    public String getTelefono() {
        return telefono;
    }

    // Método setter para establecer el telefono del inversionista
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // Método getter para obtener el id del proyecto del inversionista
    public String getIdproyecto() {
        return idproyecto;
    }

    // Método setter para establecer el id del proyecto del inversionista
    public void setIdproyecto(String idproyecto) {
        this.idproyecto = idproyecto;
    }

}
