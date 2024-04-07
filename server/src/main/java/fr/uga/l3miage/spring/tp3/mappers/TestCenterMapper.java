package fr.uga.l3miage.spring.tp3.mappers;

import fr.uga.l3miage.spring.tp3.models.TestCenterEntity;
import fr.uga.l3miage.spring.tp3.responses.TestCenterResponse;
import org.mapstruct.Mapper;

@Mapper
public interface TestCenterMapper {

    TestCenterResponse toResponse(TestCenterEntity testCenterEntity) ;
}
