package aptekaEconomTests;

import base.BaseTest;
import config.AptekaTestsConfig;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.Test;
import pages.CatalogPage;
import pages.HomePage;
import pages.PostponedProductsPage;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static utils.dataHelpers.TestDataHelper.PRICE_OF_PRODUCTS_IN_BASKET;

/**
 * Класс с тестом, проверяющим, что товар можно отложить.
 */
@Epic("AptekaEconom")
@Feature("Добавление товара в список отложенных товаров. ")
public class AddItemToWishlist extends BaseTest {

    /**
     * Экземпляр конфигурации с параметрами для тестов.
     */
    private final AptekaTestsConfig config = ConfigFactory.create(AptekaTestsConfig.class, System.getenv());

    @Test(description = "Проверка добавления товара в список отложенных товаров.")
    @Story("Добавление товара в список отложенных товаров. Позитивный сценарий.")
    public void addItemToWishlistPositiveTest() {

        new HomePage(driver).inputStringInSearchField(config.inputSearch());

        new CatalogPage(driver)
                .searchByAvailability()
                .postponeFirstProduct()
                .getPostponedProducts();

        PostponedProductsPage postponedProductsPage = new PostponedProductsPage(driver);

        String textOfPriceOfPostponedProducts = postponedProductsPage.getTextOfPriceOfPostponedProducts();
        String textOfPostponedProductsIcon = postponedProductsPage.getTextOfPostponedProductsIcon();
        String textOfPriceProductsInBasket = postponedProductsPage.getTextOfPriceProductsInBasket();

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(textOfPriceOfPostponedProducts)
                            .withFailMessage("Цена отложенных товаров не соответствует цене с иконки.")
                            .isEqualTo(textOfPostponedProductsIcon);
                    softAssertions
                            .assertThat(PRICE_OF_PRODUCTS_IN_BASKET)
                            .withFailMessage("Цена товаров в корзине указана неверно.")
                            .isEqualTo(textOfPriceProductsInBasket);
                }
        );
    }
}

