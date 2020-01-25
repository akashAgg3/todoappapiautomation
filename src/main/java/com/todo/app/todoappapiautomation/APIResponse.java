package com.todo.app.todoappapiautomation;

import com.jayway.restassured.response.Response;

public class APIResponse {
	protected final Response res;
	private static final String BREAK_LINE = "</br>";

	public APIResponse(Response res) {
		this.res = res;
	}

	public String getResponseAsString() throws Exception {
		String response = "";
		try {
			if (res != null)
				response = this.res.asString();
		} catch (Exception e) {
			throw new Exception(e);
		}
		return response;
	}

	public int getStatusCode() throws Exception {
		int actualStatusCode = 0;
		String infoMessage = "";
		try {
			actualStatusCode = res.getStatusCode();
			infoMessage = infoMessage + BREAK_LINE + " A: " + actualStatusCode;
		} catch (Exception e) {
			throw new Exception(e);
		}
		return actualStatusCode;
	}

	public String getResponseHeaderValue(String headerName) throws Exception {
		System.out.println("++++++" + res.getHeaders().toString());
		return res.header(headerName);
	}

}
