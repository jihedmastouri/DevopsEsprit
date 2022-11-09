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

	
	@Mock // declaration d'un mock
	ProduitRepository pr;

	@InjectMocks
	ProduitServiceImpl psi;

	Produit p = Produit.builder().codeProduit("159").dateCreation(null).dateDerniereModification(null)
			.libelleProduit("arctic").prix(1200).build();

	@DisplayName("GET Produits")
	@Test
	void GetProductTest() {

		List<Produit> lst = new ArrayList<>();
		lst.add(new Produit());

		Mockito.when(pr.findAll()).thenReturn(lst);
		List<Produit> exp = psi.retrieveAllProduits();
		assertEquals(exp, lst);
	}

	@DisplayName("GET Produit By id")
	@Test
	void GetbyID() {

		Mockito.when(pr.findById(Mockito.anyLong())).thenReturn(Optional.of(p));
		Produit prod = psi.retrieveProduit(3L);
		assertNotNull(prod);
		verify(pr).findById(Mockito.anyLong());

	}

	@DisplayName("Update Produit")
	@Test
	void EditProductTest() {
		Produit pedit = new Produit();
		pedit.setIdProduit(3L);
		pedit.setLibelleProduit("edit");

		Produit new_pedit = new Produit();
		new_pedit.setLibelleProduit("new edit");

		Mockito.lenient().when(pr.findById(pedit.getIdProduit())).thenReturn(Optional.of(pedit));
		// assertEquals(pedit, psi.updateProduit(new_pedit) );
		pedit = psi.updateProduit(new_pedit);
		verify(pr).save(pedit);
	}

	@DisplayName("Add Produit")
	@Test
	void AddProductTest() {

		Produit produit = new Produit();
		produit.setLibelleProduit("arctic");
		Mockito.lenient().when(pr.save(produit)).thenReturn(produit);
		Produit newp = psi.addProduit(produit);
		assertEquals(produit.getLibelleProduit(), newp.getLibelleProduit());
		verify(pr).save(produit);
	}

	@DisplayName("Delete Produit")
	@Test
	void DeleteTest() {
		Produit p = new Produit();
		p.setLibelleProduit("libelle");
		p.setIdProduit((long) 3);
		Long pid = p.getIdProduit();
		Mockito.lenient().when(pr.findById(pid)).thenReturn(Optional.of(p));

		psi.deleteProduit(pid);
		verify(pr).deleteById(pid);
	}


}
