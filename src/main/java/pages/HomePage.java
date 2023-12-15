package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static utils.ScreenShotHelper.makeScreenShot;
import static utils.Waiters.*;


/**
 * Класс, в котором происходит взаимодействие с главной страницей Аптеки.
 */
public class HomePage {

    /**
     * Экземпляр драйвера для управления браузером.
     */
    private final WebDriver driver;


    /**
     * Элемент с полем поиска на главной странице.
     */
    @FindBy(xpath = "(//input[@class='search-input'])[1]")
    private WebElement searchField;


    /**
     * Конструктор создания главной страницы сайта.
     *
     * @param driver - драйвер для управления браузером.
     */
    public HomePage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    /**
     * Метод ввода текста в поле поиска.
     *
     * @param searchText - текст ввода.
     * @return - текущая страница.
     */
    @Step("Поиск по строке.")
    public HomePage inputStringInSearchField(String searchText) {
        waitUntilClickable(driver, searchField);
        searchField.sendKeys(searchText, Keys.ENTER);
        waitLoadingPage(driver);
        makeScreenShot(driver);
        return this;
    }

}
