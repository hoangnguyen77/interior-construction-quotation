package com.swp.spring.interiorconstructionquotation.controller;

import com.swp.spring.interiorconstructionquotation.dao.repository.PasswordResetTokenRepository;
import com.swp.spring.interiorconstructionquotation.entity.DTO.PasswordResetDto;
import com.swp.spring.interiorconstructionquotation.entity.PasswordResetToken;
import com.swp.spring.interiorconstructionquotation.entity.User;
import com.swp.spring.interiorconstructionquotation.service.IUserService;
import com.swp.spring.interiorconstructionquotation.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reset-password")
@RequiredArgsConstructor

public class ChangePasswordController {

/* @Autowired
    private UserService userService;
    @Autowired
    private ChangePasswordService changePasswordService;

    @Autowired
    private Validation formValidation;

    @GetMapping()
    public String showChangePasswordForm() {
        return "change-password"; // Thymeleaf template name
    }

    @PostMapping("/save_password")
    public String saveNewPassword (HttpServletRequest request, Model model){

        ChangPassword changPassword = new ChangPassword();
        changPassword.setUsername(request.getParameter("Username"));
        changPassword.setCurrentPassword(request.getParameter("current_password"));
        changPassword.setNewPassword(request.getParameter("new_password"));
        changPassword.setConfirmPassword(request.getParameter("confirm_password"));

        StringBuilder errors = new StringBuilder();

        formValidation.validate(changPassword, errors);

        if(errors.toString().trim().isEmpty()) { //all form inputs are valid
            changePasswordService.changePassword(changPassword, errors);
        }

        if(!errors.toString().trim().isEmpty()) {
            model.addAttribute("errorMsg", errors.toString());
            return "change-password";
        } else {
            model.addAttribute("successMsg", "Password was successfully changed");
            return "successpage";
        }


    }*//*


}
*/




    private final UserService userService;
    private final PasswordResetTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    @ModelAttribute("passwordResetForm")
    public PasswordResetDto passwordResetDto() {
        return new PasswordResetDto();
    }

    @GetMapping
    public String displayResetPasswordForm(@RequestParam(required = false) String token,
                                           Model model){
        PasswordResetToken resetToken = tokenRepository.findByToken(token);
        if (resetToken == null) {
            model.addAttribute("error", "Could not find password reset token");
        } else if (resetToken.isExpired()) {
            model.addAttribute("error", "Token has expired, please request a new password reset.");
        } else {
            model.addAttribute("token", resetToken.getToken());
        }
        return "reset-password";
    }

    @PostMapping
    @Transactional
    public String handlePasswordReset(@ModelAttribute("passwordResetForm") @Valid PasswordResetDto resetDto,
                                      BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.class.getName() + ".passwordResetForm", result);
            redirectAttributes.addFlashAttribute("passwordResetForm", resetDto);
            return "redirect:/reset-password?token=" + resetDto.getToken();
        }

        PasswordResetToken token = tokenRepository.findByToken(resetDto.getToken());
        User user = token.getUser();
        String updatePassword = passwordEncoder.encode(resetDto.getPassword());
        userService.updatepassword(updatePassword, user.getId() );
        tokenRepository.delete(token);

        return "redirect:/login?resetSuccess";
    }
}

