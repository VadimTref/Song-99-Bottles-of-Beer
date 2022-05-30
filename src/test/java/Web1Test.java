import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Web1Test {

    private static final String CHROME_DRIVER = "webdriver.chrome.driver";
    private static final String DRIVER_PATH = "/webdriver/chromedriver.exe";
    private static final String URL = "http://www.99-bottles-of-beer.net/";

    @Test
    public void testMenuStartTitle() {

        String expectedResult = "Welcome to 99 Bottles of Beer";

        System.setProperty(CHROME_DRIVER, DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        driver.get(URL);

        WebElement menuBrowseLanguages = driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='navigation']/"
                        + "ul[@id='menu']/li/a[@href='/abc.html']"));
        menuBrowseLanguages.click();
        WebElement menuStart = driver.findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='navigation']/"
                        + "ul[@id='menu']/li/a[@href='/']"));
        menuStart.click();
        WebElement h2 = driver.findElement(By.xpath("//body/div[@id='wrap']/"
                + "div[@id='main']/h2"));

        String actualResult = h2.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testMainPageHeader() {

        String expectedResult = "99 Bottles of Beer";

        System.setProperty(CHROME_DRIVER, DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        driver.get(URL);

        WebElement mainPageHeader = driver.findElement(
                By.xpath("//div[@id='header']/h1"));

        String actualResult = mainPageHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testNameOfLastMenuItem() {

        String expectedResult = "Submit new Language";

        System.setProperty(CHROME_DRIVER, DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        driver.get(URL);

        WebElement nameOfLastMenuItem = driver.findElement(
                By.xpath("//ul[@id='menu']/li[last()]"));

        String actualResult = nameOfLastMenuItem.getText();

        Assert.assertEquals(actualResult, expectedResult.toUpperCase());

        driver.quit();
    }

    @Test
    public void testNameOfSubHeaderInLastMenuItem() {

        String expectedResult = "Submit New Language";

        System.setProperty(CHROME_DRIVER, DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        driver.get(URL);

        WebElement nameOfLastMenuItem = driver.findElement(
            By.xpath("//ul[@id='menu']/li/a[@href='/submitnewlanguage.html']"));
        nameOfLastMenuItem.click();
        WebElement nameOfSubHeaderInLastMenuItem = driver.findElement(
                By.xpath("//ul[@id='submenu']/li/"
                        + "a[@href='./submitnewlanguage.html']"));

        String actualResult = nameOfSubHeaderInLastMenuItem.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testNameOfFirstItemInSubmenuOfSecondMenuItem() {

        String expectedResult = "0-9";

        System.setProperty(CHROME_DRIVER, DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        driver.get(URL);

        WebElement nameOfSecondMenuItem = driver.findElement(
                By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']"));
        nameOfSecondMenuItem.click();
        WebElement nameOfFirstItemInSubmenuOfSecondMenuItem = driver.findElement(
                By.xpath("//ul[@id='submenu']/li/a[@href='0.html']"));

        String actualResult = nameOfFirstItemInSubmenuOfSecondMenuItem.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testNamesOfTeamsMembers() {

        String expectedResult1 = "Oliver Schade";
        String expectedResult2 = "Gregor Scheithauer";
        String expectedResult3 = "Stefan Scheler";

        System.setProperty(CHROME_DRIVER, DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        driver.get(URL);

        WebElement nameOfFirstSubmenuOnMainPage = driver.findElement(
                By.xpath("//ul[@id='submenu']/li/a[@href='team.html']"));
        nameOfFirstSubmenuOnMainPage.click();
        WebElement name1 = driver.findElement(
                By.xpath("//h3[text()='Oliver Schade']"));
        WebElement name2 = driver.findElement(
                By.xpath("//h3[text()='Gregor Scheithauer']"));
        WebElement name3 = driver.findElement(
                By.xpath("//h3[text()='Stefan Scheler']"));

        String actualResult1 = name1.getText();
        String actualResult2 = name2.getText();
        String actualResult3 = name3.getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);

        driver.quit();
    }

    @Test
    public void testErrorIfInputIsIncomplete() {

        String expectedResult = "Error: Precondition failed - Incomplete Input.";

        System.setProperty(CHROME_DRIVER, DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        driver.get(URL);

        WebElement lastMenuItem = driver.findElement(
                By.xpath("//ul[@id='menu']/li/"
                        + "a[@href='/submitnewlanguage.html']"));
        lastMenuItem.click();
        WebElement btnSubmitLanguage = driver.findElement(
                By.xpath("//input[@name='submitlanguage']"));
        btnSubmitLanguage.click();
        WebElement error = driver.findElement(
                By.xpath("//*[@id='main']/p[contains"
                        + "(.,' Precondition failed - Incomplete Input')]"));

        String actualResult = error.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testErrorTextIsCorrect() {

        String expectedResult1 = "Error";
        String expectedResult2 = "Precondition";
        String expectedResult3 = "Incomplete";
        String expectedResult4 = "Input";
        String expectedResult5 = "failed";
        String expectedResult6 = ":";
        String expectedResult7 = "-";
        String expectedResult8 = ".";

        System.setProperty(CHROME_DRIVER, DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        driver.get(URL);

        WebElement lastMenuItem = driver.findElement(
                By.xpath("//ul[@id='menu']/li/"
                        + "a[@href='/submitnewlanguage.html']"));
        lastMenuItem.click();
        WebElement btnSubmitLanguage = driver.findElement(
                By.xpath("//input[@name='submitlanguage']"));
        btnSubmitLanguage.click();
        WebElement error = driver.findElement(
                By.xpath("//*[@id='main']/p[contains "
                        + "(.,' Precondition failed - Incomplete Input')]"));

        String[] errorText = error.getText().replace(":", " :").
                replace(".", " .").split("[\\s+]");

        String actualResult1 = errorText[0];
        String actualResult2 = errorText[2];
        String actualResult3 = errorText[5];
        String actualResult4 = errorText[6];
        String actualResult5 = errorText[3];
        String actualResult6 = errorText[1];
        String actualResult7 = errorText[4];
        String actualResult8 = errorText[7];

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);
        Assert.assertEquals(actualResult4, expectedResult4);
        Assert.assertEquals(actualResult5, expectedResult5);
        Assert.assertEquals(actualResult6, expectedResult6);
        Assert.assertEquals(actualResult7, expectedResult7);
        Assert.assertEquals(actualResult8, expectedResult8);

        driver.quit();
    }

    @Test
    public void testTextIsCorrect() {

        String expectedResult = "IMPORTANT: Take your time! The more carefully "
                + "you fill out this form (especially the language name and "
                + "description), the easier it will be for us and the faster "
                + "your language will show up on this page. We don't have the "
                + "time to mess around with fixing your descriptions etc. "
                + "Thanks for your understanding.";

        System.setProperty(CHROME_DRIVER, DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        driver.get(URL);

        WebElement lastMenuItem = driver.findElement(
                By.xpath("//ul[@id='menu']/li/"
                        + "a[@href='/submitnewlanguage.html']"));
        lastMenuItem.click();
        WebElement text = driver.findElement(
                By.xpath("//div[@id='wrap']/div[@id='main']/ul/"
                        + "li[contains(text(),'Take your time!')]"));

        String actualResult = text.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testNamesOfTablesColumns() {

        String expectedResult1 = "Language";
        String expectedResult2 = "Author";

        System.setProperty(CHROME_DRIVER, DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        driver.get(URL);

        WebElement nameOfSecondMenuItem = driver.findElement(
                By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']"));
        nameOfSecondMenuItem.click();

        WebElement nameOfFirstTablesColumn = driver.findElement(
                By.xpath("//*[@id='category']/tbody/tr/"
                        + "th[contains(text(), 'Language')]"));
        WebElement nameOfSecondTablesColumn = driver.findElement(
                By.xpath("//*[@id='category']/tbody/tr/"
                        + "th[contains(text(), 'Author')]"));

        String actualResult1 = nameOfFirstTablesColumn.getText();
        String actualResult2 = nameOfSecondTablesColumn.getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);

        driver.quit();
    }

    @Test
    public void testNoNewComments() {

        String expectedResult = "New Comments\n{LIST}";

        System.setProperty(CHROME_DRIVER, DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        driver.get(URL);

        WebElement nameOfMenuItemTopList = driver.findElement(
                By.xpath("//ul[@id='menu']/li/a[@href='/toplist.html']"));
        nameOfMenuItemTopList.click();

        WebElement nameOfSubmenuItemNewComments = driver.findElement(
                By.xpath("//*[@id='submenu']/li/a[@href='./newcomments.html']"));
        nameOfSubmenuItemNewComments.click();

        WebElement newComments = driver.findElement(By.xpath("//*[@id='main']"));

        String actualResult = newComments.getText().trim();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testWordImportantIsCorrect() {

        String expectedResult = "IMPORTANT:";
        String expectedResultFont = "700";
        String expectedResultTextColor = "rgba(255, 255, 255, 1)";
        String expectedResultBackgroundColor = "rgba(0, 0, 0, 0)";

        System.setProperty(CHROME_DRIVER, DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        driver.get(URL);

        WebElement lastMenuItem = driver.findElement(
                By.xpath("//ul[@id='menu']/li/"
                        + "a[@href='/submitnewlanguage.html']"));
        lastMenuItem.click();
        WebElement text = driver.findElement(
                By.xpath("//*[@id='main']/ul/li/"
                        + "span[@style='background-color:red; color: white']/"
                        + "b[contains(text(), 'IMPORTANT:')]"));

        String actualResult = text.getText();
        String actualResultFont = text.getCssValue("font-weight");
        String actualResultTextColor = text.getCssValue("color");
        String actualResultBackgroundColor = text.
                getCssValue("background-color");

        //Assert
        Assert.assertEquals(actualResult, expectedResult.toUpperCase());
        Assert.assertEquals(actualResultFont, expectedResultFont);
        Assert.assertEquals(actualResultTextColor, expectedResultTextColor);
        Assert.assertEquals(actualResultBackgroundColor,
                expectedResultBackgroundColor);

        driver.quit();
    }

    @Test
    public void testMenuItems() {

        String[] expectedResult = {
                                    "Start",
                                    "Browse Languages",
                                    "Search Languages",
                                    "Top Lists",
                                    "Guestbook",
                                    "Submit new Language"
        };

        System.setProperty(CHROME_DRIVER, DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        driver.get(URL);

        List<WebElement> menuItems = driver.findElements(
                By.xpath("//ul[@id='menu']/li"));
        String[] actualResult = new String[menuItems.size()];
        for (int i = 0; i < menuItems.size(); i++) {
            actualResult[i] = driver.findElement(
                            By.xpath("//ul[@id='menu']/li[" + (i + 1) + "]"))
                    .getText().toUpperCase();
            System.out.println(actualResult[i]);
            Assert.assertEquals(actualResult[i], expectedResult[i].toUpperCase());
        }

        driver.quit();
    }
}

