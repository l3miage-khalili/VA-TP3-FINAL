package fr.uga.l3miage.spring.tp3.exo.repositories;

import fr.uga.l3miage.spring.tp3.enums.TestCenterCode;
import fr.uga.l3miage.spring.tp3.models.CandidateEntity;
import fr.uga.l3miage.spring.tp3.models.CandidateEvaluationGridEntity;
import fr.uga.l3miage.spring.tp3.models.TestCenterEntity;
import fr.uga.l3miage.spring.tp3.repositories.CandidateEvaluationGridRepository;
import fr.uga.l3miage.spring.tp3.repositories.CandidateRepository;
import fr.uga.l3miage.spring.tp3.repositories.TestCenterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class CandidateRepositoryTest {
    @Autowired
    private CandidateRepository candidateRepository ;

    @Autowired
    private TestCenterRepository testCenterRepository ;

    @Autowired
    private CandidateEvaluationGridRepository candidateEvaluationGridRepository ;

    @Test
    void testRequestFindAllByTestCenterEntityCode(){
        // Given
        TestCenterEntity testCenterEntityGre = TestCenterEntity
                .builder()
                .code(TestCenterCode.GRE)
                .build() ;

        TestCenterEntity testCenterEntityPar = TestCenterEntity
                .builder()
                .code(TestCenterCode.PAR)
                .build() ;

        testCenterRepository.save(testCenterEntityGre) ;
        testCenterRepository.save(testCenterEntityPar) ;

        CandidateEntity candidateEntity = CandidateEntity
                .builder()
                .email("c1@gmail.com")
                .testCenterEntity(testCenterEntityGre)
                .build() ;

        CandidateEntity candidateEntity2 = CandidateEntity
                .builder()
                .email("c2@gmail.com")
                .testCenterEntity(testCenterEntityPar)
                .build() ;

        candidateRepository.save(candidateEntity) ;
        candidateRepository.save(candidateEntity2) ;

        // when
        Set<CandidateEntity> candidateEntitiesResponse = candidateRepository.findAllByTestCenterEntityCode(TestCenterCode.GRE) ;

        // then
        assertThat(candidateEntitiesResponse).hasSize(1) ;
        assertThat(candidateEntitiesResponse.stream().findFirst().get().getTestCenterEntity().getCode()).isEqualTo(TestCenterCode.GRE) ;

    }

    @Test
    void testRequestFindAllByCandidateEvaluationGridEntitiesGradeLessThan() {
        // Given
        CandidateEvaluationGridEntity candidateEvaluationGridEntity1 = CandidateEvaluationGridEntity
                .builder()
                .grade(12.50)
                .build() ;

        Set<CandidateEvaluationGridEntity> candidate1Evaluations = new HashSet<>() ;
        candidate1Evaluations.add(candidateEvaluationGridEntity1) ;

        CandidateEvaluationGridEntity candidateEvaluationGridEntity2 = CandidateEvaluationGridEntity
                .builder()
                .grade(8.00)
                .build() ;

        Set<CandidateEvaluationGridEntity> candidate2Evaluations = new HashSet<>() ;
        candidate2Evaluations.add(candidateEvaluationGridEntity2) ;

        CandidateEntity candidateEntity1 = CandidateEntity
                .builder()
                .email("c1@gmail.com")
                .candidateEvaluationGridEntities(candidate1Evaluations)
                .build() ;

        CandidateEntity candidateEntity2 = CandidateEntity
                .builder()
                .email("c2@gmail.com")
                .candidateEvaluationGridEntities(candidate2Evaluations)
                .build() ;

        candidateRepository.save(candidateEntity1) ;
        candidateRepository.save(candidateEntity2) ;

        candidateEvaluationGridEntity1.setCandidateEntity(candidateEntity1);
        candidateEvaluationGridEntity2.setCandidateEntity(candidateEntity2);

        candidateEvaluationGridRepository.save(candidateEvaluationGridEntity1) ;
        candidateEvaluationGridRepository.save(candidateEvaluationGridEntity2) ;


        // when
        Set<CandidateEntity> candidateEntitiesResponse = candidateRepository.findAllByCandidateEvaluationGridEntitiesGradeLessThan(10.00) ;

        // then
        assertThat(candidateEntitiesResponse).hasSize(1) ;
        //assertThat(candidateEntitiesResponse.stream().findFirst().get().getCandidateEvaluationGridEntities().stream().findFirst().get().getGrade()).isEqualTo(8.00) ;

    }

    @Test
    void testRequestFindAllByHasExtraTimeFalseAndBirthDateBefore() {
        // Given
        CandidateEntity candidateEntity1 = CandidateEntity
                .builder()
                .email("c1@gmail.com")
                .hasExtraTime(false)
                .birthDate(LocalDate.of(1995,1,1))
                .build() ;

        CandidateEntity candidateEntity2 = CandidateEntity
                .builder()
                .email("c2@gmail.com")
                .hasExtraTime(true)
                .birthDate(LocalDate.of(1995,1,1))
                .build() ;

        CandidateEntity candidateEntity3 = CandidateEntity
                .builder()
                .email("c3@gmail.com")
                .hasExtraTime(false)
                .birthDate(LocalDate.of(1998,1,1))
                .build() ;

        candidateRepository.save(candidateEntity1) ;
        candidateRepository.save(candidateEntity2) ;
        candidateRepository.save(candidateEntity3) ;

        // when
        Set<CandidateEntity> candidateEntitiesResponse = candidateRepository.findAllByHasExtraTimeFalseAndBirthDateBefore(LocalDate.of(1996,1,1)) ;

        assertThat(candidateEntitiesResponse).hasSize(1) ;
        assertThat(candidateEntitiesResponse.stream().findFirst().get().isHasExtraTime()).isEqualTo(false) ;
        assertThat(candidateEntitiesResponse.stream().findFirst().get().getBirthDate()).isEqualTo(LocalDate.of(1995,1,1)) ;

    }
}
