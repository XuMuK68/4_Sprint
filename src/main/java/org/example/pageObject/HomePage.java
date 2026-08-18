package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class HomePage {

    private final WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }


    //Кнопка принятия Cookie с текстом "да все привыкли"
    private static final By buttonConfirmCookie = By.id("rcc-confirm-button");

    //Кнопка "Заказать" наверху сайта
    private static final By orderUpButton = By.className("Button_Button__ra12g");

    //Кнопка "Заказать" внизу сайта
    private static final By orderDownButton = By.className("Button_Middle__1CSJM");

    //Вопросы о важном: кнопки 1-8
    private static final By[] QUESTIONS = {
            By.id("accordion__heading-0"),
            By.id("accordion__heading-1"),
            By.id("accordion__heading-2"),
            By.id("accordion__heading-3"),
            By.id("accordion__heading-4"),
            By.id("accordion__heading-5"),
            By.id("accordion__heading-6"),
            By.id("accordion__heading-7")
    };

    //Ответы на вопросы о важном: поля 1-8
    private static final By[] ANSWERS = {
            By.id("accordion__panel-0"),
            By.id("accordion__panel-1"),
            By.id("accordion__panel-2"),
            By.id("accordion__panel-3"),
            By.id("accordion__panel-4"),
            By.id("accordion__panel-5"),
            By.id("accordion__panel-6"),
            By.id("accordion__panel-7")
    };

    //открыть сайт бронирования самокатов
    public HomePage openSite() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        return this;
    }

    //Принять куки
    public HomePage clickButtonCookie() {
        driver.findElement(buttonConfirmCookie).click();
        return this;
    }

    //Нажать кнопку "Заказать" вверху сайта
    public HomePage clickOrderUpButton() {
        driver.findElement(orderUpButton).click();
        return this;
    }

    //Нажать кнопку "Заказать" внизу сайта
    public HomePage clickOrderDownButton() {
        driver.findElement(orderDownButton).click();
        return this;
    }

    //прокрутка до последнего вопроса
    public HomePage scrollToLastQuestion() {
        WebElement lastQuestion = driver.findElement(QUESTIONS[QUESTIONS.length - 1]);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", lastQuestion);
        return this;
    }

    //Нажатие на вопрос их списка
    public void clickQuestionArrow(int questionNumber) {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(QUESTIONS[questionNumber])).click();
    }

    // Вывод текста при раскрытие вопроса
    public String getAnswerText(int answerNumber) {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOfElementLocated(ANSWERS[answerNumber]));

        return driver.findElement(ANSWERS[answerNumber]).getText();
    }
}