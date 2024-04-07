package fr.uga.l3miage.spring.tp3.components;

import fr.uga.l3miage.spring.tp3.exceptions.technical.CandidateNotFoundException;
import fr.uga.l3miage.spring.tp3.models.CandidateEntity;
import fr.uga.l3miage.spring.tp3.models.CandidateEvaluationGridEntity;
import fr.uga.l3miage.spring.tp3.repositories.CandidateEvaluationGridRepository;
import fr.uga.l3miage.spring.tp3.repositories.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CandidateComponent {
    private final CandidateEvaluationGridRepository candidateEvaluationGridRepository;
    private final CandidateRepository candidateRepository;

    /**
     * Récupère les entités de la grille d'évaluation des candidats avec une note inférieure ou égale à 5,
     * extrait les entités de candidat associées, puis retourne un ensemble de ces candidats éliminés
     * */
    public Set<CandidateEntity> getAllEliminatedCandidate(){
        return candidateEvaluationGridRepository.findAllByGradeIsLessThanEqual(5)
                // convertit la liste résultante en un flux (Stream), ce qui permet de traiter les éléments de manière
                // séquentielle ou parallèle
                .stream()
                // transforme chaque entité de la grille d'évaluation (CandidateEvaluationGridEntity) en son entité de
                // candidat associée (CandidateEntity)
                .map(CandidateEvaluationGridEntity::getCandidateEntity)
                // Rassemble les candidats transformés en un ensemble (Set). L'utilisation de Collectors.toSet()
                // garantit qu'il n'y aura pas de doublons dans l'ensemble résultant
                .collect(Collectors.toSet());
    }


    public CandidateEntity getCandidatById(Long id) throws CandidateNotFoundException {
       return candidateRepository.findById(id).orElseThrow(()-> new CandidateNotFoundException(String.format("Le candidat [%s] n'a pas été trouvé",id),id));
    }



}
