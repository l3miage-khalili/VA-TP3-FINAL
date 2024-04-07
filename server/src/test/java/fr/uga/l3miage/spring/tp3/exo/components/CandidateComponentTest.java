package fr.uga.l3miage.spring.tp3.exo.components;

import fr.uga.l3miage.spring.tp3.components.CandidateComponent;
import fr.uga.l3miage.spring.tp3.exceptions.technical.CandidateNotFoundException;
import fr.uga.l3miage.spring.tp3.models.CandidateEntity;
import fr.uga.l3miage.spring.tp3.models.CandidateEvaluationGridEntity;
import fr.uga.l3miage.spring.tp3.repositories.CandidateRepository;
import fr.uga.l3miage.spring.tp3.services.CandidateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CandidateComponentTest {
    @Autowired
    private CandidateComponent candidateComponent ;

    @MockBean
    private CandidateEvaluationGridEntity candidateEvaluationGridEntity ;

    @MockBean
    private CandidateRepository candidateRepository ;

    @Autowired
    private CandidateService candidateService ;

    @Test
    void getCandidateNotFound(){
        // Given
        when(candidateRepository.findById(anyLong())).thenReturn(Optional.empty()) ;

        // then - when
        assertThrows(CandidateNotFoundException.class, () -> candidateComponent.getCandidatById(1L)) ;
    }

//    @Test
//    void getCandidateAverageFound(){
//        // Given
//
//        // note 1
//        CandidateEvaluationGridEntity candidateEvaluationGridEntity1 = CandidateEvaluationGridEntity
//                .builder()
//                .grade(12.00)
//                .build() ;
//        // note 2
//        CandidateEvaluationGridEntity candidateEvaluationGridEntity2 = CandidateEvaluationGridEntity
//                .builder()
//                .grade(8.00)
//                .build() ;
//
//        Set<CandidateEvaluationGridEntity> candidateEvaluations = new HashSet<>() ;
//        candidateEvaluations.add(candidateEvaluationGridEntity1) ;
//        candidateEvaluations.add(candidateEvaluationGridEntity2) ;
//
//        CandidateEntity candidateEntity = CandidateEntity
//                .builder()
//                .email("c@gmail.com")
//                .candidateEvaluationGridEntities(candidateEvaluations)
//                .build() ;
//
//        candidateRepository.save(candidateEntity) ;
//
//        when(candidateService.getCandidateAverage(anyLong())).thenReturn(Optional.of()) ;
//
//        // when - then
//        assertDoesNotThrow(() -> candidateService.getCandidateAverage(10L));
//    }


}
