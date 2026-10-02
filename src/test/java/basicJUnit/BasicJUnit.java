package basicJUnit;

import org.junit.jupiter.api.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //para ordenar
public class BasicJUnit {


    @BeforeEach //antes de cada test
    void inicializar(){
        System.out.println("Este método se ejecuta antes de cada test ...");
    }

    @AfterEach //después de cada test
    void limpiar(){
        System.out.println("Este método se ejecuta después de cada test ...");
    }

    @Order(1)

    @Test //para ejecutar test
    void crearProyecto(){
        System.out.println("Este es un test que verifica la creacion de un proyecto.");
    }

    @Order(2)
    @Test //para ejecutar test
    void actualizarProyecto(){
        System.out.println("Este es un test que verifica la actualizacion de un proyecto");
    }
}
