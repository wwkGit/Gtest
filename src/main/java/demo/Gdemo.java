package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;



import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class Gdemo {
	
	 public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		    InputStream is = new URL(url).openStream();
		    try {
		      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		      StringBuilder sb = new StringBuilder();
			  int cp;
			  while ((cp = rd.read()) != -1) {
			    sb.append((char) cp);
			  }
		      String jsonText = sb.toString();
		      JSONObject json = JSONObject.fromObject(jsonText);  
		      return json;
		    } finally {
		      is.close();
		    }
		  }

	public static void main(String[] args) throws IOException {
		 	String url = "https://api.github.com/search/repositories?q=user:FIRHQ";   
		    JSONObject json = Gdemo.readJsonFromUrl(url);  
		    JSONArray js=(JSONArray) json.get("items");		    
		    if(js.size()>0){
			    for(int i=0;i<js.size();i++){
			    	JSONObject job = js.getJSONObject(i); // 遍历 jsonarray 数组，把每一个对象转成 json 对象
			    	System.out.println("项目名称:"+job.get("name").toString()+"    star:"+job.get("stargazers_count"));
			    	}
		    }else{
		    	System.out.println("无");
		    }
	}

}
