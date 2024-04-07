package fr.uga.l3miage.spring.tp3.endpoints;

import fr.uga.l3miage.spring.tp3.enums.SessionStatus;
import fr.uga.l3miage.spring.tp3.errors.ChangeSessionStatusErrorResponse;
import fr.uga.l3miage.spring.tp3.request.SessionCreationRequest;
import fr.uga.l3miage.spring.tp3.responses.SessionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Gestion des sessions")
@RestController
@RequestMapping("/api/sessions")
public interface SessionEndpoints {

    @Operation(description = "Créer une session")
    @ApiResponse(responseCode = "201",description = "La session à bien été créer")
    @ApiResponse(responseCode = "400" ,description = "La session n'a pas être créer", content = @Content(schema = @Schema(implementation = String.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    SessionResponse createSession(@RequestBody SessionCreationRequest request);

    @Operation(description = "Changer l'état d'une session")
    @ApiResponse(responseCode = "200", description = "l'état de la session a bien été changé")
    @ApiResponse(responseCode = "409", description = "il y a eu un conflit de changement de l'état de la session",content = @Content(schema = @Schema(implementation = ChangeSessionStatusErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @PatchMapping("/{idEcoSession}/change")
    @ResponseStatus(HttpStatus.OK)
    SessionResponse changeSessionStatus(@PathVariable(name = "idEcoSession")Long idEcoSession, @RequestParam SessionStatus status) ;



}
