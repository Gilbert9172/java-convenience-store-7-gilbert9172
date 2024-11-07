package store;

import store.config.AppConfig;
import store.controller.ConvenienceController;
import store.controller.InitiateController;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = AppConfig.getInstance();
        InitiateController initiateController = appConfig.initiateController();
        initiateController.initiateData();

        ConvenienceController convenienceController = appConfig.convenienceController();
        convenienceController.run();
    }
}
