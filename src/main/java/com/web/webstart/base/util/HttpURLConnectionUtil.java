package com.web.webstart.base.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.constant.position.Location;
import com.web.liuda.business.constant.position.ShowLocation;

public class HttpURLConnectionUtil {
	
	/**
     * 接口调用 GET
     */
    public static String httpURLConectionGET(String urls) {
        try {
            URL url = new URL(urls);    // 把字符串转换为URL请求地址
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 打开连接
            connection.connect();// 连接会话
            // 获取输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {// 循环读取流
                sb.append(line);
            }
            br.close();// 关闭流
            connection.disconnect();// 断开连接
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败!");
        }
        return null;
    }
    
    /**
     * 接口调用  POST
     */
    @SuppressWarnings("unused")
	public static String httpURLConnectionPOST (String urls) {
        try {
            URL url = new URL(urls);
            
            // 将url 以 open方法返回的urlConnection  连接强转为HttpURLConnection连接  (标识一个url所引用的远程对象连接)
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 此时cnnection只是为一个连接对象,待连接中
            
            // 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
            connection.setDoOutput(true);
            
            // 设置连接输入流为true
            connection.setDoInput(true);
            
            // 设置请求方式为post
            connection.setRequestMethod("POST");
            
            // post请求缓存设为false
            connection.setUseCaches(false);
            
            // 设置该HttpURLConnection实例是否自动执行重定向
            connection.setInstanceFollowRedirects(true);
            
            // 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
            // application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            
            // 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
            connection.connect();
            
            // 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
            DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
            String parm = "storeId=" + URLEncoder.encode("32", "utf-8"); //URLEncoder.encode()方法  为字符串进行编码
            
            // 将参数输出到连接
            dataout.writeBytes(parm);
            
            // 输出完成后刷新并关闭流
            dataout.flush();
            dataout.close(); // 重要且易忽略步骤 (关闭流,切记!) 
            
            System.out.println(connection.getResponseCode());
            
            // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
            String line;
            StringBuilder sb = new StringBuilder(); // 用来存储响应数据
            
            // 循环读取流,若不到结尾处
            while ((line = bf.readLine()) != null) {
                sb.append(bf.readLine());
            }
            bf.close();    // 重要且易忽略步骤 (关闭流,切记!) 
            connection.disconnect(); // 销毁连接
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static ShowLocation getLocation(String adress){
    	try{
    		String url = "http://api.map.baidu.com/geocoder/v2/?address="+URLEncoder.encode(adress,"UTF-8")+"&output=json&ak=XNzHyZGhkt7CUkTsi7qlBniV";
    		ShowLocation location = null;
    		location = JSON.parseObject(httpURLConectionGET(url), ShowLocation.class);
    		if(XaUtil.isNotEmpty(location)){
    			return location;
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		return new ShowLocation();
    	}
    	return new ShowLocation();
    }
    
    /** 
     * 计算两点之间距离 
     * @param start 
     * @param end 
     * @return 米 
     */  
    public static double getDistance(Location start,Location end){  
        double lat1 = (Math.PI/180)*start.getLat();  
        double lat2 = (Math.PI/180)*end.getLat();  
          
        double lon1 = (Math.PI/180)*start.getLng();  
        double lon2 = (Math.PI/180)*end.getLng();  
          
//      double Lat1r = (Math.PI/180)*(gp1.getLatitudeE6()/1E6);  
//      double Lat2r = (Math.PI/180)*(gp2.getLatitudeE6()/1E6);  
//      double Lon1r = (Math.PI/180)*(gp1.getLongitudeE6()/1E6);  
//      double Lon2r = (Math.PI/180)*(gp2.getLongitudeE6()/1E6);  
          
        //地球半径  
        double R = 6371;  
          
        //两点间距离 km，如果想要米的话，结果*1000就可以了  
        double d =  Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon2-lon1))*R;  
          
        return d*1000;  
    }  
    
    public static void main(String[] args) {
    	getLocation("巴厘岛");
	}

}
