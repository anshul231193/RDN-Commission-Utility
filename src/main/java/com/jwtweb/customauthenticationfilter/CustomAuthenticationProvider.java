package com.jwtweb.customauthenticationfilter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	private final String USER_AGENT = "Mozilla/5.0";
	
	private static String jsessionid = "";
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		try {
            System.out.println("JUGGAD"+authentication);

            final String username = authentication.getName();
            final String password = authentication.getCredentials().toString();
            System.out.println("HI NAKLI"+username+password);
//         rest api call check for 200
//            if (user == null) {
//                throw new BadCredentialsException("Username not found.");
//            }\

    		String authUrl = "https://bi.reddotnetwork.com/rest/login?j_username="+username+"&j_password="+password;
    		
            if(checkJSApiCode(username,password) == 200){
            	
                return new UsernamePasswordAuthenticationToken(username, password, getAuthorities(username,password,jsessionid));
            }else{
            	throw new BadCredentialsException("Unauthorized");
            }

        } catch (BadCredentialsException e) {
        	e.printStackTrace();
            throw e;
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	private void getToken(HttpURLConnection conn) throws IOException {
		// TODO Auto-generated method stub
		
		Map<String, List<String>> headerFields = conn.getHeaderFields();

		Set<String> headerFieldsSet = headerFields.keySet();
		Iterator<String> hearerFieldsIter = headerFieldsSet.iterator();
		
		while (hearerFieldsIter.hasNext()) {
			
			 String headerFieldKey = hearerFieldsIter.next();
			 
			 if ("Set-Cookie".equalsIgnoreCase(headerFieldKey)) {
				 
				 List<String> headerFieldValue = headerFields.get(headerFieldKey);
				 for (String headerValue : headerFieldValue) {
					 
					
					String[] fields = headerValue.split(";");

					String cookieValue = fields[0];
					
					jsessionid = cookieValue;
					
				 }
				 
			 }
			
		}
	}

	private Collection<GrantedAuthority> getAuthorities(String username, String password, String jsessionid2) throws IOException, ParserConfigurationException, SAXException {
		// TODO Auto-generated method stub
		
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        String url = "https://bi.reddotnetwork.com/rest_v2/roles?user="+username;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");
		con.setRequestProperty("Cookie", jsessionid);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		String res = response.toString();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new InputSource(new StringReader(res)));
		org.w3c.dom.Element element = document.getDocumentElement();	
		
		NodeList nodeList = element.getElementsByTagName("name");
	    for (int i = 0; i < nodeList.getLength(); i++) {
	    	authorities.add(new SimpleGrantedAuthority(getTextValue(nodeList.item(i))));
	    }
	    System.out.println(authorities);
	    return authorities;
	}	
	
	public static String getTextValue(Node node) {
	    StringBuffer textValue = new StringBuffer();
	    int length = node.getChildNodes().getLength();
	    for (int i = 0; i < length; i ++) {
	      Node c = node.getChildNodes().item(i);
	      if (c.getNodeType() == Node.TEXT_NODE) {
	        textValue.append(c.getNodeValue());
	      }
	    }
	    return textValue.toString().trim();
	  }

	private int checkJSApiCode(String username, String password) throws IOException {
		// TODO Auto-generated method stub
		String url = "https://bi.reddotnetwork.com/rest/login?j_username="+username+"&j_password="+password;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		
		getToken(con);
		
		int responseCode = con.getResponseCode();
		return responseCode;
	}

	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(
		      UsernamePasswordAuthenticationToken.class);
	}

}
