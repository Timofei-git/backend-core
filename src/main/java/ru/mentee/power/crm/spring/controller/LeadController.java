package ru.mentee.power.crm.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mentee.power.crm.domain.Lead;
import ru.mentee.power.crm.spring.service.LeadService;

import java.util.List;

@Controller
public class LeadController {

  private final LeadService leadService;

  public LeadController(LeadService leadService) {
    this.leadService = leadService;
  }

  @GetMapping("/leads")
  public String showLeads(Model model) {
    List<Lead> leads = leadService.findAll();
    model.addAttribute("leads", leads);
    return "leads/list";
  }

  // ВРЕМЕННО: добавим тестовый endpoint
  @GetMapping("/test-controller")
  @ResponseBody
  public String testController() {
    return "Контроллер работает! LeadService: " + (leadService != null ? "внедрен" : "null");
  }
}