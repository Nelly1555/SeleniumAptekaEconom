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
@Feature("Удаление из корзины отложенных и активных товаров и их доступность для нового заказа.")
public class DeleteItemsFromBasketAndWishlist extends BaseTest {

    /**
     * Экземпляр конфигурации с параметрами для тестов.
     */
    private final AptekaTestsConfig config = ConfigFactory.create(AptekaTestsConfig.class, System.getenv());

    @Test(description = "Проверка удаления из корзины отложенных и активных товаров и их доступности для нового заказа.")
    @Story("Удаление из корзины отложенных и активных товаров и их доступность для нового заказа. Позитивный сценарий.")
    public void deleteItemsFromBasketAndWishlistPositiveTest() {

        HomePage homePage = new HomePage(driver)
                .inputStringInSearchField(config.inputSearch());

        CatalogPage catalogPage = new CatalogPage(driver)
                .searchByAvailability()
                .postponeFirstProduct()
                .addSecondProductInBasket()
                .waitAttributeChangeAfterClick();

        String styleOfPostponeButtonAfterAdd = catalogPage.getStyleAttributeOfPostponeButton();
        String styleOfBasketButtonAfterAdd = catalogPage.getStyleAttributeOfBasketButton();

        catalogPage.clickToBasketButton();

        new BasketPage(driver)
                .clearBasket()
                .goHomePage();

        homePage.inputStringInSearchField(config.inputSearch());

        catalogPage.waitAttributeChange();

        String styleOfPostponeButton = catalogPage.getStyleAttributeOfPostponeButton();
        String textOfBasketButton = catalogPage.getTextOfInBasketButton();

            assertSoftly(
                    softAssertions -> {
                        softAssertions
                                .assertThat(styleOfPostponeButtonAfterAdd)
                                .withFailMessage("Кнопка \"Отложить\" не должна быть активной.")
                                .isEqualTo(STYLE_ATTRIBUTE_AFTER_CLICK);
                        softAssertions
                                .assertThat(styleOfBasketButtonAfterAdd)
                                .withFailMessage("Кнопка \"В корзину\" не должна быть активной.")
                                .isEqualTo(DISPLAY_NONE);
                        softAssertions
                                .assertThat(styleOfPostponeButton)
                                .withFailMessage("Кнопка \"В отложенных\" не должна быть активной.")
                                .isEqualTo(STYLE_ATTRIBUTE);
                        softAssertions
                                .assertThat(textOfBasketButton)
                                .withFailMessage("Кнопка \"В корзинe\" не должна быть активной.")
                                .isEqualTo(DISPLAY_NONE);
                    }
            );
            catalogPage.goHomePage();
        }
    }
