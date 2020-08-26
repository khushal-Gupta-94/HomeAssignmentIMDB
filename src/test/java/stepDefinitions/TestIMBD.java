package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestIMBD {
    public WebDriver driver;
    public static Logger log = Logger.getLogger(TestIMBD.class.getName());

    @Given("^Open Chrome Browser$")
    public void open_Chrome_Browser() {
      //  System.setProperty("webdriver.chrome.driver",".\\chromedriver.exe");
        driver = new ChromeDriver();
        Date d = new Date();
        System.setProperty("current.date", d.toString().replace(":", "_").replace(" ", "_"));
        PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");
        log.info("Chrome driver is opened successfully");
    }

    @When("^Enter URL \"([^\"]*)\"$")
    public void enter_URL(String url) throws InterruptedException {
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(1000);
    }

    @Then("^Verify the site is opened successfully with title \"([^\"]*)\"$")
    public void verifyIMBDsite(String title)
    {
        String temp = driver.getTitle();
//        System.out.println(temp);
        Assert.assertEquals("Web Application is not opened Successfully",title,temp);
        log.info("Application is opened successfully");
    }

    @Then("^Fetch name, ratings, and movie release year of top 50 movies from IMDB site and verify that each and every movie link page is opened and all mentioned data is correct.$")
    public void fetchdata() throws InterruptedException, IOException {

        SoftAssert sa= new SoftAssert();

        XSSFWorkbook wk = new XSSFWorkbook();
        XSSFSheet sh = wk.createSheet("TestData");

        Row row = sh.createRow(0);
        row.createCell(0).setCellValue("Rank");
        row.createCell(1).setCellValue("Movie Name");
        row.createCell(2).setCellValue("Movie Year");
        row.createCell(3).setCellValue("Movie Rating");
        row.createCell(4).setCellValue("Page Movie Name");
        row.createCell(5).setCellValue("Page Movie Year");
        row.createCell(6).setCellValue("Page Movie Rating");

        for(int i = 1; i <= 50; i++)
        {
            Thread.sleep(2000);
//collecting data from IMBD main page
            WebElement movietitle = driver.findElement(By.xpath("//tbody[@class='lister-list']/tr["+i+"]/td[@class='titleColumn']/a"));
            String movieName = movietitle.getText();
            String movieYear = driver.findElement(By.xpath("//tbody[@class='lister-list']/tr["+i+"]/td[@class='titleColumn']/span")).getText();
            String movieRating = driver.findElement(By.xpath("//tbody[@class='lister-list']/tr["+i+"]//strong")).getAttribute("title");
//click on movie link
            movietitle.click();
            Thread.sleep(2000);
// Collecting data on clicking movie link
            String movieName1 = driver.findElement(By.xpath("//div[@class='title_wrapper']/h1")).getText();
            String movieYear1 = driver.findElement(By.xpath("//div[@class='title_wrapper']/h1/span/a")).getText();
            String movieRating1 = driver.findElement(By.xpath("//div[@class='ratingValue']/strong")).getAttribute("title");
//Verifying Movie name, year, rating and subscriber
            String movieNameFinal = movieName + " " + movieYear;
            sa.assertEquals(movieNameFinal,movieName1,"Movie name not matched for "+movieNameFinal);

            String movieYearFinal = movieYear.replace("(","").replace(")","");
            sa.assertEquals(movieYearFinal,movieYear1,"Movie year is not matched for "+movieNameFinal);

            sa.assertEquals(extractRatingandSubscriber(movieRating,"rating"),extractRatingandSubscriber(movieRating1,"rating"),"Rating is not matched for ."+movieNameFinal);
            sa.assertEquals(extractRatingandSubscriber(movieRating,"subscriber"),extractRatingandSubscriber(movieRating1,"subscriber"),"Subscriber are not equal for "+movieNameFinal);
//inserting data into excel sheet
            row = sh.createRow(i);
                row.createCell(0).setCellValue(i);
                row.createCell(1).setCellValue(movieNameFinal);
                row.createCell(2).setCellValue(movieYearFinal);
                row.createCell(3).setCellValue(movieRating);
                row.createCell(4).setCellValue(movieName1);
                row.createCell(5).setCellValue(movieYear1);
                row.createCell(6).setCellValue(movieRating1);

            log.info("Rank " + i + " : " + movieNameFinal + "\t" + movieYearFinal + "\t" + movieRating);
            log.info("Rank " + i + " : " + movieName1 + "\t" + movieYear1 + "\t" + movieRating1);
            driver.navigate().back();

        }

        File f = new File("./Test.xlsx");
        FileOutputStream fo = new FileOutputStream(f);
        wk.write(fo);
        fo.close();
        sa.assertAll();
    }

    public String extractRatingandSubscriber(String r, String type)
    {
        r = r.replaceAll("[^\\d]", " ");
        r = r.trim();
        r = r.replaceAll(" +", "");
        if (r.equals(""))
            return "-1";
        if (type.equals("rating"))
            r = r.substring(0,2);
        else if(type.equals("subscriber"))
            r = r.substring(2);
        return r;
    }
}
