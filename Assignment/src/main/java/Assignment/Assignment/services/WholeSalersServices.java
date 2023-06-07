package Assignment.Assignment.services;

import Assignment.Assignment.dto.WholeSalersDto;
import Assignment.Assignment.paging.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
public interface WholeSalersServices {

    List<WholeSalersDto>getAllWholeSalers();

    WholeSalersDto putWholeSalers(WholeSalersDto wholeSalersDto);

    WholeSalersDto updateWholeSaler(String wholeSalerId , WholeSalersDto wholeSalersDto);

    WholeSalersDto findWholeSalerById(String wholeSalerId);

    ResponseEntity<?> deletewholeSalerById(String id);

    PageResponse getAllWholeSellers(int pageNo, int pageSize, String sortBy, String sortDir);

    Page<WholeSalersDto> findByFiltering(WholeSalersDto wholeSalersDto, int pageNo, int pageSize);

}
