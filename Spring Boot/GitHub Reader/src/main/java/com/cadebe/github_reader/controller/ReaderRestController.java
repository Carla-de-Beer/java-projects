package com.cadebe.github_reader.controller;

import com.cadebe.github_reader.service.ReaderService;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ReaderRestController.BASE_URL)
public class ReaderRestController {

    static final String BASE_URL = "/api/v1/reader";
    private static ReaderService readerService;

    @Autowired
    public ReaderRestController(ReaderService readerService) {
        ReaderRestController.readerService = readerService;
    }

    @RequestMapping(value = "/repos/{token}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    List<com.cadebe.github_reader.model.Repository>
    getRepositoryList(@PathVariable("token") String token) throws IOException {
        return ReaderRestController.readerService.buildRepositoryList(createGitHubClient(token));
    }

    @RequestMapping(value = "/languages/{token}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Map<String, Integer>
    getAllLanguages(@PathVariable("token") String token) throws IOException {
        return ReaderRestController.readerService.getAllLanguages(createGitHubClient(token));
    }

    @RequestMapping(value = "/frequencies/{token}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Map<String, Double>
    getLanguageFrequencies(@PathVariable("token") String token) throws IOException {
        return ReaderRestController.readerService.getLanguageFrequencies(createGitHubClient(token));
    }

    @RequestMapping(value = "/{token}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public List<Repository> getAllRepositories(@PathVariable("token") String token) throws IOException {
        return ReaderRestController.readerService.getAllRepositories(token);
    }

    @RequestMapping(value = "/user/{token}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public com.cadebe.github_reader.model.User getUser(@PathVariable("token") String token) throws IOException {
        return ReaderRestController.readerService.getUser(createGitHubClient(token));
    }

    @RequestMapping(value = "/repos/count/{token}", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public int countAllRepositories(@PathVariable("token") String token) throws IOException {
        return ReaderRestController.readerService.countAllRepositories(createGitHubClient(token));
    }

    private GitHubClient createGitHubClient(String token) {
        GitHubClient client = new GitHubClient();
        client.setOAuth2Token(token);
        return client;
    }

}
