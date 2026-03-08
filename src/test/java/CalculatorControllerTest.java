import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CalculatorControllerTest {

    private final CalculatorController controller = new CalculatorController();

    @Test
    void testAdd() {
        assertEquals(10.0, controller.sum(5, 5));
    }

    @Test
    void testSubtract() {
        assertEquals(0.0, controller.sub(5, 5));
    }

    @Test
    void testProduct() {
        assertEquals(25.0, controller.product(5, 5));
    }
}