package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utils.dataHelpers.UtilsDataHelper.EXPLICIT_WAIT;
import static utils.dataHelpers.UtilsDataHelper.IMPLICIT_WAIT;

/**
 * Класс с методами ожидания.
 */
public class Waiters {

    /**
     * Неявное ожидание.
     *
     * @param driver - драйвер для управления браузером.
     */
    public static void waitSomeSeconds(final WebDriver driver) {
        driver
                .manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
    }

    /**
     * Явное ожидание появления элемента на странице.
     *
     * @param driver  - драйвер для управления браузером.
     * @param element - элемент страницы.
     * @return - найденный элемент.
     */
    public static WebElement waitUntilVisible(final WebDriver driver, WebElement element) {
        WebElement button = (new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOf(element)));
        return button;
    }

    /**
     * Явное ожидание возможности кликнуть элемент страницы.
     *
     * @param driver - драйвер для управления браузером.
     * @param element - элемент страницы.
     */
    public static void waitUntilClickable(final WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                .until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    /**
     * Явное ожидание загрузки страницы.
     *
     * @param driver - драйвер для управления браузером.
     */
    public static void waitLoadingPage(final WebDriver driver) {
        driver
                .manage()
                .timeouts()
                .pageLoadTimeout(Duration.ofSeconds(EXPLICIT_WAIT));
    }

    /**
     * Явное ожидание изменения значения аттрибута.
     *
     * @param driver - драйвер для управления браузером.
     * @param element - элемент страницы.
     * @param attributeName - имя аттрибута.
     * @param attributeValue - значение аттрибута.
     */
    public static void waitUntilAttributeChange(final WebDriver driver, WebElement element, String attributeName, String attributeValue) {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                .until(ExpectedConditions.domAttributeToBe(element, attributeName, attributeValue));
    }

}
