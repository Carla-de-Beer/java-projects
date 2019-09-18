package com.cadebe.github_reader.controller;

import com.cadebe.github_reader.model.GitHubRepository;
import com.cadebe.github_reader.model.User;
import com.cadebe.github_reader.service.ReaderService;
import com.google.gson.JsonArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(ReaderController.BASE_URL)
public class ReaderController {

    static final String BASE_URL = "/";
    private static ReaderService readerService;

    @Autowired
    public ReaderController(ReaderService readerService) {
        ReaderController.readerService = readerService;
    }

    @GetMapping("/")
    public String greetingForm(Model model) {
        model.addAttribute("gitHubName", "");
        return "index";
    }

    @PostMapping("/")
    public String greetingSubmit(@RequestParam String gitHubName, Model model) {
        JsonArray getJsonArray = ReaderController.readerService.getJsonArray(gitHubName);
        List<GitHubRepository> repoList = ReaderController.readerService.getAllRepositories(getJsonArray);
        int repoCount = ReaderController.readerService.countAllRepositories(repoList);

        User user = ReaderController.readerService.getUser(getJsonArray.get(0));

        Map<String, Integer> langMap = ReaderController.readerService.getAllLanguages(repoList);
        Map<String, Double> freqMap = ReaderController.readerService.getLanguageFrequencies(langMap, repoCount);

        model.addAttribute("user", user);
        model.addAttribute("repoCount", repoCount);
        model.addAttribute("repoList", repoList);
        model.addAttribute("frequencies", freqMap);
        return "displayInfo";
    }
}