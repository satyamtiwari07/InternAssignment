package Assignment.Assignment.services;

import Assignment.Assignment.dto.WholeSalersDto;
import Assignment.Assignment.entity.WholeSalers;
import Assignment.Assignment.exception.AllExceptions;
import Assignment.Assignment.mapperDto.MappingDto;
import Assignment.Assignment.paging.PageResponse;
import Assignment.Assignment.repository.WholeSalerRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class WholeSalersServiceImpl implements WholeSalersServices{

    WholeSalerRepo wholeSalerRepo;

    @Autowired
    public WholeSalersServiceImpl(WholeSalerRepo wholeSalerRepo) {
        this.wholeSalerRepo = wholeSalerRepo;
    }

    @Override
    public List<WholeSalersDto> getAllWholeSalers() {

        List<WholeSalers>temp = wholeSalerRepo.findAll();

        List<WholeSalersDto>allWholeSalers=new ArrayList<>();

        for (WholeSalers t:temp) {
            WholeSalersDto wholeSalersDto = MappingDto.wholeSalerToWholeSalerDto(t);
            allWholeSalers.add(wholeSalersDto);
        }

        return allWholeSalers;
    }

    @Override
    public WholeSalersDto putWholeSalers(WholeSalersDto wholeSalersDto) {

        String id = wholeSalersDto.getWholeSalerId();
        if(wholeSalerRepo.findBywholeSalerId(id)!=null) throw new AllExceptions("This WholeSalerId Already Exists");

        WholeSalers wholeSalers = MappingDto.wholeSalerDtoToWholeSaler(wholeSalersDto);
        WholeSalers t = new WholeSalers();
        t = wholeSalerRepo.save(wholeSalers);
        return MappingDto.wholeSalerToWholeSalerDto(t);
    }

    @Override
    public WholeSalersDto updateWholeSaler(String wholeSalerId , WholeSalersDto wholeSalersDto) {

        WholeSalers t = wholeSalerRepo.findBywholeSalerId(wholeSalerId);
        if(t==null) throw new AllExceptions("WholeSaler with this id not exists");

        t.setFirstName(wholeSalersDto.getFirstName());
        t.setLastName(wholeSalersDto.getLastName());
        t.setEmailId(wholeSalersDto.getEmailId());
        t.setPhoneNo(wholeSalersDto.getPhoneNo());
        t.setRole(wholeSalersDto.getRole());
        t.setWholeSalerId(wholeSalersDto.getWholeSalerId());
        t.setLocId(wholeSalersDto.getLocId());

        wholeSalerRepo.save(t);

        return MappingDto.wholeSalerToWholeSalerDto(t);
    }

    @Override
    public WholeSalersDto findWholeSalerById(String wholeSalerId ) {

        WholeSalers t = wholeSalerRepo.findBywholeSalerId(wholeSalerId);
        if(t==null) throw new AllExceptions("WholeSaler with this id not exists");

        WholeSalersDto wholeSalersDto = MappingDto.wholeSalerToWholeSalerDto(t);
        return wholeSalersDto;
    }

    @Override
    public ResponseEntity<?> deletewholeSalerById(String id) {

        WholeSalers t = wholeSalerRepo.findBywholeSalerId(id);
        if(t==null) throw new AllExceptions("Not Found WholeSaler With id "+id);

        wholeSalerRepo.deleteBywholeSalerId(id);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("status", HttpStatus.ACCEPTED);
        map.put("message", "Record is deleted successfully!");

        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @Override
    public PageResponse getAllWholeSellers(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<WholeSalers> wholeSalers = wholeSalerRepo.findAll(pageable);

        // get content for page object
        List<WholeSalers> listOfWholeSellers = wholeSalers.getContent();

        List<WholeSalersDto> content= listOfWholeSellers.stream().map(wholeSeller -> MappingDto.wholeSalerToWholeSalerDto(wholeSeller)).collect(Collectors.toList());

        PageResponse pageResponse = new PageResponse();
        pageResponse.setContent(content);
        pageResponse.setPageNo(wholeSalers.getNumber());
        pageResponse.setPageSize(wholeSalers.getSize());
        pageResponse.setTotalElements(wholeSalers.getTotalElements());
        pageResponse.setTotalPages(wholeSalers.getTotalPages());
        pageResponse.setLast(wholeSalers.isLast());

        return pageResponse;
    }

    @Override
    public Page<WholeSalersDto> findByFiltering(WholeSalersDto wholeSalersDto,int pageNo,int pageSize) {

        String fname=wholeSalersDto.getFirstName();
        HashSet<WholeSalers> hashSet = new HashSet<>();

        if(fname!=null && wholeSalerRepo.findByfirstName(fname)!=null){
            hashSet.addAll(wholeSalerRepo.findByfirstName(fname));
        }

        String lname=wholeSalersDto.getLastName();
        if(lname!=null && wholeSalerRepo.findBylastName(lname)!=null){
            hashSet.addAll(wholeSalerRepo.findBylastName(lname));
        }

        String emailId = wholeSalersDto.getEmailId();
        if(emailId!=null && wholeSalerRepo.findByemailId(emailId)!=null){
            hashSet.addAll(wholeSalerRepo.findByemailId(emailId));
        }

        String phoneNo = wholeSalersDto.getPhoneNo();
        if(phoneNo!=null && wholeSalerRepo.findByphoneNo(phoneNo)!=null){
            hashSet.addAll(wholeSalerRepo.findByphoneNo(phoneNo));
        }

        String wholeSalerId = wholeSalersDto.getWholeSalerId();
        List<WholeSalersDto>AllFilteredWholeSallers = new ArrayList();

        if(wholeSalerId!=null && wholeSalerRepo.findBywholeSalerId(wholeSalerId)!=null){
            AllFilteredWholeSallers.add(MappingDto.wholeSalerToWholeSalerDto(wholeSalerRepo.findBywholeSalerId(wholeSalerId)));
        }

        for (WholeSalers value : hashSet) {
            AllFilteredWholeSallers.add(MappingDto.wholeSalerToWholeSalerDto(value));
        }

        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<WholeSalersDto> page = new PageImpl<WholeSalersDto>(AllFilteredWholeSallers, pageable, AllFilteredWholeSallers.size());

        return page;

    }

}
