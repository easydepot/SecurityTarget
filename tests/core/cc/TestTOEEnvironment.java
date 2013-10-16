package core.cc;

import junit.framework.Assert;

import org.junit.Test;

import core.asset.CCAsset;


public class TestTOEEnvironment {

	
	@Test
	public void test_getRole() {
		TOEEnvironment t = new TOEEnvironment();
		Assert.assertNotNull(t.getRoles());
	}
	
	@Test
	public void test_addRole() {
		TOEEnvironment t = new TOEEnvironment();
		CCAsset role = new CCAsset("ID");
		t.addRole(role);
		Assert.assertNotNull(t.getRoles());
		Assert.assertEquals(role, t.getRoles().get(0));
	}
	
	@Test
	public void test_addDirectExternalEntity() {
		TOEEnvironment t = new TOEEnvironment();
		CCAsset entity = new CCAsset("ID");
		t.addDirectExternalEntitiese(entity);
		Assert.assertNotNull(t.getDirectExternalEntities());
		Assert.assertEquals(entity, t.getDirectExternalEntities().get(0));
	}
	

	@Test
	public void test_addInDirectExternalEntity() {
		TOEEnvironment t = new TOEEnvironment();
		CCAsset entity = new CCAsset("ID");
		t.addIndirectExternalEntity(entity);
		Assert.assertNotNull(t.getIndirectExternalEntities());
		Assert.assertEquals(entity, t.getIndirectExternalEntities().get(0));
	}
	
	
	
	
	@Test
	public void test_getDirectExternalEntities() {
		TOEEnvironment t = new TOEEnvironment();
		Assert.assertNotNull(t.getDirectExternalEntities());
	}
	
	@Test
	public void test_getIndirectExternalEntities() {
		TOEEnvironment t = new TOEEnvironment();
		Assert.assertNotNull(t.getIndirectExternalEntities());
	}
	
	
}
