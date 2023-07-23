package team.yellow.docconnect.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team.yellow.docconnect.entity.Country;
import team.yellow.docconnect.exception.ResourceNotFoundException;
import team.yellow.docconnect.payload.dto.CountryDto;
import team.yellow.docconnect.payload.mapper.CountryMapper;
import team.yellow.docconnect.payload.response.CountryResponse;
import team.yellow.docconnect.repository.CountryRepository;
import team.yellow.docconnect.service.CountryService;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public CountryDto createCountry(CountryDto countryDto) {
        Country countryToCreate = CountryMapper.INSTANCE.dtoToEntity(countryDto);
        return CountryMapper.INSTANCE.entityToDTO(countryRepository.save(countryToCreate));
    }

    @Override
    public CountryDto getCountryById(Long countryId) {
       Country foundCountry = countryRepository.findById(countryId)
               .orElseThrow(() -> new ResourceNotFoundException("Country", "Id", countryId));
       return CountryMapper.INSTANCE.entityToDTO(foundCountry);
    }

    @Override
    public CountryResponse getAllCountries(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Country> countries = countryRepository.findAll(pageable);
        return getResponse(countries);
    }

    @Override
    public CountryDto updateCountryById(CountryDto countryDto, Long countryId) {
        Country foundCountry = countryRepository.findById(countryId)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "Id", countryId));
        foundCountry.setName(countryDto.name());
        return CountryMapper.INSTANCE.entityToDTO(foundCountry);
    }

    @Override
    public void deleteCountryId(Long countryId) {
        Country foundCountry = countryRepository.findById(countryId)
                .orElseThrow(() -> new ResourceNotFoundException("Country", "Id", countryId));
        countryRepository.delete(foundCountry);
    }

    private CountryResponse getResponse(Page<Country> countries) {
        List<CountryDto> content = CountryMapper.INSTANCE.entityToDTO(countries.getContent());
        return new CountryResponse
                (
                        content,
                        countries.getNumber(),
                        countries.getSize(),
                        countries.getTotalElements(),
                        countries.getTotalPages(),
                        countries.isLast()
                );
    }
}
