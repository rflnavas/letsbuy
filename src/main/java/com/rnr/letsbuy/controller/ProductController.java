package com.rnr.letsbuy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rnr.letsbuy.exception.NoItemFoundException;
import com.rnr.letsbuy.model.Product;
import com.rnr.letsbuy.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	protected ProductService productService;

	@RequestMapping(path = "/showproducts", method = { RequestMethod.GET })
	public String listProducts(Model model,
			@RequestParam(value = "order", required = false, defaultValue = "name") final String order) {
		ProductOrderFactory pof = ProductOrderFactory.get(order).orElse(ProductOrderFactory.NAME);
		List<Product> products = pof.orderBy(productService);// productService.findAllOrderByPrice();
		model.addAttribute("products", products);
		return "views/products";

	}

	@RequestMapping(path = "/product/{productId}", method = { RequestMethod.GET })
	public String productDetail(@PathVariable(name = "productId") Long id, Model model) {
		Product product = productService.find(id)
				.orElseThrow(() -> new NoItemFoundException("No product with " + id + " found"));
		model.addAttribute("product", product);
		return "views/productDetail";
	}

	@RequestMapping(path = "/product/create", method = { RequestMethod.GET })
	public String createProduct(Model model) {
		// We bind an empty product that will be later bound by the view by
		// th:object
		model.addAttribute("product", new Product());
		return "views/productForm";
	}

	@RequestMapping(path = "/product/edit/{productId}", method = { RequestMethod.GET })
	public String editProduct(Model model, @PathVariable(name = "productId") Long id) {
		// This time we bind an existing product
		Product product = productService.find(id).orElse(new Product());
		model.addAttribute("product", product);
		return "views/productForm";
	}

	@RequestMapping(path = "/product/save", method = { RequestMethod.POST })
	public String saveProduct(@Valid Product product, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "views/productForm";
		} else {
			Product savedProduct = productService.save(product);
			model.addAttribute("savedProduct", savedProduct);
			return "redirect:/product/" + savedProduct.getId();
		}

	}

	enum ProductOrderFactory {
		NAME {
			@Override
			public List<Product> orderBy(ProductService productService) {
				return productService.findAll();
			}
		},
		PRICE {
			@Override
			public List<Product> orderBy(ProductService productService) {
				return productService.findAllOrderByPrice();
			}
		};

		private static Map<String, ProductOrderFactory> orderStrategy = new HashMap<>();

		static {
			for (ProductOrderFactory pof : ProductOrderFactory.values()) {
				orderStrategy.put(pof.name(), pof);
			}
		}

		public static Optional<ProductOrderFactory> get(String strategyName) {
			return Optional.ofNullable(orderStrategy.get(strategyName.toUpperCase()));
		}

		public abstract List<Product> orderBy(ProductService productService);
	}

}
