package guru.springfamework.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.controllers.v1.VendorController;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService {
	
	private final VendorMapper vendorMapper;
	private final VendorRepository vendorRepository;
	
	public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
		this.vendorMapper = vendorMapper;
		this.vendorRepository = vendorRepository;
	}

	@Override
	public VendorDTO getVendorById(Long id) {
		// TODO Auto-generated method stub
		return vendorRepository.findById(id)
				.map(vendorMapper::vendorToVendorDTO)
				.map(vendorDTO -> {
					vendorDTO.setVendorUrl(getVendorUrl(id));
					return vendorDTO;
				})
				.orElseThrow(ResourceNotFoundException::new);
	}
	
	private String getVendorUrl(Long id) {
		return VendorController.BASE_URL + "/" + id;
	}

	@Override
	public VendorListDTO getAllVendors() {
		
		List<VendorDTO> vendorsDTOS = vendorRepository
				.findAll()
				.stream()
				.map(vendor -> {
					VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
					vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
					return vendorDTO;
				})
				.collect(Collectors.toList());
		
		return new VendorListDTO(vendorsDTOS);
	}

	@Override
	public VendorDTO createNewVendor(VendorDTO vendorDTO) {
		// TODO Auto-generated method stub
		return saveAndReturnDTO(vendorMapper.vendorDTOtoVendor(vendorDTO));
	}
	
	private VendorDTO saveAndReturnDTO(Vendor vendor) {
		Vendor savedVendor = vendorRepository.save(vendor);
		
		VendorDTO returnDto = vendorMapper.vendorToVendorDTO(savedVendor);
		
		returnDto.setVendorUrl(getVendorUrl(savedVendor.getId()));
		
		return returnDto;
	}

	@Override
	public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
		
		Vendor vendorToSave = vendorMapper.vendorDTOtoVendor(vendorDTO);
		vendorToSave.setId(id);
		
		return saveAndReturnDTO(vendorToSave);
		
	}

	@Override
	public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
		
		return vendorRepository.findById(id)
				.map(vendor -> {
					
					if(vendorDTO.getName() != null) {
						vendor.setName(vendorDTO.getName());
					}
					
					return saveAndReturnDTO(vendor);
				}).orElseThrow(ResourceNotFoundException::new);
	}

	@Override
	public void deleteVendorById(Long id) {
		vendorRepository.deleteById(id);

	}

}
