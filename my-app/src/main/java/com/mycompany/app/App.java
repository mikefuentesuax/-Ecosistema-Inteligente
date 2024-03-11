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