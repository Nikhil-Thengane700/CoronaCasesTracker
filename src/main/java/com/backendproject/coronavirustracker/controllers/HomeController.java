package com.backendproject.coronavirustracker.controllers;
import com.backendproject.coronavirustracker.models.LocationStats;
import com.backendproject.coronavirustracker.service.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller

public class HomeController {
   @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")

    private String home(Model model){
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute( "locationStats", allStats);
        model.addAttribute( "totalReportedCases", totalReportedCases);
        model.addAttribute( "totalNewCases", totalNewCases);
        return "home.html";
    }

}
