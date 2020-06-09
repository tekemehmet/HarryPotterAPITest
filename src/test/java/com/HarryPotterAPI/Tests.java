package com.HarryPotterAPI;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.gson.annotations.SerializedName;
import com.pojos.HPCharacter;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.*;


public class Tests {

    private final String API_KEY = "$2a$10$ehOP.wkEu.rQLv3y6H3P9e6F6OZPs/9jwLesi5wB8gjFfh3ls2jUq";
    private final String BAD_API_KEY = "$2a$10$ehOP.wkEu.rQLv3y6H3P9e6F6OZPs/9jwLesi5wB8gjFfh3ls2jUw";

    @BeforeAll
    public static void setup(){

        baseURI = "https://www.potterapi.com/v1/";
        config = config().objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON));
    }

    /*
    Send a get request to /sortingHat. Request includes :
     Verify status code 200, content type application/json; charset=utf-8
    Verify that response body contains one of the following houses:
    "Gryffindor", "Ravenclaw", "Slytherin", "Hufflepuff"
     */

    @Test
    @DisplayName("Verify sorting hat")
    public void verifySortingHat(){

        List<String> houseList = Arrays.asList("Gryffindor", "Slytherin", "Hufflepuff", "Ravenclaw");

        Response response = given().contentType(ContentType.JSON).
                                    queryParam("key",API_KEY).
                            when().
                                    get("/sortingHat").prettyPeek();

        String houseFromResponse = response.as(String.class);

        response.then().
                       assertThat().
                                    statusCode(200).
                                    contentType("application/json; charset=utf-8");

        Assertions.assertTrue(houseList.contains(houseFromResponse));


    }

    /*
    Send a get request to /characters. Request includes :
    Header Accept with value application/json
    Query param key with value invalid
    Verify status code 401, content type application/json; charset=utf-8
    Verify response status line include message Unauthorized
    Verify that response body says "error": "API Key Not Found"
     */
    @Test
    @DisplayName("verify bad key")
    public void verifyBadKey(){
        Response response = given().
                                    contentType(ContentType.JSON).
                                    header("Accept", "application/json").
                                    queryParam("key",BAD_API_KEY).
                            when().get("/characters").prettyPeek();

        response.then().assertThat().
                statusCode(401).
                contentType("application/json; charset=utf-8").
                statusLine(containsString("Unauthorized")).
                body("error", is("API Key Not Found"));

    }

    /*
    Verify no key
    Send a get request to /characters. Request includes :
    Header Accept with value application/json
    Verify status code 409, content type application/json; charset=utf-8
    Verify response status line include message Conflict
    Verify that response body says "error": "Must pass API key for request"
     */
    @Test
    @DisplayName("Verify No Key")
    public void verifyNoKey(){
        Response response = given().
                contentType(ContentType.JSON).
                header("Accept", "application/json").
                when().get("/characters").prettyPeek();

        response.then().
                assertThat().
                            statusCode(409).
                            contentType("application/json; charset=utf-8").
                            body("error", is("Must pass API key for request"));

    }

    /*
    Verify number of characters
    Send a get request to /characters. Request includes :
    Header Accept with value application/json
    Query param key with value {{apiKey}}
    Verify status code 200, content type application/json; charset=utf-8
    Verify response contains 194 characters
     */
    @Test
    public void verifyNumberOfCharacters(){
        Response response = given().
                contentType(ContentType.JSON).
                header("Accept", "application/json").
                queryParam("key",API_KEY).
                when().get("/characters").prettyPeek();

        Assertions.assertEquals(200, response.statusCode());


        List<Object> ids = response.jsonPath().get("_id");
        System.out.println(ids.size());
        Assertions.assertEquals(195, ids.size() );

        //with POJO class
        List<HPCharacter> characterList = response.jsonPath().getList("",HPCharacter.class);
        System.out.println("characterList = " + characterList);

        Assertions.assertTrue(characterList.size()==195);


    }

    /*
    Verify number of character id and house
    Send a get request to /characters. Request includes :
    Header Accept with value application/json
    Query param key with value {{apiKey}}
    Verify status code 200, content type application/json; charset=utf-8
    Verify all characters in the response have id field which is not empty
    Verify that value type of the field dumbledoresArmy is a boolean in all characters in the response
    Verify value of the house in all characters in the response is one of the following:
    "Gryffindor", "Ravenclaw", "Slytherin", "Hufflepuff"
     */
    @Test
    @DisplayName("Verify number of character id and house")
    public void verifyNumberOfCharIDAndHouse() {

        Response response = given().
                contentType(ContentType.JSON).
                header("Accept", "application/json").
                queryParam("key", API_KEY).
                when().
                get("/characters").prettyPeek();

        List<String> houseList = Arrays.asList("Gryffindor", "Slytherin", "Hufflepuff", "Ravenclaw");

        response.then().assertThat().statusCode(200)
                                    .contentType("application/json; charset=utf-8").
                                    body("_id",everyItem(not(isEmptyString()))).
                                    body("dumbledoresArmy", everyItem(is(instanceOf(Boolean.class)))).
                                    body("house", everyItem(is(oneOf("Gryffindor", "Slytherin", "Hufflepuff", "Ravenclaw",null))));



    }

        /*
        Verify all character information
        Send a get request to /characters. Request includes :
        Header Accept with value application/json
        Query param key with value {{apiKey}}
        Verify status code 200, content type application/json; charset=utf-8
        Select name of any random character
        Send a get request to /characters. Request includes :
        Header Accept with value application/json
        Query param key with value {{apiKey}}
        Query param name with value from step 3
        Verify that response contains the same character information from step 3. Compare all fields.
         */
        @Test
        @DisplayName("Verify all character information")
        public void verifyAllCharacterInformation(){
            Response response = given().
                    contentType(ContentType.JSON).
                    header("Accept", "application/json").
                    queryParam("key", API_KEY).
                    when().
                    get("/characters").prettyPeek();

            response.then().statusCode(200).assertThat().contentType("application/json; charset=utf-8");

            List<Map<String,String>> allCharacters = response.jsonPath().getList("");
            System.out.println("allCharacters = " + allCharacters);

            int randomCharacter = new Random().nextInt(allCharacters.size());

            String anyName = allCharacters.get(randomCharacter).get("name");
            System.out.println("anyName = " + anyName);



            Response response1 = given().
                                        header("Accept","application/json").
                                        queryParam("key", API_KEY).
                                        queryParam("name", anyName).
                                when().
                                        get("/characters").prettyPeek();
                    response1.then().
                            assertThat().body("[0].name",is(anyName));

        }

        /*
        Verify name search
        Send a get request to /characters. Request includes :
        Header Accept with value application/json
        Query param key with value {{apiKey}}
        Query param name with value Harry Potter
        Verify status code 200, content type application/json; charset=utf-8
        Verify name Harry Potter
        Send a get request to /characters. Request includes :
        Header Accept with value application/json
        Query param key with value {{apiKey}}
        Query param name with value Marry Potter
        Verify status code 200, content type application/json; charset=utf-8
        Verify response body is empty
         */
        @Test
    @DisplayName("Verify name search")
    public void verifyNameSearch(){
            Response response = given().
                    contentType(ContentType.JSON).
                    header("Accept", "application/json").
                    queryParam("key", API_KEY).
                    queryParam("name", "Harry Potter").
                    when().
                    get("/characters").prettyPeek();

            response.
                    then().statusCode(200).assertThat().contentType("application/json; charset=utf-8").
                    assertThat().body("[0].name",is("Harry Potter"));

            Response response1 = given().
                    contentType(ContentType.JSON).
                    header("Accept", "application/json").
                    queryParam("key", API_KEY).
                    queryParam("name", "Marry Potter").
                    when().
                    get("/characters").prettyPeek();

            response1.
                    then().statusCode(200).assertThat().contentType("application/json; charset=utf-8").
                    assertThat().body("[0].name",is(isEmptyOrNullString()));

    }

    /*
        Verify house members
    1. Send a get request to /houses. Request includes :
    • Header Accept with value application/json
    • Query param key with value {{apiKey}}
    2. Verify status code 200, content type application/json; charset=utf-8
    3. Capture the id of the Gryffindor house
    4. Capture the ids of the all members of the Gryffindor house
    5. Send a get request to /houses/:id. Request includes :
    • Header Accept with value application/json
    • Query param key with value {{apiKey}}
    • Path param id with value from step 3
    6. Verify that response contains the same member ids as the step 4
     */
    @Test
    @DisplayName("Verify house members")
    public void verifyHouseMembers(){
        Response response = given().
                                header("Accept", "application/json").
                                queryParam("key", API_KEY).
                            when().
                                    get("/houses").prettyPeek();

        response.then().assertThat().
                                    statusCode(200).
                                    assertThat().contentType("application/json; charset=utf-8");

        String gryffindorID = response.jsonPath().getString("find{it.name=='Gryffindor'}_id");

        List<String> memberIDs = response.jsonPath().getList("find{it.name == 'Gryffindor'}.members");
        System.out.println("Gryffindor memeberIDs = " + memberIDs);

        Response response2 = given().
                                header("Accept", "application/json").
                                queryParam("key", API_KEY).
                                pathParam("id", gryffindorID).
                    when().
                            get("/houses/{id}").prettyPeek();

        List<String> memberIDsFrom2ndResponse = response2.jsonPath().getList("[0].members._id");
        System.out.println("memberIDsFrom2ndResponse = " + memberIDsFrom2ndResponse);

        Assertions.assertEquals(memberIDs, memberIDsFrom2ndResponse);

    }

    /*
        Verify house members again
    1. Send a get request to /houses/:id. Request includes :
    • Header Accept with value application/json
    • Query param key with value {{apiKey}}
    • Path param id with value 5a05e2b252f721a3cf2ea33f
    2. Capture the ids of all members
    3. Send a get request to /characters. Request includes :
    • Header Accept with value application/json
    • Query param key with value {{apiKey}}
    • Query param house with value Gryffindor
    4. Verify that response contains the same member ids from step 2
     */
    @Test
    @SerializedName("Verify house members again")
    public void houseMember(){
        Response response = given().
                header("Accept", "application/json").
                queryParam("key", API_KEY).
                pathParam("id","5a05e2b252f721a3cf2ea33f").
                when().
                get("/houses/{id}").prettyPeek();

        List<String> memberIDs = response.jsonPath().getList("[0].members._id");

        Response response2 = given().
                header("Accept", "application/json").
                queryParam("key", API_KEY).
                queryParam("house","Gryffindor").
                when().
                    get("/characters").prettyPeek();

        List<String> characterIDs = response.jsonPath().getList("findAll{it.house =='Gryffindor'}._id");

        Assertions.assertEquals(memberIDs , characterIDs);

    }

    /*
        Verify house with most members
    1. Send a get request to /houses. Request includes :
    • Header Accept with value application/json
    • Query param key with value {{apiKey}}
    2. Verify status code 200, content type application/json; charset=utf-8
    3. Verify that Gryffindor house has the most members
     */
    @Test
    @DisplayName("Verify house with most members")
    public void verifyHouseWithMostMembers(){

        Response response = given().
                header("Accept", "application/json").
                queryParam("key", API_KEY).
                pathParam("name" ,"Gryffindor").
                when().
                get("/houses/{name}").prettyPeek();


    }





    }

