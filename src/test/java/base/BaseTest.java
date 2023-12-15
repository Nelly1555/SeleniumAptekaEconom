package base;

import config.BaseConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static utils.ScreenShotHelper.makeScreenShot;
import static utils.dataHelpers.TestDataHelper.CURRENT_REGION;
import static utils.dataHelpers.TestDataHelper.SAINT_PETERSBURG;
import static utils.Waiters.waitSomeSeconds;

/**
 * Общий класс с настройками для всех тестов.
 */
public class BaseTest {

    /**
     * Переменная с экземпляром драйвера.
     */
    protected WebDriver driver;

    /**
     * Экземпляр конфигурации с общими параметрами.
     */
    private final BaseConfig config = ConfigFactory.create(BaseConfig.class, System.getenv());

    /**
     * Общие настройки перед выполнением каждого теста.
     */
    @BeforeMethod
    public void setUp() {

        //Установка настройки с путем к google драйверу.
        System.setProperty(config.driverProperty(), config.driverPath());

        //Создание экземпляра драйвера.
        driver = new ChromeDriver();

        //Открытие страницы по URL.
        driver.get(config.url());

        //Разворот страницы на полное окно.
        driver.manage().window().maximize();

        //Создание cookies с выбором города.
        Cookie getCookie = new Cookie(CURRENT_REGION, SAINT_PETERSBURG);

        //Добавление cookies.
        driver.manage().addCookie(getCookie);

        //Ожидание по умолчанию 15 секунд.
        waitSomeSeconds(driver);

        //Делаем скриншот.
        makeScreenShot(driver);
    }

    /**
     * Общие настройки после выполнения каждого теста.
     */
    @AfterMethod
    public void tearDown() {

        //Ожидание по умолчанию 15 секунд.
        waitSomeSeconds(driver);

        //Остановка работы драйвера.
        driver.close();
    }
}
