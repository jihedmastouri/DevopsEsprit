package tn.esprit.rh.achat.services;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.repositories.ProduitRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProduitServiceTest {

	
	@Mock
	ProduitRepository produitRepository;

	@InjectMocks
	ProduitServiceImpl produitService;

	Produit p = Produit.builder().codeProduit("20420").libelleProduit("WHAT")
			.prix(9999).dateCreation(null).dateDerniereModification(null).build();

	@DisplayName("GET Produits")
	@Test
	void GetProducts() {

		List<Produit> produits = new ArrayList<>();
		produits.add(new Produit());

		Mockito.when(produitRepository.findAll()).thenReturn(produits);
		List<Produit> exp = produitService.retrieveAllProduits();
		assertEquals(exp, produits);
	}

	@DisplayName("GET Produit By id")
	@Test
	void GetbyID() {

		Mockito.when(produitRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(p));
		Produit prod = produitService.retrieveProduit(42L);
		assertNotNull(prod);
		verify(produitRepository).findById(Mockito.anyLong());

	}

	@DisplayName("Update Produit")
	@Test
	void EditProduct() {
		Produit pd = new Produit();
		pd.setIdProduit(42L);
		pd.setLibelleProduit("Best Product");

		Produit new_pd = new Produit();
		new_pd.setLibelleProduit("new Product");

		Mockito.when(produitRepository.findById(pd.getIdProduit())).thenReturn(Optional.of(pd));
		pd = produitService.updateProduit(new_pd);
		verify(produitRepository).save(pd);
	}

	@DisplayName("Add Produit")
	@Test
	void AddProduct() {

		Produit produit = new Produit();
		produit.setLibelleProduit("Ma9arouna");
		Mockito.when(produitRepository.save(produit)).thenReturn(produit);
		Produit newp = produitService.addProduit(produit);
		assertEquals(produit.getIdProduit(), newp.getIdProduit());
		verify(produitRepository).save(produit);
	}

	@DisplayName("Delete Produit")
	@Test
	void Delete() {
		Produit p = new Produit();
		p.setLibelleProduit("Name");
		p.setIdProduit((long) 3);
		Long pid = p.getIdProduit();
		Mockito.when(produitRepository.findById(pid)).thenReturn(Optional.of(p));

		produitService.deleteProduit(pid);
		verify(produitRepository).deleteById(pid);
	}


}
