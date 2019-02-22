import Webpages.HomeScreen;
import Webpages.IntroReg;
import Webpages.SenderReciverScreen;
import Webpages.SenderScreen;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import controlers.Constans;
import controlers.ScreenCapture;
import controlers.XmlGetter;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static Webpages.SenderScreen.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING) // it will sort that tests will run by an order.

public class Main {
    private static AndroidDriver<MobileElement> driver;
    // create ExtentReports and attach reporter(s)
    private static ExtentReports extent ;

    // creates a toggle for the given test, adds all log events under it
    private static ExtentTest test ;

    @BeforeClass
    public static void setUp() throws IOException, ParserConfigurationException, SAXException { // beforeclass setup device.
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(XmlGetter.getXmlData(Constans.xmlReportPathString));
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        test = extent.createTest(Constans.testName, Constans.testDescription);
        extent.setSystemInfo(Constans.systemInfoKeyEn, Constans.systemInfoEnValue);
        extent.setSystemInfo(Constans.systemInfoKeyTe, Constans.systemInfoTeValue);
        test.log(Status.INFO, Constans.statusInfo);
        boolean driverEstablish = false;
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Constans.platforName);
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Constans.deviceName);
           // capabilities.setCapability(MobileCapabilityType.APP , "C:\\Users\\Alena\\Desktop\\alex\\BuyMe - Mobile\\src\\APK\\BuyMe.apk");
            capabilities.setCapability(Constans.appPackageCapibalityName, XmlGetter.getXmlData(Constans.keyNameAppPackge));
            capabilities.setCapability(Constans.appActivityCapibalityName, XmlGetter.getXmlData(Constans.keyNameAppactivity));
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);
           // capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
            driver = new AndroidDriver(new URL(Constans.appiumURL), capabilities);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

            driverEstablish = true;
        } catch (Exception e) {
            test.log(Status.FATAL, Constans.msgDriverStatusFailed + e.getMessage());
            driverEstablish = false;
        } finally {
            if (driverEstablish) {
                System.out.println(Constans.msgDriverStatusSuccess);
                test.log(Status.PASS, Constans.msgDriverStatusSuccess , MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
            }
        }
    }

    @Test
    public void test01_introRegistration() throws  IOException { // Sign up and chose google account
        test.log(Status.INFO, IntroReg.msgInfo);
        boolean appOpened = false;
        try {
            test.log(Status.PASS, IntroReg.msgPassClickSignUp , MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
            System.out.println(IntroReg.msgPassClickSignUp);
            IntroReg.signUpInbtn(driver).click();
            test.pass(IntroReg.msgPassClickedSignUp, MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
            System.out.println(IntroReg.msgPassClickedSignUp);


            System.out.println(IntroReg.msgPassClickGoogleOption);
            IntroReg.signUpBtnGoogle(driver).click();
            test.pass(IntroReg.msgPassClickedGoogleOption, MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
            System.out.println(IntroReg.msgPassClickedGoogleOption);

            System.out.println(IntroReg.msgPassClickGoogleIcon);
            test.pass(IntroReg.msgPassClickGoogleIcon, MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
            IntroReg.signUpGoogleAcc(driver).click();
            test.pass(IntroReg.msgPassClickedGoogleIcon, MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
            System.out.println(IntroReg.msgPassClickedGoogleIcon);


        }catch (Exception e){
            e.printStackTrace();
            test.log(Status.FAIL, IntroReg.msgFail + e.getMessage() , MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
            appOpened = false;
        } finally {
            if (appOpened) {
                test.pass(IntroReg.msgPassTest, MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
                test.log(Status.PASS, IntroReg.msgPassTest);
            }
        }
    }

    @Test
    public void test02_homeScreen() throws IOException { // Chose a gift to send.
        test.log(Status.INFO, HomeScreen.msgInfo);

        boolean homeScreen = false;
        try {
            System.out.println(HomeScreen.msgPassClickGiftOption);
            HomeScreen.firstGiftChose(driver).click();
            test.pass(HomeScreen.msgPassClickEdGiftOption, MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
            System.out.println(HomeScreen.msgPassClickEdGiftOption);

            System.out.println(HomeScreen.msgPassClickBussOption);
            HomeScreen.firstBusinessChose(driver).click();
            test.pass(HomeScreen.msgPassClickedBussOption, MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
            System.out.println(HomeScreen.msgPassClickedBussOption);

            System.out.println(HomeScreen.msgPassEnterPrice);
            HomeScreen.priceEditbox(driver).sendKeys(Constans.sumPrice);
            driver.hideKeyboard();
            test.pass(HomeScreen.msgPassEnteredPrice, MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
            System.out.println(HomeScreen.msgPassEnteredPrice);

            System.out.println(HomeScreen.msgPassClickChoseBtn);
            HomeScreen.choseButton(driver).click();
            test.pass(HomeScreen.msgPassClickedChoseBtn, MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
            System.out.println(HomeScreen.msgPassClickedChoseBtn);


        }catch (Exception e){
            test.log(Status.FAIL, HomeScreen.msgFail + e.getMessage() , MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
            homeScreen = false;
        }finally {
            if (homeScreen) {
                test.pass(HomeScreen.msgPassTest, MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
                test.log(Status.PASS, HomeScreen.msgPassTest);
            }
        }
    }

    @Test
    public void test03_sendDetailsScreen() throws IOException { // fill all the details to send the gift.
        test.log(Status.INFO, SenderReciverScreen.msgInfo);
        boolean sendScreen = false;
        try {
            System.out.println(SenderReciverScreen.msgPassClickScrollDown);
            SenderReciverScreen.scrollDwonIcon(driver).click();
            test.pass(SenderReciverScreen.msgPassClickedScrollDown, MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
            System.out.println(SenderReciverScreen.msgPassClickedScrollDown);

            System.out.println(SenderReciverScreen.msgPassEnterReciverName);
            SenderReciverScreen.reciverEditBox(driver).sendKeys(Constans.reciverName);
            driver.hideKeyboard();
            test.pass(SenderReciverScreen.msgPassEnteredReciverName, MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
            System.out.println(SenderReciverScreen.msgPassEnteredReciverName);

            System.out.println(SenderReciverScreen.msgPassClickEventOption);
            SenderReciverScreen.eventTypeDropDownBox(driver).click();
            test.pass(SenderReciverScreen.msgPassClickedEventOption, MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
            System.out.println(SenderReciverScreen.msgPassClickedEventOption);

            System.out.println(SenderReciverScreen.msgPassClickEventFirstOption);
            SenderReciverScreen.eventTypeDropDownFirstOption(driver).click();
            test.pass(SenderReciverScreen.msgPassClickedEventFirstOption, MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
            System.out.println(SenderReciverScreen.msgPassClickedEventFirstOption);

            System.out.println(SenderReciverScreen.msgPassClearBlessing);
            SenderReciverScreen.blessEditBox(driver).clear();
            test.pass(SenderReciverScreen.msgPassClearedBlessing, MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
            System.out.println(SenderReciverScreen.msgPassClearedBlessing);

            System.out.println(SenderReciverScreen.msgPassEnterBlessing);
            SenderReciverScreen.blessEditBox(driver).sendKeys(Constans.blessingMessage);
            driver.hideKeyboard();
            test.pass(SenderReciverScreen.msgPassEnteredBlessing, MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
            System.out.println(SenderReciverScreen.msgPassEnteredBlessing);

            System.out.println(SenderReciverScreen.msgPassClearSenderEditBox);
            SenderReciverScreen.senderEditBox(driver).clear();
            test.pass(SenderReciverScreen.msgPassClearedSenderEditBox, MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
            System.out.println(SenderReciverScreen.msgPassClearedSenderEditBox);

            System.out.println(SenderReciverScreen.msgPassEnterSenderName);
            SenderReciverScreen.senderEditBox(driver).sendKeys(Constans.senderName);
            driver.hideKeyboard();
            test.pass(SenderReciverScreen.msgPassEnteredSenderName, MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
            System.out.println(SenderReciverScreen.msgPassEnteredSenderName);

            System.out.println(SenderReciverScreen.msgPassClickNxtBtn);
            SenderReciverScreen.nextButton(driver).click();
            test.pass(SenderReciverScreen.msgPassClickedNxtBtn, MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
            System.out.println(SenderReciverScreen.msgPassClickedNxtBtn);
        }catch (Exception e){
            test.log(Status.FAIL, SenderReciverScreen.msgFail + e.getMessage() , MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
            sendScreen = false;
        }finally {
            if (sendScreen) {
                test.pass(SenderReciverScreen.msgPassTest, MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
                test.log(Status.PASS, SenderReciverScreen.msgPassTest);
            }
        }
    }

    @Test
    public void test04_senderScreen() throws IOException { // Sender screen fill the detials
        test.log(Status.INFO, msgInfo);
        boolean sendScreen = false;
        try {
            System.out.println(msgPassClickPaymentRadioBtn);
            SenderScreen.paymentNowRadioButton(driver).click();
            test.pass(msgPassClickedPaymentRadioBtn, MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
            System.out.println(msgPassClickedPaymentRadioBtn);

            System.out.println(msgPassClickEamilCheckBox);
            SenderScreen.emailOptionCheckBox(driver).click();
            test.pass(msgPassClickedEamilCheckBox, MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
            System.out.println(msgPassClickedEamilCheckBox);

            System.out.println(msgPassEnterEmailTo);
            SenderScreen.emailEditBox(driver).sendKeys(Constans.senderScreenEmail);
            driver.hideKeyboard();
            test.pass(msgPassEnteredEmailTo, MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
            System.out.println(msgPassEnteredEmailTo);

            System.out.println(msgPassClickNextBtn);
            SenderScreen.nextBtn(driver).click();
            test.pass(msgPassClickeNexteBtn, MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
            System.out.println(msgPassClickeNexteBtn);

        }catch (Exception e){
            test.log(Status.FAIL, msgFail + e.getMessage() , MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
            sendScreen = false;
        }finally {
            if (sendScreen) {
                test.pass(msgPassTest, MediaEntityBuilder.createScreenCaptureFromPath(ScreenCapture.takeScreenShot(Constans.imgSaverPath + String.valueOf(System.currentTimeMillis()),driver)).build());
                test.log(Status.PASS, msgPassTest);
            }
        }
    }


    @AfterClass
    public static void afterClass(){
        test.log(Status.INFO, Constans.msgInfoAfterClass);
        driver.quit();
        extent.flush();
    }
}
