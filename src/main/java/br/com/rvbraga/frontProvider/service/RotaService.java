package br.com.rvbraga.frontProvider.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.rvbraga.frontProvider.models.Escola;
import br.com.rvbraga.frontProvider.models.Programa;
import br.com.rvbraga.frontProvider.models.Rota;
import reactor.core.publisher.Mono;

@Service
public class RotaService {
	@Autowired
    private WebClient.Builder webClientBuilder;
	
	
    public List<Rota> listRotas() {
        Mono<Rota[]> response = webClientBuilder.build()
                .get()
                .uri("http://localhost:8085/api/rotas")
                .retrieve()
                .bodyToMono(Rota[].class);         
        return Arrays.asList(response.block());
    }
   
    public Rota saveRota(Rota rota) {
        Mono<Rota> response = webClientBuilder.build()
                .post()
                .uri("http://localhost:8085/api/rotas/add")
                .body(Mono.just(rota), Rota.class)
                .retrieve()
                .bodyToMono(Rota.class);
                

        return response.block();
    }

   
    public Rota getRota(UUID id) {
        Mono<Rota> response = webClientBuilder.build()
                .get()
                .uri("http://localhost:8085/api/rotas/" + id)
                .retrieve()
                .bodyToMono(Rota.class);

        
        return response.block();
    }


    public Rota updateRota(UUID id,Rota rota) {
       Mono<Rota> response =  webClientBuilder.build()
                .put()                
                .uri("http://localhost:8085/api/rotas/" + id)
                .body(Mono.just(rota), Rota.class)
                .retrieve()
                .bodyToMono(Rota.class);
               

        return response.block();
    }
    public void deleteRota(UUID id) {
    	webClientBuilder.build()
        .delete()
        .uri("http://localhost:8085/api/rotas/" + id)
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
