/* (C)2022 */
package br.com.nascimento.pocaxon.web.controller;

import br.com.nascimento.pocaxon.domain.entity.RedeemEvents;
import br.com.nascimento.pocaxon.domain.saga.RegisterUserSaga;
import br.com.nascimento.pocaxon.domain.saga.StartFlowSaga;
import br.com.nascimento.pocaxon.web.request.RegisteUserStepRequest;
import br.com.nascimento.pocaxon.web.request.StartFlowRequest;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/redeem")
public class RedeemController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    private final StartFlowSaga startFlowSaga;
    private final RegisterUserSaga registerUserSaga;

    public RedeemController(
            CommandGateway commandGateway,
            QueryGateway queryGateway,
            StartFlowSaga startFlowSaga,
            RegisterUserSaga registerUserSaga) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
        this.startFlowSaga = startFlowSaga;
        this.registerUserSaga = registerUserSaga;
    }

    @PostMapping()
    public ResponseEntity<RedeemEvents> start(@RequestBody StartFlowRequest request) {
        try {
            return new ResponseEntity<>(startFlowSaga.start(request), HttpStatus.CREATED);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/step-user")
    public ResponseEntity<Object> registerUser(@RequestBody RegisteUserStepRequest request) {
        try {
            return new ResponseEntity<>(registerUserSaga.start(request), HttpStatus.CREATED);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
