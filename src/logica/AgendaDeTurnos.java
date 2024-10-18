/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package logica;

import db.DaO;

/**
 *
 * @author adria
 */
public class AgendaDeTurnos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Medico medico = new Medico(1, "Agenda de turnos médicos", "Cardiología");
         
         DaO Dao = new DaO(); 
         Dao.conectar();
         medico.getAgenda().agregarTurno(new Turno( "2024-10-16", "11:00", 987654321, "María García"));
         Dao.mostrarTurnos();
         Dao.desconectar();
    }
    
}
