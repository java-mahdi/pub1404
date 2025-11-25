package com.kh.testspring1.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.annotation.PostConstruct;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    private HashMap<Integer, String> names = new HashMap<>();
    private ArrayList<Integer> selected_names = new ArrayList<>();
    private String selected_name = "";

    @PostConstruct
    private void init() {
        if (!names.isEmpty())
            return;
        names.put(1, "John Doe");
        names.put(2, "Jane Smith");
        names.put(3, "Mike Johnson");
        names.put(4, "Sarah Wilson");
        names.put(5, "David Brown");
    }

    private void add_models_toResponse(Model model) {
        model.addAttribute("names", names);
        model.addAttribute("selected_names", selected_names);
        model.addAttribute("selected_name", selected_name);
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Welcome to testspring1");
        add_models_toResponse(model);

        return "index";
    }

    @PostMapping("/postNames")
    public String postMethodNames(@RequestParam Map<String, String> selected_options, Model model) {
        selected_names.clear();
        selected_options.entrySet().forEach(e -> {
            selected_names.add(Integer.parseInt(e.getKey().replace("name", "")));
        });

        add_models_toResponse(model);
        return "/index";
    }

    @PostMapping("/postName")
    public String postMethodName(@RequestParam Integer selected_option, Model model) {

        if (selected_option != null)
            selected_name = names.get(selected_option);
        add_models_toResponse(model);
        return "/index";
    }

    @GetMapping("/informValues")
    public String getMethodName() {
        System.out.println("selecte namessss =" + selected_names);
        System.out.println("selecte name=" + selected_name);
        return "/index";
    }

}
