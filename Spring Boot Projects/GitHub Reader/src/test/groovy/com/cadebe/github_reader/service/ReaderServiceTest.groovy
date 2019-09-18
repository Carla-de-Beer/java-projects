package com.cadebe.github_reader.service

import com.cadebe.github_reader.model.GitHubRepository
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class ReaderServiceTest extends Specification {

    @Shared
    def language1 = "language1"

    @Shared
    def language2 = "language2"

    @Shared
    List<GitHubRepository> repoList

    def setup() {
        repoList = []
        repoList.add(GitHubRepository.builder()
                .repoName("repoName1")
                .urlLink("urlLink1")
                .description("description1")
                .language(language1)
                .build())
        repoList.add(GitHubRepository.builder()
                .repoName("repoName2")
                .urlLink("urlLink2")
                .description("description2")
                .language(language2)
                .build())
        repoList.add(GitHubRepository.builder()
                .repoName("repoName3")
                .urlLink("urlLink3")
                .description("description3")
                .language(language1)
                .build())
    }

    @Subject
    def readerService = new ReaderService()

    def "ReaderService: getJsonArray()"() {
        given: "A name"
        def name = "octocat"

        when: "calling getJsonArray()"
        JsonArray result = readerService.getJsonArray(name)

        then: "getJsonArray() successfully called"
        result != null
        result.getClass() == JsonArray
        result.size() > 1
    }

    def "ReaderService: getAllRepositories()"() {
        given: "A JsonArray"
        def jsonArray = new JsonArray()
        def list = new JsonArray()

        JsonParser parser = new JsonParser()
        Object object = parser.parse(new FileReader("src/test/resources/com/cadebe/service/data.json"))
        list.add(object)
        when: "calling getAllRepositories()"
        List<GitHubRepository> result = readerService.getAllRepositories(jsonArray)

        then: "getAllRepositories() successfully called"
        result != null
        result.size() == 0
    }

    def "ReaderService: countAllRepositories()"() {
        given: "A list of GitHubRepositories"

        when: "calling countAllRepositories()"
        int result = readerService.countAllRepositories(repoList)

        then: "countAllRepositories() successfully called"
        result == repoList.size()
    }

    def "ReaderService: getAllLanguages()"() {
        given: "A list of GitHubRepositories"

        when: "calling getAllLanguages()"
        Map<String, Integer> result = readerService.getAllLanguages(repoList)

        then: "getAllLanguages() successfully called"
        result != null
        result.get(language1) == 2
        result.get(language2) == 1
    }

    def "ReaderService: getLanguageFrequencies()"() {
        given: "A language map and a language count"
        Map<String, Integer> langMap = new HashMap<>()
        langMap.put(givenLanguage, frequency)

        when: "calling getLanguageFrequencies()"
        Map<String, Double> result = readerService.getLanguageFrequencies(langMap, 4)

        then: "getLanguageFrequencies() successfully called"
        result != null
        result.get(givenLanguage) == percentage

        where:
        givenLanguage | frequency | percentage
        language1     | 2         | 50.0
        language2     | 1         | 25.0
    }
}