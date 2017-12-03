package Page_Object_Factory;

//import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.relevantcodes.extentreports.LogStatus;

import Project_Utilities.Base_Project;
import Project_Utilities.Project_CommonFunction;
public class SearchToShopping_Factory extends Base_Project
{
	Project_CommonFunction cf = new Project_CommonFunction();
	private String ValueSendToreport;
	public String ShoppingCartAfter;

	@FindBy(how = How.XPATH,using="//*[@id='layer_cart']/div[1]")
	public WebElement ContainarDiv; 

	@FindBy(how = How.XPATH,using= "//*[@id='layer_cart']/div[1]/div[1]/span[1]") 
	public WebElement TitleShoppingDiv;
	
	@FindBy(how = How.XPATH,using= "//*[@id='layer_cart']/div[1]/div[2]/div[1]/span") 
	public WebElement TotalProductsDiv; 

	@FindBy(how = How.CSS,using= "span.cross[title=�Close window�]") 
	public WebElement CloseDiv;

	@FindBy(how = How.XPATH,using= "//*[@id='layer_cart']/div[1]/div[1]/span[1]") 
	public WebElement CloseDiv1;

	@FindBy(how = How.XPATH,using= "//*[@id='layer_cart']/div[1]/div[2]/span/span[2]")   
	public WebElement TitleShoppingDiv1; 

	@FindBy(how = How.XPATH,using= "//*[@id='layer_cart']/div[1]/div[2]/div[1]/span") 
	public WebElement TotalProducts1;

	@FindBy(how = How.XPATH,using= "//*[@id='layer_cart']/div[1]/div[2]/div[2]/span") 
	public WebElement TotalshippingDiv; 

	@FindBy(how = How.XPATH,using= "//*[@id='layer_cart']/div[1]/div[2]/div[3]/span") 
	public WebElement TotalIncludsDiv;

	@FindBy(how = How.XPATH,using= "//*[@id='layer_cart_product_title']") 
	public WebElement ProductTitleDiv;

	@FindBy(how = How.CSS,using= "div.shopping_cart") 
	public WebElement ShoppingCart;
	
	public void VerifyElementIndiv() throws Exception
	{
		/*
		cf.verifyElementExist_new(TitleShoppingDiv1);
		cf.waitToElement(TitleShoppingDiv1);
		cf.asserequal(TitleShoppingDiv1.getText(),TitleInShoppingDiv);
		cf.asserequal(TotalProductsDiv.getText(),productPrice);
		cf.asserequal(TotalshippingDiv.getText(),ShippingPrice);
		cf.asserequal(TotalIncludsDiv.getText(),TotalPrice);
		cf.asserequal(ProductTitleDiv.getText(),ProductName);
		 */
		ValueSendToreport=CloseDiv.getAttribute("title");
		cf.ClickOnElement(CloseDiv,ValueSendToreport);
		try 
		{
			ShoppingCartAfter=ShoppingCart.getText();
			logger.info("The Element: "+ ShoppingCartAfter+" appear  !!");
			test.log(LogStatus.PASS, "The Element :"+ ShoppingCartAfter+"  appear !!");
		}
		catch(Exception e) 
		{
			logger.error("Failed to get the text from "+ShoppingCart+"  : "+e.getMessage());
			test.log(LogStatus.FAIL,"Failed to get text from "+ ShoppingCart+"  :  see screenshot: "+e.getMessage()+" "+test.addScreenCapture(getscreenshot()));
		}
	}


}
