package fr.uga.l3miage.spring.tp3.endpoints;

import fr.uga.l3miage.spring.tp3.errors.AddCandidatesToCenterErrorResponse;
import fr.uga.l3miage.spring.tp3.models.CandidateEntity;
import fr.uga.l3miage.spring.tp3.responses.TestCenterResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Tag(name = "Gestion du centre", description = "Tous les endpoints du centre du test")
@RestController
@RequestMapping("/api/centre")
public interface TestCenterEndpoints {

    @Operation(description = "Ajouter des candidats au centre")
    @ApiResponse(responseCode = "202", description = "la liste des candidats a bien été ajoutée au centre")
    @ApiResponse(responseCode = "404", description = "le centre de test ou l'étudiant n'est pas trouvé", content = @Content(schema = @Schema(implementation = AddCandidatesToCenterErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "400", description = "mauvaise requête", content = @Content(schema = @Schema(implementation = AddCandidatesToCenterErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @PatchMapping("/{idCenter/add}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    TestCenterResponse addCandidatesToCenter(@PathVariable(name = "idCenter")Long idCenter, @RequestParam Set<CandidateEntity> candidateEntities) ;
}
