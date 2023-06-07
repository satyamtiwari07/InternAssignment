package Assignment.Assignment.controller;

import Assignment.Assignment.constants.AllConstants;
import Assignment.Assignment.dto.WholeSalersDto;
import Assignment.Assignment.paging.PageResponse;
import Assignment.Assignment.services.WholeSalersServiceImpl;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Transactional
public class Controllers {

    WholeSalersServiceImpl wholeSalersService;

    @Autowired
    public Controllers(WholeSalersServiceImpl wholeSalersService) {
        this.wholeSalersService = wholeSalersService;
    }

//    @GetMapping("/WholeSellers")
//    List<WholeSalersDto> getAllWholeSaler(){
//        return wholeSalersService.getAllWholeSalers();
//    }

    @PostMapping("/WholeSellers")
    WholeSalersDto putWholeSellers(@Valid @RequestBody WholeSalersDto wholeSalersDto){
        return wholeSalersService.putWholeSalers(wholeSalersDto);
    }

    @PutMapping("/{id}")
    WholeSalersDto updateWholeSellers(@Valid @PathVariable(value="id") String id ,@Valid @RequestBody WholeSalersDto wholeSalersDto){
        return wholeSalersService.updateWholeSaler(id,wholeSalersDto);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteWholeSellers(@Valid @PathVariable(value="id") String id){
      return wholeSalersService.deletewholeSalerById(id);
    }

    @GetMapping("/WholeSellers")
    public PageResponse getAllWholeSellers(
            @RequestParam(value = "pageNo", defaultValue = AllConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AllConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AllConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AllConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return wholeSalersService.getAllWholeSellers(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/filter")
    Page<WholeSalersDto> findAllWholeSellersByFiltering(
            @RequestBody WholeSalersDto wholeSalersDto,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize){
        return wholeSalersService.findByFiltering(wholeSalersDto,pageNo,pageSize);
    }


}
