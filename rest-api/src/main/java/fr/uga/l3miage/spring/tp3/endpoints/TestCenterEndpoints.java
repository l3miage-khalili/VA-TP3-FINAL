package fr.uga.l3miage.spring.tp3.endpoints;

import fr.uga.l3miage.spring.tp3.enums.SessionStatus;
import fr.uga.l3miage.spring.tp3.responses.CenterResponse;
import fr.uga.l3miage.spring.tp3.responses.SessionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Gestion du centre", description = "Tous les endpoints du centre de test")
@RestController
@RequestMapping("/api/centre")
public interface TestCenterEndpoints {

    @Operation(description = "Ajouter des candidats au centre")
    @ApiResponse(responseCode = "202", description = "la liste des candidats a bien été ajoutée au centre")
    @ApiResponse(responseCode = "404", description = "le centre de test ou l'étudiant n'est pas trouvé")
    @ApiResponse(responseCode = "400", description = "mauvaise requête")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    CenterResponse addCandidatesToCenter(@PathVariable(name = "idEcoSession")Long idEcoSession, @RequestParam SessionStatus status) ;
}
