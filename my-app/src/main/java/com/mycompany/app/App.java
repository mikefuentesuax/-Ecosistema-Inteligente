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
    private String recursos;

    // Constructor, getters y setters

    void simularPredacion(Animal animal, Planta planta) {
        animal.comer(planta);
    }

    void simularCompetencia(Animal animal1, Animal animal2) {
        if (animal1.hambriento && animal2.hambriento) {
            // Implementar la lógica de competencia por recursos
        }
    }

    void simularPolinizacion(Planta planta) {
        planta.polinizar();
    }
}
