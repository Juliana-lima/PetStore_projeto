//Pacotes
package petstore;

//Bibliotecas

import org.testng.annotations.Test;

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
    @Test
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

    //Consulta do pet
    @Test
    public void consultarPet(){
         String petId = "58952417895";

         given()
                 .contentType("application/json")
                 .log().all()
         .when()
                 .get(uri + "/" + petId)

         .then()
                 .log().all()
                 .statusCode(200)

         ;


    }

}
