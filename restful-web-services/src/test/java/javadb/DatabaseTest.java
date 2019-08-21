package javadb;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javadb.Database;

class DatabaseTest {

	static Database db;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		db = new Database();
	}

	@Test
	void testCheckId() {
		assertTrue(db.checkId("idFromDatabase"));
	}

	@Test
	void testCheckPassword() {
		assertTrue(db.checkPassword("idFromDatabase", "pass"));
	}

//	@Test
//	void testCheck_for_sale() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSearch_by_category() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSearch_by_category_descpt() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSale_history() {
//		fail("Not yet implemented");
//	}

	@Test
	void testSell_item() {
		fail("Not yet implemented");
	}

	@Test
	void testEdit_price() {
		fail("Not yet implemented");
	}

	@Test
	void testEdit_item_name() {
		fail("Not yet implemented");
	}

	@Test
	void testEdit_description() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete_item() {
		fail("Not yet implemented");
	}

	@Test
	void testSold_item() {
		fail("Not yet implemented");
	}

}
