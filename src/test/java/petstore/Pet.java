//Pacotes
package petstore;

//Bibliotecas
import org.testng.annotations.Test;
import org.testng.annotations.TestInstance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

//Criação das classes
public class Pet {

//Criação dos atributos
    String uri = "https://petstore.swagger.io/v2/pet"; //endereço da entidade Pet

//Metodos e funções
    //Não muda
     public String lerJson(String caminhoJson) throws IOException { //Sempre incluir o throws
         return new String(Files.readAllBytes(Paths.get(caminhoJson)));
     }

     //Incluir -Create - Post
    @Test (priority = 1)
    public void incluirPet() throws IOException{
         String jsonBody = lerJson("db/pet1.json"); //Onde está o arquivo?


        //Sintaxe Gherkin
        given()
                .contentType("application/json") //Comum API REST
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri)
        .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Snoopy"))
                .body("status", is("available"))
        ;

    }

    //Consultando do pet
    @Test(priority = 2)
    public void consultarPet(){
         String petId = "58952817895";

         String token =

         given()
                 .contentType("application/json")
                 .log().all()
         .when()
                 .get(uri + "/" + petId)

         .then()
                 .log().all()
                 .statusCode(200)

         .extract()
                 .path("category.name")
         ;

        System.out.println("O Token é: " + token);

    }

    //Alterando o pet
    @Test(priority = 3)
    public void alterarPet() throws IOException {
         String jsonBody = lerJson("db/pet2.json");


         given()
                 .contentType("application/json")
                 .log().all()
                 .body(jsonBody)
         .when()
                 .put(uri)
         .then()
                 .log().all()
                 .statusCode(200)
                 .body("name", is("Snoopy"))
                 .body("status", is ("sold"))
         ;
    }

    @Test(priority = 4)
    public void excluirPet(){
         String petId = "58952817895";

         given()
                 .contentType("application/jason")
                 .log().all()
         .when()
                 .delete(uri + "/" + petId)
         .then()
                 .log().all()
                 .statusCode(200)
                 .body("code", is(200))
                 .body("type", is ("unknown"))
                 .body("message", is(petId))

         ;
    }

}
