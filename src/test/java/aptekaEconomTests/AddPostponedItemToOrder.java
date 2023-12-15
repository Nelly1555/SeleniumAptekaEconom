package aptekaEconomTests;

import base.BaseTest;
import config.AptekaTestsConfig;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.Test;
import pages.BasketPage;
import pages.CatalogPage;
import pages.HomePage;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static utils.dataHelpers.TestDataHelper.*;

/**
 * Класс с тестом, проверяющим, что можно добавить отложенный товар к заказу.
 */
@Epic("AptekaEconom")
@Feature("Добавление отложенного товара к заказу.")
public class AddPostponedItemToOrder extends BaseTest {

    /**
     * Экземпляр конфигурации с параметрами для тестов.
     */
    private final AptekaTestsConfig config = ConfigFactory.create(AptekaTestsConfig.class, System.getenv());

    @Test(description = "Проверка добавления отложенного товара к заказу.")
    @Story("Добавление отложенного товара к заказу. Позитивный сценарий.")
    public void addPostponedItemToOrderPositiveTest() {

        new HomePage(driver).inputStringInSearchField(config.inputSearch());

        CatalogPage catalogPage = new CatalogPage(driver)
                .searchByAvailability()
                .postponeFirstProduct();

        String priceOfFirstProduct = catalogPage.getPriceOfFirstProduct() + RUB;

        catalogPage.clickToBasketButton();

        BasketPage basketPage = new BasketPage (driver)
                .addProductToBasket()
                .waitBasketIconAttribute();

        String textOfAmountProduct = basketPage.getAmountOfProduct();
        String textOfPriceProduct = basketPage.getPriceOfProduct();
        String textOfTotalPriceBasket = basketPage.getTotalPriceOfBasket();
        String textOfPostponedProductsIcon = basketPage.getPostponedProductsIconText();
        String textOfBasketProductsIcon = basketPage.getBasketIconText();

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(textOfAmountProduct)
                            .withFailMessage("Количество товаров в корзине указано неверно.")
                            .isEqualTo(AMOUNT_OF_PRODUCTS_IN_BASKET);
                    softAssertions
                            .assertThat(textOfPriceProduct)
                            .withFailMessage("Цена товара указана неверно.")
                            .isEqualTo(priceOfFirstProduct);
                    softAssertions
                            .assertThat(textOfTotalPriceBasket)
                            .withFailMessage("Цена всех товаров в корзине указана неверно.")
                            .isEqualTo(priceOfFirstProduct);
                    softAssertions
                            .assertThat(textOfPostponedProductsIcon)
                            .withFailMessage("Текст и исконки отложенных товаров указан неверно.")
                            .isEqualTo(POSTPONED_PRODUCTS_ICON_TEXT);
                    softAssertions
                            .assertThat(textOfBasketProductsIcon)
                            .withFailMessage("Текст с иконки корзины указан неверно.")
                            .isEqualTo(PRODUCTS_IN_BASKET_TEXT);
                }
        );
        basketPage.goHomePage();
    }
}
