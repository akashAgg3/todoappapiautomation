package com.todo.app.todoappapiautomation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

/**
 * Unit test for simple todo App.
 */
public class AppTest extends RequestUtils {

	JsonUtils jsonutils = new JsonUtils();

	String todoListUrl = "http://localhost:4000/todos/";
	String createTodoUrl = "http://localhost:4000/todos/add";
	String updateTodoUrl = "http://localhost:4000/todos/update/5e2c070921dd722ec49a261e";
	String deleteTodoUrl = "http://localhost:4000/todos/delete/5e292ddef1f4c74d34d7e713";
	long timeInMillis = 5000;

	// Testing Todo list API
	@Test
	public void testTodo() {

		try {
			HashMap<String, String> headerParams = new HashMap<String, String>();
			headerParams.put("Cache-Control", "no-cache");

			RequestSpecification spec = requestHeadersSpec(headerParams);
			Response resp = RequestUtils.get(todoListUrl, spec, null);
			APIResponse apiResp = new APIResponse(resp);
			JSONArray respJson = new JSONArray(apiResp.getResponseAsString());

			assertEquals(resp.getStatusCode(), 200); // assertion of status code
			assertTrue(resp.getTimeIn(TimeUnit.MILLISECONDS) < timeInMillis); // assertion of response time
			assertTrue(respJson.length() > 0); // assertion of response length
			for (int i = 0; i < respJson.length(); i++) { // Asserting all todos have id
				JSONObject objects = respJson.getJSONObject(i);
				assertNotNull(objects.get("_id"));
			}

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Testing Create todo api
	@Test
	public void createTodo() {

		try {
			HashMap<String, String> headerParams = new HashMap<String, String>();
			headerParams.put("Cache-Control", "no-cache");

			HashMap<String, Object> formParams = new HashMap<String, Object>();
			formParams.put("todo_description", "Test Todo");
			formParams.put("todo_responsible", "Test");
			formParams.put("todo_priority", "Medium");
			formParams.put("todo_completed", false);

			String json = jsonutils.toJson(formParams);
			RequestSpecification spec = requestHeadersFormSpecForPost(json, headerParams);
			Response resp = RequestUtils.post(createTodoUrl, null, spec, formParams);
			APIResponse apiResp = new APIResponse(resp);
			JSONObject respJson = new JSONObject(apiResp.getResponseAsString());

			assertEquals(resp.getStatusCode(), 200); // assertion of status code
			assertTrue(resp.getTimeIn(TimeUnit.MILLISECONDS) < timeInMillis); // assertion of response time
			assertEquals(respJson.get("todo"), "todo added successfully"); // verifying response

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Testing update todo API
	@Test
	public void updateTodo() {

		try {
			HashMap<String, String> headerParams = new HashMap<String, String>();
			headerParams.put("Cache-Control", "no-cache");

			HashMap<String, Object> formParams = new HashMap<String, Object>();
			formParams.put("todo_description", "Test Todo");
			formParams.put("todo_responsible", "Test");
			formParams.put("todo_priority", "Medium");
			formParams.put("todo_completed", true);

			String json = jsonutils.toJson(formParams);
			RequestSpecification spec = requestHeadersFormSpecForPost(json, headerParams);
			Response resp = RequestUtils.post(updateTodoUrl, null, spec, formParams);
			APIResponse apiResp = new APIResponse(resp);
			assertEquals(resp.getStatusCode(), 200); // assertion of status code
			assertTrue(resp.getTimeIn(TimeUnit.MILLISECONDS) < timeInMillis); // assertion of response time
			assertEquals(apiResp.getResponseAsString(), "\"Todo updated\""); // verifying response

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Testing delete todo API
	@Test
	public void deleteTodo() {

		try {
			HashMap<String, String> headerParams = new HashMap<String, String>();
			headerParams.put("Cache-Control", "no-cache");

			RequestSpecification spec = requestHeadersSpec(headerParams);
			Response resp = RequestUtils.delete(deleteTodoUrl, null, spec);
			APIResponse apiResp = new APIResponse(resp);
			assertEquals(resp.getStatusCode(), 200); // assertion of status code
			assertTrue(resp.getTimeIn(TimeUnit.MILLISECONDS) < timeInMillis); // assertion of response time
			assertEquals(apiResp.getResponseAsString(), "\"Todo deleted\""); // verifying response

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
