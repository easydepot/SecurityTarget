package core.printer.securityTarget;

import junit.framework.Assert;

import org.junit.Test;

import core.asset.Asset;
import core.asset.CCAsset;
import core.cc.SecurityTarget;
import core.cc.TermAndDefinition;
import core.cc.attributes.SecurityAttribute;
import core.printing.BasicElement;
import core.printing.BasicElementWithChildren;
import core.printing.SimpleText;
import core.printing.WarningText;
import core.printing.list.ListItem;
import core.printing.table.TablePrinter;
import core.securityObjective.SecurityObjective;
import core.securityneed.Integrity;

public class TestSecurityTargetPrinter {

	@Test
	public void test_Contructor() {
		SecurityTarget st = new SecurityTarget();
		SecurityTargetPrinter stprinter = new SecurityTargetPrinter(st);
		Assert.assertNotNull(stprinter);
	}
	
	@Test
	public void test_printListOfSubobjective_withnosubobjective() throws Exception {
		SecurityObjective secobj = new SecurityObjective("id");
		BasicElementWithChildren s = (BasicElementWithChildren) SecurityTargetPrinter.printListOfSubobjective(secobj);
		Assert.assertEquals(0, s.size());
		

	}
	
	@Test
	public void test_printListOfTermAndDefinition_return_EmptyListItemWhenEmpty() throws Exception {
		SecurityTarget st = new SecurityTarget();
		ListItem l = SecurityTargetPrinter.printListOfTermAndDefinition(st.getTermAndDefinitionList());
		Assert.assertNotNull(l);
		Assert.assertEquals(ListItem.class,l.getClass());
		Assert.assertEquals(0, l.size());
	}
	
	@Test
	public void test_printLineTermAndDefinition_return_TermLine() throws Exception {
		TermAndDefinition def = new TermAndDefinition("term","definition");
		BasicElementWithChildren s = SecurityTargetPrinter.printLineTermAndDefinition(def);
		Assert.assertEquals(3, s.size());
		Assert.assertEquals("term", ((SimpleText)s.get(0)).getText());
		Assert.assertEquals("definition", ((SimpleText)s.get(2)).getText());
	}
	
	@Test
	public void test_printListOfTermAndDefinition_return_NonEmptyListItemWhenNonEmpty() throws Exception {
		SecurityTarget st = new SecurityTarget();
		st.addTermAndDefinition("term1", "definition1");
		ListItem l = SecurityTargetPrinter.printListOfTermAndDefinition(st.getTermAndDefinitionList());
		Assert.assertNotNull(l);
		Assert.assertEquals(ListItem.class,l.getClass());
		Assert.assertEquals(1, l.size());
		
	}
	
	@Test
	public void test_printListOfSubobjective_withsubobjective() throws Exception {
		SecurityObjective secobj = givenASecurityObjectiveWithOneSubobjective();
		BasicElementWithChildren s = (BasicElementWithChildren) SecurityTargetPrinter.printListOfSubobjective(secobj);
		Assert.assertNotSame(0, s.size());
		Assert.assertEquals("This objective covers the following subobjectives:", ((SimpleText)s.get(0)).getText());
		Assert.assertEquals(ListItem.class,s.get(1).getClass());
		Assert.assertEquals(1,((ListItem) s.get(1)).size());

	}

	private SecurityObjective givenASecurityObjectiveWithOneSubobjective() throws Exception {
		SecurityObjective secobj = new SecurityObjective("id");
		SecurityObjective subobj = new SecurityObjective("id");
		subobj.setDescription("description");
		subobj.setId("myID");
		secobj.add(subobj);
		return secobj;
	}
	
	@Test
	public void test_getListPrintStructure_create_a_listItem_with_objective() throws Exception {
		SecurityObjective secobj = givenASecurityObjectiveWithOneSubobjective();
		ListItem l = SecurityTargetPrinter.getListPrintStructure(secobj);
		Assert.assertEquals(1, l.size());
		
	}
	
	@Test
	public void test_subobjectiveLine_returns_a_string_containing_the_sub_objective_description() throws Exception {
		SecurityObjective secobj = new SecurityObjective("id");
		BasicElement description = new SimpleText("description");
		secobj.setDescription(description);
		BasicElement s = SecurityTargetPrinter.printSubObjectiveLine(secobj);
		Assert.assertEquals(description, s);
	}
	
	
	
	
	@Test
	public void test_addAssetLines_with_asset_without_attributes_SubjectCol() throws Exception {
		TablePrinter table = prepare_addAssetLine_test();
		Assert.assertEquals(4,table.getMaxNumberOfCol());
		Assert.assertEquals(1,table.getNumberOfRow());
		Assert.assertEquals("S.id", ((SimpleText)table.getCell(0, 0).getContent()).getText());
		
	}
	
	@Test
	public void test_addAssetLines_with_asset_with_one_attribute_SubjectCol() throws Exception {
		TablePrinter table = prepare_addAssetLine_test_with_one_attribute();
		Assert.assertEquals(4,table.getMaxNumberOfCol());
		Assert.assertEquals(1,table.getNumberOfRow());
		Assert.assertEquals("S.id", ((SimpleText)table.getCell(0, 0).getContent()).getText());
		
	}
	
	@Test
	public void test_addAssetLines_with_asset_with_several_attributes_SubjectCol() throws Exception {
		TablePrinter table = prepare_addAssetLine_test_with_several_attributes();
		Assert.assertEquals(4,table.getMaxNumberOfCol());
		Assert.assertEquals(2,table.getNumberOfRow());
		Assert.assertEquals("S.id", ((SimpleText)table.getCell(0, 0).getContent()).getText());
		Assert.assertEquals("", ((SimpleText)table.getCell(1, 0).getContent()).getText());
		
	}
	
	@Test
	public void test_addAssetLines_with_asset_with_several_attributes_SecAttCol() throws Exception {
		TablePrinter table = prepare_addAssetLine_test_with_several_attributes();
		Assert.assertEquals(4,table.getMaxNumberOfCol());
		Assert.assertEquals(2,table.getNumberOfRow());
		Assert.assertEquals("AT.auth", ((SimpleText)table.getCell(0, 1).getContent()).getText());
		Assert.assertEquals("AT.attr2", ((SimpleText)table.getCell(1, 1).getContent()).getText());
		
	}
	
	@Test
	public void test_addAssetLines_with_asset_with_several_attributes_PossibleValue() throws Exception {
		TablePrinter table = prepare_addAssetLine_test_with_several_attributes();
		Assert.assertEquals(4,table.getMaxNumberOfCol());
		Assert.assertEquals(2,table.getNumberOfRow());
		Assert.assertEquals("yes, no", ((SimpleText)table.getCell(0, 2).getContent()).getText());
		Assert.assertEquals("null", ((SimpleText)table.getCell(1, 2).getContent()).getText());
		
	}
	
	@Test
	public void test_addAssetLines_with_asset_with_several_attributes_InitialValue() throws Exception {
		TablePrinter table = prepare_addAssetLine_test_with_several_attributes();
		Assert.assertEquals(4,table.getMaxNumberOfCol());
		Assert.assertEquals(2,table.getNumberOfRow());
		Assert.assertEquals("no", ((SimpleText)table.getCell(0, 3).getContent()).getText());
		Assert.assertEquals("null", ((SimpleText)table.getCell(1, 3).getContent()).getText());
		
	}
	
	@Test
	public void test_addAssetLines_with_asset_with_one_attribute_SecAttCol() throws Exception {
		TablePrinter table = prepare_addAssetLine_test_with_one_attribute();
		Assert.assertEquals(4,table.getMaxNumberOfCol());
		Assert.assertEquals(1,table.getNumberOfRow());
		Assert.assertEquals("AT.auth", ((SimpleText)table.getCell(0, 1).getContent()).getText());
		
	}
	
	@Test
	public void test_addAssetLines_with_asset_with_one_attribute_PossibleValue() throws Exception {
		TablePrinter table = prepare_addAssetLine_test_with_one_attribute();
		Assert.assertEquals(4,table.getMaxNumberOfCol());
		Assert.assertEquals(1,table.getNumberOfRow());
		Assert.assertEquals("yes, no", ((SimpleText)table.getCell(0, 2).getContent()).getText());
		
	}
	
	@Test
	public void test_addAssetLines_with_asset_with_one_attribute_InitialValue() throws Exception {
		TablePrinter table = prepare_addAssetLine_test_with_one_attribute();
		Assert.assertEquals(4,table.getMaxNumberOfCol());
		Assert.assertEquals(1,table.getNumberOfRow());
		Assert.assertEquals("no", ((SimpleText)table.getCell(0, 3).getContent()).getText());
		
	}
	
	@Test
	public void test_addAssetLines_with_asset_without_attributes_SecAttCol() throws Exception {
		TablePrinter table = prepare_addAssetLine_test();
		Assert.assertEquals(4,table.getMaxNumberOfCol());
		Assert.assertEquals(1,table.getNumberOfRow());
		Assert.assertEquals("n/A", ((SimpleText)table.getCell(0, 1).getContent()).getText());
		
	}
	
	@Test
	public void test_addAssetLines_with_asset_without_attributes_PossibleValue() throws Exception {
		TablePrinter table = prepare_addAssetLine_test();
		Assert.assertEquals(4,table.getMaxNumberOfCol());
		Assert.assertEquals(1,table.getNumberOfRow());
		Assert.assertEquals("n/A", ((SimpleText)table.getCell(0, 2).getContent()).getText());
		
	}
	
	@Test
	public void test_addAssetLines_with_asset_without_attributes_InitialValue() throws Exception {
		TablePrinter table = prepare_addAssetLine_test();
		Assert.assertEquals(4,table.getMaxNumberOfCol());
		Assert.assertEquals(1,table.getNumberOfRow());
		Assert.assertEquals("n/A", ((SimpleText)table.getCell(0, 3).getContent()).getText());
		
	}

	private TablePrinter prepare_addAssetLine_test() throws Exception {
		TablePrinter table = new TablePrinter();
		CCAsset asset = new CCAsset("id");
		SecurityTargetPrinter.addAssetLines("S", table, asset);
		return table;
	}
	
	private TablePrinter prepare_addAssetLine_test_with_one_attribute() throws Exception {
		TablePrinter table = new TablePrinter();
		CCAsset asset = new CCAsset("id");
		SecurityAttribute att = new SecurityAttribute();
		asset.addSecurityAttribute(att);
		att.setId("auth");
		att.getListOfPossibleValue().add("yes");
		att.getListOfPossibleValue().add("no");
		att.setInitialValue("no");
		SecurityTargetPrinter.addAssetLines("S", table, asset);
		return table;
	}
	
	private TablePrinter prepare_addAssetLine_test_with_several_attributes() throws Exception {
		TablePrinter table = new TablePrinter();
		CCAsset asset = new CCAsset("id");
		SecurityAttribute att = new SecurityAttribute();
		asset.addSecurityAttribute(att);
		att.setId("auth");
		att.getListOfPossibleValue().add("yes");
		att.getListOfPossibleValue().add("no");
		att.setInitialValue("no");
		SecurityAttribute att2 = new SecurityAttribute();
		asset.addSecurityAttribute(att2);
		att2.setId("attr2");
		att2.getListOfPossibleValue().add("null");
		att2.setInitialValue("null");
		SecurityTargetPrinter.addAssetLines("S", table, asset);
		
		return table;
	}

	
	@Test
	public void test_getAugmentationListItem_size() throws Exception {
		ListItem result = getListPrint();
		Assert.assertEquals(2, result.size());
		
	}
	
	@Test
	public void test_getAugmentationListItem_content() throws Exception {
		ListItem result = getListPrint();
		Assert.assertEquals("ALC_FLR.3", ((SimpleText)((BasicElementWithChildren) result.get(0)).get(0)).getText());
		Assert.assertEquals("AVA_VAN.3", ((SimpleText)((BasicElementWithChildren) result.get(1)).get(0)).getText());
		
	}

	private ListItem getListPrint() throws Exception {
		SecurityTarget st = new SecurityTarget();
		st.getAssuranceLevel().addAugmentation("ALC_FLR.3");
		st.getAssuranceLevel().addAugmentation("AVA_VAN.3");
		SecurityTargetPrinter stprinter = new SecurityTargetPrinter(st);
		ListItem result = stprinter.getAugmentationListItem();
		return result;
	}
	
	
	

}
