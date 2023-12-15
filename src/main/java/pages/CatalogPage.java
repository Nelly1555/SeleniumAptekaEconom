package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static utils.ScreenShotHelper.makeScreenShot;
import static utils.Waiters.*;
import static utils.dataHelpers.TestDataHelper.*;

/**
 * Класс, в котором происходит взаимодействие с каталогом.
 */
public class CatalogPage {

    /**
     * Экземпляр драйвера для управления браузером.
     */
    private final WebDriver driver;

    /**
     * Экземпляр Actions для интерактивной работы с веб-страницей.
     */
    private Actions actions;


    /**
     * Элемент, сортирующий товары по наличию.
     */
    @FindBy(xpath = "//span[.='По наличию']")
    private WebElement searchByAvailabilityButton;

    /**
     * Элемент, добавляющий товар в список отложенных.
     */
    @FindBy(xpath = "(//div[@class='wish_item_button'])[1]")
    private WebElement firstProductPostponeButton;

    /**
     * Элемент со списком отложенных товаров.
     */
    @FindBy(xpath = "//a[contains(@class, 'basket-link delay with_price big')]")
    private WebElement postponedProductsButton;

    /**
     * Элемент для перехода в корзину.
     */
    @FindBy(xpath = "//a[contains(@class, 'basket-link basket has_prices')]")
    private WebElement basketButton;

    /**
     * Элемент с ценой первого товара из каталога.
     */
    @FindBy(xpath = "(//div[contains(@class,'item_block col-4')]//span[@class='price_value'])[1]")
    private WebElement priceOfFirstProduct;

    /**
     * Элемент, который становится невидимым после клика по кнопке
     * (добавляющей товар в список отложенных).
     */
    @FindBy(xpath = "(//span[@style='display: none;'])[1]")
    private WebElement postponeButtonState;

    /**
     * Элемент со вторым товаром из каталога.
     */
    @FindBy(xpath = "(//div[contains(@class,'item_block col-4')])[2]")
    private WebElement secondProduct;

    /**
     * Элемент, добавляющий товар в корзину.
     */
    @FindBy(xpath = "(//span[contains(@class, 'small to-cart')])[2]")
    private WebElement inBasketButton;

    /**
     * Элемент с текстом кнопки, добавляющей товар в корзину.
     */
    @FindBy(xpath = "(//a[contains(@class, 'small in-cart')])[2]")
    private WebElement styleOfInBasketButton;

    /**
     * Элемент для перехода на главную страницу сайта.
     */
    @FindBy(xpath = "//div[@class='logo-block col-md-2 col-sm-3']")
    private WebElement homeIcon;


    /**
     * Конструктор создания страницы с каталогом сайта.
     *
     * @param driver - драйвер для управления браузером.
     */
    public CatalogPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    /**
     * Метод для сортировки товара по наличию.
     *
     * @return - текущая страница.
     */
    @Step("Сортировка товара по наличию.")
    public CatalogPage searchByAvailability() {
        searchByAvailabilityButton.click();
        waitSomeSeconds(driver);
        makeScreenShot(driver);
        return this;
    }

    /**
     * Метод для добавления товара в список отложенных.
     *
     * @return - текущая страница.
     */
    @Step("Добавление первого товара в список отложенных.")
    public CatalogPage postponeFirstProduct(){
        firstProductPostponeButton.click();
        makeScreenShot(driver);
        return this;
    }

    /**
     * Метод для получения цены товара.
     *
     * @return - текущая страница.
     */
    @Step("Получение цены отложенного товара.")
    public String getPriceOfFirstProduct() {
        String textOfPriceOfFirstProduct = priceOfFirstProduct.getText();
        return textOfPriceOfFirstProduct;
    }

    /**
     * Метод для просмотра списка добавленных товаров.
     *
     * @return - текущая страница.
     */
    @Step("Просмотр списка отложенных товаров.")
    public CatalogPage getPostponedProducts() {
        postponedProductsButton.click();
        waitLoadingPage(driver);
        return this;
    }

    /**
     * Метод для перехода в корзину.
     *
     * @return - текущая страница.
     */
    @Step("Переход в корзину.")
    public CatalogPage clickToBasketButton() {
        basketButton.click();
        waitLoadingPage(driver);
        makeScreenShot(driver);
        return this;
    }

    /**
     * Метод для получения значения аттрибута элемента
     * для добавления товара в отложенные.
     *
     * @return - значение аттрибута.
     */
    @Step("Получение значения аттрибута элемента для добавления товара в отложенные.")
    public String getStyleAttributeOfPostponeButton() {
        String styleAttributeOfPostponeButton = postponeButtonState.getAttribute(TITLE);
        return styleAttributeOfPostponeButton;
    }

    /**
     * Метод для добавления второго товара в корзину.
     *
     * @return - текущая страница.
     */
    @Step("Добавление второго товара к заказу.")
    public CatalogPage addSecondProductInBasket() {
        actions = new Actions(driver);
        actions.moveToElement(secondProduct);
        actions.perform();
        WebElement button = waitUntilVisible(driver, inBasketButton);
        button.click();
        waitLoadingPage(driver);
        makeScreenShot(driver);
        return this;
    }

    /**
     * Метод для ожидания изменения значения аттрибута
     * кнопки добавления в корзину после нажатия.
     *
     * @return - текущая страница.
     */
    @Step("Ожидание изменения значения аттрибута кнопки добавления в корзину после нажатия.")
    public CatalogPage waitAttributeChangeAfterClick() {
        waitUntilAttributeChange(driver, inBasketButton, STYLE , DISPLAY_NONE);
        return this;
    }

    /**
     * Метод для ожидания изменения значения аттрибута
     * кнопки добавления в корзину.
     *
     * @return - текущая страница.
     */
    @Step("Ожидание изменения значения аттрибута кнопки добавления в корзину.")
    public CatalogPage waitAttributeChange() {
        waitUntilAttributeChange(driver, styleOfInBasketButton, STYLE , DISPLAY_NONE_WITHOUT_SPACE);
        return this;
    }

    /**
     * Метод для получения значения аттрибута элемента
     * для добавления товара в корзину.
     *
     * @return - значение аттрибута.
     */
    @Step("Получение значения аттрибута кнопки добавления в корзину после нажатия.")
    public String getStyleAttributeOfBasketButton() {
        String textOfBasketButtonAfterClick = inBasketButton.getAttribute(STYLE);
        return textOfBasketButtonAfterClick;
    }

    /**
     * Метод для получения значения аттрибута элемента
     * для добавления товара в корзину.
     *
     * @return - значение аттрибута.
     */
    @Step("Получение значения аттрибута элемента для добавления товара в корзину.")
    public String getTextOfInBasketButton() {
       String inBasketButtonState = styleOfInBasketButton.getAttribute(STYLE);
       return inBasketButtonState;
    }

    /**
     * Метод для перехода на главную страницу сайта.
     *
     * @return - текущая страница.
     */
    @Step("Переход на главную страницу сайта.")
    public CatalogPage goHomePage() {
        homeIcon.click();
        waitLoadingPage(driver);
        return this;
    }

}
