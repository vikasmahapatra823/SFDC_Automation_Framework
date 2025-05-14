package Page;

import commands.SeleniumCommand;
import constants.ApplicationConstant;

public class GenericActions extends SeleniumCommand {

    public void openURL(){
        getURL(ApplicationConstant.SF_APPLICATION_URL);
    }
}
