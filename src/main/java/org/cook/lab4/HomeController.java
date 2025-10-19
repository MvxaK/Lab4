package org.cook.lab4;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    private final Services service;

    @GetMapping("/")
    public String home(Model model){
        List<ApplicationRequest> requests = service.getAllRequests();
        model.addAttribute("requests", requests);

        return "home";
    }

    @GetMapping("/pending")
    public String pending(Model model){
        List<ApplicationRequest> requests = service.getAllRequests();
        model.addAttribute("requests", requests);

        return "pending";
    }

    @GetMapping("/handled")
    public String handled(Model model){
        List<ApplicationRequest> requests = service.getAllRequests();
        model.addAttribute("requests", requests);

        return "handled";
    }

    @GetMapping("/request/{id}")
    public String details(@PathVariable Long id, Model model){
        ApplicationRequest request = service.getById(id);
        List<Courses> courses = service.getAllCourses();
        List<Operators> operators = service.getAllOperators();

        model.addAttribute("request", request);
        model.addAttribute("courses", courses);
        model.addAttribute("operators", operators);

        return "updateRequest";
    }

    @GetMapping("/create_request")
    public String addRequest(Model model){
        List<Courses> courses = service.getAllCourses();
        List<Operators> operators = service.getAllOperators();

        model.addAttribute("courses", courses);
        model.addAttribute("operators", operators);
        return "addRequest";
    }

    @PostMapping("/create_request")
    public String createRequest(@ModelAttribute ApplicationRequest requestToCreate, @RequestParam Long courseId, @RequestParam(required = false) List<Long> operatorIds, Model model){
        service.createRequest(requestToCreate, courseId, operatorIds);

        return "redirect:/";
    }

    @PostMapping("/update_request/{id}")
    public String updateRequest(@PathVariable Long id, @ModelAttribute ApplicationRequest requestToUpdate, @RequestParam Long courseId, @RequestParam(required = false) List<Long> operatorIds, Model model){
        service.updateRequest(id, requestToUpdate, courseId, operatorIds);
        model.addAttribute("request", service.getById(id));

        return "redirect:/";
    }

    @PostMapping("handle_request/{id}")
    public String handle_request(@PathVariable Long id){
        service.handleRequest(id);

        return "redirect:/";
    }


    @PostMapping("/delete_request/{id}")
    public String deleteRequest(@PathVariable Long id){
        service.deleteById(id);

        return "redirect:/";
    }
}
