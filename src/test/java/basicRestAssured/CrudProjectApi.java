package basicRestAssured;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.IsEqual.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CrudProjectApi {
    private static int projectId;

    @Order(1)
    @Test
    void crear() {
        JSONObject payload = new JSONObject();
        payload.put("Content", "CRUD");
        payload.put("Icon", 3);

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
                        .log().ifValidationFails()
                        .statusCode(200)
                        .body("Content", equalTo("CRUD"))
                        .body("Icon", equalTo(3))
                        .extract().response();

        projectId = response.jsonPath().getInt("Id");
        System.out.println("*** projectId: " + projectId );
    }

    @Order(2)
    @Test
    void actualizar() {
        JSONObject payload = new JSONObject();
        payload.put("Content", "CRUD Updated");
        payload.put("Icon", 5);

        given()
                .auth()
                .preemptive()
                .basic("gilsonjb@gmail.com", "gilson4855")
                .body(payload.toString())
                .log()
                .all()
        .when()
                .put("https://todo.ly/api/projects/" + projectId + ".json")
        .then()
                .log().ifValidationFails() //siempre que falla una validacion
                .statusCode(200)
                .body("Content", equalTo("CRUD Updated"))
                .body("Icon", equalTo(5));

    }

    @Order(3)
    @Test
    void buscarPorId() {

        given()
                .auth()
                .preemptive()
                .basic("gilsonjb@gmail.com", "gilson4855")
                .log()
                .all()
                .when()
                .get("https://todo.ly/api/projects/" + projectId + ".json")
                .then()
                .log().all() // siempre que pase o falle una validación
                .statusCode(200)
                .body("Content", equalTo("CRUD Updated"))
                .body("Icon", equalTo(5));

    }

    @Order(4)
    @Test
    void eliminar() {

        given()
                .auth()
                .preemptive()
                .basic("gilsonjb@gmail.com", "gilson4855")
                .log()
                .all()
        .when()
                .delete("https://todo.ly/api/projects/" + projectId + ".json")
        .then()
                .log().all()
                .statusCode(200)
                .body("Content", equalTo("CRUD Updated"))
                .body("Icon", equalTo(5))
                .body("Deleted", equalTo(true));

    }


    //otra forma para solo eliminar
    //4554823
    @Order(5)
    @Test
    void eliminar2() {

        int projectId2 = 4554547;

        given()
                .auth()
                .preemptive()
                .basic("gilsonjb@gmail.com", "gilson4855")
                .log()
                .all()
                .when()
                .delete("https://todo.ly/api/projects/" + projectId2 + ".json")
                .then()
                .log().all()
                .statusCode(200)
                .body("Content", equalTo("RestAssuredV3"))
                .body("Icon", equalTo(6))
                .body("Deleted", equalTo(true));

    }
}

