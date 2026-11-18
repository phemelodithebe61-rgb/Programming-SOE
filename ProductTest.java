package com.mattress;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private Product sample() {
        return new Product(SalesReportApp.PRODUCTS, new int[][]{{25,18,20},{15,10,12},{18,15,20}});
    }
    @Test void GetTotalProductSales_WithValidData_ReturnsCorrectValue() {
        assertEquals(153, sample().GetTotalProductsSold());
    }
    @Test void GetAverageSales_WithValidData_ReturnsCorrectValue() {
        assertEquals(17.0, sample().GetAverageProductSold(), 0.00001);
    }
    @Test void GetMostPopularProduct_WithValidData_ReturnsCorrectValue() {
        assertEquals("Cozy Nights Monarch(25)", sample().GetMostPopularProduct());
    }
    @Test void rejectsNegativeSales() {
        assertThrows(IllegalArgumentException.class, () -> new Product(new String[]{"A"}, new int[][]{{1,-2,3}}));
    }
}
