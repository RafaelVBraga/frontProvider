package br.com.rvbraga.frontProvider.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.rvbraga.frontProvider.models.Escola;
import br.com.rvbraga.frontProvider.models.Programa;
import reactor.core.publisher.Mono;

@Service
public class ProgramaService {
	@Autowired
    private WebClient.Builder webClientBuilder;
	
	
    public List<Programa> listProgramas() {
        Mono<Programa[]> response = webClientBuilder.build()
                .get()
                .uri("http://localhost:8085/api/programas")
                .retrieve()
                .bodyToMono(Programa[].class);         
        return Arrays.asList(response.block());
    }
   
    public Programa savePrograma(Programa programa) {
        Mono<Programa> response = webClientBuilder.build()
                .post()
                .uri("http://localhost:8085/api/programas")
                .body(Mono.just(programa), Programa.class)
                .retrieve()
                .bodyToMono(Programa.class);
                

        return response.block();
    }

   
    public Programa getPrograma(UUID id) {
        Mono<Programa> response = webClientBuilder.build()
                .get()
                .uri("http://localhost:8085/api/programas/" + id)
                .retrieve()
                .bodyToMono(Programa.class);

        
        return response.block();
    }


    public Programa updatePrograma(UUID id,Programa programa) {
       Mono<Programa> response =  webClientBuilder.build()
                .put()                
                .uri("http://localhost:8085/api/programas/" + id)
                .body(Mono.just(programa), Programa.class)
                .retrieve()
                .bodyToMono(Programa.class);
               

        return response.block();
    }
    public void deletePrograma(UUID id) {
    	webClientBuilder.build()
        .delete()
        .uri("http://localhost:8085/api/programas/" + id)
        .retrieve()
        .bodyToMono(Void.class)
        .block();
         
     }
    

    public Escola[] getEscolas() {
        Mono<Escola[]> response = webClientBuilder.build()
                .get()
                .uri("http://localhost:8085/api/escolas")
                .retrieve()
                .bodyToMono(Escola[].class);

        return response.block();
    }

    public Programa[] getProgramas() {
        Mono<Programa[]> response = webClientBuilder.build()
                .get()
                .uri("http://localhost:8085/api/programas")
                .retrieve()
                .bodyToMono(Programa[].class);

        return response.block();
    }
}
