package steps;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class MyStepdefs {

    /*
    * {}        ->reemplaza cualquier valor como parametro
    * {word}    ->reemplaza una sola palabra
    * {string}  ->reemplaza un valor cuando esta en comillas dobles
    * {int}     ->reemplaza un valor numerico
    * {float}   ->reemplaza un valor decimal
    * */

    @Given("que tengo acceso a {}")
    public void queTengoAccesoAFacebook(String appName) {
        System.out.println("que tengo acceso a red social: " + appName);
    }

    @When("ingreso mi email: {word}")
    public void ingresoMiEmail(String email) {
        System.out.println("step: ingreso mi usuario: " + email);
    }

    @And("ingreso mi password: {string}")
    public void ingresoMiPassword(String password) {
        System.out.println("step: ingreso mi clave: " + password);
    }

    @Then("hago clic en el botón iniciar sesión")
    public void hagoClicEnElBotónIniciarSesión() {
        System.out.println("step: clic boton iniciar sesion");
    }


    @And("muestra la página principal")
    public void muestraLaPáginaPrincipal() {
        System.out.println("muestra pagina principal");
    }


    @And("deberia ver los siguientes menus")
    public void deberiaVerLosSiguientesMenus(List<String> menus) {
        for (String menu : menus) {
            System.out.println("menu: " + menu);

        }
    }

    @When("me registro con")
    public void meRegistroCon(Map<String, String> data) {
        data.forEach((key, value) -> {
            System.out.println("clave: " + key + " - valor: " + value);
        });
    }

    //PARA EL FEATURE 7
   /* @When("me registro usando")
    public void meRegistroUsando(Persona persona) {
        System.out.println("Persona: ");
        System.out.println("nombre: " + persona.getNombre());
        System.out.println("apellidos: " + persona.getApellidos());
        System.out.println("telefono: " + persona.getTelefono());
        System.out.println("direccion: " + persona.getDireccion());
        System.out.println("dni: " + persona.getDni());
    }*/


  @When("me registro usando")
    public void meRegistroUsando(List<Persona> personas) {
        for (Persona persona : personas) {
            System.out.println("Persona: ");
            System.out.println("nombre: " + persona.getNombre());
            System.out.println("apellidos: " + persona.getApellidos());
            System.out.println("telefono: " + persona.getTelefono());
            System.out.println("direccion: " + persona.getDireccion());
            System.out.println("dni: " + persona.getDni());
            System.out.println("----");

        }
    }

    @DataTableType
    public Persona convertToPersona(Map<String, String> data) {
        Persona persona = new Persona();
        persona.setNombre(data.get("nombre"));
        persona.setApellidos(data.get("apellidos"));
        persona.setTelefono(data.get("telefono"));
        persona.setDireccion(data.get("direccion"));
        persona.setDni(data.get("dni"));
        return persona;
    }

    @Then("muestra la pagina principal")
    public void muestraLaPaginaPrincipal() {
    }

    @Then("deberia ver sus terminos de aceptacion")
    public void deberiaVerSusTerminosDeAceptacion(String data) {
        System.out.println("términos de aceptación: \n" + data);
    }

}
