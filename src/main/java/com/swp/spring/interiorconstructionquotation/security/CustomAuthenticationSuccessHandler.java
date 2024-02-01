package com.swp.spring.interiorconstructionquotation.security;

import com.swp.spring.interiorconstructionquotation.entity.User;
import com.swp.spring.interiorconstructionquotation.service.IUserService;
import com.swp.spring.interiorconstructionquotation.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private IUserService iUserService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {
        // Lấy thông tin người dùng từ UserService (hoặc từ Authentication nếu bạn muốn)


        User user = iUserService.findByUsername(authentication.getName());

        // Lưu thông tin người dùng vào session
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
