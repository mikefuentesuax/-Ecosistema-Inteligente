package com.mycompany.app;

abstract class Organismo {
    protected String posicion;
    protected int salud;
    protected int edad;
    protected boolean estadoReproductivo;


}

class Planta extends Organismo {
    // Atributos específicos de las plantas
    boolean polinizado;

    void polinizar() {
        this.polinizado = true;
    }
}

class Animal extends Organismo {
    // Atributos específicos de los animales
    boolean hambriento;

    void comer(Planta planta) {
        if (this.hambriento) {
            planta.salud--;
            this.hambriento = false;
        }
    }
}

class Ambiente {
    private String clima;
    private String terreno;
    private int recursosDisponibles;
    private List<Organismo> organismos;

    public Ambiente(String clima, String terreno, int recursosDisponibles, List<Organismo> organismos) {
        this.clima = clima;
        this.terreno = terreno;
        this.recursosDisponibles = recursosDisponibles;
        this.organismos = organismos;
    }

    // Constructor, getters y setters

    void simularPredacion(Animal animal, Planta planta) {
        animal.comer(planta);
    }

    void simularPolinizacion(Planta planta) {
        planta.polinizar();
    }
}
