import org.example.pageObject.HomePage;
import org.example.pageObject.OrderPageFirst;
import org.example.pageObject.OrderPageSecond;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class OrderSamokatTest extends BaseTest {

    // Заказ через верхнюю кнопку "Заказать"
    @Test
    public void OrderingSamokatUpOrderButton() {
        new HomePage(driver)
                .openSite()
                .clickButtonCookie()
                .clickOrderUpButton();

        new OrderPageFirst(driver)
                .setFirstName("Александр")
                .setLastName("Гаврилов")
                .setAddress("Москва, ул. Ярцевская, 27А")
                .setPhoneNumber("88005553535")
                .selectMetroStation("Молодежная")
                .clickNextButton();

        boolean isDisplayed = new OrderPageSecond(driver)
                .setOrderDate("25.08.2026")
                .setOrderTime()
                .clickCheckBoxBlackColour()
                .setComment("Привезите с полным зарядом")
                .clickOrderButton()
                .clickOrderButtonYes()
                .isModalOrderWindowDisplayed();
        assertTrue("Окно 'Заказ оформлен' не отображается", isDisplayed);
    }
    // Заказ через нижнюю кнопку "Заказать"
    @Test
    public void OrderingSamokatDownOrderButton() {
        new HomePage(driver)
                .openSite()
                .clickButtonCookie()
                .clickOrderDownButton();

        new OrderPageFirst(driver)
                .setFirstName("Май")
                .setLastName("Абрикосов")
                .setAddress("Москва, Проспект Мира, 119")
                .setPhoneNumber("88004567889")
                .selectMetroStation("ВДНХ")
                .clickNextButton();

        boolean isDisplayed = new OrderPageSecond(driver)
                .setOrderDate("11.09.2026")
                .setOrderTime()
                .clickCheckBoxGreyColour()
                .setComment("Купите по пути бигтейсти")
                .clickOrderButton()
                .clickOrderButtonYes()
                .isModalOrderWindowDisplayed();
        assertTrue("Окно 'Заказ оформлен' не отображается", isDisplayed);
    }
}