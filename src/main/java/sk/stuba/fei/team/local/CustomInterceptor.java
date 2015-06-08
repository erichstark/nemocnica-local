package sk.stuba.fei.team.local;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import sk.stuba.fei.team.local.security.CustomUser;
import sk.stuba.fei.team.local.service.FacilityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("customInterceptor")
public class CustomInterceptor extends HandlerInterceptorAdapter {

    private static boolean setupRequired = true;
    private static boolean initFlag = true;

    @Autowired
    FacilityService facilityService;

    public boolean isSetUp() {
        if (initFlag) {
            initFlag = false;
            setupRequired = facilityService.findAll().iterator().hasNext();
        }
        return setupRequired;
    }

    public void setSetupRequired(boolean setupRequired) {
        CustomInterceptor.setupRequired = setupRequired;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (!isSetUp() && !requestURI.startsWith("/setup")) {
            response.sendRedirect("/setup");
            return false;
        }
        if (isSetUp() && requestURI.startsWith("/setup") && !requestURI.equals("/setup?finished")) {
            response.sendRedirect("/setup?finished");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(final HttpServletRequest request,
                           final HttpServletResponse response, final Object handler,
                           final ModelAndView modelAndView) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                CustomUser userDetails = (CustomUser) principal;
                if (modelAndView != null) {
                    modelAndView.getModelMap().
                            addAttribute("user", userDetails);
                }
            }
        }
    }
}