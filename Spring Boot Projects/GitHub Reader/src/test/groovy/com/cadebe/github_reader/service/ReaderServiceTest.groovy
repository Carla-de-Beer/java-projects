package groovy.com.cadebe.github_reader.service

import com.cadebe.github_reader.model.User
import com.cadebe.github_reader.service.ReaderService
import org.eclipse.egit.github.core.Repository
import org.eclipse.egit.github.core.client.GitHubClient
import org.eclipse.egit.github.core.service.RepositoryService
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class ReaderServiceTest extends Specification {

    @Subject
    def readerService = new ReaderService()

    @Shared
    GitHubClient client

    @Shared
    List<Repository> list

//    def setup() {
//        client = new GitHubClient()
//        client.setOAuth2Token("")
//
//        def owner = new org.eclipse.egit.github.core.User()
//        owner.setUrl("url")
//        owner.setLogin("login")
//        owner.setHtmlUrl("HTML_url")
//
//        def repo = new Repository()
//        repo.setOwner(owner)
//
//        list = [repo]
//        def service = new RepositoryService(client)
//        service.getRepositories() >> list
//    }

//    def "ReaderService: getUser()"() {
//        given: "Get user when given a client"
//        def userName = list.get(0).getOwner().getLogin()
//        def url = list.get(0).getOwner().getHtmlUrl()
//        def expected = new User(userName, url)
//
//        when: "calling getUser()"
//        User result = readerService.getUser(client)
//
//        then: "getUser() successfully called"
//        result != null
//        result == expected
//    }
//
//    def "ReaderService: countAllRepositories()"() {
//        given: "Get user when given a client"
//        def expected = list.size()
//
//        when: "calling countAllRepositories()"
//        def result = readerService.countAllRepositories(client)
//
//        then: "countAllRepositories() successfully called"
//        result == expected
//    }
}
