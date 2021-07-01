package com.bayside.util;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bayside.app.opinion.showdata.user.model.User;
import com.bayside.app.util.AppConstant;

public class CheckLoginFilter extends OncePerRequestFilter {

	private static final Logger log = Logger.getLogger(CheckLoginFilter.class);

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		User user = (User) request.getSession().getAttribute("user");
		String requesturi = request.getRequestURI();
		String[] openurl = AppConstant.mediaType.openurl;
		if(request.getSession().getAttribute("userid")==null||"".equals(request.getSession().getAttribute("userid"))||"null".equals(request.getSession().getAttribute("userid"))){
			if ("/app-opinion-showdata/selectLoginUser".equals(requesturi) || "/app-opinion-showdata/fastLogin".equals(requesturi) || "/app-opinion-showdata/".equals(requesturi) ||
					"/app-opinion-showdata/checklogin".equals(requesturi) || requesturi.contains("jsp") || 
					requesturi.contains("png") || requesturi.contains("css") || requesturi.contains("html") || requesturi.contains("js") || requesturi.contains("gif")) {
				System.out.println("允许请求：" + requesturi);
				filterChain.doFilter(request, response);
				/*String userid = "";
				if (user != null) {
					userid = user.getId();
				}
				String ip = "";
				try {
					ip = IpUtil.getIpAddr(httpRequest);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					log.info("为获取到客户端ip地址");
				}

				int status = response.getStatus();
				boolean successed = false;
				if (status == 200) {
					successed = true;
				}
				log.info("userid:" + userid + ",loginip:" + ip + ",requestAddress:" + requesturi + ",successed:"
						+ successed);*/
				return;
			} else {
				System.out.println(request.getRequestURI());
				System.out.println(request.getSession().getId() + "未登录 请求转到登录页面");
				/*if (null != request.getSession().getAttribute("userid")) {
					response.sendError(402);
					return;
				} else {
					response.sendError(401);
					return;
				}*/
				response.sendError(401);
				return;
			}
		}
		 filterChain.doFilter(request, response);
	}

}
