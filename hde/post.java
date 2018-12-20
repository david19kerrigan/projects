import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;

public class post
{
    public static void main(String args[]) throws Exception{
        URL url = new URL("https://hdechallenge-solve.appspot.com/challenge/003/endpoint");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(5000);//5 secs
        connection.setReadTimeout(5000);//5 secs
    
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Basic david19kerrigan@gmail.com:"+TOTP.getKey());
        System.out.println(TOTP.getKey());
    
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());  
        out.write(
                "{ " +
                "\"github_url\":" +
                "\"https://gist.github.com/david19kerrigan/f1c9636f036821b1aa37fb924256a8c1\"," +
                "\"contact_email\":" +
                "\"david19kerrigan@gmail.com"+
                "}");
        out.flush();
        out.close();
    
        int res = connection.getResponseCode();
    
        System.out.println(res);
    
    
        InputStream is = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while((line = br.readLine() ) != null) {
            System.out.println(line);
        }
        connection.disconnect();
    
    }
}