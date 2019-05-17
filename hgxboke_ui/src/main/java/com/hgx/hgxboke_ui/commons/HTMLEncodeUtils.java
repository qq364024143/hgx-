package com.hgx.hgxboke_ui.commons;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * HTML转义，如<a>  转为  &gt; a &lt;
 * @author hgx
 *
 */
public class HTMLEncodeUtils {
	
	//key：关键字，value：实体标签
	private static Map<String,String> HTML_KEYS = new HashMap<String,String>();
	static{
		HTML_KEYS.put("<", "&lt;");
		HTML_KEYS.put(">", "&gt;");
		HTML_KEYS.put("##）#！！（090*", "&amp;");//"&", "&amp;" &用##）#！！（090*代替，##）#！！（090*必须是用户不常输入的
		HTML_KEYS.put("\"", "&quot;");
		HTML_KEYS.put("'", "&apos;");
		HTML_KEYS.put(" ", "&nbsp;");
	}
	
	/**
	 * HTML转义
	 * @param htmlStr
	 * @return
	 */
	public static String encodeHTML(String htmlStr){
		if(htmlStr==null||"".equals(htmlStr)){
			return htmlStr;
		}
		String encodedHtmlStr = htmlStr.replaceAll("&", "##）#！！（090*");//防止替换&时多次替换，先将&转为##）#！！（090*再替换##）#！！（090*为html实体&amp;
		Set<Entry<String,String>> entrySet = HTML_KEYS.entrySet();
		Iterator<Entry<String, String>> it = entrySet.iterator();
		
		while(it.hasNext()){
			Entry<String,String> entry = it.next();
			encodedHtmlStr = encodedHtmlStr.replaceAll(entry.getKey(), entry.getValue());
		}
		return encodedHtmlStr;
	}
	
	/**
	 * 反转义&lt;转为<
	 * @param encodeHTMLStr
	 * @return
	 */
	public static String decodeHTML(String encodeHTMLStr){
		if(StringUtils.isEmpty(encodeHTMLStr)){
			return encodeHTMLStr;
		}
		
		String decodeHTMLResult = "";
		Set<Entry<String,String>> entrySet = HTML_KEYS.entrySet();
		Iterator<Entry<String, String>> it = entrySet.iterator();
		
		while(it.hasNext()){
			Entry<String,String> entry = it.next();
			encodeHTMLStr = encodeHTMLStr.replaceAll(entry.getValue(), entry.getKey());
		}
		decodeHTMLResult = encodeHTMLStr.replaceAll("##）#！！（090*", "&");
		return decodeHTMLResult;
	}
}
