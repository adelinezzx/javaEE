<%@page import="java.util.Random"%>
<%@page import="java.awt.Color"%>
<%@ page   contentType="image/jpeg"
    pageEncoding="UTF-8"%>
<%!
       //设置字体颜色为随机
       Color getRandColor(int cb ,int fb ){
	       Random random =  new Random();
	       if(cb>255)
	    	    cb = 255;
	       if(fb>255)
	    	    fb= 255;
	       int r = cb +  random.nextInt(fb - cb);
	       int g = cb + random.nextInt(fb - cb);
	       int b = cb + random.nextInt(fb - cb);
	   return new Color(r,g,b);
       }

%>