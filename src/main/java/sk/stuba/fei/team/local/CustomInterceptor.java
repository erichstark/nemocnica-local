package sk.stuba.fei.team.local;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import sk.stuba.fei.team.local.repository.EmployeeRepository;
import sk.stuba.fei.team.local.security.CustomUser;
import sk.stuba.fei.team.local.service.FacilityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("customInterceptor")
public class CustomInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    FacilityService facilityService;

    @Autowired
    EmployeeRepository employeeRepository;

    public boolean isSetUp() {
        return facilityService.getFacility() != null;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String url = "/setup";
        if (!isSetUp() && !requestURI.startsWith(url)) {
            response.sendRedirect(url);
            return false;
        }
        if (isSetUp() && requestURI.startsWith(url)) {
            response.sendRedirect("/");
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
                    modelAndView.getModelMap().addAttribute("user", employeeRepository.findOne(userDetails.getUsername()));
                }
            }
        }
        if (modelAndView != null && facilityService.getFacility() != null) {
            modelAndView.getModelMap().addAttribute("headerText", facilityService.getFacility().getName());
        }
    }
}