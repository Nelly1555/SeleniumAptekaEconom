package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static utils.dataHelpers.TestDataHelper.POSTPONED_PRODUCTS_TEXT;
import static utils.dataHelpers.TestDataHelper.TITLE;

/**
 * Класс, в котором происходит взаимодействие со страницей со списком отложенных товаров.
 */
public class PostponedProductsPage {

    /**
     * Экземпляр драйвера для управления браузером.
     */
    private final WebDriver driver;


    /**
     * Элемент со списком всех отложенных товаров.
     */
    @FindBy(xpath = "//span[contains(@id, 'basket-item-sum-price')]")
    private WebElement allPostponedProducts;

    /**
     * Элемент с ценой всех отложенных товаров с иконки.
     */
    @FindBy(xpath = "//a[contains(@class, 'basket-link delay with_price')]")
    private WebElement priceOfPostponedProductsIcon;

    /**
     * Элемент с ценой всех товаров в корзине.
     */
    @FindBy(xpath = "//div[@class='basket-coupon-block-total-price-current']")
    private WebElement priceOfProductsInBasket;

    /**
     * Элемент перехода в корзину.
     */
    @FindBy(xpath = "//a[contains(@class, 'basket-link basket has_prices')]")
    private WebElement basketButton;


    /**
     * Конструктор создания страницы со списком отложенных товаров.
     *
     * @param driver - драйвер для управления браузером.
     */
    public PostponedProductsPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    /**
     * Метод для получения цены отложенного товара.
     *
     * @return - цена отложенного товара.
     */
    @Step("Получение цены отложенного товара.")
    public String getTextOfPriceOfPostponedProducts() {
        String textOfPriceOfPostponedProducts = POSTPONED_PRODUCTS_TEXT + allPostponedProducts.getText();
        return textOfPriceOfPostponedProducts;
    }

    /**
     * Метод для получения цены всех отложенных товаров с иконки.
     *
     * @return - цена всех отложенных товаров с иконки.
     */
    @Step("Получение цены всех отложенных товаров с иконки.")
    public String getTextOfPostponedProductsIcon() {
        String textOfPostponedProductsIcon = priceOfPostponedProductsIcon.getAttribute(TITLE);
        return textOfPostponedProductsIcon;
    }

    /**
     * Метод для получения цены всех товаров в корзине.
     *
     * @return - цена всех товаров в корзине.
     */
    @Step("Получения цены всех товаров в корзине.")
    public String getTextOfPriceProductsInBasket() {
        String textOfPriceProductsInBasket = priceOfProductsInBasket.getText();
        return textOfPriceProductsInBasket;
    }

}
