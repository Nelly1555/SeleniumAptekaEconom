package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static utils.ScreenShotHelper.makeScreenShot;
import static utils.Waiters.*;
import static utils.dataHelpers.TestDataHelper.*;


/**
 * Класс, в котором происходит взаимодействие со страницей, содержащей корзину.
 */
public class BasketPage {

    /**
     * Экземпляр драйвера для управления браузером.
     */
    private final WebDriver driver;


    /**
     * Элемент с количеством добавленного товара в корзине.
     */
    @FindBy(xpath = "//input[@class='basket-item-amount-filed']")
    private WebElement amountOfProductsInBasket;

    /**
     * Элемент с ценой добавленного товара в корзине.
     */
    @FindBy(xpath = "(//span[@class='basket-item-price-current-text'])[2]")
    private WebElement priceOfProductInBasket;

    /**
     * Элемент с суммой всех товаров в корзине.
     */
    @FindBy(xpath = "//div[@class='basket-checkout-block-total-price-inner']")
    private WebElement totalPriceInBasket;

    /**
     * Элемент добавления отложенного товара к заказу.
     */
    @FindBy(xpath = "//a[@data-entity='basket-item-remove-delayed']")
    private WebElement addToOrderButton;

    /**
     * Элемент с информацией об отложенных товарах с иконки.
     */
    @FindBy(xpath = "//a[contains(@class, 'basket-link delay with_price')]")
    private WebElement postponedProductsIcon ;

    /**
     * Элемент с информацией о сумме товаров в корзине с иконки.
     */
    @FindBy(xpath = "//a[contains(@class, 'basket-link basket has_prices')]")
    private WebElement basketIcon;

    /**
     * Элемент удаления всех товаров из корзины.
     */
    @FindBy(xpath = "//span[contains(@class,'delete_all')]")
    private WebElement clearButton;

    /**
     * Элемент перехода на главную страницу сайта.
     */
    @FindBy(xpath = "//div[@class='logo-block col-md-2 col-sm-3']")
    private WebElement homeIcon;


    /**
     * Конструктор создания страницы с корзиной.
     *
     * @param driver - драйвер для управления браузером.
     */
    public BasketPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Метод для добавления отложенного товара к заказу.
     *
     * @return - текущая страница.
     */
    @Step("Добавление отложенного товара в корзину.")
    public BasketPage addProductToBasket() {
        int attempts = 0;
        while (attempts < 5) {
            try {
                waitUntilClickable(driver, addToOrderButton);
                makeScreenShot(driver);
                return this;
            } catch (StaleElementReferenceException exception) {
            }
            attempts++;
        }
        throw new RuntimeException("Не удалось выполнить клик");
    }

    /**
     * Метод для получения количества товара в корзине.
     *
     * @return - количество товара.
     */
    @Step("Получение количества товара в корзине")
    public String getAmountOfProduct() {
        String textOfAmountProduct = amountOfProductsInBasket.getAttribute(VALUE);
        return textOfAmountProduct;
    }

    /**
     * Метод для получения цены товара в корзине.
     *
     * @return - цена товара в корзине.
     */
    @Step("Получение цены товара в корзине.")
    public String getPriceOfProduct() {
        String textOfPriceProduct = priceOfProductInBasket.getText();
        return textOfPriceProduct;
    }

    /**
     * Метод для получения цены всех товаров в корзине.
     *
     * @return - цена всех товаров в корзине.
     */
    @Step("Получение цены всех товаров в корзине.")
    public String getTotalPriceOfBasket() {
        String textOfTotalPriceBasket = totalPriceInBasket.getText();
        return textOfTotalPriceBasket;
    }

    /**
     * Метод для ожидания изменения данных в корзине после добавления товара.
     *
     * @return - текущая страница.
     */
    @Step("Ожидание изменения данных в корзине после добавления товара.")
    public BasketPage waitBasketIconAttribute() {
        waitUntilAttributeChange(driver, basketIcon, TITLE, PRODUCTS_IN_BASKET_TEXT);
        return this;
    }

    /**
     * Метод для получения текста с иконки отложенных товаров.
     *
     * @return - текст с иконки отложенных товаров.
     */
    @Step("Получение текста с иконки отложенных товаров.")
    public String getPostponedProductsIconText() {
        String textOfPostponedProductsIcon = postponedProductsIcon.getAttribute(TITLE);
        return textOfPostponedProductsIcon;
    }

    /**
     * Метод для получения текста с иконки корзины.
     *
     * @return - текст с иконки корзины.
     */
    @Step("Получение текста с иконки корзины.")
    public String getBasketIconText() {
        String textOfBasketProductsIcon = basketIcon.getAttribute(TITLE);
        return textOfBasketProductsIcon;
    }

    /**
     * Метод для удаления всех товаров из корзины.
     *
     * @return - текущая страница.
     */
    @Step("Удаление всех товаров из корзины.")
    public BasketPage clearBasket() {
        waitUntilVisible(driver, homeIcon);
        clearButton.click();
        waitLoadingPage(driver);
        makeScreenShot(driver);
        return this;
    }

    /**
     * Метод для перехода на главную страницу сайта.
     *
     * @return - текущая страница.
     */
    @Step("Переход на главную страницу сайта.")
    public BasketPage goHomePage() {
        int attempts = 0;
        while (attempts < 5) {
            try {
                waitUntilClickable(driver, homeIcon);
                return this;
            } catch (StaleElementReferenceException exception) {
            }
            attempts++;
        }
        throw new RuntimeException("Не удалось выполнить клик");
    }

}
