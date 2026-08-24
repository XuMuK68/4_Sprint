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

     // Делаем по 1 заказу с каждой кнопки с разными данными
    @Parameterized.Parameters(name = "Кнопка: {0}, Пользователь: {1} {2}")
    public static Object[][] getOrderData() {
        return new Object[][]{
                {
                        "UP",
                        "Александр",
                        "Гаврилов",
                        "Москва, ул. Ярцевская, 27А",
                        "88005553535",
                        "Молодежная",
                        "25.08.2026",
                        true,
                        "Привезите с полным зарядом"
                },
                {
                        "DOWN",
                        "Май",
                        "Абрикосов",
                        "Москва, Проспект Мира, 119",
                        "88004567889",
                        "ВДНХ",
                        "11.09.2026",
                        false,
                        "Купите по пути бигтейсти"
                }
        };
    }

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