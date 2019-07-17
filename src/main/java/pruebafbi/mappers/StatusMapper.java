package pruebafbi.mappers;

import pruebafbi.dtos.StatusDto;
import pruebafbi.entities.EntityFactory;
import pruebafbi.entities.Status;

public class StatusMapper {

    public static Status dtoToBasicEntity(StatusDto statusDto)
    {
        final Status status = EntityFactory.instanceStatus(statusDto.getId());
        statusDto.setName(statusDto.getName());
        return status;
    }

}
