package command.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectPage extends AbstractPage {
	
	private Map<String, String[]> requestParameters;
		
	public RedirectPage (String path) {
		super(path);
	}
		
	public RedirectPage(String path, Map<String, String[]> requestParameters) {
		super(path);
		this.requestParameters = requestParameters;
	}
	
	public Map<String, String[]> getRequestParameters() {
		return requestParameters;
	}

	public void setRequestParameters(Map<String, String[]> requestParameters) {
		this.requestParameters = requestParameters;
	}

	@Override
	public void finishRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestParameters = request.getParameterMap();
		if(requestParameters.isEmpty() || !request.getMethod().equals("GET")) {
			response.sendRedirect(getPath());
		} else {
			response.sendRedirect(getPath() + "?" + this.convertParametersToString());
		}
	}
	
	private String convertParametersToString() {
		Set<Map.Entry<String,String[]>> entrySet = requestParameters.entrySet();
		List<String> listOfpairs = new ArrayList<String>();
		for(Entry<String,String[]> entry : entrySet) {
			String key = entry.getKey();
			String[] values = entry.getValue();
			for(String value : values) {
				listOfpairs.add(key + "=" + value);
			}
		} 
		return listOfpairs.stream().collect(Collectors.joining("&"));
	}
}
