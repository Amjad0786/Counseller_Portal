package in.amjadIT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.amjadIT.DTO.CounsellorDTO;
import in.amjadIT.DTO.DashboardResponseDTO;
import in.amjadIT.service.CounsellorService;
import in.amjadIT.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {

    @Autowired
    private CounsellorService counsellorService;

    @Autowired
    private EnquiryService enquiryService;

    @GetMapping("/")
    public String index(Model model) {
        CounsellorDTO cdto = new CounsellorDTO();
        model.addAttribute("counsellor", cdto);
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        model.addAttribute("counsellor", new CounsellorDTO());
        return "index";
    }

    @PostMapping("/login")
    public String handleLogin(HttpServletRequest req, @ModelAttribute("counsellor") CounsellorDTO counsellor, Model model) {
        CounsellorDTO counsellorDto = counsellorService.login(counsellor);

        if (counsellorDto == null) {
            model.addAttribute("emsg", "Invalid Credentials");
            return "index";
        } else {
            HttpSession session = req.getSession(true);
            session.setAttribute("counsellorId", counsellorDto.getCounsellorId());

            DashboardResponseDTO dashboardDto = enquiryService.getDashboardInfo(counsellorDto.getCounsellorId());
            model.addAttribute("dashboardDto", dashboardDto);
            return "dashboard";
        }
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("counsellor", new CounsellorDTO());
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("counsellor") CounsellorDTO counsellor, Model model) {
        boolean unique = counsellorService.uniqueEmailCheck(counsellor.getEmail());

        if (unique) {
            boolean register = counsellorService.register(counsellor);
            if (register) {
                model.addAttribute("smsg", "Registration Success");
            } else {
                model.addAttribute("emsg", "Registration Failed");
            }
        } else {
            model.addAttribute("emsg", "Enter Unique Email");
        }
        return "register";
    }
    
    @GetMapping("/dashboard")
    public String displayDashBoard(HttpServletRequest req, Model model) {
    	
    	HttpSession session = req.getSession(false);
    	Integer counsellorId = (Integer)session.getAttribute("counsellorId");
   
    	DashboardResponseDTO dashboardDto = enquiryService.getDashboardInfo(counsellorId); 
    	
    	model.addAttribute("dashboardDto", dashboardDto);
    	
    	return "dashboard";
    }
    
}
