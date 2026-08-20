package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPageSecond {
    private final WebDriver driver;

    // Второе окно бронирования самоката

    // Дата аренды
    private final By orderDateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    // Срок аренды
    private final By orderTimeField = By.className("Dropdown-placeholder");

    // Список со сроками аренды
    private final By orderTime = By.xpath(".//*[(@role ='option' and text()='трое суток')]");

    // Цвет "черный жемчуг"
    private final By checkBoxBlackColour = By.xpath(".//input[@id='black']");

    // Цвет "серая безысходность"
    private final By checkBoxGreyColour = By.xpath(".//input[@id='grey']");

    // Поле комментария для курьера
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    // Кнопка "Заказать"
    private final By orderButton = By.xpath(".//button[(@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать')]");

    // Кнопка "Да" в окне подтверждения аренды
    private final By orderButtonYes = By.xpath(".//button[text()='Да']");

    // Локатор модального окна подтверждения аренды
    private final By modalOrderWindow = By.xpath(".//div[contains(@class, 'Order_ModalHeader__3FDaJ')]");

    // Модальное окно "Заказ оформлен"
    public boolean isModalOrderWindowDisplayed() {
        return driver.findElement(modalOrderWindow).isDisplayed();
    }

    // Передаём WebDriver
    public OrderPageSecond(WebDriver driver) {
        this.driver = driver;
    }

    // Выбор даты доставки в поле
    public OrderPageSecond setOrderDate(String date) {
        WebElement dateField = driver.findElement(orderDateField);
        dateField.sendKeys(date);
        dateField.sendKeys(Keys.ENTER);
        return this;
    }

    // Выбор срока аренды
    public OrderPageSecond setOrderTime() {
        driver.findElement(orderTimeField).click();
        driver.findElement(orderTime).click();
        return this;
    }

    // Выбор черного самоката
    public OrderPageSecond clickCheckBoxBlackColour() {
        driver.findElement(checkBoxBlackColour).click();
        return this;
    }

    // Выбор серого самоката
    public OrderPageSecond clickCheckBoxGreyColour() {
        driver.findElement(checkBoxGreyColour).click();
        return this;
    }

    // Ввод комментария
    public OrderPageSecond setComment(String userComment) {
        driver.findElement(commentField).sendKeys(userComment);
        return this;
    }

    // Нажать "Заказать"
    public OrderPageSecond clickOrderButton() {
        driver.findElement(orderButton).click();
        return this;
    }

    // Нажать "Да" при подтверждении бронирования
    public OrderPageSecond clickOrderButtonYes() {
        driver.findElement(orderButtonYes).click();
        return this;
    }
}