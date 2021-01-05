package guru.springfamework.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;

@Mapper
public interface VendorMapper {
	
	VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);
	
	VendorDTO vendorToVendorDTO(Vendor vendor);
	
	Vendor vendorDTOtoVendor(VendorDTO vendorDTO);

}
