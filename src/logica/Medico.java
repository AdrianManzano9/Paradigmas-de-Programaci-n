package logica;


public class Medico {

    private int idMedico;
    private String nombre;
    private String especialidad;
    private AgendaMedica agenda;

    public Medico(int idMedico, String nombre, String especialidad) {
        this.idMedico = idMedico;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.agenda = new AgendaMedica();
        }

    public AgendaMedica getAgenda() {
        return agenda;
        }
    
    public int getIdMedico(){
        return idMedico;
        }
    
    public String getNombre(){
        return nombre;
        }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
        }
   
    public String getEspecialidad(){
        return especialidad;
        }
    
    public void setEspecialidad(String especialidad){
        this.especialidad = especialidad;
        }
    }