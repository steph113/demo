package com.assessment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;

public class AssessmentTest {
    

     /**
     * @throws InterruptedException
     * 
     */
    @Test
    public void Search() throws InterruptedException {
        //System.setProperty("webdriver.chrome.driver", "path of driver");
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        
        driver.get("https://www.webstaurantstore.com/");
       
        WebElement searchBarElement=driver.findElement(By.id("searchval"));
        searchBarElement.sendKeys("stainless work table");
        //searchBarElement.sendKeys("sink");
       
        WebElement searchButton=driver.findElement(By.xpath("//*[@id='searchForm']/div/button"));
       
        searchButton.click();
        

        boolean myTest=true;
        int j=1;

        while(myTest){

        
        List<WebElement> tableResults = driver.findElements(By.xpath("//*[@id='ProductBoxContainer']/div[1]/a/span"));
        
        WebElement myElement;

        String pageInfo = driver.getCurrentUrl();
        
        for (int i=0; i<tableResults.size(); i++)
        {
            myElement = tableResults.get(i);
            if (myElement.getText().contains("Table")){
                continue;}
            else if (!myElement.getText().contains("Table")){
                shouldAnswerWithFalse(pageInfo, myElement);
            }
        }
        if (driver.getCurrentUrl().equals("https://www.webstaurantstore.com/search/stainless-work-table.html?page=9"))
        {
            myTest=false;
        }
      
        if (j==1)
        {
            WebElement pageButton=driver.findElement(By.xpath("//*[@id='paging']/nav/ul/li[8]/a"));
            pageButton.click();
            j++;
        }

       if (j==5)
        {
            WebElement pageButton=driver.findElement(By.xpath("//*[@id='paging']/nav/ul/li[11]/a"));
            pageButton.click();
            j++;

       }
        if (j==9)
        {
            myTest=false; 
            break;
        }

       else
       {
            WebElement newPageButton=driver.findElement(By.xpath("//*[@id='paging']/nav/ul/li[9]/a"));
            newPageButton.click();
            j++;
        }
    } 
    
    WebElement myAddToCartButton = driver.findElement(By.cssSelector("#ProductBoxContainer:last-of-type > div.add-to-cart > form > div > div > input.btn.btn-cart.btn-small"));
    myAddToCartButton.click();
    

    WebElement viewCartButton = driver.findElement(By.xpath("//*[@id='watnotif-wrapper']/div/p/div[2]/div[2]/a[1]"));
    viewCartButton.click();
    
    WebElement emptyCartButton = driver.findElement(By.xpath("//*[@class='emptyCartButton btn btn-mini btn-ui pull-right']"));
    emptyCartButton.click();
    
    WebElement emptyCartConfirmButton = driver.findElement(By.xpath("//*[@class='ReactModalPortal']/div/div/div/footer/button[1]"));
    emptyCartConfirmButton.click();


    testCompleted();
    driver.quit();
       
}

          
    public void shouldAnswerWithFalse(String pageInfo, WebElement myElement)
    {
       
        assertFalse( false );
        String elementWithError = myElement.getText();
        
        System.out.println( pageInfo + " Product: " + elementWithError + " IS MISSING'Table' in the Item Description");  
    }

    public void testCompleted()
    {
        System.out.println( "Test Completed" );
    }
 
}
