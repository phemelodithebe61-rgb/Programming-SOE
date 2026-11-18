package za.ac.mattress;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ProductCalculationsTest {
    @Test void GetVat_WithValidData_ReturnsCorrectValue() {
        assertEquals(525.0, new ProductCalculations(3500).GetVat(), 0.000001);
    }
    @Test void GetTotal_WithValidData_ReturnsCorrectValue() {
        assertEquals(4200.0, new ProductCalculations(3500).GetTotal(), 0.000001);
    }
    @Test void GetCommission_WithValidData_ReturnsCorrectValue() {
        assertEquals(175.0, new ProductCalculations(3500).GetCommission(), 0.000001);
    }
    @Test void InvalidPrice_IsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new ProductCalculations(-1));
    }
}
