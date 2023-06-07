package Assignment.Assignment.mapperDto;

import Assignment.Assignment.dto.WholeSalersDto;
import Assignment.Assignment.entity.WholeSalers;

public class MappingDto {

    public static WholeSalers wholeSalerDtoToWholeSaler(WholeSalersDto wholeSalersDto){

        WholeSalers wholeSalers = new WholeSalers();

        wholeSalers.setFirstName(wholeSalersDto.getFirstName());
        wholeSalers.setLastName(wholeSalersDto.getLastName());
        wholeSalers.setEmailId(wholeSalersDto.getEmailId());
        wholeSalers.setPhoneNo(wholeSalersDto.getPhoneNo());
        wholeSalers.setWholeSalerId(wholeSalersDto.getWholeSalerId());
        wholeSalers.setRole(wholeSalersDto.getRole());
        wholeSalers.setLocId(wholeSalersDto.getLocId());

        return wholeSalers;
    }

    public static WholeSalersDto wholeSalerToWholeSalerDto(WholeSalers wholeSalers){

        WholeSalersDto wholeSalersDto = new WholeSalersDto();

        wholeSalersDto.setId(wholeSalers.getId());
        wholeSalersDto.setFirstName(wholeSalers.getFirstName());
        wholeSalersDto.setLastName(wholeSalers.getLastName());
        wholeSalersDto.setEmailId(wholeSalers.getEmailId());
        wholeSalersDto.setPhoneNo(wholeSalers.getPhoneNo());
        wholeSalersDto.setWholeSalerId(wholeSalers.getWholeSalerId());
        wholeSalersDto.setRole(wholeSalers.getRole());
        wholeSalersDto.setLocId(wholeSalers.getLocId());

        return wholeSalersDto;
    }




}
