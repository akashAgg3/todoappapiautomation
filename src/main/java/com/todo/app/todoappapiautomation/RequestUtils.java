package com.todo.app.todoappapiautomation;

import static com.jayway.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

/**
 * Hello world!
 *
 */
public class RequestUtils {
	public static Response post(String url, String payload, RequestSpecification spec, Map<?, ?> params)
			throws Exception {
		Response res = null;
		try {
			if (payload == null) {
				res = given().spec(spec).when().post(url);
			} else {
				res = given().spec(spec).body(payload).when().post(url);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return res;
	}

	public static Response get(String url, RequestSpecification spec, Map<?, ?> params) throws Exception {
		Response res = null;
		try {

			res = given().spec(spec).when().get(url);
		} catch (Exception e) {
			throw new Exception(e);
		}
		return res;
	}

	public static Response delete(String url, String payload, RequestSpecification spec) throws Exception {
		Response res = null;
		try {
			if (payload == null) {
				res = given().spec(spec).when().delete(url);
			} else {
				res = given().spec(spec).body(payload).when().delete(url);
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return res;
	}

	protected RequestSpecification requestHeadersSpec(HashMap<String, String> headers) {
		return given().contentType("application/json; charset=UTF-8").accept(ContentType.JSON).headers(headers);
	}

	protected RequestSpecification requestHeadersFormSpecForPost(String body, HashMap<String, String> headers) {
		return given().contentType("application/json; charset=UTF-8").accept(ContentType.JSON).headers(headers)
				.body(body);
	}

}
