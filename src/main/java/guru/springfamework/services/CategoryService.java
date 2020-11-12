package guru.springfamework.services;

import java.util.List;

import guru.springfamework.api.v1.model.CategoryDTO;

public interface CategoryService {
	
	List<CategoryDTO> getAllCategories();
	
	CategoryDTO getCategoryByName(String name);

}
