package com.cadebe.github_reader.controller;

import com.cadebe.github_reader.model.Repository;
import com.cadebe.github_reader.model.Token;
import com.cadebe.github_reader.service.ReaderService;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(ReaderController.BASE_URL)
public class ReaderController {

    static final String BASE_URL = "/";
    private static ReaderService readerService;
    private static String token = "";

    @Autowired
    public ReaderController(ReaderService readerService) {
        ReaderController.readerService = readerService;
    }

    @GetMapping("/")
    public String greetingForm(Model model) {
        model.addAttribute("token", new Token());
        return "index";
    }

    @PostMapping("/")
    public String greetingSubmit(@ModelAttribute Token input, Model model) throws IOException {
        token = input.getTokenString();
        com.cadebe.github_reader.model.User user = ReaderController.readerService.getUser(createGitHubClient(token));
        List<Repository> repos = ReaderController.readerService.buildRepositoryList(createGitHubClient(token));
        Map<String, Double> map = ReaderController.readerService.getLanguageFrequencies(createGitHubClient(token));
        model.addAttribute("user", user);
        model.addAttribute("repoCount", repos.size());
        model.addAttribute("repoList", repos);
        model.addAttribute("frequencies", map);
        return "displayInfo";
    }

    private GitHubClient createGitHubClient(String token) {
        GitHubClient client = new GitHubClient();
        client.setOAuth2Token(token);
        return client;
    }

}