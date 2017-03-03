package onlinejudge.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String path = ((HttpServletRequest) req).getRequestURI();
		if(path.startsWith("/login")){
			chain.doFilter(req, res);
			return;
		}
		
		HttpSession session = ((HttpServletRequest) req).getSession();
		if(session.getAttribute("userName") == null || session.getAttribute("userId") == null){
			((HttpServletResponse) res).sendRedirect("/login");
		}else{
			chain.doFilter(req, res);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
}