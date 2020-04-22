package br.com.zaiac.swinteaapp.REST;

import br.com.zaiac.swinteaapp.entities.Analise;
import br.com.zaiac.swinteaapp.entities.Websrvjson;
import br.com.zaiac.swinteaapp.entities.Websrvlogin;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Retina {
    protected static final Logger logger = Logger.getLogger(Retina.class.getName());
    
    public static String getTokenToAccess(String restUrl, String username, String password)  
    {
        String jsonObject = "";
        
        String usernamePassword = "username=" + username + "&password=" + password;
        logger.log(Level.INFO, "URL: " + restUrl);
        try {
            HttpPost httpPost = new HttpPost(restUrl);
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.addHeader("Accept", "application/json");
            httpPost.addHeader("X-CSRFToken", "ajOg7kr7GpvjerH0Z6YfjlC9xfL3jTiBN7O0SxeSz79cjjY74pez94uajqpfWLLX");
            httpPost.setEntity(new StringEntity(usernamePassword));
            jsonObject = executeHttpRequest(httpPost);
        } catch (UnsupportedEncodingException e) {
            
        }        
        return jsonObject;
    }
    



    public static String executeHttpRequest(HttpPost httpPost, JSONObject jsonObjectParser) 
    {
        StringBuffer result = new StringBuffer();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray;
        try {        
            HttpResponse response=null;
            String line = "";
            HttpClient client = HttpClientBuilder.create().build();

            StringEntity params =new StringEntity(jsonObjectParser.toString());
            httpPost.setEntity(params);
            response = client.execute(httpPost);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            while ((line = reader.readLine()) != null){ 
                result.append(line); 
                logger.log(Level.INFO, line);
            }    
        } catch (UnsupportedEncodingException e) {
            result = new StringBuffer(erroRequest("UnsupportedEncodingException"));
        } catch (ClassCastException e) {
            result = new StringBuffer(erroRequest("ClassCastException"));
        } catch (IOException e) {
            result = new StringBuffer(erroRequest("IOException"));
        } finally {
            httpPost.releaseConnection();
        }
        return result.toString();
    }   
    
    
    public static HttpPost createPostConnectivity(String restUrl, String token)
    {
        HttpPost post = new HttpPost(restUrl);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept", "application/json");
        post.setHeader("Authorization", "Token " + token);
        return post;
    }

    public static HttpPut createPutConnectivity(String restUrl, String token)
    {
        HttpPut post = new HttpPut(restUrl);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept", "application/json");
        post.setHeader("Authorization", "Token " + token);
        return post;
    }
    
    
    public static HttpGet createGetConnectivity(String restUrl, String token)
    {
        HttpGet get = new HttpGet(restUrl);
        get.setHeader("Content-Type", "application/json");
        get.setHeader("Accept", "application/json");
        get.setHeader("Authorization", "Token " + token);
        return get;
    }
    
    public static HttpDelete createDeleteConnectivity(String restUrl, String token, String plate)
    {
        String url = restUrl + plate + "/";
        HttpDelete delete = new HttpDelete(url);
        delete.setHeader("Content-Type", "application/json");
        delete.setHeader("Accept", "application/json");
        delete.setHeader("Authorization", "Token " + token);
        return delete;
    }


    public static String executeHttpRequestDelete(HttpDelete httpDelete) 
            
    {
        StringBuffer result = new StringBuffer();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {        
            HttpResponse response=null;
            String line = "";
            HttpClient client = HttpClientBuilder.create().build();
            response = client.execute(httpDelete);
            BufferedReader reader;
            
            try {
                reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            } catch (NullPointerException e) {
                return null;
            }
            while ((line = reader.readLine()) != null){ 
                result.append(line); 
                logger.log(Level.INFO, line);
            }
        } catch (UnsupportedEncodingException e) {
            result = new StringBuffer(erroRequest("UnsupportedEncodingException"));
        } catch (ClassCastException e) {
            result = new StringBuffer(erroRequest("ClassCastException"));
        } catch (IOException e) {
            result = new StringBuffer(erroRequest("IOException"));
        } finally {
            httpDelete.releaseConnection();
        }
        return result.toString();
    }   

    public static String executeHttpRequestPut(HttpPut httpPut, JSONObject jsonObjectParser) 
    {
        StringBuffer result = new StringBuffer();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray;
        try {        
            HttpResponse response=null;
            String line = "";
            HttpClient client = HttpClientBuilder.create().build();

            StringEntity params =new StringEntity(jsonObjectParser.toString());
            httpPut.setEntity(params);
            response = client.execute(httpPut);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            while ((line = reader.readLine()) != null){ 
                result.append(line); 
                logger.log(Level.INFO, line);
            }    
        } catch (UnsupportedEncodingException e) {
            result = new StringBuffer(erroRequest("UnsupportedEncodingException"));
        } catch (ClassCastException e) {
            result = new StringBuffer(erroRequest("ClassCastException"));
        } catch (IOException e) {
            result = new StringBuffer(erroRequest("IOException"));
        } finally {
            httpPut.releaseConnection();
        }
        return result.toString();
    }   
    
    public static String executeHttpRequestPut(HttpPut httpPut) 
            
    {
        StringBuffer result = new StringBuffer();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {        
            HttpResponse response=null;
            String line = "";
            HttpClient client = HttpClientBuilder.create().build();
            response = client.execute(httpPut);
            BufferedReader reader;
            
            try {
                reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            } catch (NullPointerException e) {
                return null;
            }
            while ((line = reader.readLine()) != null){ 
                result.append(line); 
                logger.log(Level.INFO, line);
            }
        } catch (UnsupportedEncodingException e) {
            result = new StringBuffer(erroRequest("UnsupportedEncodingException"));
        } catch (ClassCastException e) {
            result = new StringBuffer(erroRequest("ClassCastException"));
        } catch (IOException e) {
            result = new StringBuffer(erroRequest("IOException"));
        } finally {
            httpPut.releaseConnection();
        }
        return result.toString();
    }   

    public static String executeHttpRequest(HttpPost httpPost) 
    {
        StringBuffer result = new StringBuffer();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {        
            HttpResponse response=null;
            String line = "";
            HttpClient client = HttpClientBuilder.create().build();

            response = client.execute(httpPost);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            while ((line = reader.readLine()) != null){ 
                result.append(line); 
                logger.log(Level.INFO, line);
            }        
        } catch (UnsupportedEncodingException e) {
            result = new StringBuffer(erroRequest("UnsupportedEncodingException"));
        } catch (ClassCastException e) {
            result = new StringBuffer(erroRequest("ClassCastException"));
        } catch (IOException e) {
            result = new StringBuffer(erroRequest("IOException"));
        } finally {
            httpPost.releaseConnection();
        }
        return result.toString();
    }   
    
    
    
    private static boolean checkResult(JSONObject jasonObject) {
        
        String result = (String) jasonObject.get("result");
        if (result.equals("success")) {
            return true;
        } else {
            return false;
        }
        
    }
    
    private static String erroRequest(String error) {
        JSONObject response = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        response.put("error", 1);
        response.put("reason", "RETINA - " + error);
        jsonArray.add(response);
        return jsonArray.toString();
    }
    
    private static String sucessRequest(String value) {
        JSONObject response = new JSONObject();
        response.put("error", 0);
        response.put("reason", value);
        return response.toString();
    }
    
    
    
    public static String checkTipoDado(String s) {
        JSONObject jsonObject = new JSONObject();
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = new JSONArray();
        
        if (Objects.isNull(s)) {
            return null;
        }
        
        try {
            jsonObject = (JSONObject) jsonParser.parse(s);
            return "JSONObject";
        } catch (Exception e) {
            try {
                jsonArray = (JSONArray) jsonParser.parse(s);
                return "JSONArray";
            } catch (Exception e1) {
                return "HTML";
            }
        }
    }
    
    
    public static String inserirRetina(Websrvlogin websrvlogin, Websrvjson websrvjsonToken, Websrvjson websrvjsonSinister, Analise analise) throws ParseException {
        String strJsonObject = "";
        String strTipoJson = "";
        
        JSONObject jsonObject;
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray;
        String jsonStringResp;
        HttpPost post;

        String token;
        
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        strJsonObject = Retina.getTokenToAccess(websrvjsonToken.getJsoUrl(), websrvlogin.getJslLogin(), websrvlogin.getJslPassword());
        strTipoJson = Retina.checkTipoDado(strJsonObject);
        if (strTipoJson.equals("JSONArray")) {
            jsonArray = (JSONArray) jsonParser.parse(strJsonObject);    
            return jsonArray.toString();
        }
                
        if (strTipoJson.equals("HTML")) {
            String error = erroRequest("Falha ao acessar o SITE");
            return error;
        }
               
                
        jsonObject = (JSONObject) jsonParser.parse(strJsonObject);
        token = (String) jsonObject.get("token");
                
        jsonObject.clear();
        jsonObject.put("plate", analise.getAnaVeiculoPlaca().getVeiPlaca());
        jsonObject.put("company", "SWINT");
                
        switch (analise.getSitId().getSitId()) {
            case 6: jsonObject.put("reason", "MISAPPROPRIATION");
            case 4: jsonObject.put("reason", "ROBBERY");
            case 2: jsonObject.put("reason", "THEFT");
        }
        jsonObject.put("registered_date_at", fmt.format(analise.getAnaDt()));
            
        post = Retina.createPostConnectivity(websrvjsonSinister.getJsoUrl(), token);
        strJsonObject = Retina.executeHttpRequest(post, jsonObject);
        strTipoJson = Retina.checkTipoDado(strJsonObject);
                    
        if (strTipoJson.equals("JSONArray")) {
            return strJsonObject;
        }
                
        if (strTipoJson.equals("HTML")) {
            return erroRequest("Falha ao acessar o SITE");
        }
        logger.log(Level.INFO, strJsonObject);
        jsonObject = (JSONObject) jsonParser.parse(strJsonObject);
            
        try {
            String created_at = (String) jsonObject.get("created_at");
            if (created_at.length() > 0) {
                return sucessRequest("Retina - Inserido com sucesso");
            }
        } catch (NullPointerException e) {
            jsonArray = (JSONArray) jsonObject.get("plate");
            return jsonArray.toString();
        } catch (Exception e) {
            return erroRequest("Retina - Falha para inserir ");
        }
        return sucessRequest("Retina - Inserido com sucesso");
    }
    
    public static String recoveredRetina(Websrvlogin websrvlogin, Websrvjson websrvjsonToken, Websrvjson websrvjsonSinister, Analise analise) throws ParseException {
        String strJsonObject = "";
        String strTipoJson = "";
        
        JSONObject jsonObject;
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray;
        String jsonStringResp;
        HttpPut put;

        String token;
        
        strJsonObject = Retina.getTokenToAccess(websrvjsonToken.getJsoUrl(), websrvlogin.getJslLogin(), websrvlogin.getJslPassword());
        strTipoJson = Retina.checkTipoDado(strJsonObject);
        if (strTipoJson.equals("JSONArray")) {
            jsonArray = (JSONArray) jsonParser.parse(strJsonObject);    
            return jsonArray.toString();
        }
                
        if (strTipoJson.equals("HTML")) {
            String error = erroRequest("Falha ao acessar o SITE");
            return error;
        }
               
                
        jsonObject = (JSONObject) jsonParser.parse(strJsonObject);
        token = (String) jsonObject.get("token");
                
        jsonObject.clear();
        jsonObject.put("plate", analise.getAnaVeiculoPlaca().getVeiPlaca());
        jsonObject.put("company", "SWINT");
        jsonObject.put("recovered", "true");
        
        String sinister =  websrvjsonSinister.getJsoUrl() + analise.getAnaVeiculoPlaca().getVeiPlaca() + "/";
        
        put = Retina.createPutConnectivity(sinister, token);
        strJsonObject = Retina.executeHttpRequestPut(put, jsonObject);
        strTipoJson = Retina.checkTipoDado(strJsonObject);
                    
        if (strTipoJson.equals("JSONArray")) {
            return strJsonObject;
        }
                
        if (strTipoJson.equals("HTML")) {
            return erroRequest("Falha ao acessar o SITE");
        }
        logger.log(Level.INFO, strJsonObject);
        jsonObject = (JSONObject) jsonParser.parse(strJsonObject);

        try {
            String created_at = (String) jsonObject.get("created_at");
            if (created_at == null) {
                return erroRequest("Retina - Falha para inserir ");
            }
            if (created_at.length() > 0) {
                return sucessRequest("Retina - Inserido com sucesso");
            }
        } catch (NullPointerException e) {
            jsonArray = (JSONArray) jsonObject.get("plate");
            return jsonArray.toString();
        } catch (Exception e) {
            return erroRequest("Retina - Falha para inserir ");
        }
            
        return sucessRequest("Retina - Inserido com sucesso");
    }

    public static String recoveredBackRetina(Websrvlogin websrvlogin, Websrvjson websrvjsonToken, Websrvjson websrvjsonSinister, Analise analise) throws ParseException {
        String strJsonObject = "";
        String strTipoJson = "";
        
        JSONObject jsonObject;
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray;
        String jsonStringResp;
        HttpPut put;

        String token;
        
        strJsonObject = Retina.getTokenToAccess(websrvjsonToken.getJsoUrl(), websrvlogin.getJslLogin(), websrvlogin.getJslPassword());
        strTipoJson = Retina.checkTipoDado(strJsonObject);
        if (strTipoJson.equals("JSONArray")) {
            jsonArray = (JSONArray) jsonParser.parse(strJsonObject);    
            return jsonArray.toString();
        }
                
        if (strTipoJson.equals("HTML")) {
            String error = erroRequest("Falha ao acessar o SITE");
            return error;
        }
               
                
        jsonObject = (JSONObject) jsonParser.parse(strJsonObject);
        token = (String) jsonObject.get("token");
                
        jsonObject.clear();
        jsonObject.put("plate", analise.getAnaVeiculoPlaca().getVeiPlaca());
        jsonObject.put("company", "SWINT");
        jsonObject.put("recovered", "false");

        String sinister =  websrvjsonSinister.getJsoUrl() + analise.getAnaVeiculoPlaca().getVeiPlaca() + "/";

        put = Retina.createPutConnectivity(sinister, token);
        strJsonObject = Retina.executeHttpRequestPut(put, jsonObject);
        strTipoJson = Retina.checkTipoDado(strJsonObject);
                    
        if (strTipoJson.equals("JSONArray")) {
            return strJsonObject;
        }
                
        if (strTipoJson.equals("HTML")) {
            return erroRequest("Falha ao acessar o SITE");
        }
        logger.log(Level.SEVERE, strJsonObject);
        jsonObject = (JSONObject) jsonParser.parse(strJsonObject);
        try {
            String created_at = (String) jsonObject.get("created_at");
            if (created_at == null) {
                return erroRequest("Retina - Falha para inserir ");
            }
            if (created_at.length() > 0) {
        return sucessRequest("Retina - Inserido com sucesso");
            }
        } catch (NullPointerException e) {
            jsonArray = (JSONArray) jsonObject.get("plate");
            return jsonArray.toString();
        } catch (Exception e) {
            return erroRequest("Retina - Falha para inserir ");
        }
        return sucessRequest("Retina - Inserido com sucesso");
    }
}
