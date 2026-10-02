package basicRestAssured;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.IsEqual.equalTo;

public class BasicRestAssured {

    /*
    given() -> controla la configuración de las peticiones
            -headers
            -parametros
            -body/payload
            -authorization
            -log

     when() -> especificamos el metodo : GET | POST | PUT | DELETE
               especificamos la url => ip:puerto | hostname

     then() ->  controlar la respuesta
            -response code
            -response body
            -response header
            -response size
            -response time
            implementar verificaciones
            implementar extracciones (extraer valores del response y asignarlo a variables)
            logs
     */

    @Test
    void crearProyecto() {
        given()
                .auth()
                .preemptive() //sirve para que colocque usuario y contraseña
                .basic("gilsonjb@gmail.com", "gilson4855")
                .body("""
                        {
                            "Content": "RestAssured",
                            "Icon": 5
                        }
                        """)
                .log()
                .all()
        .when()
                .post("https://todo.ly/api/projects.json");

    }

    @Test
    void crearProyectoUsandoJsonObject() {
        JSONObject payload = new JSONObject();
        payload.put("Content", "RestAssuredJsonObject");
        payload.put("Icon", 3);

        given()
                .auth()
                .preemptive()
                .basic("gilsonjb@gmail.com", "gilson4855")
                .body(payload.toString())
                .log()
                .all()
        .when()
                .post("https://todo.ly/api/projects.json");

    }

    @Test
    void crearProyectoUsandoJsonFile() {
        String rutaJsonFile = getClass()
                .getClassLoader()
                .getResource("projects.json")
                .getPath();

        System.out.println("ruta:" + rutaJsonFile);

        given()
                .auth()
                .preemptive()
                .basic("gilsonjb@gmail.com", "gilson4855")
                .body(new File(rutaJsonFile))
                .log()
                .all()
        .when()
                .post("https://todo.ly/api/projects.json");
    }

    @Test
    void crearProyectoConVerificaciones() {
        JSONObject payload = new JSONObject();
        payload.put("Content", "RestAssuredV3");
        payload.put("Icon", 6);

        given()
                .auth()
                .preemptive()
                .basic("gilsonjb@gmail.com", "gilson4855")
                .body(payload.toString())
                .log()
                .all()
        .when()
                .post("https://todo.ly/api/projects.json")
        .then()
                .log().all()
                .statusCode(200)
                .body("Content", equalTo("RestAssuredV3"))
                .body("Icon", equalTo(6));

    }

    @Test
    void crearProyectoConVerificaciones2() {
        JSONObject payload = new JSONObject();
        payload.put("Content", "RestAssuredV4");
        payload.put("Icon", 7);

        Response response =
                given()
                        .auth()
                        .preemptive()
                        .basic("gilsonjb@gmail.com", "gilson4855")
                        .body(payload.toString())
                        .log()
                        .all()
                .when()
                        .post("https://todo.ly/api/projects.json")
                .then()
                        .log().ifValidationFails() //se aumento linea de validación
                        .statusCode(200)
                        .body("Id",greaterThan(0))
                        .body("Content", equalTo("RestAssuredV4"))
                        .body("Icon", equalTo(7))
                        .time(lessThan(5000L)) //se aumento linea de validación
                .extract().response();

        int id = response.jsonPath().getInt("Id");
        String content = response.jsonPath().getString("Content");
        int icon = response.jsonPath().getInt("Icon");

        System.out.println("Id: " + id);
        System.out.println("Content: " + content);
        System.out.println("Icon: " + icon);
    }
}
