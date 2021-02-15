package de.upb.crypto.incentive.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class IssueClient {

    /*
     * Webclient configured with the url of the issue service
     */
    private WebClient issueClient;

    /*
     * Sends an request to the / endpoint which is configured to return the name of the service
     * This can be used to test whether a service is alive and reachable under some url
     */
    public Mono<String> sendAliveRequest() {
        return issueClient.get()
                .uri("/")
                .retrieve()
                .bodyToMono(String.class);
    }

}
