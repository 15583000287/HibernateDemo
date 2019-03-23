package com.example.jpademo.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 
 * @author sillycat
 * @version
 */
public final class StringUtil {
	protected static final Log logger = LogFactory.getLog(StringUtil.class);

	public static boolean isBlank(String str) {
		return (str == null || str.trim().length() < 1);
	}
	public static boolean isBlank(Object str) {
		return (str == null || str.toString().trim().length() < 1);
	}
	public static boolean isBlank(Number number) {
		return (number == null || number.intValue() == 0);
	}

	/**
	 * <pre>
	 *    mergeStrings(null, &quot;ss&quot;) = &quot;ss&quot;
	 *    mergeStrings(&quot;ss&quot;, null) = &quot;ss&quot;
	 *    mergeStrings(null, null) = null
	 *    mergeStrings(&quot;ss&quot;, &quot;dd&quot;) = &quot;ssdd&quot;
	 * </pre>
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static String mergeStrings(String s1, String s2) {
		if (s1 == null) {
			return s2;
		} else if (s2 == null) {
			return s1;
		} else {
			return s1.concat(s2);
		}
	}



	public static String getString(int num) {
		StringBuffer sb = new StringBuffer();
		int total = num - 1;
		for (int i = 0; i < total; i++) {
			sb.append("?,");
		}
		sb.append("?");
		return sb.toString();
	}




	/**
	 * get length of String; NULL return 0;
	 *
	 * @param s
	 * @return
	 */
	public static int getLength(String s) {
		if (isBlank(s)) {
			return 0;
		}
		return s.length();
	}


	public static String mergeName(String firstName, String middleName,
                                   String lastName, String split) {
		StringBuffer sb = new StringBuffer();
		if (!isBlank(firstName)) {
			sb.append(firstName);
			if (!isBlank(middleName)) {
				sb.append(split).append(middleName);
				if (!isBlank(lastName)) {
					sb.append(split).append(lastName);
				}
			} else {
				if (!isBlank(lastName)) {
					sb.append(split).append(lastName);
				}
			}
		} else if (!isBlank(middleName)) {
			sb.append(middleName);
			if (!isBlank(lastName)) {
				sb.append(split).append(lastName);
			}
		} else {
			if (!isBlank(lastName)) {
				sb.append(lastName);
			}
		}
		return sb.toString();
	}



	public static String getStringInRange(int range, String target) {
		if (target == null || target.length() < range) {
			return target;
		} else {
			return target.substring(target.length() - range, target.length());
		}
	}

	/*
	 * 16进制数字字符集
	 */
	private static String hexString = "0123456789ABCDEF ";

	/*
	 * 将字符串编码成16进制数字,适用于所有字符（包括中文）
	 */
	public static String strToHex(String str) {
		// 根据默认编码获取字节数组
		byte[] bytes = str.getBytes();
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		// 将字节数组中每个字节拆解成2位16进制整数
		for (int i = 0; i < bytes.length; i++) {
			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
			sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
		}
		return sb.toString();
	}

	/*
	 * 将16进制数字解码成字符串,适用于所有字符（包括中文）
	 */
	public static String hexToStr(String bytes) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(
				bytes.length() / 2);
		// 将每2位16进制整数组装成一个字节
		for (int i = 0; i < bytes.length(); i += 2)
			baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
					.indexOf(bytes.charAt(i + 1))));
		return new String(baos.toByteArray());
	}

	public static String DoubleToString(Double doub) {
		DecimalFormat f = new DecimalFormat("##############0.00");
		String str = "";
		try {
			str = f.format(doub);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return str;
	}

	public static String DoubleToString1(Double doub) {
		DecimalFormat f = new DecimalFormat("##############0.0000");
		String str = "";
		try {
			str = f.format(doub);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return str;
	}

	public static String DoubleToString2(Double count) {
		DecimalFormat f = new DecimalFormat("###,###,##0.00");
		String str = "";
		try {
			str = f.format(count);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return str;
	}

	public static String DoubleToString3(Double doub) {
		DecimalFormat f = new DecimalFormat("##############0.000000");
		String str = "";
		try {
			str = f.format(doub);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return str;
	}

	/**
	 * 获取字符串的base编码格式
	 * 
	 * @param str
	 * @return
	 */
	public static String getBase64Str(String str) {
		Base64.Encoder encoder = Base64.getEncoder();
		try {
			return encoder.encodeToString(str.getBytes("UTF-8")).replaceAll("\\r", "")
					.replaceAll("\\n", "");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
		return "";
	}

	/**
	 * 把base64编码的字符串还原
	 * 
	 * @param str
	 * @return
	 */
	public static String decodeBase64Str(String str) {
		Base64.Decoder decoder = Base64.getDecoder();
		try {
			return new String(decoder.decode(str.replaceAll("\\r", "")
					.replaceAll("\\n", "")), "UTF-8");
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return "";
	}

	/**
	 * 获得字符串长度，相对oracle汉字的字符长度为*3
	 * 
	 * @param str
	 * @return
	 */
	public static int getStringLengthByOracle(String str) {
		int count = 0;
		String regEx = "[\\u4e00-\\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		while (m.find()) {
			for (int i = 0; i <= m.groupCount(); i++) {
				count = count + 1;
			}
		}
		int length = str.length() - count + 3 * count;
		return length;
	}




	private static String getStar(int num) {
		StringBuffer str = new StringBuffer();
		while (str.length() < num) {
			str.append("*");
		}
		return str.toString();
	}



	/**
	 * 把长整型数字补齐指定字符串,形成定长的字符串
	 * 
	 * @param
	 *            源字符
	 * @param iLength
	 *            目标字符串的长度
	 * @param ch
	 *            填充字符串
	 * @param type
	 *            填充方式 front前面;after后面
	 * @return 异常返回 null或 "" ,成功返回定长的字符串
	 */
	public static String toDefiniteLengthString(String src, int iLength,
                                                String ch, String type) {
		StringBuffer sb = new StringBuffer("");
		if (iLength < src.length()) {
			return null;
		}
		if ("after".equals(type)) {
			sb.append(src);
		}
		for (int i = 0; i < iLength - src.length(); i++) {
			sb.append(ch);
		}
		if ("front".equals(type)) {
			sb.append(src);
		}
		return sb.toString();
	}
	/*由于Java是基于Unicode编码的，因此，一个汉字的长度为1，而不是2。 
     * 但有时需要以字节单位获得字符串的长度。例如，“123abc长城”按字节长度计算是10，而按Unicode计算长度是8。 
     * 为了获得10，需要从头扫描根据字符的Ascii来获得具体的长度。如果是标准的字符，Ascii的范围是0至255，如果是汉字或其他全角字符，Ascii会大于255。 
     * 因此，可以编写如下的方法来获得以字节为单位的字符串长度。*/  
    public static int getWordCount(String s)
    {  
        int length = 0;  
        for(int i = 0; i < s.length(); i++)  
        {  
            int ascii = Character.codePointAt(s, i);
            if(ascii >= 0 && ascii <=255)  
                length++;  
            else  
                length += 2;  
                  
        }  
        return length;  
          
    }  
      
    /*基本原理是将字符串中所有的非标准字符（双字节字符）替换成两个标准字符（**，或其他的也可以）。这样就可以直接例用length方法获得字符串的字节长度了*/  
    public static  int getWordCountRegex(String s)
    {  
  
        s = s.replaceAll("[^\\x00-\\xff]", "**");  
        int length = s.length();  
        return length;  
    }  
      
    /*按特定的编码格式获取长度*/  
    public static int getWordCountCode(String str, String code) throws UnsupportedEncodingException {
        return str.getBytes(code).length;  
    }  
    
    public static String DoubleToString4(Double doub) {
		DecimalFormat f = new DecimalFormat("##############");
		String str = "";
		try {
			str = f.format(doub);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return str;
	}
	public static void main(String[] argus) {
		// String a = "一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四";
		// String re = getBase64Str(a);
		// String abc =
		// "5LiA5LqM5LiJ5Zub5LqU5YWt5LiD5YWr5Lmd5Y2B5LiA5LqM5LiJ5Zub5LqU5YWt5LiD5YWr5Lmd5Y2B5LiA5LqM5LiJ5Zub5LqU5YWt5LiD5YWr5Lmd5Y2B5LiA5LqM5LiJ5Zub5LqU5YWt5LiD5YWr5Lmd5Y2B5LiA5LqM5LiJ5Zub";
		// 1eK99r321rvKx7j2suLK1NXivfa99ta7yse49rLiytTV4r32vfbWu8rHuPay4srU
		// TW9Cby5qYXI=
		// String ss = "luohua,haha;heihei";
		// String[] temps = ss.split("[,;]");
		// for (int i = 0; i < temps.length; i++) {
		// }
		// byte[] b = new byte[1];
		// StringBuffer sb = new StringBuffer("");
		// for (int i = 0; i < 33; i++) {
		// sb.append("一二三四五六七八九十");
		// }
		// // sb.append("!@#$%^&*()_+-={}[];':\"|\\?><,./`~，。；");
		// sb.append("你好不a好");
		// String ss = null;
		// if (ss instanceof String) {
		// } else {
		// }
		//
		// final String NAME_SPLIT = " ";
//		System.out.println("idcard========" + getIdCardInfo("230102831126713"));
//		System.out.println("mobileNo======" + getMobileNo("18628022917"));
//		System.out.println("email======" + getEmail("b@163.com"));
//		System.out.println("bankNo======" + getBankNo("123"));

		System.out.println("a======="+ Double.parseDouble("1.01")*100+"");
		System.out.println("a======="+DoubleToString4(Double.parseDouble("1.01")*100));
	}
	
	public static String byte2Base64(byte[] b) {
		Base64.Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(b);
	}
	
	public static byte[] base642Byte(String str) throws Exception {
		Base64.Decoder decoder = Base64.getDecoder();
		return decoder.decode(str.replaceAll("\\r", "").replaceAll("\\n", ""));
	}
	 public static String convertNullStr(Object str) {
	        if (str == null) {
	            return "";
	        }
	        return String.valueOf(str);
	    }
}
