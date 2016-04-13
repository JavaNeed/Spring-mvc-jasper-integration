package org.krams.tutorial.dao;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.krams.tutorial.dto.Sales;

public class SalesDAO {

	/**
	 * Our dummy datasource. Retrieves a collection of data that is wrapped by a JRBeanCollectionDataSource.
	 *  
	 * @return a JRBeanCollectionDataSource collection
	 */
	public  JRDataSource getDataSource() {
		// Create an array list of Sales 
		List<Sales> items = new ArrayList<Sales>();
		
		// For this demo, we'll create an in-memory list of items
		// Create first item
		Sales item1 = new Sales();
		item1.setId(1001L);
		item1.setName("Pencil");
		item1.setDescription("This is used for sketching drawings");
		item1.setPrice(10.50);
		
		// Create second item
		Sales item2 = new Sales();
		item2.setId(1002L);
		item2.setName("Pen");
		item2.setDescription("This is used for signing autographs");
		item2.setPrice(15.00);
		
		// Create third item
		Sales item3 = new Sales();
		item3.setId(1003L);
		item3.setName("Bag");
		item3.setDescription("This is used for storing other items");
		item3.setPrice(50.00);
		
		Sales item4 = new Sales();
		item4.setId(1003L);
		item4.setName("Mobile");
		item4.setDescription("This is used for Talking Remotely");
		item4.setPrice(150.00);
		
		Sales item5 = new Sales();
		item5.setId(1003L);
		item5.setName("Laptop");
		item5.setDescription("This is used for Computer Work");
		item5.setPrice(150.00);
		
		// Add to list
		items.add(item1);
		items.add(item2);
		items.add(item3);
		items.add(item4);
		items.add(item5);
		
		// Wrap the collection in a JRBeanCollectionDataSource
		// This is one of the collections that Jasper understands
		JRDataSource ds = new JRBeanCollectionDataSource(items);	
		
		// Return the wrapped collection
		return ds;
	}
}
