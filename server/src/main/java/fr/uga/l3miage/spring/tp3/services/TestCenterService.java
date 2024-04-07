package fr.uga.l3miage.spring.tp3.services;

import fr.uga.l3miage.spring.tp3.components.TestCenterComponent;
import fr.uga.l3miage.spring.tp3.exceptions.rest.AddingCandidatesToCenterRestException;
import fr.uga.l3miage.spring.tp3.exceptions.technical.NotFoundCenterException;
import fr.uga.l3miage.spring.tp3.mappers.TestCenterMapper;
import fr.uga.l3miage.spring.tp3.models.CandidateEntity;
import fr.uga.l3miage.spring.tp3.models.TestCenterEntity;
import fr.uga.l3miage.spring.tp3.responses.TestCenterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class TestCenterService {

    private final TestCenterComponent testCenterComponent ;
    private final TestCenterMapper testCenterMapper ;

    public TestCenterResponse addCandidatesToCenter(Long idCenter, Set<CandidateEntity> candidateEntities) {
        try {
            TestCenterEntity testCenterEntity = testCenterComponent.getTestCenter(idCenter) ;
            testCenterEntity.getCandidateEntities().addAll(candidateEntities) ;
            return testCenterMapper.toResponse(testCenterEntity) ;
        } catch (NotFoundCenterException e){
            throw new AddingCandidatesToCenterRestException(e.getMessage()) ;
        }
    }
}
