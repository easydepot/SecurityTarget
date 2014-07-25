package core.sfr;

import static org.junit.Assert.*;

import java.util.ArrayList;


import org.junit.Test;

import core.printing.BasicElement;
import core.printing.Sequence;
import core.printing.SimpleText;
import core.sfr.SFRElement;
import core.sfr.SFRElementAssignement;


public class TestSFRElement {
	
	@Test
	public void test_SFRElement() {
		 given_a_SFR_element();
		when_adding_a_text_content();
		assertTrue(sut.hasContent());
	}
	
	@Test
	public void test_that_by_default_a_sfr_is_not_refined() {
		 given_a_SFR_element();
		assertTrue(sut.isNotRefined());
	}
	
	@Test
	public void test_that_a_sfr_is_refined_when_a_refined_has_been_set() {
		 given_a_SFR_element();

		sut.setRefinement("refinement");
		assertFalse(sut.isNotRefined());
	}
	
	@Test
	public void test_getRefinement_returns_the_refined_value_when_defined() {
		 given_a_SFR_element();

		sut.setRefinement("refinement");
		SimpleText result = (SimpleText) sut.getRefinement();
		assertEquals("refinement",result.getText());
	}

	
	
	@Test
	public void test_SFRElement_has_content_return_false() {
		 given_a_SFR_element();
		assertFalse(sut.hasContent());
	}
	
	@Test
	public void test_getIdent_returns_upper_case() {
		given_a_SFR_element();
		assertEquals("FMT_MTD.1.1",sut.getIdent());
	}
	

	@Test
	public void test_that_getContent_return_empty_String() throws Exception {
		 given_a_SFR_element();
		Sequence content = (Sequence) sut.getContent();
		assertNotNull(content);
		assertTrue(content.isEmpty());
	}
	
	@Test
	public void test_that_getContent_return_added_elements() throws Exception {
		 given_a_SFR_element();
		when_adding_a_text_content();
		assertEquals("content",((Sequence)sut.getContent()).getText());
	}
	
	@Test
	public void test_that_getContent_return_all_added_elements() throws Exception {
		 given_a_SFR_element();
		when_adding_a_text_content();
		sut.addContent(" and another content");
		assertEquals("content and another content",((Sequence)sut.getContent()).getText());
	}
	
	@Test
	public void test_that_getContent_return_all_added_elements_including_assignment() throws Exception {
		 given_a_SFR_element();
		when_adding_a_text_content();
		sut.addNewAssignement();
		sut.addContent(" and another content");
		assertEquals("content[assignement] and another content",((Sequence)sut.getContent()).getText());
	}
	
	@Test
	public void test_that_getContent_return_all_added_assignment() throws Exception {
		 given_a_SFR_element();
		sut.addNewAssignement();
		sut.addNewAssignement();
		assertEquals("[assignement][assignement]",((Sequence)sut.getContent()).getText());
	}
	
	@Test
	public void test_that_getListOfAssignement_returns_an_empty_list_when_no_assigment_is_added() {
		 given_a_SFR_element();
		ArrayList<SFRElementAssignement> list = sut.getListOfAssigment();
		assertTrue(list.isEmpty());
	}
	
	SFRElement sut;
	SFRElementAssignement a1;
	SFRElementAssignement a2;

	ArrayList<SFRElementAssignement> list;
	
	@Test
	public void test_that_getListOfAssignement_returns_an_non_empty_list_when_assigment_is_added() {
		given_a_SFR_element();
		whenAddingAnAssignement();
		when_calling_getListOfAssignement();
		assert_that_list_of_assignement_is_not_empty();
		assertEquals(1,list.size());
		assertTrue(list.contains(a1));
	}
	
	@Test
	public void test_that_a_SFR_Element_without_assignement_is_considered_as_not_assigned() {
		given_a_SFR_element();
		whenAddingAnAssignement();
		assert_that_SFR_Element_is_not_assigned();

		
		
	}
	
	@Test
	public void test_that_a_SFR_Element_without_assignement_completed_is_considered_as_assigned() {
		given_a_SFR_element();
		whenAddingAnAssignement();
		when_assigning_the_first_assignement();
		assert_that_SFR_Element_is_assigned();	
	}
	
	@Test
	public void test_that_a_SFR_Element_without_all_assignement_completed_is_considered_as_not_assigned() {
		given_a_SFR_element();
		whenAddingAnAssignement();
		a2 = sut.addNewAssignement();
		when_assigning_the_first_assignement();
		assert_that_SFR_Element_is_not_assigned();	
	}



	private void when_assigning_the_first_assignement() {
		a1.setContent("content");
	}



	private void assert_that_SFR_Element_is_not_assigned() {
		assertFalse(sut.isAssigned());
	}



	private void assert_that_SFR_Element_is_assigned() {
		assertTrue(sut.isAssigned());
	}
	
	@Test
	public void test_that_a_SFR_Element_with_assignement_is_considered_as_assigned() {
		given_a_SFR_element();
		assert_that_SFR_Element_is_assigned();
	}



	private void assert_that_list_of_assignement_is_not_empty() {
		assertFalse(list.isEmpty());
	}



	private void when_calling_getListOfAssignement() {
		list = sut.getListOfAssigment();
	}



	private void whenAddingAnAssignement() {
		a1 = sut.addNewAssignement();
	}
	
	private void when_adding_a_text_content() {
		sut.addContent("content");
	}

	private void given_a_SFR_element() {
		sut = new SFRElement("fmt_mtd.1.1");
	}

}
