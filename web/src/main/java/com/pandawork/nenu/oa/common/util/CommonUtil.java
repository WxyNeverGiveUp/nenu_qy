package com.pandawork.nenu.oa.common.util;

import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public final class CommonUtil {
	private static Logger logger = Logger.getLogger(CommonUtil.class);


    //car_len 要求大于12
    public static String generateCardNum(int car_len) {


        final int maxNum = 10;
        int i;
        int count = 0;
        char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        String data6 = sdf.format(date);
        StringBuffer cardNum = new StringBuffer(data6);
        Random r = new Random();
        while (count < (car_len - 12)){
            i = Math.abs(r.nextInt(maxNum));
            cardNum.append(str[i]);
            count++;
        }
        return cardNum.toString();

    }
    public static String generateCardPwd(int pwd_len) {
        final int maxNum = 36;
        int i;
        int count = 0;
        char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < pwd_len){
            i = Math.abs(r.nextInt(maxNum));
            if(i>=0 && i<str.length){
                pwd.append(str[i]);
                count++;
            }
        }
        return pwd.toString();

    }


	public static boolean assertNotNull(String s){
		if(s != null && !s.equals("")&& !s.equals("-1")){
			return true;
		}
		return false;
	}
	public static String encrypt(String info) {
		byte[] digesta = null;
		try{
			MessageDigest alga = MessageDigest.getInstance("MD5");
			alga.update(info.getBytes());
			digesta = alga.digest();
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		
		String rs = null;
		
		if(digesta != null){
			rs = byte2hex(digesta);
		}
		
		return rs;
	}

	private static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
	}

	public static String startWithHttp(String url){
		if(!url.startsWith("http://")){
			url = "http://"+url;
		}
		return url;
	}

	public static String encode(String url){
		url = startWithHttp(url);
		url = isendWith(url);
		return URLEncoder.encode(url);
	}
	public static String decode(String url){
		URLDecoder.decode(url);
		url = startWithHttp(url);
		url = isendWith(url);
		return url;
	}
	public static String endWith(String url){
		if(!url.endsWith("/")){
			url = url+"/";
		}
		return url;

	}
	public static String isendWith(String url) {
		if(url.endsWith("/"))
			return url.substring(0,url.length()-1);
		else return url;
	}

	    public static String getResponseFromServer(final String  url) throws MalformedURLException {
	        URL constructedUrl = new URL(url);
	     	URLConnection conn = null;
	         try {
	             conn = constructedUrl.openConnection();
	             final BufferedReader in;
	             logger.debug("In commonUtil with getResp "+constructedUrl.toString());
	                 in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	             String line;
	             final StringBuilder stringBuffer = new StringBuilder(255);
	             while ((line = in.readLine()) != null) {
	                 stringBuffer.append(line);
	                 stringBuffer.append("\n");
	             }

	             return stringBuffer.toString();
	         } catch (final Exception e) {
	             throw new RuntimeException(e);
	         } finally {
	             if (conn != null && conn instanceof HttpURLConnection) {
	                 ((HttpURLConnection)conn).disconnect();
	             }
	         }

	     }

	    /**
	     * 对一个byte数组计算md5值
	     *
	     * @param b
	     * @return
	     */
	    public static String md5(byte b[]) {
	        int i;
	        StringBuffer buf = new StringBuffer("");
	        for (int offset = 0; offset < b.length; offset++) {
	            i = b[offset];
	            if (i < 0) i += 256;
	            if (i < 16) buf.append("0");
	            buf.append(Integer.toHexString(i));
	        }
	        return buf.toString();
	    }

	    public static String md5(String plainText) {
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            md.update(plainText.getBytes());
	            byte b[] = md.digest();
	            return md5(b);
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }


		public static void assertNotNull(final Object object, final String message) {
	        if (object == null) {
	            throw new IllegalArgumentException(message);
	        }
	    }


		public static String getBasepath(HttpServletRequest request) {
			String forReturn = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
			return forReturn;
		}

	    public static void assertNotEmpty(final Collection<?> c, final String message) {
	        assertNotNull(c, message);
	        if (c.isEmpty()) {
	            throw new IllegalArgumentException(message);
	        }
	    }

		public static Cookie findCookieByName(String cookieName, HttpServletRequest request){
			Cookie[] cookies = request.getCookies();
			if(cookies == null)return null;
			for(Cookie cookie : cookies){
				if(cookie.getName().equals(cookieName)){
					return cookie;
				}
			}
			return null;
		}


		/**
		 * 穿入一个字符串，去掉字符串中的分割符 endToken 获得   字符的列表
		 *
		 * @param nameAll
		 * @return
		 */
		public static List<String> chooseName(String nameAll,String endToken) {
			List<String> nameList = new ArrayList<String>();
			if (nameAll != null) {
				String after = nameAll;
				int end = after.indexOf(endToken);
				int start = 0;
				while (end > -1) {
					String subSignName = after.substring(start, end);
					nameList.add(subSignName);

					after = after.substring(end + 1, after.length());
					end = after.indexOf(endToken);
				}
				if (end > -1) {
				} else {
					String endAuthorName = after;
					nameList.add(endAuthorName);
				}
			}
			return nameList;
		}

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 输入两个起始时间和结束时间，如果两个时间冲突则返回false，否则返回true
     * @param fromDate1
     * @param thruDate1
     * @param fromDate2
     * @param thruDate2
     * @return
     */
    public static boolean isConflict(String fromDate1 , String thruDate1 , String fromDate2 , String thruDate2){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date1 = null;
        Date date2 = null;
        Date date3 = null;
        Date date4 = null;
        try {
             date1 = format.parse(fromDate1);  //time1 起始时间
             date2 = format.parse(thruDate1); //time1结束时间
             date3 = format.parse(fromDate2);  //time2 起始时间
             date4 = format.parse(thruDate2); //time2结束时间
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ((date1.getTime() - date4.getTime())/(60*1000) >=30)  || ((date3.getTime() - date2.getTime())   / (60*1000) >=30);

    }



    public static void main(String[] args) {
//        System.out.println(isConflict("2014-08-25 10:37:00", "2014-08-25 11:00:00"));
    }
}
