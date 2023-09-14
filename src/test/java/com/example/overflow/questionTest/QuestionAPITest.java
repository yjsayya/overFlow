//package com.example.overflow.questionTest;
//
//import com.example.overflow.ApiTest;
//import com.example.overflow.question.Question;
//import io.restassured.RestAssured;
//import io.restassured.response.ExtractableResponse;
//import org.apache.catalina.connector.Response;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@SpringBootTest
//public class QuestionAPITest extends ApiTest {
//
//    @Test
//    void 질문등록() {
//        final String title = "java 질문 입니다.";
//        final String content = "String 을 int 로 바꾸려면 어떻게 해야하는지 알려주세요";
//        final var request = new Question(title, content);
//
//        final var response = 질문등록요청(request);
//
//        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
//    }
//
//    private static AddQuestionRequest 질문등록요청_생성() {
//        final String title = "java 질문 입니다.";
//        final String content = "String 을 int 로 바꾸려면 어떻게 해야하는지 알려주세요";
//        return new AddQuestionRequest(title, content);
//    }
//
//    private static ExtractableResponse<Response> 질문등록요청(final AddQuestionRequest request) {
//        return RestAssured.given().log().all()
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .body(request)
//                .when()
//                .post("/questions")
//                .then()
//                .log().all().extract();
//    }
//
//}
