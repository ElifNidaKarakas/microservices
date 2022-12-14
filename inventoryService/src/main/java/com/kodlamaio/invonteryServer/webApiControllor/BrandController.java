package com.kodlamaio.invonteryServer.webApiControllor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.invonteryServer.business.abstracts.BrandService;
import com.kodlamaio.invonteryServer.business.request.create.CreateBrandRequest;
import com.kodlamaio.invonteryServer.business.request.update.UpdateBrandRequest;
import com.kodlamaio.invonteryServer.business.responses.cretae.CreateBrandResponse;
import com.kodlamaio.invonteryServer.business.responses.get.GetAllBrandsResponse;
import com.kodlamaio.invonteryServer.business.responses.update.UpdateBrandResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/brands")
public class BrandController {
	private BrandService brandService;

	@GetMapping
	public List<GetAllBrandsResponse> getAll() {
		return this.brandService.getAll();
	}

	@PostMapping
	public CreateBrandResponse add(@RequestBody CreateBrandRequest createBrandRequest) {
		return this.brandService.add(createBrandRequest);
	}

	@PutMapping
	public UpdateBrandResponse update(@RequestBody UpdateBrandRequest updateBrandRequest) {
		return brandService.update(updateBrandRequest);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		this.brandService.delete(id);
	}

}
