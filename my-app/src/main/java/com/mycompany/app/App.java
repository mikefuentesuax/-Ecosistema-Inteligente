package com.mycompany.app;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;


abstract class Organismo {
    protected String posicion;
    protected int salud;
    protected int edad;
    protected boolean estadoReproductivo;
    protected int tasaCrecimiento;
    
    abstract void crecer();
    abstract Organismo reproducir();
    public int getTasaCrecimiento() {
        return this.tasaCrecimiento;
    }
}

class Planta extends Organismo {
    // Atributos específicos de las plantas
    boolean polinizado;

    void polinizar() {
        this.polinizado = true;
    }

    @Override
    void crecer() {
        this.edad += this.tasaCrecimiento;
    }
    
    @Override
    Organismo reproducir() {
        if (this.polinizado) {
            return new Planta(); // Aquí puedes configurar los atributos de la nueva planta
        }
        return null;
    }
}

class Animal extends Organismo {
    boolean hambriento;

    void comer(Planta planta) {
        if (this.hambriento) {
            planta.salud--;
            this.hambriento = false;
        }
    }

    @Override
    void crecer() {
        this.edad += this.tasaCrecimiento;
    }

    @Override
    Organismo reproducir() {
        if (this.estadoReproductivo) {
            return new Animal(); //pdt. configurar los atributos del nuevo animal
        }
        return null;
    }
}

class Ambiente {
    private String clima;
    private String terreno;
    private int recursosDisponibles;
    private List<Organismo> organismos;
    private Random random = new Random();
    

    public Ambiente(String clima, String terreno, int recursosDisponibles) {
        this.clima = clima;
        this.terreno = terreno;
        this.recursosDisponibles = recursosDisponibles;
        this.organismos = new ArrayList<>();
    }

    // Constructor, getters y setters

    void simularPredacion() {
        // Implementar la lógica de predación
        for (Organismo organismo : organismos) {
            if (organismo instanceof Animal) {
                Animal animal = (Animal) organismo;
                for (Organismo posiblePresa : organismos) {
                    if (posiblePresa instanceof Planta && animal.hambriento) {
                        animal.comer((Planta) posiblePresa);
                        break;
                    }
                }
            }
        }
    }

    void simularCompetencia() {
        // Implementar la lógica de competencia por recursos
        if (recursosDisponibles < organismos.size()) {
            List<Organismo> organismosCompetidores = new ArrayList<>(organismos);
            organismosCompetidores.sort(Comparator.comparingInt(Organismo::getTasaCrecimiento).reversed());
            int numOrganismosASobrevivir = recursosDisponibles;
            if (numOrganismosASobrevivir > organismosCompetidores.size()) {
                numOrganismosASobrevivir = organismosCompetidores.size();
            }
            organismosCompetidores.subList(numOrganismosASobrevivir, organismosCompetidores.size()).clear();
            organismos.clear();
            organismos.addAll(organismosCompetidores);
        }
    }

    void simularPolinizacion() {
        // Implementar la lógica de polinización
        for (Organismo organismo : organismos) {
            if (organismo instanceof Planta) {
                ((Planta) organismo).polinizar();
            }
        }
    }

    void simularCrecimientoYReproduccion() {
        List<Organismo> nuevosOrganismos = new ArrayList<>();
        for (Organismo organismo : this.organismos) {
            organismo.crecer();
            Organismo nuevoOrganismo = organismo.reproducir();
            if (nuevoOrganismo != null) {
                nuevosOrganismos.add(nuevoOrganismo);
            }
        }
        this.organismos.addAll(nuevosOrganismos);
    }

    void simularEventosAleatorios() {
        // Implementar la lógica de eventos aleatorios
        for (Organismo organismo : organismos) {
            int evento = random.nextInt(3);
            switch (evento) {
                case 0: // Desastre natural
                    organismo.salud -= 10;
                    break;
                case 1: // Enfermedad
                    organismo.salud -= 5;
                    break;
                case 2: // Cambio climático
                    organismo.salud -= 2;
                    break;
            }
        }
    }

    void mostrarResultadosSimulacion() {
        // Implementar la lógica para calcular y presentar estadísticas
        int totalPlantas = 0;
        int totalAnimales = 0;
        int saludTotalPlantas = 0;
        int saludTotalAnimales = 0;
        int edadTotalPlantas = 0;
        int edadTotalAnimales = 0;
        int estadoReproductivoPlantas = 0;
        int estadoReproductivoAnimales = 0;

        
        for (Organismo organismo : organismos) {
            if (organismo instanceof Planta) {
                totalPlantas++;
            saludTotalPlantas += organismo.salud;
            edadTotalPlantas += organismo.edad;
            estadoReproductivoPlantas += organismo.estadoReproductivo ? 1 : 0;
        } else if (organismo instanceof Animal) {
            totalAnimales++;
            saludTotalAnimales += organismo.salud;
            edadTotalAnimales += organismo.edad;
            estadoReproductivoAnimales += organismo.estadoReproductivo ? 1 : 0;
        }
    }

    System.out.println("Total de plantas: " + totalPlantas);
    System.out.println("Salud promedio de las plantas: " + (totalPlantas > 0 ? saludTotalPlantas / totalPlantas : 0));
    System.out.println("Edad promedio de las plantas: " + (totalPlantas > 0 ? edadTotalPlantas / totalPlantas : 0));
    System.out.println("Porcentaje de plantas en estado reproductivo: " + (totalPlantas > 0 ? (estadoReproductivoPlantas * 100.0 / totalPlantas) + "%" : "0%"));

    System.out.println("Total de animales: " + totalAnimales);
    System.out.println("Salud promedio de los animales: " + (totalAnimales > 0 ? saludTotalAnimales / totalAnimales : 0));
    System.out.println("Edad promedio de los animales: " + (totalAnimales > 0 ? edadTotalAnimales / totalAnimales : 0));
    System.out.println("Porcentaje de animales en estado reproductivo: " + (totalAnimales > 0 ? (estadoReproductivoAnimales * 100.0 / totalAnimales) + "%" : "0%"));
 }
}