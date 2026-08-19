package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// Первое окно бронирования самоката
public class OrderPageFirst {
    private final WebDriver driver;

    // Поле "Имя"
    private final By FirstName = By.xpath(".//input[@placeholder='* Имя']");

    // Поле "Фамилия"
    private final By LastName = By.xpath(".//input[@placeholder='* Фамилия']");

    // Поле "Адрес"
    private final By Address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    // Поле "Станция метро"
    private final By deliveryMetroStation = By.xpath(".//input[@placeholder='* Станция метро']");

    // Поле "Телефон"
    private final By deliveryClientPhoneNumber = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Кнопка "Далее"
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    // Передаём WebDriver
    public OrderPageFirst(WebDriver driver) {
        this.driver = driver;
    }

    // водим имя
    public OrderPageFirst setFirstName(String firstName) {
        driver.findElement(FirstName).sendKeys(firstName);
        return this;
    }
    // Вводим фамилию
    public OrderPageFirst setLastName(String lastName) {
        driver.findElement(LastName).sendKeys(lastName);
        return this;
    }

    // Вводим адрес доставки
    public OrderPageFirst setAddress(String address) {
        driver.findElement(Address).sendKeys(address);
        return this;
    }

    // Выбираем станцию метро
    public OrderPageFirst selectMetroStation(String metroStationFromOrder) {
        WebElement metroField = driver.findElement(deliveryMetroStation);

        metroField.click();
        metroField.sendKeys(metroStationFromOrder);
        metroField.sendKeys(Keys.DOWN, Keys.ENTER);
        return this;
    }

    // Вводим номер телефона
    public OrderPageFirst setPhoneNumber(String phoneNumber) {
        driver.findElement(deliveryClientPhoneNumber).sendKeys(phoneNumber);
        return this;
    }

    // Жмём кнопку "Далее"
    public OrderPageFirst clickNextButton() {
        driver.findElement(nextButton).click();
        return this;
    }
}