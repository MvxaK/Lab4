package org.cook.lab4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private final ApplicationRequestService applicationRequestService;

    public HomeController(ApplicationRequestService applicationRequestService) {
        this.applicationRequestService = applicationRequestService;
    }

    @GetMapping("/")
    public String home(Model model){
        List<ApplicationRequest> requests = applicationRequestService.getAllRequests();
        model.addAttribute("requests", requests);

        return "home";
    }

    @GetMapping("/pending")
    public String addRequest(Model model){
        List<ApplicationRequest> requests = applicationRequestService.getAllRequests();
        model.addAttribute("requests", requests);

        return "pending";
    }

    @GetMapping("/handled")
    public String handled(Model model){
        List<ApplicationRequest> requests = applicationRequestService.getAllRequests();
        model.addAttribute("requests", requests);

        return "handled";
    }

    @GetMapping("/request/{id}")
    public String details(@PathVariable Long id, Model model){
        ApplicationRequest request = applicationRequestService.getById(id);
        model.addAttribute("request", request);

        return "updateRequest";
    }

    @GetMapping("/create_request")
    public String addRequest(){
        return "addRequest";
    }

    @GetMapping("/handle_request/{id}")
    public String handle(@PathVariable Long id, Model model){
        ApplicationRequest request = applicationRequestService.getById(id);
        model.addAttribute("request", request);
        return "handleRequest";
    }

    @PostMapping("/create_request")
    public String createRequest(@ModelAttribute ApplicationRequest requestToCreate, Model model){
        applicationRequestService.createRequest(requestToCreate);

        return "redirect:/";
    }

    @PostMapping("/update_request/{id}")
    public String updateRequest(@PathVariable Long id, @ModelAttribute ApplicationRequest requestToUpdate, Model model){
        applicationRequestService.updateRequest(id, requestToUpdate);
        model.addAttribute("request", applicationRequestService.getById(id));

        return "redirect:/";
    }

    @PostMapping("handle_request/{id}")
    public String handle_request(@PathVariable Long id){
        applicationRequestService.handleRequest(id);

        return "redirect:/";
    }


    @PostMapping("/delete_request/{id}")
    public String deleteRequest(@PathVariable Long id){
        applicationRequestService.deleteById(id);

        return "redirect:/";
    }
}
