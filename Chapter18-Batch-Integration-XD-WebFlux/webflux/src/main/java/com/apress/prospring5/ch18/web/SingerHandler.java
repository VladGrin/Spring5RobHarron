package com.apress.prospring5.ch18.web;

import com.apress.prospring5.ch18.entities.Singer;
import com.apress.prospring5.ch18.repos.ReactiveSingerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
public class SingerHandler {

	@Autowired ReactiveSingerRepo reactiveSingerRepo;

	public HandlerFunction<ServerResponse> list = serverRequest -> ServerResponse.ok()
			.contentType(APPLICATION_JSON).body(reactiveSingerRepo.findAll(), Singer.class);

	public Mono<ServerResponse> show(ServerRequest request) {
		Mono<Singer> singerMono = reactiveSingerRepo.findById(Long.valueOf(request.pathVariable("id")));
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		return singerMono
				.flatMap(singer -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(singer)))
				.switchIfEmpty(notFound);
	}

	public HandlerFunction<ServerResponse> save = serverRequest -> ServerResponse.ok()
			.build(reactiveSingerRepo.save(serverRequest.bodyToMono(Singer.class)));
}
