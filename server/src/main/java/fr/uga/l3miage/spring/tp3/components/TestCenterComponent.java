package fr.uga.l3miage.spring.tp3.components;

import fr.uga.l3miage.spring.tp3.exceptions.technical.NotFoundCenterException;
import fr.uga.l3miage.spring.tp3.models.TestCenterEntity;
import fr.uga.l3miage.spring.tp3.repositories.TestCenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestCenterComponent {

    private TestCenterRepository testCenterRepository ;

    public TestCenterEntity getTestCenter(Long id) throws NotFoundCenterException {
        return testCenterRepository.findById(id).orElseThrow(() -> new NotFoundCenterException(String.format("Le centre %s n'existe pas", id))) ;
    }
}