import org.example.pageObject.HomePage;
import org.example.pageObject.OrderPageFirst;
import org.example.pageObject.OrderPageSecond;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderSamokatTest extends BaseTest {

    // Задаём поля
    private final String orderButton;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String phone;
    private final String metro;
    private final String date;
    private final boolean blackColour;
    private final String comment;

    // Вызываем конструктор
    public OrderSamokatTest(String orderButton,
                            String firstName,
                            String lastName,
                            String address,
                            String phone,
                            String metro,
                            String date,
                            boolean blackColour,
                            String comment) {

        this.orderButton = orderButton;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.metro = metro;
        this.date = date;
        this.blackColour = blackColour;
        this.comment = comment;
    }

     @Parameterized.Parameters
    public static Object[][] getData() {
        return OrderSamokatTestData.getOrderData();
    }

    // Делаем по 2 заказа с каждой кнопки с 2-мя разными данными
    @Test
    public void orderingSamokatTest() {

        HomePage homePage = new HomePage(driver);
        homePage.openSite()
                .clickButtonCookie();

        if (orderButton.equals("UP")) {
            homePage.clickOrderUpButton();
        } else {
            homePage.clickOrderDownButton();
        }

        // Заполняем первую страницу заказа
        new OrderPageFirst(driver)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setAddress(address)
                .setPhoneNumber(phone)
                .selectMetroStation(metro)
                .clickNextButton();

        OrderPageSecond orderPageSecond = new OrderPageSecond(driver);

        // Заполняем вторую страницу заказа
        orderPageSecond
                .setOrderDate(date)
                .setOrderTime();

        if (blackColour) {
            orderPageSecond.clickCheckBoxBlackColour();
        } else {
            orderPageSecond.clickCheckBoxGreyColour();
        }

        boolean isDisplayed = orderPageSecond
                .setComment(comment)
                .clickOrderButton()
                .clickOrderButtonYes()
                .isModalOrderWindowDisplayed();

        assertTrue("Окно 'Заказ оформлен' не отображается", isDisplayed);
    }
}