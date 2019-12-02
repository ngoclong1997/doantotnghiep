package com.drato.graduationthesis.controller.manager;

import com.drato.graduationthesis.model.Subject;
import com.drato.graduationthesis.service.interfaces.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/manager")
public class SubjectController {


    @Autowired
    SubjectService subjectService;


    @RequestMapping("/subject")
    public String index(Model model) {
        model.addAttribute("title", "lang.list-subject");
        model.addAttribute("subjects", subjectService.getAll());
        return "manager/subject/index";
    }

    @GetMapping("/subject/add")
    public String addSubject(Model model) {
        model.addAttribute("title", "lang.add-subject");
        model.addAttribute("subject", new Subject());
        return "manager/subject/add-subject";
    }

    @PostMapping("/subject/add")
    public String addSubject(@ModelAttribute("subject") @Valid Subject subject, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (subjectService.nameExisted(subject.getName())) {
            result.rejectValue("name", "err.subject-existed", "Môn này đã tồn tại");
        }

        if (result.hasErrors()){
            model.addAttribute("title", "lang.add-subject");
            return "manager/subject/add-subject";
        }
        subjectService.createSubject(subject);
        redirectAttributes.addFlashAttribute("message", "lang.add-succeed");
        return "redirect:/manager/subject";
    }

    @GetMapping("/subject/edit")
    public String editSubject(Model model, @RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        Subject subject = subjectService.getById(id);
        if (subject == null) {
            redirectAttributes.addFlashAttribute("message", "err.subject-not-found");
            return "redirect:/manager/subject";
        }
        model.addAttribute("subject", subject);
        model.addAttribute("title", "lang.edit-subject");
        return "manager/subject/edit-subject";
    }

    @PostMapping("/subject/edit")
    public String editUser(Model model, RedirectAttributes redirectAttributes, @ModelAttribute("subject") Subject subject, BindingResult result) {
        if (subjectService.nameUsedByOther(subject.getId(), subject.getName())) {
            result.rejectValue("name", "err.subject-existed", "Môn này đã tồn tại");
        }

        if (result.hasErrors()) {
            model.addAttribute("title", "lang.edit-subject");
            model.addAttribute("selectableSubjects", subjectService.getAll());
            return "manager/subject/edit-subject";
        }
        subjectService.editSubject(subject);
        redirectAttributes.addFlashAttribute("message", "lang.edit-succeed");
        return "redirect:/manager/subject";
    }

    @GetMapping("/subject/delete")
    public String deleteUser(RedirectAttributes redirectAttributes, @RequestParam("id") Long id) {
        Subject subject = subjectService.getById(id);
        if (subject == null) {
            redirectAttributes.addFlashAttribute("message", "err.subject-not-found");
            return "redirect:/manager/subject";
        }
        subjectService.deleteSubject(subject);
        redirectAttributes.addFlashAttribute("message", "lang.delete-succeed");
        return "redirect:/manager/subject";
    }
}
