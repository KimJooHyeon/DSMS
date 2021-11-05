package controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@WebServlet("/validation.do")
public class CaptchaValidation extends HttpServlet {
   private static final long serialVersionUID = 1L;
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String view = "/captcha/captcha-validation.jsp";
      
      String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
      JSONObject json = getJSONResponse(gRecaptchaResponse);
      
      boolean isSuccess = (boolean)json.get("success");
      request.setAttribute("gRecaptchaResponse", gRecaptchaResponse);
      request.setAttribute("isSuccess", isSuccess);
      request.setAttribute("json", json.toString());
      request.getRequestDispatcher(view).forward(request, response);
   }
   
   private JSONObject getJSONResponse(String gRecaptchaResponse) {
      String url = "https://www.google.com/recaptcha/api/siteverify";
      String secretKey = "6LffpnoUAAAAAO8yDjVzMTGtvflsZW1K7g7c****";
      
      String response = getResponse(url, secretKey, gRecaptchaResponse);
      JSONObject json = getJSONObject(response);
            
      return json;
   }

   private JSONObject getJSONObject(String jsonString) {
      JSONObject json = new JSONObject();
      
      try {
         JSONParser parser = new JSONParser();
         json = (JSONObject)parser.parse(jsonString);
//         System.out.println("json: " + json.toJSONString());
         
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      return json;
   }
   
   private String getResponse(String url, String secretKey, String gRecaptchaResponse) {
      String response = "";
      
      try {
         URL urlObject = new URL(url);
         HttpsURLConnection connection = (HttpsURLConnection) urlObject.openConnection();
         connection.setRequestMethod("POST");
         connection.setDoOutput(true); // 서버에서 온 데이터를 출력할 수 있는 상태인지 여부 확인(default : false)
         String param = "secret=" + secretKey + "&response=" + gRecaptchaResponse;
         
         DataOutputStream stream = new DataOutputStream(connection.getOutputStream());
         stream.writeBytes(param);
         stream.flush();
         stream.close();
         
         BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
         String inputLine;
   
         while ((inputLine = reader.readLine()) != null) {
            response += inputLine;
         }
         reader.close();
         
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      return response;
   }
}