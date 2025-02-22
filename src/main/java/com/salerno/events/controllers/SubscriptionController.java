package com.salerno.events.controllers;
import com.salerno.events.dto.ErrorMessage;
import com.salerno.events.dto.SubscriptionResponse;
import com.salerno.events.exception.EventNotFoundException;
import com.salerno.events.exception.SubscriptionConflictException;
import com.salerno.events.exception.UserIndicNotFoundException;
import com.salerno.events.model.User;
import com.salerno.events.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService service;

    @PostMapping({"/{prettyName}", "/{prettyName}/{userId}"})
    public ResponseEntity<?> createSubscription(
            @PathVariable String prettyName,
            @RequestBody User subscriber,
            @PathVariable(required = false) Integer userId) {

        // Validação de entrada
        if (prettyName == null || prettyName.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorMessage("Event name cannot be null or empty."));
        }
        if (subscriber == null) {
            return ResponseEntity.badRequest().body(new ErrorMessage("Subscriber cannot be null."));
        }

        try {
            // Chama o serviço para criar a inscrição
            SubscriptionResponse res = service.createNewSubscription(prettyName, subscriber, userId);
            return ResponseEntity.ok(res);

        } catch (EventNotFoundException ex) {
            // Evento não encontrado
            return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));

        } catch (SubscriptionConflictException ex) {
            // Conflito de inscrição (usuário já inscrito no evento)
            return ResponseEntity.status(409).body(new ErrorMessage(ex.getMessage()));

        } catch (UserIndicNotFoundException ex) {
            // Usuário indicador não encontrado
            return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));

        } catch (IllegalArgumentException ex) {
            // Erro de validação (parâmetros inválidos)
            return ResponseEntity.badRequest().body(new ErrorMessage(ex.getMessage()));

        } catch (Exception ex) {
            // Erro genérico
            return ResponseEntity.internalServerError().body(new ErrorMessage("An unexpected error occurred."));
        }
    }
}