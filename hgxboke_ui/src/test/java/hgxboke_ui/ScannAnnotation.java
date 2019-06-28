package hgxboke_ui;


import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;

import com.hgx.hgxboke_ui.config.ErrorPageConfig;

public class ScannAnnotation {
	
	public static void main(String[] args) {
		String value = ErrorPageConfig.class.getAnnotation(MyAnnotation.class).value();
		//
		String url = ErrorPageConfig.class.getAnnotation(RequestMapping.class).value()[0];
		
		Map<String,String> m = new HashMap<String,String>();
		//save(m);
		
	}
}
