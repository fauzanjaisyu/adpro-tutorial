package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    /**
     * The port number assigned to the running application during test execution.
     * Set automatically during each test run by Spring Framework's test context.
     */
    @LocalServerPort
    private int serverPort;

    /**
     * The base URL for testing. Default to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setUpTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void testButtonFromHomePageToProductListPage(ChromeDriver driver) throws Exception {
        // Exercise
        driver.get(baseUrl);

        WebElement listProductButton = driver.findElement(By.className("btn"));
        listProductButton.click();

        // Verify
        String pageTitle = driver.findElement(By.tagName("h2")).getText();
        assertEquals("Product List", pageTitle);
    }

    @Test
    void testCreateProduct(ChromeDriver driver) throws Exception {
        // Exercise
        driver.get(baseUrl + "/product/create");

        WebElement productName = driver.findElement(By.id("nameInput"));
        productName.sendKeys("Sampo Cap Bambang");

        WebElement productQuantity = driver.findElement(By.id("quantityInput"));
        productQuantity.sendKeys("100");

        String pageTitle = driver.findElement(By.tagName("h2")).getText();
        assertEquals("Create New Product", pageTitle);

        WebElement submitButton = driver.findElement(By.className("btn"));
        submitButton.click();

        // Verify
        String listProductPageTitle = driver.findElement(By.tagName("h2")).getText();
        assertEquals("Product List", listProductPageTitle);
    }

    @Test
    void testProductListPage(ChromeDriver driver) throws Exception {
        // Exercise
        driver.get(baseUrl + "/product/create");

        WebElement productName = driver.findElement(By.id("nameInput"));
        productName.sendKeys("Sampo Cap Bambang");

        WebElement productQuantity = driver.findElement(By.id("quantityInput"));
        productQuantity.sendKeys("100");

        WebElement submitButton = driver.findElement(By.className("btn"));
        submitButton.click();

        List<WebElement> productList = driver.findElements(By.tagName("td"));
        String productNameText = productList.get(0).getText();
        String productQuantityText = productList.get(1).getText();

        // Verify
        assertEquals("Sampo Cap Bambang", productNameText);
        assertEquals("100", productQuantityText);
    }
}
