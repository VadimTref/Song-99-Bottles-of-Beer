import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class WebRefactorTest {

    private static final String BASE_URL = "http://www.99-bottles-of-beer.net/";

    By pTags = By.xpath("//div[@id='main']/p");

    private void getBottles(StringBuilder lyrics, int number, String btl) {
        lyrics
                .append(number)
                .append(btl);
    }

    private void  getNoMore (StringBuilder lyrics, String noMore, String btl) {
        lyrics
                .append(noMore)
                .append(btl);
    }

    private String createLyrics() {
        final String BOTTLES_WALL_CS = " bottles of beer on the wall, ";
        final String BOTTLES_DOT_LN = " bottles of beer.\n";
        final String BOTTLES_DOT = " bottles of beer on the wall.";
        final String TAKE = "Take one down and pass it around, ";
        final String NO_MORE = "No more";
        final String GO = "Go to the store and buy some more, ";

        StringBuilder lyrics = new StringBuilder();

        getBottles(lyrics, 99, BOTTLES_WALL_CS);
        getBottles(lyrics, 99, BOTTLES_DOT_LN);

        for (int i = 98; i > -1; i--) {
            lyrics.append(TAKE);

            if (i == 1) {
                getBottles(lyrics, i, BOTTLES_DOT.replace("s", ""));
                getBottles(lyrics, i, BOTTLES_WALL_CS.replace("s", ""));
                getBottles(lyrics, i, BOTTLES_DOT_LN.replace("s", ""));
            } else if (i == 0) {
                getNoMore(lyrics, NO_MORE.toLowerCase(), BOTTLES_DOT);
                getNoMore(lyrics, NO_MORE, BOTTLES_WALL_CS);
                getNoMore(lyrics, NO_MORE.toLowerCase(), BOTTLES_DOT_LN);
            } else {
                getBottles(lyrics, i, BOTTLES_DOT);
                getBottles(lyrics, i, BOTTLES_WALL_CS);
                getBottles(lyrics, i, BOTTLES_DOT_LN);
            }
        }
        lyrics.append(GO);
        getBottles(lyrics, 99, BOTTLES_DOT);

        return lyrics.toString();
    }

    @Test
    public void testLyricTextSong99Bottles() {

        final String expectedResult = createLyrics();

        String CHROME_DRIVER = "webdriver.chrome.driver";
        String DRIVER_PATH = "/webdriver/chromedriver.exe";
        System.setProperty(CHROME_DRIVER, DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        driver.get(BASE_URL);

        driver.findElement(By.linkText("Song Lyrics")).click();

        StringBuilder actualResult = new StringBuilder();

        List<WebElement> pAll = driver.findElements(pTags);

        Assert.assertFalse(pAll.isEmpty());

        for (WebElement p : pAll) {
            actualResult.append(p.getText());
        }

        Assert.assertEquals(actualResult.toString(), expectedResult);

        driver.quit();
    }
}
