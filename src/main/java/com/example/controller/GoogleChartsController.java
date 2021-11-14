package com.example.controller;

import com.example.dao.etudiant.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;
import java.util.TreeMap;

@Controller
public class GoogleChartsController {
    @Autowired
    private OffreRepository repo;

    @GetMapping("/google")
    public String getPieChart(Model model) {
        Map<String, Integer> graphData = new TreeMap<>();
        graphData.put("Offre", repo.countOffre());
        graphData.put("2014", 10);
        graphData.put("2015", 10);
        graphData.put("2016", 10);
        graphData.put("2017", 10);
        graphData.put("2018", 30);
        graphData.put("2019", 19);
        model.addAttribute("chartData", graphData);
        return "google";
    }
}
