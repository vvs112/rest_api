package api;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class LogListener implements ITestListener {

    static final Logger log = Logger.getRootLogger();


    @Override
    public void onTestStart(ITestResult result) {
        log.info("TEST "+" "+result.getName()+" "+"started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("TEST "+" "+result.getName()+" "+"failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
