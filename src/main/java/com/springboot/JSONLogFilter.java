package com.springboot;


import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建者：梁建军
 * 创建日期： 2018/1/31
 * 创建时间： 15:33
 * JSONLogFilter
 * 版本：1.0
 * 说明：
 */
@Component
@WebFilter(urlPatterns = "/*", filterName = "JSONLogFilter")
public class JSONLogFilter implements Filter {

	public static final String TAG = "--" + JSONLogFilter.class.getSimpleName();

	private static final Logger logger = LoggerFactory.getLogger(JSONLogFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		Map<String, String> header = new HashMap<>();
		Enumeration<String> stringEnumeration = httpRequest.getHeaderNames();
		while (stringEnumeration.hasMoreElements()) {
			String name = stringEnumeration.nextElement();
			header.put(name, httpRequest.getHeader(name));
		}
		logger.info("请求：url={} method={},ip={},header={}",  httpRequest.getRequestURL(),
				httpRequest.getMethod(),
				httpRequest.getRemoteAddr(),
				JSON.toJSONString(header));

		HttpMethod httpMethod = HttpMethod.resolve(httpRequest.getMethod());
		if (HttpMethod.POST == httpMethod && request.getContentType() != null && MediaType.parseMediaType(request.getContentType()).includes(MediaType.APPLICATION_JSON)) {
			//json
			LogHttpServletRequestWrapper logHttpServletRequestWrapper = new LogHttpServletRequestWrapper((HttpServletRequest) request);
			LogHttpServletResponseWrapper logHttpServletResponseWrapper = new LogHttpServletResponseWrapper((HttpServletResponse) response);
			chain.doFilter(logHttpServletRequestWrapper, logHttpServletResponseWrapper);
			logger.info("返回：header={}",  JSON.toJSONString(getResponseHeader((HttpServletResponse) response)));
			logger.info("返回：code={} response={}",  logHttpServletResponseWrapper.getStatus(), new String(logHttpServletResponseWrapper.getBody(), logHttpServletResponseWrapper.getCharacterEncoding())); //args[]

		} else if (HttpMethod.GET == httpMethod) {
			logger.info("请求：request={}",  request.getParameterMap()); //args[]
			chain.doFilter(request, response);
			logger.info("返回：header={}",  JSON.toJSONString(getResponseHeader((HttpServletResponse) response)));
			logger.info("返回：code={}",  ((HttpServletResponse) response).getStatus()); //args[]

		} else {
			chain.doFilter(request, response);
			logger.info("返回：header={}",  JSON.toJSONString(getResponseHeader((HttpServletResponse) response)));
			logger.info("返回：code={}",  ((HttpServletResponse) response).getStatus()); //args[]
		}

	}

	private Map<String, String> getResponseHeader(HttpServletResponse response) {
		Map<String, String> header = new HashMap<>();
		Collection<String> stringEnumeration = response.getHeaderNames();
		for (String name : stringEnumeration) {
			header.put(name, response.getHeader(name));
		}
		return header;
	}

	@Override
	public void destroy() {

	}

}
